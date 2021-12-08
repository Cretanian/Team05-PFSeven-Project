package com.pfseven.eshop.service;

import com.pfseven.eshop.classinterface.OrderServiceInterface;
import com.pfseven.eshop.database.CustomerRepository;
import com.pfseven.eshop.database.OrderRepository;
import com.pfseven.eshop.database.ProductRepository;
import com.pfseven.eshop.model.*;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
@Data

public class OrderService implements OrderServiceInterface {
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
    private OrderRepository orderRepository ;
    private ProductRepository productRepository;
    private CustomerRepository customerRepository;

    public  OrderService(OrderRepository orderRepository,ProductRepository productRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }

    public void newOrderInput(Order newOrder, int customerID) throws SQLException {

        Order order = new Order();
        ArrayList <OrderItem> orderList = new ArrayList<>();
        Scanner scannerInput = new Scanner(System.in);

        Integer totalProduct = -1;
        String userInput;

        order.setCustomerID(customerID);

        while(true){

            OrderItem orderItem = new OrderItem();
            Product product = new Product();
            logger.info("A) Get product from product ID  B) Get product from name");
            userInput = scannerInput.nextLine();
            if(userInput.equals("A")){
                logger.info("Enter product ID");
                product.setProductID(scannerInput.nextInt());
                //get rest from DB  //getProductFromID
             product  = productRepository.getProductFromID(product.getProductID());
//             product.setProductID(product.getProductID());
            }
            else
            {
                logger.info("Enter product name");
                String productName = scannerInput.nextLine();
                //get rest from DB
                product  = productRepository.getProductFromName(productName);
            }

            logger.info("Choose how many you want ");
            totalProduct = scannerInput.nextInt();

            if(totalProduct <= product.getStock())
            {
                orderItem.setTotal(totalProduct);
                orderItem.setProductID(product.getProductID());
                product.setStock(product.getStock() - totalProduct);
                productRepository.updateProductToDb(product);

                orderList.add(orderItem);
            }
            else
            {
                logger.info("Not enough in stock.");
            }

            scannerInput.nextLine();
            logger.info("A) Add more products B) Place order!");
            userInput = scannerInput.nextLine();

            if(userInput.equals("B"))
                break;

        }

        order.setOrderList(orderList);

        //Choose Payment Method
        label:
        do {
            logger.info("Enter Payment Method: one of the following CASH, CREDIT_CARD, WIRE_TRANSFER");
            userInput = scannerInput.nextLine();
            switch (userInput) {
                case "CASH":
                case "cash":                         //to do cases
                    order.setPaymentMethod(PaymentMethod.CASH);
                    break label;
                case "CREDIT_CARD":
                case "credit_card":
                    order.setPaymentMethod(PaymentMethod.CREDIT_CARD);
                    break label;
                case "WIRE_TRANSFER":
                case "wire_transfer":
                    order.setPaymentMethod(PaymentMethod.WIRE_TRANSFER);
                    break label;
            }
            logger.info("WRONG ... Try again...");
        }
        while(true);

        logger.info("Order {}",order);

        // ^   > <


        //newOrder.setOrderList(orderList);

        //add order to DB
    }
}
