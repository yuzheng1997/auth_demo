package nsu.littlefish.authdemo.service.impl;

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
public class PowerImpl implements PowerService {
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

    private List<Role> getUserRoles(String userName) {
        return null;
    }

    private List<Power> getUserPowers(String Role) {
        return null;
    }
}
