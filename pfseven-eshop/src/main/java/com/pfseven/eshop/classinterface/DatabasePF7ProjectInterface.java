package com.pfseven.eshop.classinterface;

import java.sql.Connection;
import java.sql.SQLException;

public interface DatabasePF7ProjectInterface {

     /* This method starts the server */
     void startServer() throws SQLException;

     /* This method closes the server */
     void closeServer();

     /* This method creates a connection with the server */
     void createDBConnection() throws SQLException;

     /* This method closes the connection with the server */
     void closeDBConnection(Connection connection) throws SQLException;

     /* This method returns the opened connection with the server */
     Connection getDBConnection() throws SQLException;

     /* This method initializes the database  */
     void initializeDB() throws SQLException;

}
