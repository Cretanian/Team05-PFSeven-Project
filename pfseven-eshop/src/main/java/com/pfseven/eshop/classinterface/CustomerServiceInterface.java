package com.pfseven.eshop.classinterface;


public interface CustomerServiceInterface {

    /* This method creates a new customer
    and then add this customer to the database.
    This method returns the customer_ID in order
    to continue with the order.    */
     Integer newCustomerInput();

    /* This method requests the customer_ID from
    console. If the customer_ID is not known then
    requests the first name and the last name from
    the console and querying the database to get the
    customer_ID for this specific person.
    This method returns the customer_ID. */
    Integer getCustomerIDfromDB();
}
