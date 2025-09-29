package com.abridge.base.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RedirectByDate {
    private Date date;
    private Long redirectCount;

    public RedirectByDate( Long redirectCount, Date date) {
        this.date = date;
        this.redirectCount = redirectCount;
    }

}
