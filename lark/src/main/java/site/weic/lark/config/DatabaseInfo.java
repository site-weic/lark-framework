package site.weic.lark.config;

import lombok.Getter;
import lombok.Setter;

/**
 * @author ZhangWei
 * @since 2023-01-05  23:47
 */
@Getter
@Setter
public class DatabaseInfo {
    String name;
    DatabaseSharding[] ds;
    BoolValue expired;
}
