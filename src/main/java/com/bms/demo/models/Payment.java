package com.bms.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Payment extends BaseModel{
    private String refNo;
    @Enumerated(EnumType.ORDINAL)
    private PaymentStatus paymentStatus;
    private int amount;
    @Enumerated(EnumType.ORDINAL)
    private PaymentGateWayProvider paymentGateWayProvider;
    @Enumerated(EnumType.ORDINAL)
    private PaymentMode paymentMode;
}
