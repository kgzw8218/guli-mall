package com.zzl.gulimall.common.exception.user;

/**
 * 验证码失效异常类
 * 
 * @author zzl.gulimall
 */
public class CaptchaExpireException extends UserException
{
    private static final long serialVersionUID = 1L;

    public CaptchaExpireException()
    {
        super("user.jcaptcha.expire", null);
    }
}
