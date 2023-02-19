package site.weic.lark.web.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author ZhangWei
 * @since 2023-01-10  23:39
 */
@Slf4j
@Service
public class AServiceImpl implements AService{

    @Override
    public String show(String pattern, Object... args) {
        return String.format(pattern, args);
    }
}
