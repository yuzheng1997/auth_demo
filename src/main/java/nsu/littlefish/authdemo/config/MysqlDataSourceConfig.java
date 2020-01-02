package nsu.littlefish.authdemo.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import javax.sql.DataSource;

/**
 * @author ：yuzheng
 * @date ：Created in 2020/1/2 16:53
 * @description：
 * @modified By：
 * @version: $
 */
@Configuration
@MapperScan(basePackages = MysqlDataSourceConfig.PACKAGE,sqlSessionFactoryRef = "mysqlSqlSessionFactory")
public class MysqlDataSourceConfig {
    /**
     * PACKAGE扫描路径
     */
    static final String PACKAGE = "nsu.littlefish.authdemo.mapper";
    /**
     * mybatis mapper扫描路径
     */
    static final String Mapper_LOCATION = "classpath:mapper/**.xml";
    @Primary
    @Bean("mysqlDataSource")
    @ConfigurationProperties("spring.datasource.druid.mysql")
    public DataSource mysqlDataSource() {
        return DruidDataSourceBuilder.create().build();
    }
    @Bean("mysqlTransactionManager")
    @Primary
    public DataSourceTransactionManager mysqlTransactionManager() {
        return new DataSourceTransactionManager(mysqlDataSource());
    }
    @Bean("mysqlSqlSessionFactory")
    @Primary
    public SqlSessionFactory mysqlSqlSessionFactory(@Qualifier("mysqlDataSource") DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        //如果不使用xml的方式配置mapper，则可以省去下面这行mapper location的配置。
        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
        .getResources(MysqlDataSourceConfig.Mapper_LOCATION));
        return sessionFactoryBean.getObject();
    }
}
