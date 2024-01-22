package com.bms.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class ShowSeat extends BaseModel{
    /*
    ShowSeat  show
    1         1
    m         1


    ShowSeat  table
    1 /A1 /BOOKED
    1/A2/BOOKED
     */
    @ManyToOne
    private Show show;
    /*
    ShowSeat Seat
    1        1
    m        1
    1/a1/booked
    1/a2/booked
    2/a1/booked
     */
    @ManyToOne
    private Seat seat;
    @Enumerated(EnumType.ORDINAL)
    private ShowSeatStatus showSeatStatus;

    private Date LockedAt;
}
