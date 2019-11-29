package nsu.littlefish.authdemo.mapper;

import nsu.littlefish.authdemo.pojo.User;
import org.springframework.stereotype.Repository;

/**
 * @author ：yuzheng
 * @date ：Created in 2019/11/25 19:15
 * @description：
 * @modified By：
 * @version: $
 */
@Repository
public interface UserMapper {
    /**
     * 根据用户名获取用户信息
     *
     * @param username 用户名
     * @return User实体
     */
    User getUserByName(String username);
}
