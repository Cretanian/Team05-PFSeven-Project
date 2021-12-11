package com.pfseven.eshop.service;

import com.pfseven.eshop.model.*;

import java.sql.SQLException;

public interface OrderService {

    /* This method gets a paymentMethode and returns an integer
     * based on the provided value. This integer is then stored
     *  inside the database. */
    void newOrderInput(Order newOrder, int customerID) throws SQLException;

    /* This method gets a paymentMethode and returns an integer
     * based on the provided value. This integer indicates the
     * discount that will be applied. */
     int paymentMethodDiscount(PaymentMethod paymentMethod);

    /* This method gets a categoryID and returns an integer
     * based on the provided value. This integer indicates the
     * discount that will be applied. */
     int categoryIDDiscount(CategoryID categoryID);


}
