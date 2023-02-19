package site.weic.lark.test.a;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.StreamUtils;
import site.weic.lark.config.DatabaseAll;
import site.weic.lark.config.DatabaseGenFactory;
import site.weic.lark.config.DatabaseInfo;
import site.weic.lark.connection.LarkConnection;
import site.weic.lark.util.Json;
import site.weic.lark.util.ObjectUtils;
import site.weic.lark.util.Yaml;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ZhangWei
 * @since 2023-01-07  23:59
 */
@Slf4j
public class A001DatabaseGenFactoryTest {

    @Test
    public void a() {
        int[] max_arr = {
                1, 9, 10, 15, 100, 999, 1000
        };
        for (int max : max_arr) {
            final int mod = (int) (Math.ceil(Math.log10(max)));
            log.info("{}\t{}\t{}", max, mod, ObjectUtils.modString(1001, max));
        }
    }

    @Test
    public void b() throws IOException {
        final String yml = StreamUtils.copyToString(Thread.currentThread().getContextClassLoader().getResourceAsStream("database.yml"),
                StandardCharsets.UTF_8);
        log.info("{}", yml);
        final DatabaseAll all = Yaml.fromString(yml, DatabaseAll.class);
        log.info("{}", Json.toString(all));
        final DatabaseGenFactory fac = new DatabaseGenFactory();
        final Map<String, DatabaseInfo> map = fac.load(all);
        log.info("{}", Json.toString(map));
        final LarkConnection conn = fac.gen("db", 1, true);
        log.info("{}", conn);
    }

    @Test
    public void c(){
        final HashMap<String, Object> map = new HashMap<>();
        map.put("name", "wei");
        map.put("age", 1);
        log.info("{}", Yaml.toString(map));
    }
}
