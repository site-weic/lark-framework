package site.weic.lark.web.model.base;

import site.weic.lark.util.Json;

/**
 * @author ZhangWei
 * @since 2023-01-10  23:49
 */
public class BaseRequest {
    @Override
    public String toString() {
        return Json.toString(this);
    }
}
