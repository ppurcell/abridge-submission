package com.abridge.base.entity;

import java.util.Date;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "bridge_link")
public class BridgeLink {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Nonnull
    private String term;

    @Nonnull
    private String redirectUrl;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "mm/dd/yyyy")
    private Date createdDate;

    @PrePersist
    protected void onCreate() {
        createdDate = new Date();
    }

}
