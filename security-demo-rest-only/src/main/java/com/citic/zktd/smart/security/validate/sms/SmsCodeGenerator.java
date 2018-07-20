package com.citic.zktd.smart.security.validate.sms;

import com.citic.zktd.smart.security.validate.ValidateCodeGenerator;
import com.citic.zktd.smart.security.validate.ValidateCode;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 短信验证码生成器
 */
@Component("smsValidateCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator {


    @Override
    public ValidateCode generate(ServletWebRequest request) {
        String code = RandomStringUtils.randomNumeric(6);
        return new ValidateCode(code, 3600);
    }


}
