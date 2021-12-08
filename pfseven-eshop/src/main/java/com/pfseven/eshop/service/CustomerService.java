package com.pfseven.eshop.service;

import com.pfseven.eshop.classinterface.CustomerServiceInterface;
import com.pfseven.eshop.database.CustomerRepository;
import com.pfseven.eshop.database.ProductRepository;
import com.pfseven.eshop.model.CategoryID;
import com.pfseven.eshop.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.Scanner;

public class CustomerService implements CustomerServiceInterface {
    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Integer newCustomerInput() throws SQLException {
        Customer customer = new Customer();
        Scanner in = new Scanner(System.in);
        Integer customerID = -1;

        logger.info("Enter user first name ");
        customer.setFirstName(in.nextLine());
        logger.info("Enter user last name ");
        customer.setLastName(in.nextLine());
        label:
        do {
            logger.info("Enter user Category ID: one of the following B2B, B2C, B2G ");
            String string = in.nextLine();
            switch (string) {
                case "B2B":
                case "b2b":                         //to do cases
                    customer.setCategoryID(CategoryID.B2B);
                    break label;
                case "B2C":
                case "b2c":
                    customer.setCategoryID(CategoryID.B2C);
                    break label;
                case "B2G":
                case "b2g":
                    customer.setCategoryID(CategoryID.B2G);
                    break label;
            }
            logger.info("WRONG ... Try again...");
        }while(true);

        logger.info("Customer name {} {} , with categoryID {}", customer.getFirstName(), customer.getLastName(), customer.getCategoryID());

        //add customer to db
        customerRepository.insertNewCustomer(customer);
        //get max CustomerID save it into customerID
        customerID = customerRepository.findMaxID();

        return customerID;
    }

    public Integer getCustomerIDfromDB() throws SQLException {
        Integer customerID = -1;
        Scanner scannerInput = new Scanner(System.in);


        logger.info("A) Get customer from customer ID  B) Get customer from name");

        if(scannerInput.nextLine().equals("A")){
            logger.info("Enter ID");
            return scannerInput.nextInt();
        }
        else
        {
            logger.info("Enter first name");
            String userFirstname = scannerInput.nextLine();
            logger.info("Enter last name");
            String userLastname = scannerInput.nextLine();

            //request customerID from database
            customerID = customerRepository.getCustomerFromName(userFirstname, userLastname).getCustomerID();

            return customerID;
        }
    }

}
