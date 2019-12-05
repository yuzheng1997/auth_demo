package nsu.littlefish.authdemo.service.impl;

import nsu.littlefish.authdemo.exception.AuthException;
import nsu.littlefish.authdemo.mapper.MenuPowerMapper;
import nsu.littlefish.authdemo.mapper.RolePowerMapper;
import nsu.littlefish.authdemo.mapper.UserRoleMapper;
import nsu.littlefish.authdemo.pojo.Power;
import nsu.littlefish.authdemo.pojo.Role;
import nsu.littlefish.authdemo.service.PowerService;
import nsu.littlefish.authdemo.vo.PowerVo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author ：yuzheng
 * @date ：Created in 2019/12/4 17:27
 * @description：
 * @modified By：
 * @version: $
 */
public class PowerServiceImpl implements PowerService {
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RolePowerMapper rolePowerMapper;
    @Autowired
    private MenuPowerMapper menuPowerMapper;
    @Override
    public PowerVo getUserPower(String userName) throws Exception {
        return null;
    }

    private List<Role> getUserRoles(String userId) throws Exception{
        List<Role> roles = userRoleMapper.getUserRoles(userId);
        if (null == roles || 0 == roles.size()) {
            throw new AuthException("该用户未分配任何角色！请联系管理员");
        }
        return roles;
    }

    private List<Power> getUserPowers(List<Role> roles) throws Exception {
        List<Power> powers = rolePowerMapper.getAllPowerType(roles);
        if (null == powers || 0 == powers.size()) {
            throw new AuthException("该用户没有任何权限，请联系管理员");
        }
        return powers;
    }
}
