package com.abridge.base.service;

import com.abridge.base.entity.BridgeLink;
import com.abridge.base.entity.RedirectAuditLog;
import com.abridge.base.repository.RedirectAuditLogRepository;
import org.springframework.stereotype.Service;

@Service
public class RedirectAuditLogService {
    RedirectAuditLogRepository redirectAuditLogRepository;

    public RedirectAuditLogService(RedirectAuditLogRepository redirectAuditLogRepository) {
        this.redirectAuditLogRepository = redirectAuditLogRepository;
    }

    public void auditLog(BridgeLink bridgeLinkInfo, String userAgentInfo) {
        RedirectAuditLog.RedirectAuditLogBuilder builder = RedirectAuditLog.builder();
        builder.term(bridgeLinkInfo.getTerm());
        builder.userAgent(userAgentInfo);

        redirectAuditLogRepository.save(builder.build());
    }
}
