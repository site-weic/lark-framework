package site.weic.lark.util;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ZhangWei
 * @since 2023-01-08  01:30
 */
@Slf4j
public class ObjectUtils {

    public static <T> T firstNonNull(T... input) {
        for (T item : input) {
            if (item != null) {
                return item;
            }
        }
        return null;
    }

    public static int mod(int max) {
        return (int) (Math.ceil(Math.log10(max)));
    }

    public static String modString(int max, int i) {
        return String.format("%0" + Math.max(mod(max), 1) + "d", i);
    }
}
