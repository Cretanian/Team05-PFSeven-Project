package com.pfseven.eshop.repository;

import com.pfseven.eshop.model.Order;
import com.pfseven.eshop.model.PaymentMethod;


import java.sql.SQLException;

public interface OrderRepository {

     /* This method gets a paymentMethode and returns an integer
      * based on the provided value. This integer is then stored
      *  inside the database. */
     int convertPaymentMethodToInt(PaymentMethod paymentMethod);
     
     /* This method inserts an Order in the database using
      * the created order variable that is passed */
     void saveOrderToDB(Order order) throws SQLException;

}
