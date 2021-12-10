package com.pfseven.eshop.service;

import com.pfseven.eshop.classinterface.CustomerServiceInterface;
import com.pfseven.eshop.database.CustomerRepository;
import com.pfseven.eshop.model.CategoryID;
import com.pfseven.eshop.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CustomerService implements CustomerServiceInterface {
    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Integer newCustomerInput() {
        Customer customer = new Customer();
        Scanner in = new Scanner(System.in);
        Integer customerID = -1;

        logger.info("Enter customer's first name ");
        customer.setFirstName(in.nextLine());

        logger.info("Enter customer's last name ");
        customer.setLastName(in.nextLine());

        label:
        do {
            logger.info("Enter customer's Category ID (B2B, B2C or B2G:)");
            String string = in.nextLine();
            switch (string.toLowerCase()) {
                case "b2b":
                case "B2b":
                case "B2B":
                case "b2B":
                    customer.setCategoryID(CategoryID.B2B);
                    break label;
                case "b2c":
                case "B2C":
                case "b2C":
                case "B2c":
                    customer.setCategoryID(CategoryID.B2C);
                    break label;
                case "b2g":
                case "b2G":
                case "B2G":
                case "B2g":
                    customer.setCategoryID(CategoryID.B2G);
                    break label;
            }
            logger.info("Invalid input! Try again!");
        }while(true);

        logger.info("Ordering for {} {}(categoryID:{})...", customer.getFirstName(), customer.getLastName(), customer.getCategoryID());

        //add customer to db
        customerRepository.insertNewCustomer(customer);
        //get max CustomerID save it into customerID
        customerID = customerRepository.findMaxID();

        return customerID;
    }

    public Integer getCustomerIDfromDB() {
        Integer customerID = -1;
        Scanner scannerInput = new Scanner(System.in);

        logger.info("A) Get customer from customer ID  B) Get customer from name");

        if(scannerInput.nextLine().toLowerCase().equals("a")){
            logger.info("Enter ID");
            try {
                return scannerInput.nextInt();
            }catch (InputMismatchException mismatchException){
                logger.error("Wrong input {}",mismatchException.toString());
                return -1;
            }
        }
        else   //It requires else if, because if we do not place "A", it tells you to choose the customer regardless.
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
//clean