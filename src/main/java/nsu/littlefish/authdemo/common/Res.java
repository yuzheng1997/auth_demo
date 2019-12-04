package nsu.littlefish.authdemo.common;

import lombok.Data;

/**
 * @author ：yuzheng
 * @date ：Created in 2019/11/25 17:24
 * @description：通用返回类
 * @modified By：
 * @version: $
 */
@Data
public class Res<T> {
    private String code;
    private String msg;
    private T date;

    public Res() {
        this.code = "1";
        this.msg = "成功";
    }

    public Res(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Res(String code, String msg, T date) {
        this.code = code;
        this.msg = msg;
        this.date = date;
    }

    public Res(String msg) {
        this.msg = msg;
    }

    public static Res ok(Object data) {
        return new Res("0", "成功", data);
    }

    public static Res failed(Object data) {
        return new Res("1", "失败", data);
    }

    public static Res error(String code, String msg) {
        return new Res(code, msg);
    }

    public static Res error(String msg) {
        return new Res(msg);
    }
}
