package nsu.littlefish.authdemo.interceptor;

import lombok.extern.slf4j.Slf4j;
import nsu.littlefish.authdemo.annotation.OnMissAuth;
import nsu.littlefish.authdemo.exception.AuthException;
import nsu.littlefish.authdemo.utils.JwtConstant;
import nsu.littlefish.authdemo.utils.JwtUtils;
import nsu.littlefish.authdemo.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;


/**
 * @author ：yuzheng
 * @date ：Created in 2019/11/27 16:45
 * @description：权限拦截器
 * @modified By：
 * @version: $
 */
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisUtils redisUtils;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        Long tokenExpire = 0L;
        Long now = System.currentTimeMillis();
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        log.info("拦截：" + handlerMethod.getShortLogMessage());
        Method method = handlerMethod.getMethod();
        // 带有OnMissAuth注解的类放行
        if (method.isAnnotationPresent(OnMissAuth.class)) {
            return true;
        } else {
            String token = request.getHeader("token");
            if (StringUtils.isEmpty(token)) {
                throw new AuthException("请重新登录");
            }
            tokenExpire = JwtUtils.getExpire(token);
            long diff = tokenExpire - now;
            /**
             * 距离过期时间还有30s时向客户端发送新的token令牌
             * 并将token作为key存放在redis中30s，防止同时的多个请求，造成请求失败
              */
            if (diff > 0 && diff < JwtConstant.THIRTY_SECONDS) {
                boolean hasKey = redisUtils.hasKey(token);
                if (hasKey) {
                    return true;
                } else {
                    redisUtils.set(token, System.currentTimeMillis() + "");
                    redisUtils.expire(token, 30);
                    String newToken = JwtUtils.createToken(JwtUtils.getUserId(token));
                    response.setHeader("token", newToken);
                }
            }
            Boolean result = JwtUtils.validToken(token);
            if (!result) {
                throw new AuthException("token无效或非法token，请重新登录");
            }
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
