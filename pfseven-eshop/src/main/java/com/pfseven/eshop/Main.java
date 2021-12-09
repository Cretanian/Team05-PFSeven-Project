package com.pfseven.eshop;

import com.pfseven.eshop.database.CustomerRepository;
import com.pfseven.eshop.database.DatabasePF7Project;
import com.pfseven.eshop.database.OrderRepository;
import com.pfseven.eshop.database.ProductRepository;
import com.pfseven.eshop.model.CategoryID;
import com.pfseven.eshop.model.Customer;
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

      // controller.closeServer();
        ProductRepository productRepository = new ProductRepository(controller.getDBConnection());
        ProductService productService = new ProductService(productRepository);

        CustomerRepository customerRepository = new CustomerRepository(controller.getDBConnection());
        CustomerService customerService = new CustomerService(customerRepository);
       // customerService.newCustomerInput();
        OrderRepository orderRepository = new OrderRepository(controller.getDBConnection());
        OrderService newOrderInput = new OrderService(orderRepository, productRepository, customerRepository);
       // newOrderInput.newOrderInput();
       // productService.newProductInput();

        //testing method insertNewCustomer

//        Customer newCustomer = new Customer();
//
//        newCustomer.setFirstName("stathis");
//        newCustomer.setLastName("antonakis");
//        newCustomer.setCategoryID(CategoryID.B2B);
//
//        CustomerRepository tester = new CustomerRepository(controller);
//        tester.insertNewCustomer(newCustomer);

        //end of testing

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

}
