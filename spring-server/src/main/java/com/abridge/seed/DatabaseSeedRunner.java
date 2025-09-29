package com.abridge.seed;

import java.util.Calendar;
import java.util.Date;

import com.abridge.base.entity.BridgeLink;
import com.abridge.base.entity.RedirectAuditLog;
import com.abridge.base.repository.BridgeLinkRepository;
import com.abridge.base.repository.RedirectAuditLogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@Profile("seed")
public class DatabaseSeedRunner implements ApplicationRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseSeedRunner.class);
    private final BridgeLinkRepository bridgeLinkRepository;
    private final RedirectAuditLogRepository redirectAuditLogRepository;

    public DatabaseSeedRunner(BridgeLinkRepository bridgeLinkRepository, RedirectAuditLogRepository redirectAuditLogRepository) {
        this.bridgeLinkRepository = bridgeLinkRepository;
        this.redirectAuditLogRepository = redirectAuditLogRepository;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        LOGGER.info("Starting DatabaseSeedRunner");
        createBridgeLinkSeeds();
    }

    private void createBridgeLinkSeeds() {
        LOGGER.info("Deleting existing data");
        bridgeLinkRepository.deleteAll();
        redirectAuditLogRepository.deleteAll();
        LOGGER.info("Adding seed data for [goog]");
        bridgeLinkRepository.save(newBridgeLink("goog", "www.google.com"));
        createSmallAuditTrail("goog");
        LOGGER.info("Adding seed data for [weather]");
        bridgeLinkRepository.save(newBridgeLink("weather", "www.weather-channel.com"));
        createSmallAuditTrail("weather");
        LOGGER.info("Adding seed data for [stack]");
        bridgeLinkRepository.save(newBridgeLink("stack", "www.stackoverflow.com"));
        createSmallAuditTrail("stack");
        LOGGER.info("Adding seed data for [zon]");
        bridgeLinkRepository.save(newBridgeLink("zon", "www.amazon.com"));
        createSmallAuditTrail("zon");
    }


    private BridgeLink newBridgeLink(String term, String redirectUrl) {
        BridgeLink.BridgeLinkBuilder builder = BridgeLink.builder();
        return builder
                .term(term)
                .redirectUrl(redirectUrl)
                .build();
    }

    private void createSmallAuditTrail(String term) {
        redirectAuditLogRepository.save(newRedirectAuditLog(term, Calendar.AUGUST, 31));
        redirectAuditLogRepository.save(newRedirectAuditLog(term, Calendar.SEPTEMBER, 5));
        redirectAuditLogRepository.save(newRedirectAuditLog(term, Calendar.SEPTEMBER, 5));
        redirectAuditLogRepository.save(newRedirectAuditLog(term, Calendar.SEPTEMBER, 5));
        redirectAuditLogRepository.save(newRedirectAuditLog(term, Calendar.SEPTEMBER, 8));
        redirectAuditLogRepository.save(newRedirectAuditLog(term, Calendar.SEPTEMBER, 8));
        redirectAuditLogRepository.save(newRedirectAuditLog(term, Calendar.SEPTEMBER, 15));
        redirectAuditLogRepository.save(newRedirectAuditLog(term, Calendar.SEPTEMBER, 15));
        redirectAuditLogRepository.save(newRedirectAuditLog(term, Calendar.SEPTEMBER, 21));
        redirectAuditLogRepository.save(newRedirectAuditLog(term, Calendar.SEPTEMBER, 23));

    }

    private RedirectAuditLog newRedirectAuditLog(String term, int month, int day) {
        Date createdDate = java.sql.Date.valueOf(java.time.LocalDate.of(2025, month, day));
        RedirectAuditLog.RedirectAuditLogBuilder builder = RedirectAuditLog.builder();
        return builder
                .term(term)
                .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/140.0.0.0 Safari/537.36")
                .createdDate(createdDate)
                .build();
    }
}
