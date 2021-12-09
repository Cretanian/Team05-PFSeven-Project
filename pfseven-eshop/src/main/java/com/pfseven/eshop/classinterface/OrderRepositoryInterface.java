package com.pfseven.eshop.classinterface;

import com.pfseven.eshop.model.Order;
import com.pfseven.eshop.model.PaymentMethod;


import java.sql.SQLException;

public interface OrderRepositoryInterface {

     /* This method gets a paymentMethode and returns an integer
      * based on the provided value. This integer is then stored
      *  inside the database. */
     int convertPaymentMethodToInt(PaymentMethod paymentMethod);

     /* This method gets an integer and returns a paymentMethode
      * based on the provided value. */
     PaymentMethod convertIntToCategoryID(int paymentMethod);

     /* This method inserts an Order in the database using
      * the created order variable that is passed */
     void saveOrderToDB(Order order) throws SQLException;

}
