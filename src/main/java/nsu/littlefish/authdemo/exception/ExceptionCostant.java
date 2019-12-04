package nsu.littlefish.authdemo.exception;


public enum ExceptionCostant {
    BAD_REQUEST("400", "参数错误"),
    UNAUTHORIZED("401", "未经授权"),
    FAILED("500","系统内部异常"),
    SUCEESS("200","成功"),
    UNKNOW("999", "未知异常");
    private String code;
    private String value;
    ExceptionCostant (String code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return this.code;
    }
}
