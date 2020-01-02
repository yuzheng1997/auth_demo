package nsu.littlefish.authdemo.aspect;

import lombok.extern.java.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author ：yuzheng
 * @date ：Created in 2020/1/2 17:46
 * @description：
 * @modified By：
 * @version: $
 */

/**
 * 定义日志切面
 */
@Aspect
@Log
@Component
public class LogAspect {
    /**
     * 定义切点
     */
    @Pointcut("@annotation(nsu.littlefish.authdemo.annotation.SystemLog)")
    public void pointCut(){};
    @Around("pointCut()")
    public Object around(ProceedingJoinPoint point) {
        Object result = null;
        String className = point.getTarget().getClass().getName();
        String methodName = point.getSignature().getName();
        log.info(className+"."+methodName + ":开始执行了！");
        long beginTime = System.currentTimeMillis();
        try {
            // 执行方法
            result = point.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        long useTime = System.currentTimeMillis() - beginTime;
        log.info(className+"."+methodName + ":执行结束。总耗时："+useTime);
        return result;
    }
}
