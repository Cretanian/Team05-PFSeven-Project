package com.pfseven.eshop;

import com.pfseven.eshop.repository.*;
import com.pfseven.eshop.model.Order;
import com.pfseven.eshop.service.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseInit.class);

    public static void main(String[] args) {
       DatabaseInit controller = new DatabaseInit();
       controller.startServer();
       controller.createDBConnection();
       controller.initializeDB();

       ProductRepositoryImpl productRepositoryImpl = new ProductRepositoryImpl(controller.getDBConnection());
       ProductServiceImpl productServiceImpl = new ProductServiceImpl(productRepositoryImpl);

       CustomerRepositoryImpl customerRepositoryImpl = new CustomerRepositoryImpl(controller.getDBConnection());
       CustomerServiceImpl customerServiceImpl = new CustomerServiceImpl(customerRepositoryImpl);


       OrderItemRepositoryImpl orderItemRepositoryImpl = new OrderItemRepositoryImpl(controller.getDBConnection());
       OrderRepositoryImpl orderRepositoryImpl = new OrderRepositoryImpl(controller.getDBConnection());
       OrderServiceImpl newOrderInput = new OrderServiceImpl(orderRepositoryImpl, productRepositoryImpl, customerRepositoryImpl, orderItemRepositoryImpl);


       ReportsRepositoryImpl reportsRepositoryImpl = new ReportsRepositoryImpl(controller.getDBConnection());
       ReportsServiceImpl reportsServiceImpl = new ReportsServiceImpl(reportsRepositoryImpl);

        logger.info("Hello admin! Select action:");
        String userInput = "";
        Scanner scannerInput = new Scanner(System.in);

        while (!userInput.equals("4")) {
            logger.info("Select one of the following actions:");
            logger.info("1) Place order  2) Edit Products  3) Get Reports  4) Terminate");
            userInput = scannerInput.nextLine();
            switch (userInput) {
                case "1":
                    logger.info("Starting order...");
                    placeOrder(newOrderInput, customerServiceImpl);
                    break;
                case "2":
                    logger.info("Editing product...");
                    chooseProductAction(productServiceImpl);
                    break;
                case "3":
                    logger.info("Getting reports...");
                    getReports(reportsServiceImpl, customerServiceImpl);
                    break;
                case "4":
                    logger.info("Terminated!");
                    System.exit(0);
                default:
                    logger.info("Invalid input! Try again!");
            }
        }

        scannerInput.close();
        controller.closeDBConnection(controller.getDBConnection());
        controller.closeServer();
    }


    private static void chooseProductAction(ProductServiceImpl productServiceImpl) {
        Scanner scannerInput = new Scanner(System.in);
        String userInput = "";


        while (!userInput.equalsIgnoreCase("c")) {
            logger.info("A) Add new product  B) Edit existing product C) Back");
            userInput = scannerInput.nextLine();
            switch (userInput.toLowerCase()) {
                case "a":
                case "A":
                    productServiceImpl.newProductInput();
                    break;
                case "b":
                case "B":
                    productServiceImpl.editProduct();
                    break;
                case "c":
                case "C":
                    logger.info("...");
                    break;
                default:
                    logger.info("Invalid input! Try again!");
            }
        }
    }
    private static Integer getCustomerID(CustomerServiceImpl customerServiceImpl) {
        Scanner scannerInput = new Scanner(System.in);
        String userInput;
        //Change name of customerID!!
        Integer customerID = -1;

        while (customerID == -1) {
            logger.info("Select customer:");
            logger.info("A) Add new customer  B) Get existing customer C) Back");
            userInput = scannerInput.nextLine();
            switch (userInput.toLowerCase()) {
                case "a":
                    customerID = customerServiceImpl.newCustomerInput();
                    break;
                case "b":
                    customerID = customerServiceImpl.getCustomerIDfromDB();
                    break;
                case "c":
                    logger.info("...");
                    customerID = -2;
                    break;
                default:
                    logger.info("Invalid input! Try again!");
            }
        }
        return customerID;
    }
    private static void placeOrder(OrderServiceImpl orderServiceImpl, CustomerServiceImpl customerServiceImpl) {

        Order newOrder = new Order();

        Integer customerID = getCustomerID(customerServiceImpl);

        if (customerID > 0) {
            newOrder.setCustomerID(customerID);
            orderServiceImpl.newOrderInput(newOrder,customerID);
        }
    }
    private static void getReports (ReportsServiceImpl reportsServiceImpl, CustomerServiceImpl customerServiceImpl)  {

            Scanner scannerInput = new Scanner(System.in);
            String userInput = "";

            while (!userInput.equalsIgnoreCase("e")) {
                logger.info("A) Get total number and cost of purchases for a particular customer" +
                        "  B) Get total number and cost of purchases per customer category" +
                        "  C) Get total number and cost of purchases per payment method" +
                        "  D) Get the customer(s) who purchased the most expensive product and how many times" +
                        "  E) Back!");

                userInput = scannerInput.nextLine();
                switch (userInput.toLowerCase()) {                            //check statement later
                    case "a":
                        getFirstReport(customerServiceImpl, reportsServiceImpl);
                        break;
                    case "b":
                        getSecondReport(reportsServiceImpl);
                        break;
                    case "c":
                        getThirdReport (reportsServiceImpl);
                        break;
                    case "d":
                        getFourthReport (reportsServiceImpl);
                        break;
                    case "e":
                        break;
                    default:
                        logger.info("Invalid input! Try again!");
                }
            }
    }
    private static void getFirstReport (CustomerServiceImpl customerServiceImpl, ReportsServiceImpl reportsServiceImpl) {
        Integer custID = customerServiceImpl.getCustomerIDfromDB();
        reportsServiceImpl.getNumberAndCostOfPurchasesForCustomer(custID);
    }
    private static void getSecondReport (ReportsServiceImpl reportsServiceImpl) {
        reportsServiceImpl.getNumberAndCostOfPurchasesForCustomerCategory();
    }
    private static void getThirdReport (ReportsServiceImpl reportsServiceImpl) {
        reportsServiceImpl.getNumberAndCostOfPurchasesPerPaymentMethod();
    }
    private static void getFourthReport (ReportsServiceImpl reportsServiceImpl) {
        reportsServiceImpl.getGoldenCustomer();
    }

}
