package com.pfseven.eshop.classinterface;

import com.pfseven.eshop.model.Order;

import java.sql.SQLException;

public interface OrderServiceInterface {

    /* ADD here explanation*/
     void newOrderInput(Order newOrder) throws SQLException;


}
