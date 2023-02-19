package site.weic.lark.config;

import lombok.Getter;
import lombok.Setter;

/**
 * @author ZhangWei
 * @since 2023-01-05  23:51
 */
@Getter
@Setter
public class DatabaseSharding {
    int index;
    String name;
    boolean master;
    DatabaseCluster[] dc;
    BoolValue expired;

    public int genClusterIndex(boolean master){
        if(master){
            return 0;
        }else{
            return 1;
        }
    }
}
