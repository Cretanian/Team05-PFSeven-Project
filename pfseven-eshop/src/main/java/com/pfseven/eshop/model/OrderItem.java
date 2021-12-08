package com.pfseven.eshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
//@ToString(callSuper = false)
public class OrderItem {
    private Integer productID;
    private BigDecimal totalEntityPrice;
    private Integer total;

}
