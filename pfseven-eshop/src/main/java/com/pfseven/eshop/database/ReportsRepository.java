package com.pfseven.eshop.database;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportsRepository {

    private Connection connection;


    public ReportsRepository(Connection connection){
        this.connection = connection;
    }

    //first report
    public Integer totalNumberOfOrdersForCustomer(Integer customerID) {

        try(PreparedStatement statement = this.connection.prepareStatement("SELECT COUNT(CUSTOMER_ID) FROM ORDERS WHERE (CUSTOMER_ID = ?)")) {
            statement.setString(1, String.valueOf(customerID));
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            //logger.info("{} {} has placed {} orders.",customer.getFirstName(),customer.getLastName(),resultSet.toString());
            return resultSet.getInt(1);
        }
        catch (SQLException throwable) {
            throwable.printStackTrace();
            return -1;
        }


    }

    public BigDecimal totalCostOfPurchases(Integer customerID) {

        try(PreparedStatement statement = this.connection.prepareStatement("SELECT SUM(COST) FROM ORDERS WHERE /*HAVING*/ (CUSTOMER_ID = ?)")) {
            statement.setString(1, String.valueOf(customerID));
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            return resultSet.getBigDecimal(1);
            //logger.info("Total cost of purchases is: {}",resultSet.toString());
        }
        catch (SQLException throwable) {
            throwable.printStackTrace();
            return BigDecimal.valueOf(-1);
        }
    }

    //second report
    public Integer totalNumberOfOrdersForCategory(Integer categoryID) {

        try(PreparedStatement statement = this.connection.prepareStatement("SELECT COUNT(ORDERS.CUSTOMER_ID) FROM ORDERS JOIN CUSTOMER on ORDERS.CUSTOMER_ID = CUSTOMER.CUSTOMER_ID WHERE CUSTOMER.CATEGORY_ID = ?")) {
            statement.setString(1, String.valueOf(categoryID));
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
        }
        catch (SQLException throwable) {
            throwable.printStackTrace();
            return -1;
        }
   }

   public BigDecimal totalCostOfPurchasesForCategory(Integer categoryID) {

        try(PreparedStatement statement = this.connection.prepareStatement("SELECT SUM(ORDERS.COST) FROM ORDERS JOIN CUSTOMER on ORDERS.CUSTOMER_ID = CUSTOMER.CUSTOMER_ID WHERE CUSTOMER.CATEGORY_ID = ?")) {
            statement.setString(1, String.valueOf(categoryID));
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getBigDecimal(1);
        }
        catch (SQLException throwable) {
            throwable.printStackTrace();
            return BigDecimal.valueOf(-1);
        }
   }

   //third report
   public Integer totalNumberOfOrdersForPaymentMethod(Integer paymentMethodID) {
        try(PreparedStatement statement = this.connection.prepareStatement("SELECT COUNT(*) FROM ORDERS WHERE PAYMENT_METHOD_ID = ?")) {
            statement.setString(1, String.valueOf(paymentMethodID));
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
        }
        catch (SQLException throwable) {
            throwable.printStackTrace();
            return -1;
        }
   }

   public BigDecimal totalCostOfPurchasesForPaymentMethod(Integer paymentMethodId) {

        try( PreparedStatement statement = this.connection.prepareStatement("SELECT SUM(COST) FROM ORDERS WHERE PAYMENT_METHOD_ID = ?")) {
            statement.setString(1, String.valueOf(paymentMethodId));
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getBigDecimal(1);
        }
        catch (SQLException throwable) {
            throwable.printStackTrace();
            return BigDecimal.valueOf(-1);
        }
   }

   //fourth report
   public ResultSet GoldenCustomer() {

        try(PreparedStatement statement = this.connection.prepareStatement("SELECT CUSTOMER.CUSTOMER_ID, CUSTOMER.FIRST_NAME, CUSTOMER.LAST_NAME, PRODUCT.PRICE FROM CUSTOMER JOIN ORDERS ON CUSTOMER.CUSTOMER_ID = ORDERS.CUSTOMER_ID JOIN PRODUCTORDER ON ORDERS.ORDER_ID = PRODUCTORDER.ORDER_ID JOIN PRODUCT ON PRODUCTORDER.PRODUCT_ID = PRODUCT.PRODUCT_ID ORDER BY PRODUCT.PRICE DESC LIMIT 1")) {
            ResultSet resultSet = statement.executeQuery();
//      resultSet.next();
            return resultSet;
        }
        catch (SQLException throwable) {
            throwable.printStackTrace();
            return null;
        }
   }

}
