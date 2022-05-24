package org.mzd.websocket.payload;


import java.io.Serializable;

/**
 * <p>
 * 键值匹配
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-12-14 17:41
 */
public class KV implements Serializable {
    /**
     * 键
     */
    private String key;

    /**
     * 值
     */
    private Object value;

    public KV(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
