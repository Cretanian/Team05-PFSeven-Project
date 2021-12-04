package com.pfseven.eshop.databases;

import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabasePF7Project {

   private static final Logger logger = LoggerFactory.getLogger(DatabasePF7Project.class);

   private static final String DB_CONNECTION_URL_MEMORY_MODE = "jdbc:h2:mem:databaseTest";
   private static final String DB_USERNAME = "admin";
   private static final String DB_PASSWORD = "123";

   private static Server server;

   public static void main(String[] args) throws Exception{

      server = Server.createTcpServer("-tcpAllowOthers", "-tcpDaemon");
      server.start();
      logger.info("H2 server has started with status '{}'.", server.getStatus());

      org.h2.Driver.load();
      logger.info("H2 JDBC driver server has been successfully loaded.");

      Connection connect = DriverManager.getConnection(DB_CONNECTION_URL_MEMORY_MODE, DB_USERNAME, DB_PASSWORD);
      Statement statement = connect.createStatement();

      String sql = "CREATE TABLE Category (" +
              " category_ID INTEGER not NULL AUTO_INCREMENT," +
              " categoryName VARCHAR(25), " +
              " PRIMARY KEY (category_ID));"
              +
              " CREATE TABLE Product (" +
              " product_ID INTEGER not NULL AUTO_INCREMENT," +
              " productName VARCHAR(50)," +
              " price NUMERIC(6,2)," +
              " stock INTEGER," +
              " PRIMARY KEY (product_ID));"
              +
              " CREATE TABLE PaymentMethod (" +
              " payment_method_ID INTEGER not NULL AUTO_INCREMENT," +
              " payment_method VARCHAR(30), " +
              " PRIMARY KEY (payment_method_ID));"
              +
              " CREATE TABLE Customer (" +
              " customer_ID INTEGER not NULL AUTO_INCREMENT," +
              " first_name varchar(30)," +
              " last_name varchar(40)," +
              " category_ID INTEGER not NULL ," +
              " PRIMARY KEY (customer_ID)," +
              " CONSTRAINT FK_CATEGORY FOREIGN KEY(category_ID) REFERENCES Category(category_ID)" +
              " ON DELETE NO ACTION ON UPDATE NO ACTION);"
              +
              " CREATE TABLE Orders (" +
              " order_ID INTEGER not NULL AUTO_INCREMENT," +
              " customer_ID INTEGER not NULL," +
              " payment_method_id INTEGER not NULL, " +
              " PRIMARY KEY (order_ID)," +
              " CONSTRAINT FK_CUSTOMER FOREIGN KEY(customer_ID) REFERENCES Customer(customer_ID)" +
              " ON DELETE NO ACTION ON UPDATE NO ACTION," +
              " CONSTRAINT FK_Payment_Method FOREIGN KEY(payment_method_ID) REFERENCES PaymentMethod(payment_method_ID)" +
              " ON DELETE NO ACTION ON UPDATE NO ACTION);"
              +
              " CREATE TABLE ProductOrder (" +
              " order_ID INTEGER not NULL," +
              " product_ID INTEGER not NULL," +
              " quantity INTEGER," +
              " PRIMARY KEY (order_ID,product_ID)," +
              " CONSTRAINT FK_ORDER FOREIGN KEY(order_ID) REFERENCES Orders(order_ID)" +
              " ON DELETE NO ACTION ON UPDATE NO ACTION," +
              " CONSTRAINT FK_Product FOREIGN KEY(product_ID) REFERENCES Product(product_ID)" +
              " ON DELETE NO ACTION ON UPDATE NO ACTION);"
              ;

      int databaseCreation = statement.executeUpdate(sql);

      logger.info("Create tables was successful with productTable {}.", databaseCreation);

//      sql = "INSERT INTO Product VALUES (NULL,'Keyboard',20,5)";
//
//      int productRows = statement.executeUpdate(sql);
//      logger.info("Update table Product was successful with {} row(s) affected.", productRows);
//
//      sql = "INSERT INTO Category VALUES (NULL,'TestCategory')";
//
//      productRows = statement.executeUpdate(sql);
//      logger.info("Update table Product was successful with {} row(s) affected.", productRows);
//
//      sql = "SELECT * FROM Product,Category";
//
//      ResultSet productData = statement.executeQuery(sql);
//      while (productData.next()) {
//         logger.info("Category_ID: {}, Category Name: {}",
//                 productData.getString("category_ID"),
//                 productData.getString("categoryName"));
//      }
//      while (productData.next()) {
//         logger.info("ID: {}, Product Name:{}, Price:{}, Stock:{}",
//                 productData.getString("product_ID"),
//                 productData.getString("productName"),
//                 productData.getString("price"),
//                 productData.getString("stock"));
//      }

      server.stop();
      server.shutdown();
      logger.info("H2 server has been shutdown");

   }
}
