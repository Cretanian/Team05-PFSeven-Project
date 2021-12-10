package com.pfseven.eshop.service;

import com.pfseven.eshop.classinterface.ProductServiceInterface;
import com.pfseven.eshop.database.ProductRepository;
import com.pfseven.eshop.model.Product;
import com.sun.jdi.connect.spi.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ProductService implements ProductServiceInterface {
    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);
    private ProductRepository productRepository ;

    public ProductService(ProductRepository productRepository){
        this.productRepository= productRepository;
    }

    public void newProductInput()  {
        Product product = new Product();
        int error = 0;

        Scanner in = new Scanner(System.in);

        logger.info("Enter product name ");
        product.setProductName(in.nextLine());
        logger.info("Enter product price with this format 'xxx.xx'");
        try {
            product.setPrice(in.nextBigDecimal());
        }catch(InputMismatchException e){
            e.printStackTrace();
            logger.error("WRONG price input...{}",e.toString());
            error = -1;
            in.nextLine();
        }
        if(error != -1) {
            logger.info("Enter product stock ");
            try {
                product.setStock(in.nextInt());

            } catch (InputMismatchException e) {
                e.printStackTrace();
                logger.error("WRONG stock input...{}",e.toString());
                error = -2;
            }
            if (error != -2) {
                productRepository.insertProductToDb(product);
                //add this product to db
                //this.productRepository.insertProductToDb(product);
                logger.info("New product name: {} , with price {} € and stock {} ", product.getProductName(), product.getPrice(), product.getStock());
            }
        }
    }

    public void editProduct()  {
        Scanner in = new Scanner(System.in);
        Product product = new Product();
        product.setProductID(0);
        Scanner scannerInput = new Scanner(System.in);
        int error =0;


        logger.info("Enter product ID");
        try {
            Integer userInput = scannerInput.nextInt();
            product = productRepository.getProductFromID(userInput);
        }catch (InputMismatchException e){
            e.printStackTrace();
            logger.error("WRONG ID input...{}",e.toString());
            error=-1;
            scannerInput.nextLine();
        }if(error!=-1&& product.getProductID()!=-1 ) {
              //request product from database
            // logger.info("The editing product : {}", product);
            logger.info("Enter product name ");
            product.setProductName(in.nextLine());
            logger.info("Enter product price with this format 'xxx.xx'");  //edit product
            try{
                product.setPrice(in.nextBigDecimal());      //it wants a "," not a "." MAYBE?!!?!?!
            }catch(InputMismatchException e){
                e.printStackTrace();
                logger.error("WRONG price input... {}",e.toString());
                error=-2;
                scannerInput.nextLine();
            }if (error!=-2) {
                try {
                    logger.info("Enter product stock ");
                    product.setStock(in.nextInt());

                }catch(InputMismatchException e){
                    e.printStackTrace();
                    logger.error("WRONG stock input...{}",e.toString());
                    error =-3;
                }
                if(error !=-3){
                    productRepository.updateProductToDb(product);   //update product
                    logger.info("The edited product : {}", productRepository.getProductFromID(product.getProductID()));
                }
            }
        }
    }
}
