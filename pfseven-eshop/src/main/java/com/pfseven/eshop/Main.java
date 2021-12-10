package com.pfseven.eshop;

import com.pfseven.eshop.database.*;
import com.pfseven.eshop.model.CategoryID;
import com.pfseven.eshop.model.Customer;
import com.pfseven.eshop.model.Order;
import com.pfseven.eshop.service.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(DatabasePF7Project.class);

    public static void main(String[] args) throws SQLException {
       DatabasePF7Project controller = new DatabasePF7Project();
       controller.startServer();
       controller.createDBConnection();
       controller.initializeDB();


        ProductRepository productRepository = new ProductRepository(controller.getDBConnection());
        ProductService productService = new ProductService(productRepository);

        CustomerRepository customerRepository = new CustomerRepository(controller.getDBConnection());
        CustomerService customerService = new CustomerService(customerRepository);

        OrderRepository orderRepository = new OrderRepository(controller.getDBConnection());
        OrderService newOrderInput = new OrderService(orderRepository, productRepository, customerRepository);


        ReportsRepository reportsRepository = new ReportsRepository(controller.getDBConnection());
        ReportsService reportsService = new ReportsService(reportsRepository);

        logger.info("Hello admin! Select action:");
        String userInput = "";
        Scanner scannerInput = new Scanner(System.in);

        while (!userInput.equals("4")) {
            logger.info("Choose one number of the following categories!");
            logger.info("1) Place order  2) Edit Products  3) Get Reports  4) Terminate");
            userInput = scannerInput.nextLine();
            switch (userInput) {
                case "1":
                    logger.info("Starting order!");
                    placeOrder(newOrderInput,customerService);
                    break;
                case "2":
                    logger.info("Editing product!");
                    chooseProductAction(productService);
                    break;
                case "3":
                    logger.info("Getting reports!");
                    getReports(reportsService,customerService);
                    //method
                    break;
                case "4":
                    logger.info("Later!!!");
                    System.exit(0);
                default:
                    logger.info("Wrong input.. Try again..");
            }
        }


        scannerInput.close();
        controller.closeDBConnection(controller.getDBConnection());
        controller.closeServer();
    }

    private static void chooseProductAction(ProductService productService) throws SQLException {
        Scanner scannerInput = new Scanner(System.in);
        String userInput = "";


        while (!userInput.equals("C")) {
            logger.info("A) Add new product  B) Edit existing product C) Kill me plz");
            userInput = scannerInput.nextLine();
            switch (userInput) {                            //check statement later
                case "A":
                    productService.newProductInput();
                    break;
                case "B":
                    productService.editProduct();
                    break;
                case "C":
                    logger.info("Thank you kind sir!");
                    break;
                default:
                    logger.info("Wrong input.. Try again..");
            }
        }
    }

    private static Integer getCustomerID(CustomerService customerService) throws SQLException {
        Scanner scannerInput = new Scanner(System.in);
        String userInput = "";
        //Change name of customerID!!
        Integer customerID = -1;

        while (customerID == -1) {
            logger.info("Select customer:");
            logger.info("A) Add new customer  B) Get existing customer C) Kill me plz");
            userInput = scannerInput.nextLine();
            switch (userInput) {
                case "A":
                    customerID = customerService.newCustomerInput();
                    break;
                case "B":
                    customerID = customerService.getCustomerIDfromDB();
                    break;
                case "C":
                    logger.info("Thank you kind sir!");
                    customerID = -2;
                    break;
                default:
                    logger.info("Wrong input.. Try again..");
            }
        }
        return customerID;
    }

    private static void placeOrder(OrderService orderService, CustomerService customerService) throws SQLException {

        Order newOrder = new Order();

        Integer customerID = getCustomerID(customerService);

        //if (newOrder.getPending() = )

        if (customerID != -2) {
            newOrder.setCustomerID(customerID);
            orderService.newOrderInput(newOrder,customerID);
        }
    }

    private static void getReports (ReportsService reportsService, CustomerService customerService) throws SQLException {

            Scanner scannerInput = new Scanner(System.in);
            String userInput = "";

            while (!userInput.equals("E")) {
                logger.info("A) Get total number and cost of purchases for a particular customer" +
                        "  B) Get total number and cost of purchases per customer category" +
                        "  C) Get total number and cost of purchases per payment method" +
                        "  D) Get the customer(s) who purchased the most expensive product and how many times" +
                        "  E) Terminate");

                userInput = scannerInput.nextLine();
                switch (userInput) {                            //check statement later
                    case "A":
                        getFirstReport(customerService,reportsService);
                        break;
                    case "B":
                        getSecondReport(reportsService);
                        break;
                    case "C":
                        getThirdReport (reportsService);
                        break;
                    case "D":
                        getFourthReport (reportsService);
                        break;
                    case "E":
                        break;
                    default:
                        logger.info("Wrong input.. Try again..");
                }
            }
    }
    private static void getFirstReport (CustomerService customerService, ReportsService reportsService) throws SQLException {

        Integer custID = customerService.getCustomerIDfromDB();

        reportsService.getNumberAndCostOfPurchasesForCustomer(custID);

        //Integer customerID = getCustomerID(customerService);
    }

    private static void getSecondReport (ReportsService reportsService) throws SQLException {
        reportsService.getNumberAndCostOfPurchasesForCustomerCategory();
    }

    private static void getThirdReport (ReportsService reportsService) throws SQLException {
        reportsService.getNumberAndCostOfPurchasesPerPaymentMethod();
    }

    private static void getFourthReport (ReportsService reportsService) throws SQLException {
        reportsService.getCustomerWithTheMostExpensiveItem();
    }
}
