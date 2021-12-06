package com.pfseven.eshop.models;

import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Order {
    private Integer orderID;
    private Integer customerID;
    private ArrayList<OrderItem> orderList;
}
