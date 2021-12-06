package com.pfseven.eshop.servicies;

import com.pfseven.eshop.models.Order;
import com.pfseven.eshop.models.OrderItem;
import com.pfseven.eshop.models.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    public void newOrderInput(){
        //creating a product list
        Product product1 = new Product(1,"mouse", new BigDecimal("20"),30);
        Product product2 = new Product(2,"keyboard", new BigDecimal("30"),20);
        ArrayList<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);
        //initialising the Order
        Order order = new Order();
        OrderItem orderItem = new OrderItem();
        order.setOrderID(1);
        order.setCustomerID(1);
        ArrayList <OrderItem> orderList = new ArrayList<>();
        order.setOrderList(orderList);

        Scanner scanner = new Scanner(System.in);

        logger.info("Choose the product you want ");
        int i=0;
        Integer j;

        String str = scanner.nextLine();
        do {
            //Finding which product we want to order using the name
            if (str.equals(productList.get(i).getProductName())) {
                logger.info("Choose how many you want ");
                 j = scanner.nextInt();
                if(j<=productList.get(i).getStock()){
                    orderItem.setPrice(productList.get(i).getPrice());
                    orderItem.setProductName(productList.get(i).getProductName());
                    orderItem.setStock(productList.get(i).getStock()-j);
                    orderItem.setProductID(productList.get(i).getProductID());
                    orderItem.setTotal(j);
                    //adding the ordered item to the order list
                    order.getOrderList().add(orderItem);
                    break;
                }
            }
            i++;
        }while(productList.size()>i);
        logger.info("The new order : {} ",order);


    }
}
