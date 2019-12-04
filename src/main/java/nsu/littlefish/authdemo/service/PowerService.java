package nsu.littlefish.authdemo.service;

import nsu.littlefish.authdemo.vo.PowerVo;

/**
 * @author ：yuzheng
 * @date ：Created in 2019/12/4 17:24
 * @description：权限
 * @modified By：
 * @version: $
 */
public interface PowerService {
    /**
     * 获取用户权限
     * @param userName
     * @return
     * @throws Exception
     */
    public PowerVo getUserPower(String userName) throws Exception;
}
