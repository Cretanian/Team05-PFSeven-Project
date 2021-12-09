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

}
