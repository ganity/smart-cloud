package com.citic.zktd.smart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author:
 * @Description:
 * @Date: Create in 2018/7/11 9:58
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
@EnableHystrix
@EntityScan("com.citic.zktd.smart.entry")
public class ExWebfluxProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExWebfluxProviderApplication.class, args);
    }
}
