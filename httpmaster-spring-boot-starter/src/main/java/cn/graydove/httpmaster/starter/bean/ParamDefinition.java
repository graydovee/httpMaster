package cn.graydove.httpmaster.starter.bean;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;

/**
 * @author graydove
 */
public class ParamDefinition {

    private String key;

    private Integer index;

    private ParamDefinition(String key, Integer index) {
        this.key = key;
        this.index = index;
    }

    public String getKey() {
        return key;
    }

    public Integer getIndex() {
        return index;
    }

    public Object getArg(Object[] args) {
        Assert.notNull(args);
        Assert.isTrue(index < args.length);
        return args[index];
    }

    public String getArgStr(Object[] args) {
        return StrUtil.toString(getArg(args));
    }

    public static ParamDefinition of(String name, Integer index) {
        Assert.notNull(name);
        Assert.notNull(index);
        Assert.isTrue(index >= 0);
        return new ParamDefinition(name, index);
    }
}
