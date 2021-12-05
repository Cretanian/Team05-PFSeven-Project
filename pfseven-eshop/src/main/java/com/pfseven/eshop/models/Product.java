package com.pfseven.eshop.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
//@ToString(callSuper = false)

public class Product {
    private Integer productID;
    private String productName;
    private BigDecimal price;
    private Integer stock;
}
