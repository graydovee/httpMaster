package cn.graydove.httpmaster.starter.handler.impl.filter;


import cn.graydove.httpmaster.core.request.HttpRequest;
import cn.graydove.httpmaster.starter.exception.RequestInterruptedException;
import cn.hutool.core.collection.CollectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.RandomAccess;

public class RequestFilterChain {

    private static final Logger logger = LoggerFactory.getLogger(RequestFilterChain.class);

    private final List<RequestFilter> requestFilterList;

    private final int size;

    private int n;

    public RequestFilterChain(List<RequestFilter> requestFilterList) {
        if (requestFilterList instanceof RandomAccess) {
            this.requestFilterList = requestFilterList;
        } else {
            this.requestFilterList = new ArrayList<>(requestFilterList);
        }
        this.size = CollectionUtil.size(requestFilterList);
        this.n = 0;
    }

    public void doFilter(HttpRequest httpRequest) throws RequestInterruptedException {
        if (n < size) {
            RequestFilter requestFilter = requestFilterList.get(n);
            n++;
            try {
                requestFilter.doFilter(httpRequest, this);
            } catch (RequestInterruptedException e) {
                throw e;
            } catch (Throwable e) {
                logger.error(e.getMessage(), e);
            }
        }
    }
}
