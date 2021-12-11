package com.pfseven.eshop.repository;

import com.pfseven.eshop.model.CategoryID;
import com.pfseven.eshop.model.Customer;

import java.sql.SQLException;

public interface CustomerRepository {

     /* This method gets a CategoryID and returns an integer
      * based on the provided value. This integer is then stored
      *  inside the database. */
     int convertCategoryIDToInt(CategoryID id);

     /* This method gets an integer and returns a CategoryID
      * based on the provided value. */
     CategoryID convertIntToCategoryID(int id);

     /* This method gets a Customer customerID and
      * returns a Customer that is stored inside the database. */
     void insertNewCustomer (Customer newCustomer) throws SQLException;

     /* This method gets an integer customerID and
      * returns a Customer that is stored inside the database. */
     Customer getCustomerFromID(int customerID) throws SQLException;

     /* This method gets an firstName and lastName and
      * returns a Customer that is stored inside the database. */
     Customer getCustomerFromName(String firstName, String lastName) throws SQLException;

     /* This method finds the max value of customer_id
      stored inside the database. */
     int findMaxID() throws SQLException;

}
