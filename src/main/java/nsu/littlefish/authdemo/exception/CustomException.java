package nsu.littlefish.authdemo.exception;

import lombok.Data;

/**
 * @author ：yuzheng
 * @date ：Created in 2019/12/4 11:06
 * @description：统一异常处理
 * @modified By：
 * @version: $
 */
@Data
public class CustomException extends RuntimeException {
    private String code;
    private String msg;

    private CustomException () {}

    public CustomException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public CustomException(ExceptionCostant exceptionCostant, String msg) {
        super(msg);
        this.code = exceptionCostant.getCode();
        this.msg = msg;
    }
    public CustomException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }
    public CustomException(ExceptionCostant exceptionCostant, String msg, Throwable e) {
        super(msg, e);
        this.code = exceptionCostant.getCode();
        this.msg = msg;
    }
}
