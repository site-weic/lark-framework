package site.weic.lark.config;

import lombok.Getter;
import lombok.Setter;

/**
 * @author ZhangWei
 * @since 2023-01-08  00:50
 */
@Getter
@Setter
public class DatabaseUser {
    String name;
    String postfix = "";
    String user;
    String password;
    int weight = 1;
    int max = 10;
    int idle = 1;
}
