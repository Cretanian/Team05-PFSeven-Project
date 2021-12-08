package com.pfseven.eshop.service;

import com.pfseven.eshop.classinterface.OrderServiceInterface;
import com.pfseven.eshop.database.OrderRepository;
import com.pfseven.eshop.database.ProductRepository;
import com.pfseven.eshop.model.OrderItem;
import com.pfseven.eshop.model.Order;
import com.pfseven.eshop.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class OrderService implements OrderServiceInterface {
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
    private OrderRepository orderRepository ;
    private ProductRepository productRepository;
    public  OrderService(OrderRepository orderRepository,ProductRepository productRepository){
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public void newOrderInput(Order newOrder) throws SQLException {

        ArrayList <OrderItem> orderList = new ArrayList<>();
        Scanner scannerInput = new Scanner(System.in);

        Integer totalProduct = -1;
        String userInput;

        while(true){

            OrderItem orderItem = new OrderItem();
            Product product = new Product();
            logger.info("A) Get product from product ID  B) Get product from name");
            userInput = scannerInput.nextLine();
            if(userInput.equals("A")){
                logger.info("Enter product ID");
                orderItem.setProductID(scannerInput.nextInt());
                //get rest from DB  //getProductFromID
             product  = productRepository.getProductFromID(orderItem.getProductID());
             orderItem.setProductID(product.getProductID());


            }
            else
            {
                logger.info("Enter product name");
                String productName = scannerInput.nextLine();
                //get rest from DB
                product  = productRepository.getProductFromName(orderItem.getProductName());
            }

            logger.info("Choose how many you want ");
            totalProduct = scannerInput.nextInt();
            scannerInput.nextLine();

//            if(totalProduct <= ){
//                //change stock - total and update DB
//                orderItem.setTotal(totalProduct);
//
//                //add rest info to the orderItem
//
//                //adding the ordered item to the order list
//            }

            //orderList.add(orderItem);
            logger.info("A) Add more products B) Place order!");
            userInput = scannerInput.nextLine();

            if(userInput.equals("B"))
                break;

        }

        newOrder.setOrderList(orderList);

        //add order to DB
    }
}
