package com.pfseven.eshop.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
//@ToString(callSuper = false)
public class OrderItem extends Product{
    private Integer total;

}
