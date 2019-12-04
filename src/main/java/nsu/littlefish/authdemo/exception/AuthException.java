package nsu.littlefish.authdemo.exception;

import lombok.Data;

/**
 * @author ：yuzheng
 * @date ：Created in 2019/12/4 10:59
 * @description：权限异常
 * @modified By：
 * @version: $
 */
@Data
public class AuthException extends RuntimeException {
    private String code = ExceptionCostant.UNAUTHORIZED.getCode();
    private String msg;
    private AuthException () {
    }

    public AuthException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public AuthException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }
}
