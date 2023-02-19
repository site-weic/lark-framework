package site.weic.lark.web.model.base;

import lombok.extern.slf4j.Slf4j;
import site.weic.lark.util.Json;

/**
 * @author ZhangWei
 * @since 2023-01-10  23:49
 */
@Slf4j
public class BaseResponse {
    @Override
    public String toString() {
        return Json.toString(this);
    }
}
