package com.pfseven.eshop.service;

import com.pfseven.eshop.classinterface.ProductServiceInterface;
import com.pfseven.eshop.database.ProductRepository;
import com.pfseven.eshop.model.Product;
import com.sun.jdi.connect.spi.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.Scanner;

public class ProductService implements ProductServiceInterface {
    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);
    private ProductRepository productRepository ;

    public ProductService(ProductRepository productRepository){
        this.productRepository= productRepository;
    }

    public void newProductInput() throws SQLException {
        Product product = new Product();


        Scanner in = new Scanner(System.in);

        logger.info("Enter product name ");
        product.setProductName(in.nextLine());
        logger.info("Enter product price with this format 'xxx,xx'");
        product.setPrice(in.nextBigDecimal());      //it wants a "," not a "." MAYBE?!!?!?!
        logger.info("Enter product stock ");
        product.setStock(in.nextInt());
        productRepository.insertProductToDb(product);
        //add this product to db
        //this.productRepository.insertProductToDb(product);
        logger.info("New product name: {} , with price {}€ and stock {} ", product.getProductName(),product.getPrice(),product.getStock());
    }

    public void editProduct() throws SQLException {
        Scanner in = new Scanner(System.in);
        Product product ;
        Scanner scannerInput = new Scanner(System.in);
        logger.info("Enter product ID");
        Integer userInput = scannerInput.nextInt();
        product = productRepository.getProductFromID(userInput);  //request product from database
        logger.info("The editing product : {}", product);

        logger.info("Enter product name ");
        product.setProductName(in.nextLine());
        logger.info("Enter product price with this format 'xxx,xx'");  //edit product
        product.setPrice(in.nextBigDecimal());      //it wants a "," not a "." MAYBE?!!?!?!
        logger.info("Enter product stock ");
        product.setStock(in.nextInt());

        productRepository.updateProductToDb(product);   //update product


        logger.info("The edited product : {}",productRepository.getProductFromID(product.getProductID()));


    }
}
