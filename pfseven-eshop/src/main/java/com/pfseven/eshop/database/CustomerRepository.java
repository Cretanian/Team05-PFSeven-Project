package com.pfseven.eshop.database;

import com.pfseven.eshop.classinterface.CustomerRepositoryInterface;
import com.pfseven.eshop.model.CategoryID;
import com.pfseven.eshop.model.Customer;
import java.sql.*;

public class CustomerRepository implements CustomerRepositoryInterface {

    private Connection connection;

    public CustomerRepository(Connection connection){
        this.connection = connection;
    }


    public int convertCategoryIDToInt(CategoryID id) {
        switch (id) {
            case B2C:
                return 1;
            case B2B:
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
                return CategoryID.B2C;
            case 2:
                return CategoryID.B2B;
            case 3:
                return CategoryID.B2G;
            default:
                return null;
        }
    }

    public void insertNewCustomer (Customer newCustomer)  {
        try(PreparedStatement statement = this.connection.prepareStatement("INSERT INTO CUSTOMER(CUSTOMER_ID,FIRST_NAME,LAST_NAME,CATEGORY_ID) VALUES(NULL,?,?,?)");) {

            statement.setString(1, newCustomer.getFirstName());
            statement.setString(2, newCustomer.getLastName());
            int categoryID = convertCategoryIDToInt(newCustomer.getCategoryID());
            statement.setString(3, String.valueOf(categoryID));
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Customer getCustomerFromID(int customerID)  {
        Customer existedCustomer = new Customer();
        try(PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM CUSTOMER WHERE(CUSTOMER_ID = ?)");) {

            statement.setString(1, String.valueOf(customerID));
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            existedCustomer.setFirstName(resultSet.getString("first_name"));
            existedCustomer.setLastName(resultSet.getString("last_name"));

            CategoryID categoryID = convertIntToCategoryID(resultSet.getInt("category_id"));
            existedCustomer.setCategoryID(categoryID);

            existedCustomer.setCustomerID(customerID);
        }catch (SQLException e){
            e.printStackTrace();
            existedCustomer.setCustomerID(-1);
        }
        return existedCustomer;
    }

    public Customer getCustomerFromName(String firstName, String lastName)  {
        Customer existedCustomer = new Customer();
        try(PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM CUSTOMER WHERE(FIRST_NAME = ? AND LAST_NAME = ?)");) {

            statement.setString(1, String.valueOf(firstName));
            statement.setString(2, String.valueOf(lastName));
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            existedCustomer.setFirstName(resultSet.getString("first_name"));
            existedCustomer.setLastName(resultSet.getString("last_name"));
            existedCustomer.setCustomerID(resultSet.getInt("customer_id"));

            CategoryID categoryID = convertIntToCategoryID(resultSet.getInt("category_id"));
            existedCustomer.setCategoryID(categoryID);

           // System.out.println("customer's id is " + existedCustomer.getCustomerID());
        }catch (SQLException e){
            e.printStackTrace();
            existedCustomer.setCustomerID(-1);
        }
        return existedCustomer;

    }

    public int findMaxID()  {
        try(PreparedStatement statement = this.connection.prepareStatement("SELECT MAX(CUSTOMER_ID) FROM CUSTOMER");) {

            ResultSet resultSet = statement.executeQuery();

            resultSet.next();
            int id = resultSet.getInt(1);
            return id;
        }catch (SQLException e){
            e.printStackTrace();
            return -1;
        }

    }

}
