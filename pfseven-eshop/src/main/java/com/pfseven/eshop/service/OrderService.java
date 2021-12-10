package com.pfseven.eshop.service;

import com.pfseven.eshop.classinterface.OrderServiceInterface;
import com.pfseven.eshop.database.*;

import com.pfseven.eshop.model.*;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
@Data

public class OrderService implements OrderServiceInterface {
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
    private OrderRepository orderRepository ;
    private ProductRepository productRepository;
    private CustomerRepository customerRepository;
    private OrderItemRepository orderItemRepository;

    public  OrderService(OrderRepository orderRepository,ProductRepository productRepository, CustomerRepository customerRepository,OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
        this.orderItemRepository =  orderItemRepository;
    }

    public void newOrderInput(Order newOrder, int customerID) {

        Order order = new Order();
        Customer customer;
        customer = customerRepository.getCustomerFromID(customerID);
        ArrayList <OrderItem> orderList = new ArrayList<>();
        Scanner scannerInput = new Scanner(System.in);

        Integer totalProduct = -1;
        String userInput;

        order.setCustomerID(customerID);
        BigDecimal orderCost = new BigDecimal("0");

        while(true) {

            try {
                OrderItem orderItem = new OrderItem();
                Product product = new Product();
                logger.info("A) Get product from product ID  B) Get product from name");
                userInput = scannerInput.nextLine();
                if(userInput.toLowerCase().equals("a")) {
                    logger.info("Enter product ID");
                    try{
                        product.setProductID(scannerInput.nextInt());
                    }   catch (InputMismatchException mismatchException){
                        logger.error("Wrong price input {}",mismatchException.toString());
                    }
                    product.setProductID(scannerInput.nextInt());
                    //get rest from DB  //getProductFromID
                    product  = productRepository.getProductFromID(product.getProductID());
                    if (product.getProductID() == -1) {
                        logger.info("There is no such product! Try again!");
                        scannerInput.nextLine();
                        continue;
                    }
                }
                else if (userInput.toLowerCase().equals("a")) {
                    logger.info("Enter product name");
                    String productName = scannerInput.nextLine();
                    //get rest from DB
                    product  = productRepository.getProductFromName(productName);
                }
                else {
                    logger.info("Invalid input! Try again!");
                    continue;
                }

                logger.info("Choose how many you want ");
                totalProduct = scannerInput.nextInt();

                if(totalProduct <= product.getStock())
                {
                    orderItem.setTotal(totalProduct);
                    orderItem.setProductID(product.getProductID());
                    product.setStock(product.getStock() - totalProduct);
                    productRepository.updateProductToDb(product);
                    orderItemRepository.saveOrderItemToDB(orderItem);
                    orderList.add(orderItem);

                    orderCost = orderCost.add(product.getPrice().multiply(BigDecimal.valueOf(totalProduct)));
                }
                else
                {
                    logger.info("Not enough in stock.");
                    logger.info("You ordered {} {} but there are only {} in stock",totalProduct, product.getProductName(), product.getStock());
                }

                scannerInput.nextLine();
                logger.info("A) Add more products B) Place order!");
                userInput = scannerInput.nextLine();

                if(userInput.toLowerCase().equals("b"))
                    break;
            }
            catch (InputMismatchException throwable) {
                String mismatch = scannerInput.next();
                logger.info("mismatch: {}",mismatch);
                scannerInput.nextLine();
                continue;
            }
        }

        order.setOrderList(orderList);

        //Choose Payment Method
        label:
        do {
            logger.info("Enter Payment Method: one of the following CASH, CREDIT_CARD, WIRE_TRANSFER");
            userInput = scannerInput.nextLine();
            switch (userInput.toLowerCase()) {
                case "cash":                         //to do cases
                    order.setPaymentMethod(PaymentMethod.CASH);
                    break label;
                case "credit_card":
                case "credit card":
                    order.setPaymentMethod(PaymentMethod.CREDIT_CARD);
                    break label;
                case "wire_transfer":
                case "wire transfer":
                    order.setPaymentMethod(PaymentMethod.WIRE_TRANSFER);
                    break label;
            }
            logger.info("WRONG ... Try again...");
        }while(true);


        //add to DB
        if (!order.getOrderList().isEmpty()) {
            logger.info("Order cost without discount is: {}€",orderCost);

            int paymentMethodDiscount = paymentMethodDiscount(order.getPaymentMethod());
            int categoryIDDiscount = categoryIDDiscount(customer.getCategoryID());
            int totalDiscount = paymentMethodDiscount + categoryIDDiscount;
            double discount = (double)totalDiscount / 100;

            orderCost = orderCost.multiply(BigDecimal.valueOf(1-discount));
            logger.info("Your discount is: {}%, so the final order cost is: {}€", totalDiscount,orderCost);
            order.setCost(orderCost);
            orderRepository.saveOrderToDB(order);
            logger.info("Order {}", order);
        }
        else {
            logger.info("Your order list is empty!");
        }
    }

    public int paymentMethodDiscount(PaymentMethod paymentMethod) {
        switch(paymentMethod) {
            case CASH:
                return 0;
            case CREDIT_CARD:
                return 15;
            case WIRE_TRANSFER:
                return 10;
        }
        return -1;
    }

    public int categoryIDDiscount(CategoryID categoryID) {
        switch (categoryID) {
            case B2B:
                return 20;
            case B2C:
                return 0;
            case B2G:
                return 50;
        }
        return -1;
    }

}
