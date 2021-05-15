package cn.graydove.httpmaster.starter.bean;

import cn.graydove.httpmaster.core.enums.HttpMethod;
import cn.graydove.httpmaster.core.request.HttpRequestFactory;
import cn.graydove.httpmaster.starter.enums.HttpBodyStrategy;

import java.util.List;

/**
 * @author graydove
 */
public class RequestDefinition {
    
    private HttpRequestFactory httpRequestFactory;

    private List<ParamDefinition> headerDefinitions;

    private List<ParamDefinition> queryDefinitions;

    private List<ParamDefinition> bodyDefinitions;

    private HttpBodyStrategy httpBodyStrategy;

    private String url;

    private Integer urlPos;

    private HttpMethod httpMethod;

    public RequestDefinition() {
    }

    public HttpRequestFactory getHttpRequestFactory() {
        return httpRequestFactory;
    }

    public void setHttpRequestFactory(HttpRequestFactory httpRequestFactory) {
        this.httpRequestFactory = httpRequestFactory;
    }

    public List<ParamDefinition> getHeaderDefinitions() {
        return headerDefinitions;
    }

    public void setHeaderDefinitions(List<ParamDefinition> headerDefinitions) {
        this.headerDefinitions = headerDefinitions;
    }

    public List<ParamDefinition> getQueryDefinitions() {
        return queryDefinitions;
    }

    public void setQueryDefinitions(List<ParamDefinition> queryDefinitions) {
        this.queryDefinitions = queryDefinitions;
    }

    public List<ParamDefinition> getBodyDefinitions() {
        return bodyDefinitions;
    }

    public void setBodyDefinitions(List<ParamDefinition> bodyDefinitions) {
        this.bodyDefinitions = bodyDefinitions;
    }

    public HttpBodyStrategy getHttpBodyStrategy() {
        return httpBodyStrategy;
    }

    public void setHttpBodyStrategy(HttpBodyStrategy httpBodyStrategy) {
        this.httpBodyStrategy = httpBodyStrategy;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getUrlPos() {
        return urlPos;
    }

    public void setUrlPos(Integer urlPos) {
        this.urlPos = urlPos;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }
}
