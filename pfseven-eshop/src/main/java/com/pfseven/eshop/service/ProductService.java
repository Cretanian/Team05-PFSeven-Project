package com.pfseven.eshop.service;

import java.sql.SQLException;

public interface ProductService {

    /* This method creates a new product
    * and then add this product to the database.*/
    void newProductInput() throws SQLException;

    /* This method edits an existing product
    * and then updates this product to the database.*/
    void editProduct() throws SQLException;
}
