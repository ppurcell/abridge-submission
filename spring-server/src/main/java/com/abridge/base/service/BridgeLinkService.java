package com.abridge.base.service;

import java.util.List;

import com.abridge.base.dto.BridgeLinkAnalyticsResp;
import com.abridge.base.entity.BridgeLink;
import com.abridge.base.repository.BridgeLinkRepository;
import com.abridge.base.repository.RedirectAuditLogRepository;
import org.springframework.stereotype.Service;

@Service
public class BridgeLinkService {

    private final BridgeLinkRepository bridgeLinkRepository;
    private final RedirectAuditLogRepository redirectAuditLogRepository;

    public BridgeLinkService(BridgeLinkRepository bridgeLinkRepository, RedirectAuditLogRepository redirectAuditLogRepository) {
        this.bridgeLinkRepository = bridgeLinkRepository;
        this.redirectAuditLogRepository = redirectAuditLogRepository;
    }

    //Simple save wrapper.
    public BridgeLink save(BridgeLink bridgeLink) {
        return bridgeLinkRepository.save(bridgeLink);
    }

    //Simple delete wrapper.
    public void delete(Long id) {
        bridgeLinkRepository.deleteById(id);
    }

    //Simple list-all wrapper.
    public List<BridgeLink> findAll() {
        return (List<BridgeLink>) bridgeLinkRepository.findAll();
    }

    //Simple Find By Term wrapper.
    public BridgeLink findByTerm(String term) {
        return bridgeLinkRepository.findByTerm(term);
    }

    public BridgeLinkAnalyticsResp aggregateBridgeLinkAnalytics (String term) {
        BridgeLinkAnalyticsResp.BridgeLinkAnalyticsRespBuilder builder = BridgeLinkAnalyticsResp.builder();
        final BridgeLink bridgeLink = bridgeLinkRepository.findByTerm(term);

        //Early exit.
        if (bridgeLink == null) {
           return null;
        }

        builder.term(bridgeLink.getTerm());
        builder.createdDate(bridgeLink.getCreatedDate());
        builder.totalRedirects(redirectAuditLogRepository.countByTermEquals(term));
        builder.redirectByDate(redirectAuditLogRepository.countByDayUsingFunction(term));

        return builder.build();
    }



}
