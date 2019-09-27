package com.laowang.datasource.config;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.laowang.datasource.beans.business.BasicInfoBean;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Aspect
@Order(-1)  // 该切面应当先于 @Transactional 执行
@Component
public class DynamicDataSourceAspect {
    @Autowired
    private Environment env;

    @Pointcut("execution(* com.laowang.datasource.mapper..*(..))")
    public void test() {
    }


    /**
     * 切换数据源
     *
     * @param point
     */
    @Before("test()")
    public void switchDataSource(JoinPoint point) throws Exception {
        Object[] args = point.getArgs();
        for (Object obj : args) {
            if (obj instanceof BasicInfoBean) {
                BasicInfoBean bean = (BasicInfoBean) obj;
                String baseName = bean.getBaseName();
                if (DynamicDataSourceContextHolder.containDataSourceKey(baseName)) {
                    DynamicDataSourceContextHolder.setDataSourceKey(baseName);
                } else {
                    DynamicDataSource dynamicDataSource = SpringContextHolder.getApplicationContext().getBean("dynamicDataSource", DynamicDataSource.class);

                    DataSource dataSource = determineDataSource(bean);
                    Map<Object, Object> dataSourceMap = new HashMap<>(1);
                    dataSourceMap.put(baseName, dataSource);
//                    dynamicDataSource.setDataSources(dataSourceMap);
//                    dynamicDataSource.setDefaultDataSource(dataSource);
                    dynamicDataSource.setTargetDataSources(dataSourceMap);
                    DynamicDataSourceContextHolder.setDataSourceKey(baseName);
                }
            }
        }
    }

    /**
     * 重置数据源
     *
     * @param point
     */
    @After("test()")
    public void restoreDataSource(JoinPoint point) {
        // 将数据源置为默认数据源
        DynamicDataSourceContextHolder.clearDataSourceKey();
        System.out.println("Restore TargetDataSource to [" + DynamicDataSourceContextHolder.getDataSourceKey()
                + "] in Method [" + point.getSignature() + "]");
    }

    private DataSource determineDataSource(BasicInfoBean bean) throws Exception {
        Properties properties = new Properties();
        properties.put("driverClassName", "com.mysql.jdbc.Driver");
        properties.put("url", "jdbc:mysql://127.0.0.1:" + bean.getPort() + "/" + bean.getBaseName() + "?useSSL=false&serverTimezone=Asia/Shanghai&autoReconnect=true&useUnicode=true&characterEncoding=utf8");
        properties.put("type", "com.alibaba.druid.pool.DruidDataSource");
        properties.put("username", env.getProperty("spring.datasource." + bean.getDsName() + ".username"));
        properties.put("password", env.getProperty("spring.datasource." + bean.getDsName() + ".password"));
        return DruidDataSourceFactory.createDataSource(properties);
    }
}
