package com.citic.zktd.smart.security.validate;


import com.citic.zktd.smart.security.validate.image.ImageCodeGenerator;
import com.citic.zktd.smart.security.validate.sms.DefaultSmsCodeSender;
import com.citic.zktd.smart.security.validate.sms.SmsCodeSender;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidateCodeBeanConfig {


    /**
     * 当不存在imageValidateCodeGenerator的bean时，用以下配置
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(name = "imageValidateCodeGenerator")
    public ValidateCodeGenerator imageValidateCodeGenerator() {
        ImageCodeGenerator imageCodeGenerator = new ImageCodeGenerator();
//        imageCodeGenerator.setSecurityProperties(securityProperties);
        return imageCodeGenerator;
    }

    /**
     * 短信验证码发送方法
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsCodeSender() {
        return new DefaultSmsCodeSender();
    }

}