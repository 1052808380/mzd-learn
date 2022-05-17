package org.mzd.zookeeper.config.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>
 * Zookeeper 配置项
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-12-27 14:47
 */
@Data
@ConfigurationProperties(prefix = "zk")
public class ZkProps {
    /**
     * 连接地址
     */
    private String url;

    /**
     * 超时时间(毫秒)，默认1000
     */
    private int timeout = 1000;

    /**
     * 重试次数，默认3
     */
    private int retry = 3;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getRetry() {
        return retry;
    }

    public void setRetry(int retry) {
        this.retry = retry;
    }
}
