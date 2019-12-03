package nsu.littlefish.auth_demo;

import nsu.littlefish.authdemo.AuthDemoApplication;
import nsu.littlefish.authdemo.utils.RedisUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author ：yuzheng
 * @date ：Created in 2019/12/3 14:56
 * @description：redis测试
 * @modified By：
 * @version: $
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AuthDemoApplication.class})
public class RedisTest {
    @Autowired
    private RedisUtils redisUtils;

    @Test
    public void test() {
        redisUtils.set("name", "yuzheng");
        System.out.println(redisUtils.get("name"));
    }
}
