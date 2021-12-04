package com.pfseven.eshop;

import com.pfseven.eshop.models.*;
import com.pfseven.eshop.servicies.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Scanner;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        CustomerService customerService= new CustomerService();
        customerService.customerSomething();

    }
}
