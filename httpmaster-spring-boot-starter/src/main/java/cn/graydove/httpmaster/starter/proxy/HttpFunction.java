package cn.graydove.httpmaster.starter.proxy;

import cn.graydove.httpmaster.core.enums.HttpMethod;
import cn.graydove.httpmaster.core.request.HttpHeader;
import cn.graydove.httpmaster.core.request.HttpQuery;
import cn.graydove.httpmaster.core.request.HttpRequest;
import cn.graydove.httpmaster.core.request.HttpRequestFactory;
import cn.graydove.httpmaster.starter.bean.ParamDefinition;
import cn.graydove.httpmaster.starter.bean.RequestDefinition;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;

import java.util.*;

/**
 * @author graydove
 */
public class HttpFunction {

    private final RequestDefinition requestDefinition;

    public HttpFunction(RequestDefinition requestDefinition) {
        Assert.notNull(requestDefinition);
        this.requestDefinition = requestDefinition;
    }

    public HttpMethod getHttpMethod() {
        return requestDefinition.getHttpMethod();
    }

    public HttpRequest buildRequest(Object[] args) {
        HttpRequestFactory httpRequestFactory = requestDefinition.getHttpRequestFactory();
        HttpRequest httpRequest = httpRequestFactory.newHttpRequest()
                .url(requestDefinition.getUrl())
                .method(getHttpMethod());

        List<ParamDefinition> headerDefinitions = requestDefinition.getHeaderDefinitions();
        if (CollectionUtil.isNotEmpty(headerDefinitions)) {
            HttpHeader headers = httpRequest.header();
            for (ParamDefinition headerDefinition : headerDefinitions) {
                headers.addHeader(headerDefinition.getKey(), headerDefinition.getArgStr(args));
            }
        }

        List<ParamDefinition> queryDefinitions = requestDefinition.getQueryDefinitions();
        if (CollectionUtil.isNotEmpty(queryDefinitions)) {
            HttpQuery queries = httpRequest.query();
            for (ParamDefinition queryDefinition : queryDefinitions) {
                queries.addQuery(queryDefinition.getKey(), queryDefinition.getArg(args));
            }
        }

        List<ParamDefinition> bodyDefinitions = requestDefinition.getBodyDefinitions();
        if (CollectionUtil.isNotEmpty(bodyDefinitions)) {
            Object body = resolveBody(args, bodyDefinitions);
            httpRequest.body().data(body);
        }

        return httpRequest;
    }

    private Object resolveBody(Object[] args, List<ParamDefinition> bodyDefinitionList) {
        switch (requestDefinition.getHttpBodyStrategy()) {
            case AUTO:
                if (assertNeedMerge(args, bodyDefinitionList)) {
                    return mergeBody(args, bodyDefinitionList);
                } else {
                    return notMergeBody(args, bodyDefinitionList);
                }
            case NOT_NEED:
                return notMergeBody(args, bodyDefinitionList);
            case NEED_MERGE:
                return mergeBody(args, bodyDefinitionList);
            case NONE:
            default:
                return null;
        }
    }

    /**
     * 自动判断是否合并body的逻辑
     * @param args 参数
     * @param bodyDefinitionList body定义
     * @return 是否合并
     */
    private boolean assertNeedMerge(Object[] args, List<ParamDefinition> bodyDefinitionList) {
        int len = bodyDefinitionList.size();
        if (len != 1) {
            return true;
        }
        Object body = bodyDefinitionList.get(0).getArg(args);
        return ObjectUtil.isBasicType(body);
    }

    private Object mergeBody(Object[] args, List<ParamDefinition> bodyDefinitionList) {
        Map<String, Object> map = new HashMap<>();
        for (ParamDefinition paramDefinition : bodyDefinitionList) {
            map.put(paramDefinition.getKey(), paramDefinition.getArg(args));
        }
        return map;
    }

    private Object notMergeBody(Object[] args, List<ParamDefinition> bodyDefinitionList) {
        int len = bodyDefinitionList.size();
        if (len == 1) {
            return bodyDefinitionList.get(0).getArg(args);
        } else {
            List<Object> list = new ArrayList<>(len);
            for (ParamDefinition paramDefinition : bodyDefinitionList) {
                list.add(paramDefinition.getArg(args));
            }
            return list;
        }
    }
}
