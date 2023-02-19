package site.weic.lark.web.model;

import lombok.Getter;
import lombok.Setter;
import site.weic.lark.web.model.base.BaseRequest;

/**
 * @author ZhangWei
 * @since 2023-01-10  23:42
 */
@Getter
@Setter
public class AShowRequest extends BaseRequest {
    private String pattern;
    private Object[] args;
}
