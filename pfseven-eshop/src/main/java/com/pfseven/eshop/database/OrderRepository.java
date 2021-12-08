package com.pfseven.eshop.database;

import java.sql.Connection;

public class OrderRepository {
    private Connection connection;
    public OrderRepository(Connection connection){
        this.connection = connection;
    }

}

