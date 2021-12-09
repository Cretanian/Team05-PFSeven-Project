package com.pfseven.eshop.database;

import com.pfseven.eshop.model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderRepository {
    private Connection connection;

    public OrderRepository(Connection connection){
        this.connection = connection;

    }

    public void saveOrderToDB(Order order) throws SQLException {

            PreparedStatement statement = this.connection.prepareStatement("INSERT INTO ORDERS(ORDER_ID,CUSTOMER_ID,PAYMENT_METHOD_ID,PENDING) VALUES(NULL,?,'1',NULL)");
            statement.setInt(1, order.getCustomerID());
            // statement.setInt(2, order.getPaymentMethod());
            //pending

            statement.executeUpdate();

    }

}

