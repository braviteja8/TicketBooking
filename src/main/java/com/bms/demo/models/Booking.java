package com.bms.demo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Booking extends BaseModel{
    /*
    User   Booking
    1     M
    1     1
    unless u really require list<Booking>one users,
    dont keep that variable in User;
    always keep the id of 1 side on m side->follow this
    for classes also,
     */
    @Enumerated(EnumType.ORDINAL)
    private BookingStatus bookingStatus;
    @ManyToOne
    private User user;
    //if cancellation of booking possible
    //then it is m:m
    @OneToMany
    private List<ShowSeat> showSeatList;
    private int amount;
    @OneToMany
    private List<Payment>payments;

}
