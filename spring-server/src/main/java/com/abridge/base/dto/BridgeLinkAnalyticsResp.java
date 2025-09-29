package com.abridge.base.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class BridgeLinkAnalyticsResp {

    private String term;
    private Date createdDate;
    private Long totalRedirects;
    private List<RedirectByDate> redirectByDate;

}
