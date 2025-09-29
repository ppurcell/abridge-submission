package com.abridge.base.controller;

import java.util.Collections;
import java.util.List;

import com.abridge.base.dto.BridgeLinkAnalyticsResp;
import com.abridge.base.entity.BridgeLink;
import com.abridge.base.repository.BridgeLinkRepository;
import com.abridge.base.service.BridgeLinkService;
import com.abridge.base.service.RedirectAuditLogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BridgeLinkControllerTest {

    @InjectMocks
    private BridgeLinkController bridgeLinkController;

    @Mock
    private BridgeLinkService bridgeLinkService;

    @Mock
    private RedirectAuditLogService redirectAuditLogService;

    @Mock
    private BridgeLinkRepository bridgeLinkRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllLinks() {
        // Mock
        List<BridgeLink> mockList = Collections.singletonList(new BridgeLink());
        when(bridgeLinkService.findAll()).thenReturn(mockList);

        // Test
        ResponseEntity<List<BridgeLink>> response = bridgeLinkController.listBridgeLinks();

        // Verify
        assertEquals(200, response.getStatusCode().value());
        assertEquals(mockList, response.getBody());
        verify(bridgeLinkService).findAll();
    }

    @Test
    void saveBridgeLink_Success() {
        // Mock
        BridgeLink newLink = new BridgeLink();
        newLink.setTerm("uniqueTerm");
        when(bridgeLinkService.findByTerm("uniqueTerm")).thenReturn(null);
        when(bridgeLinkService.save(newLink)).thenReturn(newLink);

        // Test
        ResponseEntity<BridgeLink> response = bridgeLinkController.save(newLink);

        // Verify
        assertEquals(200, response.getStatusCode().value());
        assertEquals(newLink, response.getBody());
        verify(bridgeLinkService).findByTerm("uniqueTerm");
        verify(bridgeLinkService).save(newLink);
    }

    @Test
    void saveBridgeLink_Conflict() {
        // Setup
        BridgeLink existingLink = new BridgeLink();
        existingLink.setTerm("existingTerm");

        // Mock
        when(bridgeLinkService.findByTerm("existingTerm")).thenReturn(existingLink);

         // Test
        BridgeLink newLink = new BridgeLink();
        newLink.setTerm("existingTerm");
        ResponseEntity<BridgeLink> response = bridgeLinkController.save(newLink);

        // Verify
        assertEquals(409, response.getStatusCode().value());
        assertNull(response.getBody());
        verify(bridgeLinkService).findByTerm("existingTerm");
        verify(bridgeLinkService, never()).save(any());
    }

    @Test
    void deleteBridgeLink_Success() {
        BridgeLink existingLink = new BridgeLink();
        existingLink.setId(1L);
        existingLink.setTerm("termToDelete");
        // Mock
        when(bridgeLinkService.findByTerm("termToDelete")).thenReturn(existingLink);

        // Test
        ResponseEntity<Void> response = bridgeLinkController.deleteBridgeLink("termToDelete");

        // Verify
        assertEquals(204, response.getStatusCode().value());
        assertNull(response.getBody());
    }

    @Test
    void deleteBridgeLink_404() {
        // Mock
        when(bridgeLinkService.findByTerm("termToDelete")).thenReturn(null);

        // Test
        ResponseEntity<Void> response = bridgeLinkController.deleteBridgeLink("termToDelete");

        // Verify
        assertEquals(404, response.getStatusCode().value());
        assertNull(response.getBody());
        verify(bridgeLinkService, never()).delete(any());
    }

    @Test
    void forwardBridgeLink_Success() {
        BridgeLink existingLink = new BridgeLink();
        existingLink.setTerm("existingTerm");
        // Mock
        when(bridgeLinkService.findByTerm("existingTerm")).thenReturn(existingLink);

        // Test
        ResponseEntity<BridgeLink> response = bridgeLinkController.forwardBridgeLink("existingTerm", "SomeUserAgent");

        // Verify
        assertEquals(200, response.getStatusCode().value());
        assertEquals(existingLink, response.getBody());
        verify(bridgeLinkService).findByTerm("existingTerm");
        verify(redirectAuditLogService).auditLog(existingLink, "SomeUserAgent");
    }

    @Test
    void forwardBridgeLink_404() {
        // Mock
        when(bridgeLinkService.findByTerm("nonExistingTerm")).thenReturn(null);

        // Test
        ResponseEntity<BridgeLink> response = bridgeLinkController.forwardBridgeLink("nonExistingTerm", "SomeUserAgent");

        // Verify
        assertEquals(404, response.getStatusCode().value());
        assertNull(response.getBody());
        verify(bridgeLinkService).findByTerm("nonExistingTerm");
        verify(redirectAuditLogService, never()).auditLog(any(), any());
    }

    @Test
    void retrieveBridgeLinkAnalytics_Success() {
        BridgeLink existingLink = new BridgeLink();
        existingLink.setTerm("existingTerm");
        // Mock
        when(bridgeLinkService.aggregateBridgeLinkAnalytics("existingTerm")).thenReturn(
                BridgeLinkAnalyticsResp.builder()
                        .term("existingTerm")
                        .totalRedirects(10L)
                        .build()
        );

        // Test
        ResponseEntity<?> response = bridgeLinkController.retrieveBridgeLinkAnalytics("existingTerm");

        // Verify
        assertEquals(200, response.getStatusCode().value());
        assertEquals("existingTerm", ((BridgeLinkAnalyticsResp)response.getBody()).getTerm());
        assertEquals(10L, ((BridgeLinkAnalyticsResp)response.getBody()).getTotalRedirects());
        verify(bridgeLinkService).aggregateBridgeLinkAnalytics("existingTerm");
    }

    @Test
    void retrieveBridgeLinkAnalytics_404() {
        // Mock
        when(bridgeLinkService.aggregateBridgeLinkAnalytics("nonExistingTerm")).thenReturn(null);

        // Test
        ResponseEntity<?> response = bridgeLinkController.retrieveBridgeLinkAnalytics("nonExistingTerm");

        // Verify
        assertEquals(404, response.getStatusCode().value());
        assertNull(response.getBody());
        verify(bridgeLinkService).aggregateBridgeLinkAnalytics("nonExistingTerm");
    }

}