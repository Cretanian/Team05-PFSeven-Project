package com.pfseven.eshop.classinterface;


import java.sql.SQLException;

public interface CustomerServiceInterface {

    /* ADD here explanation*/
     Integer newCustomerInput() throws SQLException;

    /* ADD here explanation*/
     Integer getCustomerIDfromDB() throws SQLException;
}
