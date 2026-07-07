package com.sdut.blood.aspect;

import com.sdut.blood.common.annotation.OperationLog;
import com.sdut.blood.common.utils.SecurityUtil;
import com.sdut.blood.service.OperationLogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 操作日志AOP切面
 * 拦截标注@OperationLog的方法，成功执行后自动记录日志
 */
@Slf4j
@Aspect
@Component
public class OperationLogAspect {

    @Resource
    private OperationLogService operationLogService;

    /**
     * 方法正常返回后记录日志
     * 切点：所有标注了@OperationLog的方法
     */
    @AfterReturning(pointcut = "@annotation(operationLog)", returning = "result")
    public void recordOperationLog(JoinPoint joinPoint, OperationLog operationLog, Object result) {
        try {
            // 1. 获取注解中的操作信息
            String operationType = operationLog.operationType();
            String operationContent = operationLog.operationContent();

            // 2. 获取当前登录用户ID
            Long userId = SecurityUtil.getCurrentUserId();
            if (userId == null) {
                return;
            }

            // 3. 异步保存操作日志
            operationLogService.saveLog(operationType, operationContent);

        } catch (Exception e) {
            // 日志记录失败不影响主业务，仅打印错误
            log.error("操作日志记录失败", e);
        }
    }
}