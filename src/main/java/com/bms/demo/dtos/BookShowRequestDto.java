package com.bms.demo.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter

public class BookShowRequestDto {
    private Long userId;
    private List<Long> showSeatIds;
    private Long showId;
    private String failureReason;
}
