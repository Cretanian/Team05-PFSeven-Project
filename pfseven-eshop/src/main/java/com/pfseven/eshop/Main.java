package com.pfseven.eshop;

import com.pfseven.eshop.models.*;
import com.pfseven.eshop.servicies.*;


public class Main {

    public static void main(String[] args) {
        CustomerService customerService = new CustomerService();
        customerService.customerSomething();
        ProductService productService = new ProductService();
        productService.newProductInput();

    }
}
