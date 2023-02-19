package site.weic.lark.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author ZhangWei
 * @since 2023-01-08  00:58
 */
@Getter
@AllArgsConstructor
public class DatabaseExpired {
    BoolValue infoExpire;
    BoolValue shardingExpire;
    BoolValue clusterExpire;

    public boolean expire() {
        return infoExpire.value || shardingExpire.value || clusterExpire.value;
    }
}
