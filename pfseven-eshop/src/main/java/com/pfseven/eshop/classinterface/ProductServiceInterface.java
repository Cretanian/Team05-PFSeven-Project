package com.pfseven.eshop.classinterface;

import java.sql.SQLException;

public interface ProductServiceInterface {

    /* This method creates a new product
    * and then add this product to the database.*/
    void newProductInput() throws SQLException;

    /* This method edits an existing product
    * and then updates this product to the database.*/
    void editProduct() throws SQLException;
}
