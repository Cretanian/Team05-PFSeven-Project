package com.pfseven.eshop.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Product {
    private Integer productID;
    private String productName;
    private BigDecimal price;
    private Integer stock;
}
