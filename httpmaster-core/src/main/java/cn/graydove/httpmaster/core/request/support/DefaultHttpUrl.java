package cn.graydove.httpmaster.core.request.support;

import cn.graydove.httpmaster.core.request.HttpQuery;
import cn.graydove.httpmaster.core.request.HttpRequest;
import cn.graydove.httpmaster.core.request.HttpUrl;
import cn.hutool.core.net.url.UrlBuilder;
import cn.hutool.core.net.url.UrlQuery;
import cn.hutool.core.util.StrUtil;

/**
 * @author graydove
 */
public class DefaultHttpUrl extends AbstractHttpParam implements HttpUrl {

    private UrlBuilder urlBuilder;

    public DefaultHttpUrl(HttpRequest httpRequest) {
        super(httpRequest);
    }

    @Override
    public DefaultHttpUrl of(String url) {
        this.urlBuilder = UrlBuilder.ofHttp(url, httpRequest.getCharset());
        UrlQuery query = urlBuilder.getQuery();
        if (null != query) {
            HttpQuery httpQuery = build().query();
            httpQuery.addQueries(query.getQueryMap());
            urlBuilder.setQuery(null);
        }
        return this;
    }

    @Override
    public String getUrl() {
        if (null == urlBuilder) {
            throw new NullPointerException("url is null");
        }
        return urlBuilder.build();
    }

    @Override
    public String getFullUrl() {
        UrlBuilder builder = UrlBuilder.of(urlBuilder.build(), urlBuilder.getCharset());
        build().query().forEach((key, value) -> builder.addQuery(key, StrUtil.toString(value)));
        return builder.build();
    }
}
