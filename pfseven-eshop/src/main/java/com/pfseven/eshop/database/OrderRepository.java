package com.pfseven.eshop.database;

import com.pfseven.eshop.model.Order;
import com.pfseven.eshop.model.PaymentMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderRepository {
    private static final Logger logger = LoggerFactory.getLogger(OrderRepository.class);
    private Connection connection;

    public OrderRepository(Connection connection) {
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

}

