package com.pfseven.eshop.database;

import com.pfseven.eshop.model.CategoryID;
import com.pfseven.eshop.model.PaymentMethod;

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

    public int convertCategoryIDToInt(CategoryID id) {
        switch (id) {
            case B2C:
                return 1;
            case B2B:
                return 2;
            case B2G:
                return 3;
            default:
                return 0;
        }
    }

    public int convertPaymentMethodToInt(PaymentMethod paymentMethod) {
        switch (paymentMethod) {
            case WIRE_TRANSFER:
                return 1;
            case CREDIT_CARD:
                return 2;
            case CASH:
                return 3;
            default:
                return 0;
        }
    }
    public Integer totalNumberOfOrdersForCustomer(Integer customerID) throws SQLException {

      PreparedStatement statement = this.connection.prepareStatement("SELECT COUNT(CUSTOMER_ID) FROM ORDERS WHERE (CUSTOMER_ID = ?)");
      statement.setString(1, String.valueOf(customerID));
      ResultSet resultSet = statement.executeQuery();
      resultSet.next();
      //logger.info("{} {} has placed {} orders.",customer.getFirstName(),customer.getLastName(),resultSet.toString());

      return resultSet.getInt(1);
    }

    public BigDecimal totalCostOfPurchases(Integer customerID) throws SQLException {

        PreparedStatement statement = this.connection.prepareStatement("SELECT SUM(COST) FROM ORDERS WHERE /*HAVING*/ (CUSTOMER_ID = ?)");
        statement.setString(1, String.valueOf(customerID));
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();

        return resultSet.getBigDecimal(1);

        //logger.info("Total cost of purchases is: {}",resultSet.toString());
    }
    public Integer totalNumberOfOrdersPerCategory(CategoryID categoryID) throws SQLException {

        PreparedStatement statement = this.connection.prepareStatement("SELECT COUNT(ORDERS.CUSTOMER_ID) FROM ORDERS JOIN CUSTOMER on ORDERS.CUSTOMER_ID = CUSTOMER.CUSTOMER_ID WHERE (CATEGORY_ID = ?)");
        statement.setInt(1, convertCategoryIDToInt(categoryID));
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        return resultSet.getInt(1);
        // logger.info("There have been {} {} orders .",resultSet.toString(),categoryID);
    }
    public BigDecimal totalCostOfOrdersPerCategory(CategoryID categoryID) throws SQLException {
      PreparedStatement statement = this.connection.prepareStatement("SELECT SUM(ORDERS.COST) FROM ORDERS JOIN CUSTOMER on ORDERS.CUSTOMER_ID = CUSTOMER.CUSTOMER_ID WHERE (CATEGORY_ID = ?)");
      statement.setInt(1, convertCategoryIDToInt(categoryID));
      ResultSet resultSet = statement.executeQuery();
      resultSet.next();
      return  resultSet.getBigDecimal(1);
      //logger.info("Total cost of {} orders is: {}.",categoryID,resultSet.toString());
   }

       public Integer totalNumberOfOrdersPerPaymentMethod(PaymentMethod paymentMethod) throws SQLException {

           PreparedStatement statement = this.connection.prepareStatement("SELECT COUNT(*) FROM ORDERS WHERE PAYMENT_METHOD_ID = ?");
           statement.setInt(1, convertPaymentMethodToInt(paymentMethod));
           ResultSet resultSet = statement.executeQuery();
           resultSet.next();
           return resultSet.getInt(1);
           //logger.info("There have been {} orders via {}.", resultSet.toString(), paymentMethod);
       }
       public BigDecimal totalCostOfOrdersPerPaymentMethod(PaymentMethod paymentMethod) throws SQLException {
           PreparedStatement statement = this.connection.prepareStatement("SELECT SUM(COST) FROM ORDERS WHERE PAYMENT_METHOD_ID = ?");
           statement.setInt(1, convertPaymentMethodToInt(paymentMethod));
           ResultSet resultSet = statement.executeQuery();
           resultSet.next();
           return resultSet.getBigDecimal(1);
           //logger.info("Total cost of {} orders is: {}.", paymentMethod, resultSet.toString());
       }


   public void printGoldenCustomer() throws SQLException {

      PreparedStatement statement = this.connection.prepareStatement("SELECT CUSTOMER.CUSTOMER_ID, CUSTOMER.FIRST_NAME, CUSTOMER.LAST_NAME, PRODUCT.PRICE FROM CUSTOMER JOIN ORDERS ON CUSTOMER.CUSTOMER_ID = ORDERS.CUSTOMER_ID JOIN PRODUCTORDER ON ORDERS.ORDER_ID = PRODUCTORDER.ORDER_ID JOIN PRODUCT ON PRODUCTORDER.PRODUCT_ID = PRODUCT.PRODUCT_ID ORDER BY PRODUCT.PRICE DESC LIMIT 1");
      ResultSet resultSet = statement.executeQuery();
      resultSet.next();
      int custID= resultSet.getInt(1);
      BigDecimal price = resultSet.getBigDecimal(4);
       System.out.println("golden boy " + custID );
       System.out.println("price of product " + price);
      //use a logger
   }


}
