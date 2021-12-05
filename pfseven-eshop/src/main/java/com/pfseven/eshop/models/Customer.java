package com.pfseven.eshop.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
