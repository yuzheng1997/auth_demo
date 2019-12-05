package nsu.littlefish.authdemo.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;


/**
 * @author ：yuzheng
 * @date ：Created in 2019/11/26 13:42
 * @description：token工具类
 * @modified By：
 * @version: $
 */
@Slf4j
public class JwtUtils {
    public static String createToken(String userId) {
        SignatureAlgorithm algorithm = SignatureAlgorithm.HS256;
        long now = System.currentTimeMillis();
        HashMap<String, Object> headMap = new HashMap<>();
        HashMap<String, Object> claimsMap = new HashMap<>();
        headMap.put("alg", algorithm.getValue());
        headMap.put("typ", "JWT");
        claimsMap.put("userId", userId);
        claimsMap.put("refresh", JwtConstant.REFRESH_DATE);
        JwtBuilder builder = Jwts.builder()
                .setHeader(headMap)
                .setClaims(claimsMap)
                .signWith(algorithm, JwtConstant.SECRET_KEY);
        if (JwtConstant.EXPIRES_DATE > 0) {
            long expiresDate = JwtConstant.EXPIRES_DATE + now;
            builder.setExpiration(new Date(expiresDate));
        }
        return builder.compact();
    }

    /**
     * 解析token获取载荷数据
     *
     * @param token token
     * @return
     */
    public static Claims parseJWT(String token) {
        Claims claims = null;
        if (StringUtils.isEmpty(token)) {
            log.warn("token为空");
            return null;
        }
        try {
            claims = Jwts.parser().setSigningKey(JwtConstant.SECRET_KEY).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            log.warn("token解析失败，token过期或非法token");
        }
        return claims;
    }

    /**
     * 验证token
     *
     * @param token String
     * @return
     */
    public static boolean validToken(String token) {
        Claims claims = parseJWT(token);
        if (claims != null) {
            return true;
        }
        return false;
    }

    /**
     * 获取用户名
     *
     * @param token
     * @return
     */
    public static String getUserId(String token) {
        Claims claims = parseJWT(token);
        if (claims == null) {
            throw new RuntimeException("token过期或非法token");
        }
        return (String) claims.get("userId");
    }

    /**
     * 获取token过期时间
     * @param token
     * @return
     */
    public static Long getExpire(String token) {
        Claims claims = parseJWT(token);
        if (claims == null) {
            throw new RuntimeException("token过期或非法token");
        }
        return claims.getExpiration().getTime();
    }
}
