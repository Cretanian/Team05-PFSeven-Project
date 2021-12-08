package com.pfseven.eshop.service;

import com.pfseven.eshop.classinterface.CustomerServiceInterface;
import com.pfseven.eshop.model.CategoryID;
import com.pfseven.eshop.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Scanner;

public class CustomerService implements CustomerServiceInterface {
    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    public Integer newCustomerInput(){
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

        //add to customer to db
        //get max CustomerID save it into customerID

        return customerID;
    }

    public Integer getCustomerIDfromDB(){
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

            return customerID;
        }
    }

}
