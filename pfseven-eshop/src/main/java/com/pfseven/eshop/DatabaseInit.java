package com.pfseven.eshop;

import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class DatabaseInit {

   String firstName = null;
   String lastName = null;

   private static final Logger logger = LoggerFactory.getLogger(DatabaseInit.class);

//   private static final String DB_CONNECTION_URL_FILE_MODE = "jdbc:h2:C:/Users/TURBOX/IdeaProjects/Team05-PFSeven-Project/databasePF7Shop";
//   private static final String DB_USERNAME = "admin";
//   private static final String DB_PASSWORD = "admin123";

   private static final String DB_CONNECTION_URL_MEMORY_MODE = "jdbc:h2:mem:sample";
   private static final String DB_USERNAME = "sa";
   private static final String DB_PASSWORD = "";

   private static Server server;
   private static Connection connection;

   public void startServer() {
       try {
           server = Server.createTcpServer("-tcpAllowOthers", "-tcpDaemon");
           server.start();
           logger.info("H2 server has started with status '{}'.", server.getStatus());
           org.h2.Driver.load();
           logger.info("H2 JDBC driver server has been successfully loaded.");
       } catch (SQLException e){
           logger.info("H2 JDBC driver server has not been successfully loaded {}", e);
            System.exit(-1);
       }
   }

   public void closeServer() {
      server.stop();
      server.shutdown();
      logger.info("H2 server has been shutdown");
   }

   public void createDBConnection() {
       try {
           this.connection = DriverManager.getConnection(DB_CONNECTION_URL_MEMORY_MODE, DB_USERNAME, DB_PASSWORD);

//      this.connection = DriverManager.getConnection(DB_CONNECTION_URL_FILE_MODE, DB_USERNAME, DB_PASSWORD);
           logger.info("Connection created successfully.");
       } catch (SQLException e){
           logger.info("Connection was not created successfully.");
           System.exit(-1);
       }
   }

   public void closeDBConnection(Connection connection) {
       try {
           connection.close();
           logger.info("Connection closed successfully.");
       }
       catch (SQLException e)
       {
           logger.info("Exception in connection closing.{}", e);
       }
   }

   public Connection getDBConnection()  {
           return this.connection;
   }

   public void initializeDB() {
       try(Statement statement = this.getDBConnection().createStatement())
       {

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
                   " payment_method_id INTEGER not NULL," +
                   " cost NUMERIC(6,2)," +
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

      statement.executeUpdate(sql);

      sql = "INSERT INTO CATEGORY (CATEGORY_ID, CATEGORYNAME) Values (NULL,'B2C'), (NULL,'B2B') , (NULL, 'B2G');" +
                   "INSERT INTO PAYMENTMETHOD (PAYMENT_METHOD_ID, PAYMENT_METHOD) VALUES (NULL,'Wire Transfer'), (NULL,'Credit Card')," +
                   "(NULL,'Cash');";

      statement.executeUpdate(sql);

      sql = "INSERT INTO CUSTOMER (CUSTOMER_ID, FIRST_NAME, LAST_NAME, CATEGORY_ID)" +
              "VALUES (NULL, 'John', 'Johnathan', 2), (NULL, 'Jennifer', 'Tarin', 1), (NULL, 'Alissa', 'Alisson', 1), " +
              "(NULL, 'Lionel', 'Messi', 3);" +
              "INSERT INTO PRODUCT (PRODUCT_ID, PRODUCTNAME, PRICE, STOCK)" +
              "VALUES (NULL, 'Mouse', 35.25, 8), (NULL, 'Monitor', 200.80, 3), (NULL, 'Keyboard', 52, 4)," +
              "(NULL, 'XBOX Controller', 40.50, 2), (NULL, 'Laptop', 1200, 1), (NULL, 'PC', 2650, 2)," +
              "(NULL, 'PS5', 500, 0), (NULL, 'Speakers', 30, 23), (NULL, 'Mousepad', 20, 45)," +
              "(NULL, 'iPhone', 800, 3), (NULL, 'Samsung Galaxy', 801, 5), (NULL, 'Headphones', 80, 12)," +
              "(NULL, 'Ethernet Cable 2m', 3, 120), (NULL, 'HP Switch', 500, 6), (NULL, 'Router', 25.35, 12)," +
              "(NULL, 'GPU 1080Ti', 3500, 2), (NULL, 'RAM 8GB', 60, 5), (NULL, 'CPU i710k', 550.99, 8)," +
              "(NULL, 'Camera', 35.2, 200), (NULL, 'Optical Cable', 25, 20), (NULL, 'VR', 250, 7)," +
              "(NULL, 'HP Replicator', 100, 10), (NULL, 'iPhone Charger', 30, 4), (NULL, 'Air Pods', 160.80, 3);";

      statement.executeUpdate(sql);

       sql = "INSERT INTO ORDERS (ORDER_ID, CUSTOMER_ID, PAYMENT_METHOD_ID, COST)" +
              "VALUES (NULL, '2', '2', 100.30), (NULL, '2', '3', 43.21), (NULL, '2', '1', 32.90), " +
              "(NULL, '3', '2', 34.60), (NULL, '3', '1', 45.99), (NULL, '1', '3', 140.50)," +
              "(NULL, '1', '1',  257.50), (NULL, '1', '2', 300.20), (NULL, '1', '3', 32.30)," +
               "(NULL, '4', '2',  52.38);";

       statement.executeUpdate(sql);

      sql = "INSERT INTO PRODUCTORDER (ORDER_ID, PRODUCT_ID, QUANTITY)" +
              "VALUES (1, 5, 1), (2, 2, 2), (2, 12, 3)," +
              "(3, 24, 2), (3, 22, 5), (3, 16, 7)," +
              "(4, 9, 25), (5, 23, 2), (5, 19, 50)," +
              "(6, 6, 1), (7, 16, 3), (7, 1, 3)," +
              "(7,15,1), (7, 14, 1), (8, 15, 2)," +
              "(8, 12, 5), (8, 20, 18), (9, 10, 2);";

      statement.executeUpdate(sql);

      logger.info("Create tables was successful with productTable.");

       }
       catch (SQLException e)
       {
           logger.info("Connection problem. {}", e);
       }
   }
}
