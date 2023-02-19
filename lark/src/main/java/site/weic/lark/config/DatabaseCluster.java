package site.weic.lark.config;

import lombok.Getter;
import lombok.Setter;

/**
 * @author ZhangWei
 * @since 2023-01-05  23:48
 */
@Getter
@Setter
public class DatabaseCluster {
    int index;
    String name;
    String url;
    String usr;
    String user;
    String password;
    int weight = 1;
    int max = 10;
    int idle = 1;
    DatabaseExpired expired;
}
