package com.pfseven.eshop.service;

import com.pfseven.eshop.repository.ReportsRepositoryImpl;
import com.pfseven.eshop.model.CategoryID;
import com.pfseven.eshop.model.PaymentMethod;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportsServiceImpl implements ReportsService {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
    private ReportsRepositoryImpl reportsRepositoryImpl;

    public ReportsServiceImpl(ReportsRepositoryImpl reportsRepositoryImpl){
        this.reportsRepositoryImpl = reportsRepositoryImpl;
    }

    //first report
    public void getNumberAndCostOfPurchasesForCustomer(Integer customerID) {

        int number = reportsRepositoryImpl.totalNumberOfOrdersForCustomer(customerID);
        BigDecimal cost = reportsRepositoryImpl.totalCostOfPurchases(customerID);

        if(cost != null && number != -1 && !cost.equals(BigDecimal.valueOf(-1))) {
            logger.info("customer {} number of purchases {} with total cost of {}$", customerID, number, cost);
        }
    }

    //second report
    public void getNumberAndCostOfPurchasesForCustomerCategory()  {
        int number = reportsRepositoryImpl.totalNumberOfOrdersPerCategory(CategoryID.B2C);
        BigDecimal cost = reportsRepositoryImpl.totalCostOfOrdersPerCategory(CategoryID.B2C);

        if(cost != null && number != -1 && !cost.equals(BigDecimal.valueOf(-1))) {
            logger.info("Total number of purchases is {} with {} cost for the B2C", number, cost);
        }

        number = reportsRepositoryImpl.totalNumberOfOrdersPerCategory(CategoryID.B2B);
        cost = reportsRepositoryImpl.totalCostOfOrdersPerCategory(CategoryID.B2B);

        if(cost != null && number != -1 && !cost.equals(BigDecimal.valueOf(-1))) {
            logger.info("Total number of purchases is {} with {} cost for the B2B", number, cost);
        }

        number = reportsRepositoryImpl.totalNumberOfOrdersPerCategory(CategoryID.B2G);
        cost = reportsRepositoryImpl.totalCostOfOrdersPerCategory(CategoryID.B2G);

        if(cost != null && number != -1 && !cost.equals(BigDecimal.valueOf(-1))) {
            logger.info("Total number of purchases is {} with {} cost for the B2G", number, cost);
        }
    }

    //third report
    public void getNumberAndCostOfPurchasesPerPaymentMethod()  {
        int number = reportsRepositoryImpl.totalNumberOfOrdersPerPaymentMethod(PaymentMethod.CASH);
        BigDecimal cost = reportsRepositoryImpl.totalCostOfOrdersPerPaymentMethod(PaymentMethod.CASH);
        if(cost != null && number != -1 && !cost.equals(BigDecimal.valueOf(-1))) {
            logger.info("Total number of purchases using CASH is {} with {} cost ", number, cost);
        }

        number = reportsRepositoryImpl.totalNumberOfOrdersPerPaymentMethod(PaymentMethod.CREDIT_CARD);
        cost = reportsRepositoryImpl.totalCostOfOrdersPerPaymentMethod(PaymentMethod.CREDIT_CARD);
        if(cost != null && number != -1 && !cost.equals(BigDecimal.valueOf(-1))) {
            logger.info("Total number of purchases using CREDIT_CARD is {} with {} cost ", number, cost);
        }

        number = reportsRepositoryImpl.totalNumberOfOrdersPerPaymentMethod(PaymentMethod.WIRE_TRANSFER);
        cost = reportsRepositoryImpl.totalCostOfOrdersPerPaymentMethod(PaymentMethod.WIRE_TRANSFER);
        if(cost != null && number != -1 && !cost.equals(BigDecimal.valueOf(-1))) {
            logger.info("Total number of purchases using WIRE_TRANSFER is {} with {} cost ", number, cost);
        }
    }

    //fourth report
    public void getGoldenCustomer() {
        try {
            ResultSet resultSet = reportsRepositoryImpl.GoldenCustomer();
            if(resultSet != null){
                do {
                    int customerID = resultSet.getInt(1);
                    String firstName = resultSet.getString(2);
                    String lastName = resultSet.getString(3);
                    BigDecimal price = resultSet.getBigDecimal(4);
                    Integer quantity = resultSet.getInt(5);

                    logger.info("{} {} with id {} has purchased the most expensive product({}$) {} times so far!", firstName, lastName, customerID, price, quantity);
                }while(resultSet.next());
            }
        } catch (SQLException e){
            logger.error("Error: {}",e.toString());
        }
    }

}
