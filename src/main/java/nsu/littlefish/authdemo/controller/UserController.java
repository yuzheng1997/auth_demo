package nsu.littlefish.authdemo.controller;

import nsu.littlefish.authdemo.annotation.OnMissAuth;
import nsu.littlefish.authdemo.common.Res;
import nsu.littlefish.authdemo.pojo.User;
import nsu.littlefish.authdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：yuzheng
 * @date ：Created in 2019/11/25 17:23
 * @description：
 * @modified By：
 * @version: $
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    @OnMissAuth
    @PostMapping("/login")
    public Res<User> login(String username, String password) {
//        Assert.notNull(user, "error");
        Assert.notNull(username, "用户名不能为空！");
        Assert.notNull(password, "密码不能为空！");
        String token = userService.login(username, password);
        return Res.ok(token);
    }
}