package com.pfseven.eshop.servicies;

import com.pfseven.eshop.models.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Scanner;

public class ProductService {
    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    public void newProductInput(){
        Product product = new Product();

         Scanner in = new Scanner(System.in);

        logger.info("Enter product name ");
        product.setProductName(in.nextLine());
        logger.info("Enter product price with this format 'xxx,xx'");
        product.setPrice(in.nextBigDecimal());      //it wants a "," not a "."
        logger.info("Enter product stock ");
        product.setStock(in.nextInt());

        logger.info("New product name: {} , with price {} € and stock {} ", product.getProductName(),product.getPrice(),product.getStock());
    }
}
