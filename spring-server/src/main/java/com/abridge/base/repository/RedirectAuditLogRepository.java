package com.abridge.base.repository;

import java.util.List;

import com.abridge.base.dto.RedirectByDate;
import com.abridge.base.entity.RedirectAuditLog;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RedirectAuditLogRepository extends CrudRepository<RedirectAuditLog, Long> {

    /**
     * Count total redirects for a given term
     */
    Long countByTermEquals(@Param("term") String term);

    /**
     * Count redirects by day for a given term
     * Uses database function to cast datetime to date.
     */
    @Query("SELECT new com.abridge.base.dto.RedirectByDate(COUNT(e), CAST(e.createdDate AS date)) " +
            "FROM RedirectAuditLog e WHERE e.term = :term " +
            "GROUP BY CAST(e.createdDate AS date) " +
            "ORDER BY CAST(e.createdDate AS date)")
    List<RedirectByDate> countByDayUsingFunction(@Param("term") String term);
}
