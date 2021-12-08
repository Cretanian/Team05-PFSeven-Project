package com.pfseven.eshop.services;

import com.pfseven.eshop.interfaces.OrderServiceInterface;
import com.pfseven.eshop.models.OrderItem;
import com.pfseven.eshop.models.Order;
import com.pfseven.eshop.models.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class OrderService implements OrderServiceInterface {
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    public void newOrderInput(Order newOrder){

        ArrayList <OrderItem> orderList = new ArrayList<>();
        Scanner scannerInput = new Scanner(System.in);

        Integer totalProduct = -1;
        String userInput;

        while(true){

            OrderItem orderItem = new OrderItem();
            logger.info("A) Get product from product ID  B) Get product from name");
            userInput = scannerInput.nextLine();
            if(userInput.equals("A")){
                logger.info("Enter product ID");
                orderItem.setProductID(scannerInput.nextInt());
                //get rest from DB
            }
            else
            {
                logger.info("Enter product name");
                String productName = scannerInput.nextLine();
                //get rest from DB

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
