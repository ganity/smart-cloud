package com.citic.zktd.smart.security.config;

import com.citic.zktd.smart.security.validate.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Qualifier("userDetailsService")
    @Autowired
    private UserDetailsService userDetailsService;

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService);
//                .passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

//    @Autowired
//    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        http.csrf().disable();
//        http.authorizeRequests()
//                .anyRequest()
//                .authenticated()
//                .and()
//                // 暂时禁用CSRF，否则无法提交表单
//                .csrf().disable();
        http
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
//                .successHandler(imoocAuthenticationSuccessHandler)
//                .failureHandler(imoocAuthenticationFailureHandler);
                .and()
                // 应用验证码安全配置
                .apply(validateCodeSecurityConfig)
                .and()
                // 应用短信验证码认证安全配置
//                .apply(smsCodeAuthenticationSecurityConfig)
//                .and()
                .authorizeRequests()
                .antMatchers("/actuator/hystrix.stream", "/hystrix.stream", "/login",
                        "/code/*","/weixin/authorize","/password/encode")
                .permitAll()
                .anyRequest().authenticated()

//                .anyRequest()
//                .access("@rbacService.hasPermission(request, authentication)");
        ;

    }
}