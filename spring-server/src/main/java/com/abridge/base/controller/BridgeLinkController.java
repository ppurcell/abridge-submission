package com.abridge.base.controller;

import java.util.List;

import com.abridge.base.dto.BridgeLinkAnalyticsResp;
import com.abridge.base.entity.BridgeLink;
import com.abridge.base.service.BridgeLinkService;
import com.abridge.base.service.RedirectAuditLogService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BridgeLinkController {

    private final BridgeLinkService bridgeLinkService;
    private final RedirectAuditLogService redirectAuditLogService;

    public BridgeLinkController(BridgeLinkService bridgeLinkService, RedirectAuditLogService redirectAuditLogService) {
        this.bridgeLinkService = bridgeLinkService;
        this.redirectAuditLogService = redirectAuditLogService;
    }

    //Create new BridgeLink
    @PutMapping("/bridge-link")
    @ResponseBody
    public ResponseEntity<BridgeLink> save(@RequestBody BridgeLink bridgeLink) {
        final BridgeLink checkAvailability = bridgeLinkService.findByTerm(bridgeLink.getTerm());
        if (checkAvailability == null) {
            BridgeLink savedBridgeLink = bridgeLinkService.save(bridgeLink);
            return ResponseEntity.ok(savedBridgeLink);
        } else {
            return ResponseEntity.status(409).build(); //Conflict
        }
    }

    //Delete BridgeLink
    @DeleteMapping("/bridge-link/{term}")
    @ResponseBody
    public ResponseEntity<Void> deleteBridgeLink(@PathVariable String term) {
        final BridgeLink bridgeLink = bridgeLinkService.findByTerm(term);
        //TODO. Delete all related audit logs as well.
        if (bridgeLink == null) {
            return ResponseEntity.notFound().build();
        } else {
             bridgeLinkService.delete(bridgeLink.getId());
             return ResponseEntity.noContent().build();
        }
    }

    //List BridgeLinks
    @GetMapping("/bridge-links")
    @ResponseBody
    public ResponseEntity<List<BridgeLink>> listBridgeLinks() {
        List<BridgeLink> bridgeLinks = bridgeLinkService.findAll();
        return ResponseEntity.ok(bridgeLinks);
    }

    //Forward BridgeLink
    @PostMapping("bridge-link/{term}")
    @ResponseBody
    public ResponseEntity<BridgeLink> forwardBridgeLink(@PathVariable String term, @RequestBody String userAgentInfo) {
        final BridgeLink bridgeLinkInfo = bridgeLinkService.findByTerm(term);

         ResponseEntity<BridgeLink> response = ResponseEntity.notFound().build();
        if (bridgeLinkInfo != null) {
            redirectAuditLogService.auditLog(bridgeLinkInfo, userAgentInfo);
            response =  ResponseEntity.ok(bridgeLinkInfo);
        }

        return response;
    }

    //Analytics for single BridgeLink term.
    @GetMapping("bridge-link/{term}/analytics")
    @ResponseBody
    public ResponseEntity<BridgeLinkAnalyticsResp> retrieveBridgeLinkAnalytics(@PathVariable String term) {
        final BridgeLinkAnalyticsResp bridgeLinkAnalyticsResp = bridgeLinkService.aggregateBridgeLinkAnalytics(term);

        ResponseEntity<BridgeLinkAnalyticsResp> response = ResponseEntity.notFound().build();
        if (bridgeLinkAnalyticsResp != null) {

            response =  ResponseEntity.ok(bridgeLinkAnalyticsResp);
        }

        return response;
    }


}
