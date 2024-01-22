package com.bms.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ShowSeatType extends BaseModel{
    /*
    ShoeSeatType   show
    1    1
    M    1
    ShowSeatType table
    1/gold/250
    1/diamond/250
    2/gold/200

     */
    @ManyToOne
    private Show Show;
    private String showSeatType;
    private int price;
}
