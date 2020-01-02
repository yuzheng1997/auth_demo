package nsu.littlefish.authdemo.service.impl;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import nsu.littlefish.authdemo.annotation.SystemLog;
import nsu.littlefish.authdemo.exception.AuthException;
import nsu.littlefish.authdemo.exception.CustomException;
import nsu.littlefish.authdemo.exception.ExceptionCostant;
import nsu.littlefish.authdemo.mapper.UserMapper;
import nsu.littlefish.authdemo.pojo.Role;
import nsu.littlefish.authdemo.pojo.User;
import nsu.littlefish.authdemo.service.UserService;
import nsu.littlefish.authdemo.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
    @SystemLog
    public User getUserById(String userId) throws Exception {
        User user = userMapper.selectByPrimaryKey(userId);
        if (null == user) {
            throw new CustomException(ExceptionCostant.BAD_REQUEST, "该用户用户不存在");
        }
        return user;
    }

    @Override
    @SystemLog
    public User getUserByName(String username) throws Exception {
        User user = userMapper.getUserByUserName(username);
        if (null == user) {
            throw new CustomException(ExceptionCostant.BAD_REQUEST, "用户不存在,请检查用户名");
        }
        return user;
    }

    @Override
    @SystemLog
    public String login(String userName, String password) throws Exception{
        User user = userMapper.getUserByUserName(userName);
        if (user == null || !user.getPassword().equals(password)) {
            log.warn("用户名或密码错误！");
            throw new AuthException("用户名或密码错误！");
        }
        String token = JwtUtils.createToken(user.getUserId());
        return token;
    }
}
