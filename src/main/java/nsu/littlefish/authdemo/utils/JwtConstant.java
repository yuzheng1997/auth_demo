package nsu.littlefish.authdemo.utils;

/**
 * @author ：yuzheng
 * @date ：Created in 2019/11/27 10:18
 * @description：jwt使用的常量
 * @modified By：
 * @version: $
 */
public class JwtConstant {
    /**
     * 过期时间
     */
    public static final int EXPIRES_DATE = 15 * 60 * 1000;
    /**
     * 密钥
     */
    public static final String SECRET_KEY = "999999";
    /**
     * 自动刷新token周期
     */
    public static final Long REFRESH_DATE = 24 * 60 * 60 * 1000L;
    /**
     * 30秒
     */
    public static final int THIRTY_SECONDS = 30 * 1000;
}
