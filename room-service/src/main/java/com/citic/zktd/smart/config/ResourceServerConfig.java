package com.citic.zktd.smart.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;


@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    public static final String RESOURCE_ID = "arip";
    //    @Autowired
//    private RedisConnectionFactory connectionFactory;
//
//    @Bean
//    public RedisTokenStore tokenStore() {
//        return new RedisTokenStore(connectionFactory);
//    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().antMatchers("/api/admin", "/user").authenticated()
//                .anyRequest().permitAll();
        http.authorizeRequests()
                .antMatchers("/room/**").permitAll()
                .anyRequest().authenticated();
//                .antMatchers("/loadUserByUsername", "/loadUserByAccount", "/sendSMSVerificationCode",
//                        "/v2/api-docs", "/swagger-resources/**", "/swagger-ui.html", "/webjars/**")
//                .permitAll()
//                .antMatchers("/update").authenticated()
//                .anyRequest().hasAuthority("MEMBER");
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        // 1 远程验证方式
//        RemoteTokenServices tokenService = new RemoteTokenServices();
//        tokenService.setClientId("jsclient");
//        tokenService.setClientSecret("123456");
//        tokenService.setCheckTokenEndpointUrl("http://localhost:8810/oauth/check_token");
//        resources.resourceId(RESOURCE_ID).tokenServices(tokenService);

        // 2 jwt验证方式
        resources.resourceId(RESOURCE_ID)
//                .tokenStore(tokenStore)
        ;
    }

}