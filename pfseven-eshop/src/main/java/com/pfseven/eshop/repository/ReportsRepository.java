package com.pfseven.eshop.repository;

import com.pfseven.eshop.model.CategoryID;
import com.pfseven.eshop.model.PaymentMethod;
import com.pfseven.eshop.model.Product;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface ReportsRepository {

     int convertCategoryIDToInt(CategoryID id);

     int convertPaymentMethodToInt(PaymentMethod paymentMethod);

     Integer totalNumberOfOrdersForCustomer(Integer customerID);

     BigDecimal totalCostOfPurchases(Integer customerID);

     Integer totalNumberOfOrdersPerCategory(CategoryID categoryID);

     BigDecimal totalCostOfOrdersPerCategory(CategoryID categoryID);

     Integer totalNumberOfOrdersPerPaymentMethod(PaymentMethod paymentMethod);

     BigDecimal totalCostOfOrdersPerPaymentMethod(PaymentMethod paymentMethod);

     ResultSet GoldenCustomer();
}
