package com.pfseven.eshop.classinterface;

import com.pfseven.eshop.model.Order;

import java.sql.SQLException;

public interface OrderServiceInterface {

    /* ADD here explanation*/
    void newOrderInput(Order newOrder, int customerID) throws SQLException;



     //public void save(Order theOrder);

}
