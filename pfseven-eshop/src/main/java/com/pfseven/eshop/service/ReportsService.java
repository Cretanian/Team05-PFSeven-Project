package com.pfseven.eshop.service;

public interface ReportsService {

    void getNumberAndCostOfPurchasesForCustomer(Integer customerID);

    void getNumberAndCostOfPurchasesForCustomerCategory();

    void getNumberAndCostOfPurchasesPerPaymentMethod();

    void getGoldenCustomer();
}
