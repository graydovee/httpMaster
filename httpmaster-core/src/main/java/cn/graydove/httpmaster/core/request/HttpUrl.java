package cn.graydove.httpmaster.core.request;

public interface HttpUrl {

    HttpUrl of(String url);

    String getUrl();

    String getFullUrl();
}
