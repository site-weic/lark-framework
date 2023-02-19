package site.weic.lark.connection;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author ZhangWei
 * @since 2023-01-05  23:44
 */
@AllArgsConstructor
@Getter
public class ConnectionDB {
    final int shardingIndex;
    final private int clusterIndex;
    final boolean master;
    final String name;
    final String url;
    final String user;
}
