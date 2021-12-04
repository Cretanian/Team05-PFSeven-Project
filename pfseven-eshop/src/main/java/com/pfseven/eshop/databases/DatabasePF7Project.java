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

   private static final String DB_CONNECTION_URL_FILE_MODE = "jdbc:h2:file:./databaseTest";
   private static final String DB_USERNAME = "admin";
   private static final String DB_PASSWORD = "123";

   private static Server server;

   public static void main(String[] args) throws Exception{

      server = Server.createTcpServer("-tcpAllowOthers", "-tcpDaemon");
      server.start();
      logger.info("H2 server has started with status '{}'.", server.getStatus());

      org.h2.Driver.load();
      logger.info("H2 JDBC driver server has been successfully loaded.");

      Connection connect = DriverManager.getConnection(DB_CONNECTION_URL_FILE_MODE, DB_USERNAME, DB_PASSWORD);
      Statement statement = connect.createStatement();

      String sql = "CREATE TABLE Product (" +
              " product_ID INTEGER not NULL AUTO_INCREMENT, " +
              " productName VARCHAR(50), " +
              " price NUMERIC(6,2), " +
              " stock INTEGER, " +
              " PRIMARY KEY (product_id))";

      int productTable = statement.executeUpdate(sql);

      logger.info("Create table Product was successful with productTable {}.", productTable);

      sql = "INSERT INTO Product VALUES (NULL,'Keyboard',20,5)";

      int productRows = statement.executeUpdate(sql);
      logger.info("Update table Product was successful with {} row(s) affected.", productRows);

      sql = "INSERT INTO Product VALUES (NULL,'Mouse',29.25,7)";

      productRows = statement.executeUpdate(sql);
      logger.info("Update table Product was successful with {} row(s) affected.", productRows);

      sql = "SELECT * FROM Product";

      ResultSet productData = statement.executeQuery(sql);

      while (productData.next()) {
         logger.info("ID: {}, Product Name:{}, Price:{}, Stock:{}",
                 productData.getString("product_ID"),
                 productData.getString("productName"),
                 productData.getString("price"),
                 productData.getString("stock"));
      }

      server.stop();
      server.shutdown();
      logger.info("H2 server has been shutdown");

   }
}
