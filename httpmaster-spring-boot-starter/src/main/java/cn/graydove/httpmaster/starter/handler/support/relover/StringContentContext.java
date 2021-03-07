package cn.graydove.httpmaster.starter.handler.support.relover;

import cn.graydove.httpmaster.core.response.HttpContent;
import cn.graydove.httpmaster.core.response.HttpResponse;
import cn.graydove.httpmaster.core.response.support.StringHttpContent;
import org.springframework.core.annotation.Order;


@Order(Integer.MAX_VALUE - 10)
public final class StringContentContext {

    private static ThreadLocal<String> strContent = new ThreadLocal<>();

    public static String getStrContent(HttpResponse httpResponse) {
        String str = strContent.get();
        if (str == null) {
            HttpContent httpContent = httpResponse.getHttpContent();
            StringHttpContent stringHttpContent;
            if (httpContent instanceof StringHttpContent) {
                stringHttpContent = (StringHttpContent) httpContent;
            } else {
                stringHttpContent = new StringHttpContent(httpContent);
            }
            str = stringHttpContent.getContentStr();
            strContent.set(str);
        }
        return str;
    }

    public static void clear() {
        strContent.remove();
    }
}
