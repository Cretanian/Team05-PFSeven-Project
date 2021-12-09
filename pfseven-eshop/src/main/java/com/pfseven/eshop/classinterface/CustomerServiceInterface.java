package com.pfseven.eshop.classinterface;


import java.sql.SQLException;

public interface CustomerServiceInterface {

    /* This method creates a new Customer and then
     * returns his customer_ID. */
    Integer newCustomerInput() throws SQLException;

    /* This method asks the admin for the customer_id
    * or the first name and last name and then finds
    * and returns the customer_id.  */
    Integer getCustomerIDfromDB() throws SQLException;
}
