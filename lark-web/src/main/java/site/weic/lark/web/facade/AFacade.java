package site.weic.lark.web.facade;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.weic.lark.web.model.AShowRequest;
import site.weic.lark.web.service.AService;

/**
 * @author ZhangWei
 * @since 2023-01-10  23:40
 */
@Slf4j
@RestController
@RequestMapping("a")
public class AFacade {
    @Autowired
    AService aService;

    @PostMapping("show")
    public String show(@RequestBody AShowRequest request){
        log.info("==> {}", request);
        return aService.show(request.getPattern(), request.getArgs());
    }
}
