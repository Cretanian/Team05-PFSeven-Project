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
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Logger;

public class ReportsService {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CustomerService.class);
    private ReportsRepository reportsRepository ;

    public ReportsService(ReportsRepository reportsRepository){
        this.reportsRepository= reportsRepository;
    }

    //first report
    public void getNumberAndCostOfPurchasesForCustomer(Integer customerID) throws SQLException {

        int number = reportsRepository.totalNumberOfOrdersForCustomer(customerID);
        BigDecimal cost = reportsRepository.totalCostOfPurchases(customerID);

        logger.info("customer {} number of purchases {} with total cost of {}$",customerID,number,cost);

    }
    //second report
    public void getNumberAndCostOfPurchasesForCategory(String category) throws SQLException {
        Integer categoryID;

        switch (category.toLowerCase()) {
            case "b2b":
                categoryID = 1; break;
            case "b2c":
                categoryID = 2; break;
            case "b2g":
                categoryID = 3; break;
            default:
                categoryID = -1;
        }

        int number = reportsRepository.totalNumberOfOrdersForCategory(categoryID);
        BigDecimal cost = reportsRepository.totalCostOfPurchasesForCategory(categoryID);

        logger.info("There have been {} orders from {}-customers with total cost : {}$", number, category, cost);
    }

    //third report
    public void getNumberAndCostOfPurchasesForPaymentMethod(String paymentMethod) throws SQLException {
        Integer paymentMethodID;

        switch (paymentMethod.toLowerCase()) {
            case "cash":
                paymentMethodID = 1; break;
            case "credit card":
            case "credit_card":
                paymentMethodID = 2; break;
            case "wire transfer":
            case "wire_transfer":
                paymentMethodID = 3; break;
            default:
                paymentMethodID = -1;
        }

        int number = reportsRepository.totalNumberOfOrdersForPaymentMethod(paymentMethodID);
        BigDecimal cost = reportsRepository.totalCostOfPurchasesForPaymentMethod(paymentMethodID);

        logger.info("There have been {} orders via {} with total cost : {}$", number, paymentMethod, cost);
    }

    //fourth report
    public void getGoldenCustomer() throws SQLException {
        ResultSet resultSet = reportsRepository.GoldenCustomer();

        resultSet.next();
        int customerID = resultSet.getInt(1);
        String firstName = resultSet.getString(2);
        String lastName = resultSet.getString(3);
        BigDecimal price = resultSet.getBigDecimal(4);

        logger.info("{} {} with id {} has purchased the most expensive product({}$) so far!", firstName, lastName, customerID, price);
        }

}
