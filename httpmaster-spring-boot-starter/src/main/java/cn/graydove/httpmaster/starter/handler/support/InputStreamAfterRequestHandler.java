package cn.graydove.httpmaster.starter.handler.support;

import cn.graydove.httpmaster.core.response.HttpResponse;
import cn.graydove.httpmaster.starter.handler.AfterRequestHandler;
import cn.hutool.core.util.ClassUtil;
import org.springframework.core.annotation.Order;

import java.io.*;
import java.lang.reflect.Method;

@Order(Integer.MAX_VALUE - 20)
public class InputStreamAfterRequestHandler implements AfterRequestHandler {
    @Override
    public Object handle(HttpResponse response, Method method, Object[] args) {
        Class<?> type = method.getReturnType();
        if (ClassUtil.isAssignable(type, InputStream.class)) {
            return new HttpInputStream(response);
        }
        return null;
    }

    public static class HttpInputStream extends BufferedInputStream {

        private final HttpResponse httpResponse;

        public HttpInputStream(HttpResponse httpResponse) {
            super(httpResponse.getHttpContent().getContent());
            this.httpResponse = httpResponse;
        }

        @Override
        public void close() throws IOException {
            super.close();
            httpResponse.close();
        }
    }
}
