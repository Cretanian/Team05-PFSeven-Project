package com.pfseven.eshop.repository;

import com.pfseven.eshop.model.CategoryID;
import com.pfseven.eshop.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class CustomerRepositoryImpl implements CustomerRepository {
    private static final Logger logger = LoggerFactory.getLogger(CustomerRepositoryImpl.class);
    private Connection connection;

    public CustomerRepositoryImpl(Connection connection){
        this.connection = connection;
    }


    public int convertCategoryIDToInt(CategoryID id) {
        switch (id) {
            case B2B:
                return 1;
            case B2C:
                return 2;
            case B2G:
                return 3;
            default:
                return 0;
        }
    }

    public CategoryID convertIntToCategoryID(int id) {
        switch (id) {
            case 1:
                return CategoryID.B2B;
            case 2:
                return CategoryID.B2C;
            case 3:
                return CategoryID.B2G;
            default:
                return null;
        }
    }

    public void insertNewCustomer (Customer newCustomer) {

        try(PreparedStatement statement = this.connection.prepareStatement("INSERT INTO CUSTOMER(CUSTOMER_ID,FIRST_NAME,LAST_NAME,CATEGORY_ID) VALUES(NULL,?,?,?)")) {
            statement.setString(1, newCustomer.getFirstName());
            statement.setString(2, newCustomer.getLastName());
            int categoryID = convertCategoryIDToInt(newCustomer.getCategoryID());
            statement.setString(3, String.valueOf(categoryID));
            statement.executeUpdate();
        }
        catch (SQLException throwable) {
           logger.error("Error: {}",throwable.toString());
        }
    }

    public Customer getCustomerFromID(int customerID) {
        Customer existedCustomer = new Customer();

        try(PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM CUSTOMER WHERE(CUSTOMER_ID = ?)")) {
            statement.setString(1, String.valueOf(customerID));
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            existedCustomer.setFirstName(resultSet.getString("first_name"));
            existedCustomer.setLastName(resultSet.getString("last_name"));

            CategoryID categoryID = convertIntToCategoryID(resultSet.getInt("category_id"));
            existedCustomer.setCategoryID(categoryID);

            existedCustomer.setCustomerID(customerID);
        }
        catch (SQLException throwable) {
            logger.error("Error: {}",throwable.toString());
            existedCustomer.setCustomerID(-1);
        }
        return existedCustomer;
    }

    public Customer getCustomerFromName(String firstName, String lastName) {
        Customer existedCustomer = new Customer();

        try(PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM CUSTOMER WHERE(FIRST_NAME = ? AND LAST_NAME = ?)")) {
            statement.setString(1, String.valueOf(firstName));
            statement.setString(2, String.valueOf(lastName));
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            existedCustomer.setFirstName(resultSet.getString("first_name"));
            existedCustomer.setLastName(resultSet.getString("last_name"));
            existedCustomer.setCustomerID(resultSet.getInt("customer_id"));

            CategoryID categoryID = convertIntToCategoryID(resultSet.getInt("category_id"));
            existedCustomer.setCategoryID(categoryID);
        }
        catch (SQLException throwable) {
            logger.error("Error: {}",throwable.toString());
            existedCustomer.setCustomerID(-1);
        }
        return existedCustomer;
    }

    public int findMaxID() {
        int id;

        try(PreparedStatement statement = this.connection.prepareStatement("SELECT MAX(CUSTOMER_ID) FROM CUSTOMER")) {
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            id = resultSet.getInt(1);
        }
        catch (SQLException throwable) {
            logger.error("Error: {}",throwable.toString());
            id = -1;
        }
        return id;
    }

}
