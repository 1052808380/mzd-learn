package org.mzd.com.mzd.config;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.CookieHttpSessionIdResolver;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.session.web.http.HttpSessionIdResolver;

import javax.annotation.PostConstruct;
import java.time.Duration;

/**
 * @author: zhendong.mzd
 * @description:
 * @date: 2021/7/8 下午12:02
 */
@Configuration
@EnableRedisHttpSession(redisNamespace="mzd:daily:spring:session")
public class SessionConfig {
//    @Value("${login-client.rootdomain}")
//    private String domainName;
//    @Value("${login-client.cookiename}")
//    private String cookieName;
//    @Value("${login-client.rooturl}")
//    private String rootUrl;
//    @Bean
//    public DefaultCookieSerializer getDefaultCookieSerializer() {
//        DefaultCookieSerializer cookieSerializer = new DefaultCookieSerializer();
//        cookieSerializer.setDomainName(domainName);
//        cookieSerializer.setCookieName(cookieName);
//        cookieSerializer.setCookiePath(rootUrl);
//        cookieSerializer.setUseBase64Encoding(false);
//        return cookieSerializer;
//    }
//    @Bean
//    public LettuceConnectionFactory connectionFactory() {
//        return new LettuceConnectionFactory();
//    }

    /**
     * todo @EnableRedisHttpSession
     * @Retention(RetentionPolicy.RUNTIME)
     *  RetentionPolicy.RUNTIME：注解不仅被保存到class文件中，jvm加载class文件之后，仍然存在；
     * @Target(ElementType.TYPE) 元注解
     *  类、接口（包括注解类型）和枚举的声明
     * @Documented
     *  在生成javadoc的时候就会把@Documented注解给显示出来
     * @Import(RedisHttpSessionConfiguration.class)
     *   这个注解的主要作用是注册一个SessionRepositoryFilter，这个Filter会拦截到所有的请求，对Session进行操作，
     *   注入SessionRepositoryFilter的代码在RedisHttpSessionConfiguration这个类中。
     * @Configuration(proxyBeanMethods = false)
     *  proxyBeanMethods为false,WebConfig使用jdk代理
     *  proxyBeanMethods为true时，使用CGLIB代理
     */
    /**
     * java中元注解有四个： @Retention @Target @Document @Inherited
     *  元注解是负责对其它注解进行说明的注解，自定义注解时可以使用元注解
     * @Document：说明该注解将被包含在javadoc中
     * @Target 注解用来指定一个注解的使用范围 成员变量 构造方法 方法 类
     * @Retention 用于描述注解的生命周期，也就是该注解被保留的时间长短
     * @Inherited 是一个标记注解，用来指定该注解可以被继承
     * @Repeatable 注解是 Java 8 新增加的，它允许在相同的程序元素中重复注解，在需要对同一种注解多次使用时
     * @Native 注解修饰成员变量，则表示这个变量可以被本地代码引用，常常被代码生成工具使用
     */

    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory() {
        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
        genericObjectPoolConfig.setMaxIdle(200);
        genericObjectPoolConfig.setMinIdle(0);
        genericObjectPoolConfig.setMaxTotal(300);
        genericObjectPoolConfig.setMaxWaitMillis(60000);
        genericObjectPoolConfig.setTimeBetweenEvictionRunsMillis(100);
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setDatabase(0);
        redisStandaloneConfiguration.setHostName("r-bp1f614bbba3c9e4pd.redis.rds.aliyuncs.com");
        redisStandaloneConfiguration.setPort(6379);
        redisStandaloneConfiguration.setPassword(RedisPassword.of("RedisDaily919"));
        LettuceClientConfiguration clientConfig = LettucePoolingClientConfiguration.builder()
                .commandTimeout(Duration.ofMillis(50000))
                .shutdownTimeout(Duration.ofMillis(50000))
                .poolConfig(genericObjectPoolConfig)
                .build();
        LettuceConnectionFactory factory = new LettuceConnectionFactory(redisStandaloneConfiguration, clientConfig);
        return factory;
    }

    @Bean
    public HttpSessionIdResolver httpSessionIdResolver() {
        CookieHttpSessionIdResolver cookieHttpSessionIdResolver = new CookieHttpSessionIdResolver();
        DefaultCookieSerializer cookieSerializer = new DefaultCookieSerializer();
        cookieSerializer.setCookiePath("/");
        cookieSerializer.setUseBase64Encoding(false);
        cookieHttpSessionIdResolver.setCookieSerializer(cookieSerializer);
        return cookieHttpSessionIdResolver;
    }


}
