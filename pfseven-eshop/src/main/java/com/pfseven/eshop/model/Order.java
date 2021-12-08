package com.pfseven.eshop.model;

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
    private ArrayList<OrderItem> orderList;
}
