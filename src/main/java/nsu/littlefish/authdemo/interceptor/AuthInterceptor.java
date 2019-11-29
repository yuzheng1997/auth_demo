package nsu.littlefish.authdemo.interceptor;

import lombok.extern.slf4j.Slf4j;
import nsu.littlefish.authdemo.annotation.OnMissAuth;
import nsu.littlefish.authdemo.utils.JwtUtils;
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
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerInterceptor)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        log.info("拦截：" + handlerMethod.getShortLogMessage());
        Method method = handlerMethod.getMethod();
        if (method.isAnnotationPresent(OnMissAuth.class)) {
            return true;
        } else {
            String token = request.getHeader("token");
            if (StringUtils.isEmpty(token)) {
                throw new RuntimeException("请先登录！");
            }
            Boolean result = JwtUtils.validToken(token);
            if (!result) {
                throw new RuntimeException("401");
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
