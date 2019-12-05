package nsu.littlefish.authdemo.service;

import nsu.littlefish.authdemo.pojo.User;

/**
 * @author ：yuzheng
 * @date ：Created in 2019/11/25 19:20
 * @description：
 * @modified By：
 * @version: $
 */
public interface UserService {
    /**
     * 根据id获取用户信息
     * @param userId
     * @return
     * @throws Exception
     */
    User getUserById(String userId) throws Exception;

    /**
     * 根据用户名获取用户信息服务
     * @Exception
     * @param userName
     * @return
     */
    User getUserByName(String userName) throws Exception;

    /**
     * 登录
     *
     * @param userName 用户名
     * @param password 密码
     * @return
     */
    String login(String userName, String password) throws Exception;
}
