package nsu.littlefish.authdemo.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author ：yuzheng
 * @date ：Created in 2020/1/2 13:30
 * @description：
 * @modified By：
 * @version: $
 */
@Data
@ToString
@ApiModel(value = "user对象",description = "user对象")
public class UserVo implements Serializable {
    @ApiModelProperty(value = "用户名", name = "username",required = true)
    private String username;
    @ApiModelProperty(value = "密码",name = "password", required = true)
    private String password;
}
