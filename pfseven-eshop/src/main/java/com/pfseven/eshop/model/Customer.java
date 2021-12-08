package com.pfseven.eshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
//@ToString(callSuper = false)

public class Customer {
    private Integer customerID;
    private String firstName;
    private String lastName;
    private CategoryID categoryID;
}
