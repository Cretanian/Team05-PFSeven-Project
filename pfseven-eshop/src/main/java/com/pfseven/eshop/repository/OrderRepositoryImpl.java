package com.pfseven.eshop.repository;

import com.pfseven.eshop.model.Order;
import com.pfseven.eshop.model.PaymentMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRepositoryImpl implements OrderRepository {
    private static final Logger logger = LoggerFactory.getLogger(OrderRepositoryImpl.class);
    private Connection connection;

    public OrderRepositoryImpl(Connection connection) {
        this.connection = connection;
    }
    public int convertPaymentMethodToInt(PaymentMethod paymentMethod) {
        switch (paymentMethod) {
            case CASH:
                return 1;
            case CREDIT_CARD:
                return 2;
            case WIRE_TRANSFER:
                return 3;
            default:
                return 0;
        }
    }

    public void saveOrderToDB(Order order) {

            try(PreparedStatement statement = this.connection.prepareStatement("INSERT INTO ORDERS(ORDER_ID,CUSTOMER_ID,PAYMENT_METHOD_ID,PENDING,COST) VALUES(NULL,?,?,NULL,?)")) {
                statement.setInt(1, order.getCustomerID());
                int paymentMethod = convertPaymentMethodToInt(order.getPaymentMethod());
                statement.setInt(2, paymentMethod);
                statement.setBigDecimal(3, order.getCost());
                statement.executeUpdate();
            }
            catch (SQLException throwable) {
                logger.error("Error: {}",throwable.toString());
            }
    }

    public int findMaxID() {
        int id;

        try(PreparedStatement statement = this.connection.prepareStatement("SELECT MAX(ORDER_ID) FROM ORDERS")) {
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            id = resultSet.getInt(1);
        }
        catch (SQLException throwable) {
            logger.error("Error: {}",throwable.toString());
            id = -1;
        }
        return id;
    }

}

