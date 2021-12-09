package com.pfseven.eshop.service;

import com.pfseven.eshop.database.ProductRepository;
import com.pfseven.eshop.database.ReportsRepository;
import com.pfseven.eshop.model.CategoryID;
import com.pfseven.eshop.model.Customer;
import com.pfseven.eshop.model.PaymentMethod;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class ReportsService {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CustomerService.class);
    private ReportsRepository reportsRepository ;

    public void getNumberAndCostOfPurchasesForCustomer() {

    }


}
