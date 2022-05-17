package org.mzd.deploy;

import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

/**
 */
@EnableAsync
@ComponentScan(basePackages = {"org.mzd"})
@RestController
//@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@SpringBootApplication
@MapperScan(basePackages = {"org.mzd.deploy.dao"})
@ImportResource({"classpath:dubbo/consumer-deploy.xml"})
public class DeployStartApplication {
    private static final Logger logger = LoggerFactory.getLogger(DeployStartApplication.class);
    public static void main(String[] args) {
        try {
            SpringApplication.run(DeployStartApplication.class, args);
        } catch (Exception e) {
            logger.error("DeployStartApplication startError:{}", e);
        }
    }
    @Bean
    public ApplicationRunner applicationRunner(ApplicationContext ctx) {
        return args -> logger.info("=============="+ LocalTime.now()+"==============\n");
    }
    /**
     * Hello，World
     *
     * @param who 参数，非必须
     * @return Hello, ${who}
     */
    @GetMapping("/hello")
    public String sayHello(@RequestParam(required = false, name = "who") String who) {
        if (StringUtils.isBlank(who)) {
            who = "World";
        }
        return String.format("Hello, {}!", who);
    }

}
