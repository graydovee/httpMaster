package cn.graydove.httpmaster.core.request;

/**
 * @author graydove
 */
public interface HttpUrl {

    HttpUrl of(String url);

    String getUrl();

    String getFullUrl();
}
