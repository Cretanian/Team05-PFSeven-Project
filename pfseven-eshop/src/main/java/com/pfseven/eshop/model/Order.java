package com.pfseven.eshop.model;

import java.math.BigDecimal;
import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {
    private Integer orderID;
    private Integer customerID;
    private PaymentMethod paymentMethod;
    private BigDecimal cost;
}
