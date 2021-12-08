package com.pfseven.eshop;

import com.pfseven.eshop.database.DatabasePF7Project;
import com.pfseven.eshop.database.ProductRepository;
import com.pfseven.eshop.model.Order;
import com.pfseven.eshop.service.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(DatabasePF7Project.class);

    public static void main(String[] args) throws SQLException {

        DatabasePF7Project controller = new DatabasePF7Project();
        controller.startServer();
        controller.createDBConnection();
        controller.initializeDB();

        ProductRepository re = new ProductRepository(controller);
        re.getProductfromID(1);
        CustomerService customerService = new CustomerService();
       // customerService.newCustomerInput();
        OrderService newOrderInput = new OrderService();
       // newOrderInput.newOrderInput();
       // productService.newProductInput();


//        logger.info("Hello admin! Select action:");
//        String userInput = "";
//        Scanner scannerInput = new Scanner(System.in);
//
//        while (!userInput.equals("4")) {
//            logger.info("Choose one number of the following categories!");
//            logger.info("1) Place order  2) Edit Products  3) Get Reports  4) Terminate");
//            userInput = scannerInput.nextLine();
//            switch (userInput) {
//                case "1":
//                    logger.info("Starting order!");
//                    placeOrder();
//                    break;
//                case "2":
//                    logger.info("Editing product!");
//                    chooseProductAction();
//                    break;
//                case "3":
//                    logger.info("Getting reports!");
//                    //method
//                    break;
//                case "4":
//                    logger.info("Later!!!");
//                    System.exit(0);
//                default:
//                    logger.info("Wrong input.. Try again..");
//            }
//        }
//

//        scannerInput.close();

        controller.closeDBConnection(controller.getDBConnection());
        controller.closeServer();
    }

    private static void chooseProductAction(){
        Scanner scannerInput = new Scanner(System.in);
        String userInput = "";
        ProductService productService = new ProductService();

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

    private static Integer getCustomerID(){
        Scanner scannerInput = new Scanner(System.in);
        String userInput = "";
        Integer customerID = -1;
        CustomerService customerService = new CustomerService();
        while (customerID == -1) {
            logger.info("Select customer:");
            logger.info("A) Add new customer  B) Get existing customer C) Kill me plz");
            userInput = scannerInput.nextLine();
            switch (userInput) {                            //check statement later
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

    private static void placeOrder(){

        Order newOrder = new Order();
        OrderService orderService = new OrderService();
        Integer customerID = getCustomerID();

        if (customerID != -2) {
            newOrder.setCustomerID(customerID);
            orderService.newOrderInput(newOrder);
        }
    }

}
