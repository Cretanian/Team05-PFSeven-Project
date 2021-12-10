package com.pfseven.eshop.database;

import com.pfseven.eshop.classinterface.ProductRepositoryInterface;
import com.pfseven.eshop.model.Product;

import java.sql.*;

public class ProductRepository implements ProductRepositoryInterface {
   private Connection connection;


   public ProductRepository(Connection connection){
      this.connection = connection;
   }

   public Product getProductFromID (int id) {
      Product product = new Product();

      try( PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM PRODUCT WHERE(product_ID = ?)")) {
         statement.setString(1, String.valueOf(id));
         ResultSet resultSet =  statement.executeQuery();
         resultSet.next();
         product.setProductID(resultSet.getInt(1));
         product.setProductName(resultSet.getString(2));
         product.setPrice(resultSet.getBigDecimal(3));
         product.setStock(resultSet.getInt(4));
      }
      catch (SQLException throwable) {
         throwable.printStackTrace();
         product.setProductID(-1);
      }
     return product;
   }

   public Product getProductFromName (String name) {
      Product product = new Product();

      try(PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM PRODUCT WHERE(PRODUCTNAME= ?)")) {
         statement.setString(1, name);
         ResultSet resultSet =  statement.executeQuery();
         resultSet.next();
         //resultSet.beforeFirst();
         product.setProductID(resultSet.getInt(1));
         product.setProductName(resultSet.getString(2));
         product.setPrice(resultSet.getBigDecimal(3));
         product.setStock(resultSet.getInt(4));
      }
      catch (SQLException throwable) {
         throwable.printStackTrace();
         product.setProductID(-1);
      }
      return product;
   }

   public void insertProductToDb(Product product) {

      try(PreparedStatement statement = this.connection.prepareStatement("INSERT INTO PRODUCT (product_ID,productName,price,stock) VALUES ( NULL, ?, ?, ? ) ")) {
         statement.setString(1, product.getProductName());
         statement.setBigDecimal(2, product.getPrice());
         statement.setInt(3, product.getStock());
         statement.executeUpdate();
      }
      catch (SQLException throwable) {
         throwable.printStackTrace();
      }
   }

   public void updateProductToDb(Product product) {

      try(PreparedStatement statement = this.connection.prepareStatement("UPDATE PRODUCT SET PRODUCTNAME = ?,PRICE=? ,STOCK=?     WHERE product_ID=?")) {
         statement.setString(1, product.getProductName());
         statement.setBigDecimal(2, product.getPrice());
         statement.setInt(3, product.getStock());
         statement.setInt(4,product.getProductID());
         statement.executeUpdate();
      }
      catch (SQLException throwable) {
         throwable.printStackTrace();
      }
   }
}
