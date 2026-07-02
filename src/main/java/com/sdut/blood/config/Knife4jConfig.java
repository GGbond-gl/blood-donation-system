package com.sdut.blood.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Knife4j 接口文档配置（基于 SpringDoc OpenAPI）
 * 访问地址：http://localhost:8080/doc.html
 */
@Configuration
public class Knife4jConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("献血管理系统 - 接口文档")
                        .description("山东理工大学软件工程实训项目：献血管理系统后端API文档")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("实训1组")));
    }
}
