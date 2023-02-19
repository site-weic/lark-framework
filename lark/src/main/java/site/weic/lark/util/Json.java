package site.weic.lark.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.TimeZone;

/**
 * @author ZhangWei
 * @since 2023-01-10  23:44
 */
@Slf4j
public class Json {

    public final static ObjectMapper JACKSON =new ObjectMapper();
    static{
        StdDateFormat format=new StdDateFormat();
        format.setTimeZone(TimeZone.getDefault());
        JACKSON.setDateFormat(format);
        JACKSON.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        JACKSON.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }
    public static String toString(Object input){
        try {
            return JACKSON.writeValueAsString(input);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("无法将类型["
                    + input.getClass().getName()
                    + "]对象转换为Json");
        }
    }
    public static <T> T fromString(String input, Class<T> cla){
        try {
            return JACKSON.readValue(input,cla);
        } catch (IOException e) {
            throw new RuntimeException("无法将Json内容转换为对象类型["
                    +cla.getName()
                    +"] "+input);
        }
    }
    public static <T> T fromString(String input, TypeReference<T> valueTypeRef){
        try {
            return JACKSON.readValue(input,valueTypeRef);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
