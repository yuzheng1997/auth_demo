package nsu.littlefish.authdemo.exception;

import nsu.littlefish.authdemo.common.Res;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author ：yuzheng
 * @date ：Created in 2019/12/4 11:10
 * @description：全局异常处理
 * @modified By：
 * @version: $
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AuthException.class)
    public Res AuthException(AuthException authException) {
        return Res.error(authException.getCode(), authException.getMsg());
    }
    @ExceptionHandler(CustomException.class)
    public Res CustomException(CustomException exception) {
        return Res.error(exception.getCode(),exception.getMsg());
    }
    @ExceptionHandler(Exception.class)
    public Res Exception(Exception e) {
        return Res.error(ExceptionCostant.UNKNOW.getCode(), e.getMessage());
    }
}
