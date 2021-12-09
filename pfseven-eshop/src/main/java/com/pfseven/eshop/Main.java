package com.pfseven.eshop;

import com.pfseven.eshop.database.*;
import com.pfseven.eshop.model.Order;
import com.pfseven.eshop.service.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
                                                            //ADD METHOD
                    break;
                case "4":
                    logger.info("Later!!!");
                    System.exit(0);
                default:
                    logger.info("Wrong input.. Try again..");
            }
        }
        scannerInput.close();
    }

    private static void chooseProductAction(ProductService productService) throws SQLException {
        Scanner scannerInput = new Scanner(System.in);
        String userInput = "";

        while (!userInput.equals("C")) {
            logger.info("A) Add new product  B) Edit existing product C) Go back to menu");
            userInput = scannerInput.nextLine();
            switch (userInput) {
                case "A":
                case "a":
                    productService.newProductInput();
                    break;
                case "B":
                case "b":
                    productService.editProduct();
                    break;
                case "C":
                case "c":
                    logger.info("Bey!");
                    break;
                default:
                    logger.info("Wrong input.. Try again..");
            }
        }
    }

    private static Integer getCustomerID(CustomerService customerService) throws SQLException {
        Scanner scannerInput = new Scanner(System.in);
        String userInput = "";
        Integer customerIDHelper = -1;

        while (customerIDHelper == -1) {
            logger.info("Select customer:");
            logger.info("A) Add new customer  B) Get existing customer C) Proceed");
            userInput = scannerInput.nextLine();
            switch (userInput) {
                case "A":
                    customerIDHelper = customerService.newCustomerInput();
                    break;
                case "B":
                    customerIDHelper = customerService.getCustomerIDfromDB();
                    break;
                case "C":
                    logger.info("Next step:");
                    customerIDHelper = -2;
                    break;
                default:
                    logger.info("Wrong input.. Try again..");
            }
        }
        return customerIDHelper;
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

}
