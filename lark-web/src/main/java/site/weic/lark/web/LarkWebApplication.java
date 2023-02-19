package site.weic.lark.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ZhangWei
 * @since 2023-01-10  23:10
 */
@Slf4j
@SpringBootApplication
public class LarkWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(LarkWebApplication.class, args);
    }
}
