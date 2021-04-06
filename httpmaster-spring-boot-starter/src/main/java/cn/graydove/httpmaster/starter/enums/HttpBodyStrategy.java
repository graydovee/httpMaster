package cn.graydove.httpmaster.starter.enums;

/**
 * @author graydove
 */

public enum HttpBodyStrategy {

    /**
     * 单个body,且不为基本类型时不合并，多个合并
     */
    AUTO,

    /**
     * 合并
     */
    NEED_MERGE,

    /**
     * 不合并
     */
    NOT_NEED,

    /**
     * 不携带body, 将忽略body信息
     */
    NONE;


}
