package com.citic.zktd.smart.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Author: ganily
 * @Description:
 * @Date: Create in 2017/11/20 15:47
 */
@Data
public class Result<T> implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Timestamp timestamp;
    private Integer status;
    private T data;
    private String error;
    private String message;

    public Result() {
    }

    public static <T> Result<T> ofSuccess() {
        return (new Result<T>()).success();
    }

    public static <T> Result<T> ofSuccess(T data) {
        return (new Result<T>()).success(data);
    }

    public static <T> Result<T> ofFailed(ResultCode resultCode) {
        return (new Result<T>()).failed(resultCode);
    }
    public static <T> Result<T> ofFailed(HttpStatus resultCode) {
        return (new Result<T>()).failed(resultCode);
    }

    public static <T> Result<T> ofFailed(String msg) {
        return (new Result<T>()).failed(msg);
    }

    public static <T> Result<T> ofFailed(ResultCode resultCode, String msg) {
        return (new Result<T>()).failed(resultCode, msg);
    }
    public static <T> Result<T> ofFailed(HttpStatus resultCode, String msg) {
        return (new Result<T>()).failed(resultCode, msg);
    }

    public Result result(Integer status, T data, String error, String message) {
        this.status = status;
        this.data = data;
        this.error = error;
        this.message = message;
        this.timestamp = new Timestamp(System.currentTimeMillis());
        return this;
    }

    public Result<T> failed(String message) {
        return result(HttpStatus.BAD_REQUEST.value(), null, HttpStatus.BAD_REQUEST.getReasonPhrase(), message);
    }

    public Result<T> failed(ResultCode resultCode) {
        return result(resultCode.value(), null, resultCode.getReasonPhrase(), null);
    }
    public Result<T> failed(HttpStatus resultCode) {
        return result(resultCode.value(), null, resultCode.getReasonPhrase(), null);
    }

    public Result<T> failed(ResultCode resultCode, String message) {
        return result(resultCode.value(), null, resultCode.getReasonPhrase(), message);
    }
    public Result<T> failed(HttpStatus resultCode, String message) {
        return result(resultCode.value(), null, resultCode.getReasonPhrase(), message);
    }

    public Result<T> success(T data) {
        return result(HttpStatus.OK.value(), data, null, null);
    }

    public Result<T> success() {
        return result(HttpStatus.OK.value(), null, null, null);
    }

    @JsonIgnore
    public boolean isSuccess() {
        return this.status == HttpStatus.OK.value();
    }

    public enum ResultCode {

        AUTHENTICATED_DENIED_ERROR_SMS_CODE(1005, "短信验证码错误"),

        AUTHENTICATED_DENIED_ERROR_PHONE_OR_SMS_CODE(1006, "手机号或短信验证码错误"),

        AUTHENTICATED_DENIED_ERROR_USER_OR_PASSWORD(1007, "用户名或密码错误"),

        AUTHENTICATED_DENIED_ERROR_SIGN(2001, "错误的签名"),

        AUTHENTICATED_DENIED_ERROR_TIMESTAMP_TIMEOUT(2002, "时间戳超时"),

        AUTHENTICATED_DENIED_ERROR_INVALID_TOKEN(2003, "无效的access token"),
        AUTHENTICATED_DENIED_ERROR_TOKEN_TIMEOUT(2004, "access token超时"),

        AUTHENTICATED_DENIED_ERROR_RSA_DECODE(2005, "RSA解密错误"),
        ACCESS_DENIED(3001, "访问被拒绝或者没有权限");

        private final Integer value;
        private final String reasonPhrase;

        ResultCode(int value, String reasonPhrase) {
            this.value = value;
            this.reasonPhrase = reasonPhrase;
        }

        public Integer value() {
            return this.value;
        }

        /**
         * Return the reason phrase of this status status.
         */
        public String getReasonPhrase() {
            return this.reasonPhrase;
        }
    }
}
