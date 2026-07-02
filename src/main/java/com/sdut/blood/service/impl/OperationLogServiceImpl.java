package com.sdut.blood.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sdut.blood.common.utils.SecurityUtil;
import com.sdut.blood.domain.entity.OperationLog;
import com.sdut.blood.mapper.OperationLogMapper;
import com.sdut.blood.service.OperationLogService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class OperationLogServiceImpl extends ServiceImpl<OperationLogMapper, OperationLog> implements OperationLogService {

    @Override
    @Async
    public void saveLog(String operationType, String operationContent) {
        OperationLog log = new OperationLog();
        log.setOperatorId(SecurityUtil.getCurrentUserId());
        log.setOperationType(operationType);
        log.setOperationContent(operationContent);
        log.setOperationTime(LocalDateTime.now());
        save(log);
    }
}