package nsu.littlefish.authdemo.service;

import lombok.extern.slf4j.Slf4j;
import nsu.littlefish.authdemo.mapper.UserMapper;
import nsu.littlefish.authdemo.pojo.User;
import nsu.littlefish.authdemo.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ：yuzheng
 * @date ：Created in 2019/11/25 19:20
 * @description： sd a
 * @modified By：
 * @version: $
 */
@Service("UserService")
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(String username) {
        return userMapper.getUserByName(username);
    }

    @Override
    public String login(String userName, String password) {
        User user = userMapper.getUserByName(userName);
        if (user == null || !user.getPassword().equals(password)) {
            log.warn("用户名或密码错误！");
            return null;
        }
        String token = JwtUtils.createToken(userName);
        return token;
    }
}
