package site.weic.lark.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import site.weic.lark.connection.ConnectionDB;
import site.weic.lark.connection.LarkConnection;
import site.weic.lark.util.ObjectUtils;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author ZhangWei
 * @since 2023-01-06  01:03
 */
@Slf4j
public class DatabaseGenFactory {

    Map<String, DatabaseInfo> infoMap;

    public synchronized Map<String, DatabaseInfo> load(DatabaseAll all) {
        final Map<String, DatabaseInfo> newMap = new HashMap<>();
        final Map<String, DatabaseUser> userMap = new HashMap<>();
        for (DatabaseUser user : all.users) {
            userMap.put(user.name, user);
        }
        for (DatabaseInfo info : all.dis) {
            info.expired = new BoolValue(false);
            newMap.put(info.name, info);
            for (int i = 0; i < info.ds.length; i++) {
                DatabaseSharding sharding = info.ds[i];
                sharding.index = i;
                sharding.name = info.name + "-" + ObjectUtils.modString(info.ds.length, i);
                sharding.expired = new BoolValue(false);
                for (int j = 0; j < sharding.dc.length; j++) {
                    DatabaseCluster cluster = sharding.dc[j];
                    cluster.index = j;
                    cluster.name = sharding.name + "-" + ObjectUtils.modString(sharding.dc.length, j);
                    cluster.expired = new DatabaseExpired(info.expired, sharding.expired, new BoolValue(false));
                    final DatabaseUser user = userMap.get(info.name + "-" + cluster.user);
                    overrideClusterUser(cluster, user);
                }
            }
        }
        final Map<String, DatabaseInfo> oldMap = this.infoMap;
        this.infoMap = newMap;
        if (oldMap != null) {
            for (DatabaseInfo info : oldMap.values()) {
                info.expired.value = true;
            }
        }
        return newMap;
    }

    void overrideClusterUser(DatabaseCluster cluster, DatabaseUser user) {
        cluster.url = cluster.url + user.postfix;
        if (cluster.password == null) {
            log.info("override cluster[{}] password by user[{}]", cluster.name, user.name);
            Assert.notNull(user, "cannot find user " + cluster.name);
            cluster.password = user.password;
        }
        if (cluster.weight == 0) {
            log.info("override cluster[{}] weight by user[{}]", cluster.name, user.name);
            Assert.notNull(user, "cannot find user " + cluster.name);
            cluster.weight = user.weight;
        }
        if (cluster.max == 0) {
            log.info("override cluster[{}] max by user[{}]", cluster.name, user.name);
            Assert.notNull(user, "cannot find user " + cluster.name);
            cluster.max = user.max;
        }
        if (cluster.idle == 0) {
            log.info("override cluster[{}] idle by user[{}]", cluster.name, user.name);
            Assert.notNull(user, "cannot find user " + cluster.name);
            cluster.idle = user.idle;
        }
    }

    public LarkConnection gen(String name, int shardingIndex, boolean master) {
        final DatabaseInfo info = infoMap.get(name);
        final DatabaseSharding sharding = info.ds[shardingIndex];
        final int clusterIndex = sharding.genClusterIndex(master);
        final DatabaseCluster cluster = sharding.dc[clusterIndex];
        final ConnectionDB cdb = new ConnectionDB(shardingIndex, clusterIndex, master, cluster.name, cluster.url, cluster.user);
        final Connection connection = connectionCreator.apply(cluster);
        return new LarkConnection(cdb, connection);
    }

    Function<DatabaseCluster, Connection> connectionCreator = this::connection1;

    Connection connection(DatabaseCluster cluster) {
        try {
            return DriverManager.getConnection(cluster.url, cluster.user, cluster.password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    Connection connection1(DatabaseCluster cluster) {
        return (Connection) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{Connection.class},
                (obj, method, args) -> {
                    if (method.getName().equals("toString")) {
                        return "ConnectionProxy" + cluster.name;
                    }
                    return null;
                });
    }
}
