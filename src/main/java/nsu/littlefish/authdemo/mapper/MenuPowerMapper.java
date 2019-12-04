package nsu.littlefish.authdemo.mapper;

import nsu.littlefish.authdemo.pojo.MenuPower;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuPowerMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu_power
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu_power
     *
     * @mbg.generated
     */
    int insert(MenuPower record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu_power
     *
     * @mbg.generated
     */
    int insertSelective(MenuPower record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu_power
     *
     * @mbg.generated
     */
    MenuPower selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu_power
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(MenuPower record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu_power
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(MenuPower record);
}