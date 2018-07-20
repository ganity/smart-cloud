package com.citic.zktd.smart.security;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients({"com.citic.zktd.smart.user"}) // feign client 接口声明所在路径
@EnableHystrix
@RestController
@ComponentScan({"com.citic.zktd.smart"/*main 路径*/, "com.citic.zktd.smart.user"/*熔断器or降级类所在路径*/})
@EntityScan("com.citic.zktd.smart.entry")
public class SecurityDemoOnlyRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityDemoOnlyRestApplication.class, args);
	}


	@GetMapping("/user")
	public Authentication user(Authentication user) {
		return user;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	//@Bean
//	public ServletRegistrationBean getServlet(){
//
//		HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
//
//		ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
//
//		registrationBean.setLoadOnStartup(1);
//
//		registrationBean.addUrlMappings("/hystrix.stream");
//
//		registrationBean.setName("HystrixMetricsStreamServlet");
//
//
//		return registrationBean;
//	}


}
