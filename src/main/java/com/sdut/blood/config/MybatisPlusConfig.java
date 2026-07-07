package com.sdut.blood.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis-Plus 核心配置类
 */
@Configuration
@MapperScan("com.sdut.blood.mapper") // 全局扫描Mapper接口，无需每个Mapper单独加@Mapper注解
public class MybatisPlusConfig {

    /**
     * MyBatis-Plus 插件集合
     * 1. 分页插件：支持列表分页查询
     * 2. 主键策略：默认雪花算法生成主键ID，在yml中全局配置
     * 3. 逻辑删除：在yml中全局配置，无需代码侵入
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 添加MySQL分页插件，自动处理分页SQL
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
}