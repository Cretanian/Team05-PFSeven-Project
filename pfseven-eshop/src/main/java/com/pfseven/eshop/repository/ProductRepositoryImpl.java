package com.pfseven.eshop.repository;

import com.pfseven.eshop.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class ProductRepositoryImpl implements ProductRepository {
   private static final Logger logger = LoggerFactory.getLogger(ProductRepositoryImpl.class);
   private Connection connection;


   public ProductRepositoryImpl(Connection connection){
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
         logger.error("Error: {}",throwable.toString());
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
         product.setProductID(resultSet.getInt(1));
         product.setProductName(resultSet.getString(2));
         product.setPrice(resultSet.getBigDecimal(3));
         product.setStock(resultSet.getInt(4));
      }
      catch (SQLException throwable) {
         logger.error("Error: {}",throwable.toString());
         product.setProductID(-1);
      }
      return product;
   }

   public void insertProductToDb(Product product) {

      try(PreparedStatement statement = this.connection.prepareStatement("INSERT INTO PRODUCT (product_ID,productName,price,stock) VALUES (NULL, ?, ?, ?)")) {
         statement.setString(1, product.getProductName());
         statement.setBigDecimal(2, product.getPrice());
         statement.setInt(3, product.getStock());
         statement.executeUpdate();
      }
      catch (SQLException throwable) {
         logger.error("Error: {}",throwable.toString());
      }
   }

   public void updateProductToDb(Product product) {

      try(PreparedStatement statement = this.connection.prepareStatement("UPDATE PRODUCT SET PRODUCTNAME = ?,PRICE=? ,STOCK=? WHERE product_ID=?")) {
         statement.setString(1, product.getProductName());
         statement.setBigDecimal(2, product.getPrice());
         statement.setInt(3, product.getStock());
         statement.setInt(4,product.getProductID());
         statement.executeUpdate();
      }
      catch (SQLException throwable) {
         logger.error("Error: {}",throwable.toString());
      }
   }
}
