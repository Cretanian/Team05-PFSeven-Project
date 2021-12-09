package com.pfseven.eshop.classinterface;

import com.pfseven.eshop.model.Product;

import java.sql.SQLException;

public interface ProductRepositoryInterface {

     /* This method uses product_ID to request the database.
     * Create and returns the requested Product. */
     Product getProductFromID (int id) throws SQLException;

     /* This method uses the name of a product to request the
     * database. Create and returns the requested Product. */
     Product getProductFromName (String name) throws SQLException;

     /* This method inserts a Product to the database. */
     void insertProductToDb(Product product) throws SQLException ;

     /* This method updates a Product in the database using
     * the product_id that is stored inside product */
     void updateProductToDb(Product product) throws SQLException ;
}
