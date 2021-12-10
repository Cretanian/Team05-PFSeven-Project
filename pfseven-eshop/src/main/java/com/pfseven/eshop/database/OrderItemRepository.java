package com.pfseven.eshop.database;

import com.pfseven.eshop.classinterface.OrderItemRepositoryInterface;
import com.pfseven.eshop.model.OrderItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderItemRepository implements OrderItemRepositoryInterface {
    private Connection connection;
    private static final Logger logger = LoggerFactory.getLogger(OrderItemRepository.class);

    public OrderItemRepository (Connection connection){
        this.connection = connection;
    }

    public void saveOrderItemToDB(OrderItem orderItem) {
        try (PreparedStatement statement = this.connection.prepareStatement
                ("INSERT INTO PRODUCTORDER(order_id,product_id,quantity) VALUES(?,?,?)")) {
            statement.setInt(1, orderItem.getOrderID());
            statement.setInt(2, orderItem.getProductID());
            statement.setInt(3, orderItem.getTotal());
            statement.executeUpdate();
        } catch (SQLException throwable){
            logger.error("Error: {}", throwable.toString());
        }

    }

}
