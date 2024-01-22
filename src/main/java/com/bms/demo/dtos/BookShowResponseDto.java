package com.bms.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BookShowResponseDto {
    private Long bookingId;
    private int amount;
    private ResponseStatus responseStatus;
    private String failureReason;

//    public BookShowResponseDto(Long bookingId,int amount,ResponseStatus responseStatus,String message) {
//        this.bookingId = bookingId;
//        this.amount=amount;
//        this.responseStatus=responseStatus;
//        this.message=message;
//    }
}
