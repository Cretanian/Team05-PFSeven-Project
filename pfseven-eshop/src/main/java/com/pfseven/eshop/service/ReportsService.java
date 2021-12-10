package com.pfseven.eshop.service;

import com.pfseven.eshop.database.ProductRepository;
import com.pfseven.eshop.database.ReportsRepository;
import com.pfseven.eshop.model.CategoryID;
import com.pfseven.eshop.model.Customer;
import com.pfseven.eshop.model.PaymentMethod;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Logger;

public class ReportsService {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CustomerService.class);
    private ReportsRepository reportsRepository ;

    public ReportsService(ReportsRepository reportsRepository){
        this.reportsRepository= reportsRepository;
    }

    public void getNumberAndCostOfPurchasesForCustomer(Integer customerID)  {

        int number = reportsRepository.totalNumberOfOrdersForCustomer(customerID);
        BigDecimal cost = reportsRepository.totalCostOfPurchases(customerID);

        logger.info("customer {} number of purchases {} with total cost of {}",customerID,number,cost);

    }
    public void getNumberAndCostOfPurchasesForCustomerCategory()  {
        int number = reportsRepository.totalNumberOfOrdersPerCategory(CategoryID.B2C);
        BigDecimal cost = reportsRepository.totalCostOfOrdersPerCategory(CategoryID.B2C);
        logger.info("Total number of purchases is {} with {} cost for the B2C",number,cost);
        number = reportsRepository.totalNumberOfOrdersPerCategory(CategoryID.B2B);
        cost = reportsRepository.totalCostOfOrdersPerCategory(CategoryID.B2B);
        logger.info("Total number of purchases is {} with {} cost for the B2B",number,cost);
        number = reportsRepository.totalNumberOfOrdersPerCategory(CategoryID.B2G);
        cost = reportsRepository.totalCostOfOrdersPerCategory(CategoryID.B2G);
        logger.info("Total number of purchases is {} with {} cost for the B2G",number,cost);
    }
    public void getNumberAndCostOfPurchasesPerPaymentMethod()  {
        int number = reportsRepository.totalNumberOfOrdersPerPaymentMethod(PaymentMethod.CASH);
        BigDecimal cost = reportsRepository.totalCostOfOrdersPerPaymentMethod(PaymentMethod.CASH);
        logger.info("Total number of purchases using CASH is {} with {} cost ",number,cost);
        number = reportsRepository.totalNumberOfOrdersPerPaymentMethod(PaymentMethod.CREDIT_CARD);
        cost = reportsRepository.totalCostOfOrdersPerPaymentMethod(PaymentMethod.CREDIT_CARD);
        logger.info("Total number of purchases using CREDIT_CARD is {} with {} cost ",number,cost);
        number = reportsRepository.totalNumberOfOrdersPerPaymentMethod(PaymentMethod.WIRE_TRANSFER);
        cost = reportsRepository.totalCostOfOrdersPerPaymentMethod(PaymentMethod.WIRE_TRANSFER);
        logger.info("Total number of purchases using WIRE_TRANSFER is {} with {} cost ",number,cost);
    }
    public void getCustomerWithTheMostExpensiveItem()  {
        reportsRepository.printGoldenCustomer();
    }

}
