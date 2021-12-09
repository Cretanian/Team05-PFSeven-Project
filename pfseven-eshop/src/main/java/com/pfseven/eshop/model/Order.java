package com.pfseven.eshop.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Order {
    private Integer orderID;
    private Integer customerID;
    private ArrayList<OrderItem> orderList;
    private PaymentMethod paymentMethod;
    private String pending;
    private Integer cost;
//    private List<Product> products;
}
