package com.nju;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author maw-b
 * @date 2021/1/31 22:20
 */
@SpringBootApplication
public class BatchApplication {
    public static void main(String[] args) {
        //SpringApplication.run(BatchApplication.class,args);
//        SpringApplication springApplication = new SpringApplication(BatchApplication.class);
//        springApplication.setBannerMode(Banner.Mode.OFF);
        SpringApplication.run(BatchApplication.class,args);
    }
}
