package nsu.littlefish.authdemo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import nsu.littlefish.authdemo.annotation.OnMissAuth;
import nsu.littlefish.authdemo.common.Res;
import nsu.littlefish.authdemo.exception.AuthException;
import nsu.littlefish.authdemo.pojo.User;
import nsu.littlefish.authdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

/**
 * @author ：yuzheng
 * @date ：Created in 2019/11/25 17:23
 * @description：
 * @modified By：
 * @version: $
 */
@RestController
@RequestMapping("user")
@Api(tags = {"用户"})
public class UserController {
    @Autowired
    private UserService userService;
    @ApiOperation(value = "登录")
    @OnMissAuth
    @PostMapping("/login")
    public Res<String> login(@ApiParam(name = "username",value = "用户名", required = true) String username,
                             @ApiParam(name = "password",value = "密码", required = true) String password) throws Exception{
        Assert.notNull(username, "用户名不能为空！");
        Assert.notNull(password, "密码不能为空！");
        String token = userService.login(username, password);
        return Res.ok(token);
    }
    @GetMapping("/{username}")
    @ApiOperation(value = "获取用户信息")
    public Res<User> getUserInfo(@PathVariable @ApiParam(name="username",value="用户名",required = true) String username) throws Exception{
        Assert.notNull(username, "用户名不能为空");
        User user = userService.getUserByName(username);
        return Res.ok(user);
    }
}
