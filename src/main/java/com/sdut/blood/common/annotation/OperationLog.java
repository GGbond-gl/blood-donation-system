package com.sdut.blood.common.annotation;

import java.lang.annotation.*;

/**
 * 操作日志注解
 * 标注在Controller方法上，自动记录操作日志
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperationLog {

    /**
     * 操作类型：新增/修改/删除/导出/查询等
     */
    String operationType();

    /**
     * 操作内容描述
     */
    String operationContent();
}