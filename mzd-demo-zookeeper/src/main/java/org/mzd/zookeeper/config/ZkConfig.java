package org.mzd.zookeeper.config;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.mzd.zookeeper.config.props.ZkProps;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * <p>
 * Zookeeper配置类
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-12-27 14:45
 */
@Configuration
@EnableConfigurationProperties(ZkProps.class)
public class ZkConfig {
    @Resource
    private  ZkProps zkProps;

    @Bean
    public CuratorFramework curatorFramework() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(zkProps.getTimeout(), zkProps.getRetry());
        CuratorFramework client = CuratorFrameworkFactory.newClient(zkProps.getUrl(), retryPolicy);
        client.start();
        return client;
    }
}
