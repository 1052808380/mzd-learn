package org.mzd.com.mzd;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan({"org.mzd.com.mzd.dao"})
public class MzdApplication {

    private Logger logger = LoggerFactory.getLogger(MzdApplication.class);


    public static void main(String[] args) {
        SpringApplication.run(MzdApplication.class, args);
    }
    @Bean
    public ApplicationRunner applicationRunner(ApplicationContext ctx) {
        return args -> {
            logger.debug("==============project-start==============");
            logger.info("==============update at 20200620==============");
        };
    }
}
