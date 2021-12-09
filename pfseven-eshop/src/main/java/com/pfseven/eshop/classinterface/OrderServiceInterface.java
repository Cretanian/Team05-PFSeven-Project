package com.pfseven.eshop.classinterface;

import com.pfseven.eshop.database.CustomerRepository;
import com.pfseven.eshop.database.OrderRepository;
import com.pfseven.eshop.database.ProductRepository;
import com.pfseven.eshop.model.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public interface OrderServiceInterface {

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
