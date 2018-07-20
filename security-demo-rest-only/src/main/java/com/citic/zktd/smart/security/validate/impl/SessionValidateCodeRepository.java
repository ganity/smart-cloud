package com.citic.zktd.smart.security.validate.impl;

import com.citic.zktd.smart.security.validate.ValidateCodeRepository;
import com.citic.zktd.smart.security.validate.ValidateCode;
import com.citic.zktd.smart.security.validate.ValidateCodeType;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 基于session的验证码存取器
 */
@Component
public class SessionValidateCodeRepository implements ValidateCodeRepository {

    /**
     * 验证码放入session时的前缀
     */
    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

    /**
     * 操作session的工具类
     */
//    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    /* (non-Javadoc)
     * @see com.imooc.security.core.validate.code.ValidateCodeRepository#save(org.springframework.web.context.request.ServletWebRequest, com.imooc.security.core.validate.code.ValidateCode, com.imooc.security.core.validate.code.ValidateCodeType)
     */
    @Override
    public void save(ServletWebRequest request, ValidateCode code, ValidateCodeType validateCodeType) {
        request.setAttribute(getSessionKey(request, validateCodeType), code, 1);
    }

    /**
     * 构建验证码放入session时的key
     *
     * @param request
     * @return
     */
    private String getSessionKey(ServletWebRequest request, ValidateCodeType validateCodeType) {
        return SESSION_KEY_PREFIX + validateCodeType.toString().toUpperCase();
    }

    /* (non-Javadoc)
     * @see com.imooc.security.core.validate.code.ValidateCodeRepository#get(org.springframework.web.context.request.ServletWebRequest, com.imooc.security.core.validate.code.ValidateCodeType)
     */
    @Override
    public ValidateCode get(ServletWebRequest request, ValidateCodeType validateCodeType) {
        return (ValidateCode) request.getAttribute(getSessionKey(request, validateCodeType), 1);
//        return null;
    }

    /* (non-Javadoc)
     * @see com.imooc.security.core.validate.code.ValidateCodeRepository#remove(org.springframework.web.context.request.ServletWebRequest, com.imooc.security.core.validate.code.ValidateCodeType)
     */
    @Override
    public void remove(ServletWebRequest request, ValidateCodeType codeType) {
//        sessionStrategy.removeAttribute(request, getSessionKey(request, codeType));
        request.removeAttribute(getSessionKey(request, codeType), 1);
    }
}