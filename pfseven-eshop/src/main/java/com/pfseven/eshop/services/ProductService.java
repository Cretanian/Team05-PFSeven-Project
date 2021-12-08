package com.pfseven.eshop.services;

import com.pfseven.eshop.interfaces.ProductServiceInterface;
import com.pfseven.eshop.models.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Scanner;

public class ProductService implements ProductServiceInterface {
    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    public void newProductInput(){
        Product product = new Product();
        Scanner in = new Scanner(System.in);

        logger.info("Enter product name ");
        product.setProductName(in.nextLine());
        logger.info("Enter product price with this format 'xxx.xx'");
        product.setPrice(in.nextBigDecimal());      //it wants a "," not a "." MAYBE?!!?!?!
        logger.info("Enter product stock ");
        product.setStock(in.nextInt());

        //add this product to db

        logger.info("New product name: {} , with price {} € and stock {} ", product.getProductName(),product.getPrice(),product.getStock());
    }

    public void editProduct(){
        Scanner scannerInput = new Scanner(System.in);
        logger.info("Enter product ID");
        Integer userInput = scannerInput.nextInt();

        //request product from database
        //edit product
    }
}
