package com.pfseven.eshop.service;

import com.pfseven.eshop.repository.ProductRepositoryImpl;
import com.pfseven.eshop.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ProductServiceImpl implements ProductService {
    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
    private ProductRepositoryImpl productRepositoryImpl;

    public ProductServiceImpl(ProductRepositoryImpl productRepositoryImpl){
        this.productRepositoryImpl = productRepositoryImpl;
    }

    public void newProductInput() {
        Product product = new Product();
        int error = 0;
        Scanner in = new Scanner(System.in);

        logger.info("Enter product name ");
        product.setProductName(in.nextLine());
        logger.info("Enter product price with this format 'xxx,xx'");
        try {
            product.setPrice(in.nextBigDecimal());      //it wants a "," not a "." MAYBE?!!?!?!

        } catch (InputMismatchException mismatchException){
            logger.error("Wrong price input {}",mismatchException.toString());
            error = -1;
            in.nextLine();
        }

        if (error != -1)
        {
            logger.info("Enter product stock ");
            try {
                product.setStock(in.nextInt());
            } catch (InputMismatchException mismatchException){
                logger.error("Wrong stock input {}",mismatchException.toString());
                in.nextLine();
                error = -2;
            }
            if (error != -2)
            {
                productRepositoryImpl.insertProductToDb(product);
                //add this product to db
                //this.productRepository.insertProductToDb(product);
                logger.info("New product name: {} , with price {}€ and stock {} ", product.getProductName(),product.getPrice(),product.getStock());

            }
        }
       }

    public void editProduct() {
        Scanner in = new Scanner(System.in);
        Product product = new Product();
        Scanner scannerInput = new Scanner(System.in);
        int error = 0;

        logger.info("Enter product ID");
        try {
            Integer userInput = scannerInput.nextInt();
            product = productRepositoryImpl.getProductFromID(userInput);  //request product from database
            logger.info("The editing product : {}", product);

        } catch (InputMismatchException inputMismatchException){
            logger.error("Wrong input {}",inputMismatchException.toString());
            error = -1;
        }

        if (error != -1 && product.getProductID() != -1)
        {
            logger.info("Enter product name ");
            product.setProductName(in.nextLine());
            logger.info("Enter product price with this format 'xxx,xx'");  //edit product

            try {
                product.setPrice(in.nextBigDecimal());      //it wants a "," not a "." MAYBE?!!?!?!

            } catch (InputMismatchException inputMismatchException)
            {
                logger.error("Wrong price input {}",inputMismatchException.toString());
                error = -2;
                scannerInput.nextLine();
            }
            if (error != -2){
                logger.info("Enter product stock ");
                try {
                    product.setStock(in.nextInt());
                }catch (InputMismatchException inputMismatchException)
                {
                    logger.error("Wrong price input {}",inputMismatchException.toString());
                    error = -3;
                }
                if(error != -3){
                    productRepositoryImpl.updateProductToDb(product);   //update product
                    logger.info("The edited product : {}", productRepositoryImpl.getProductFromID(product.getProductID()));
                }
            }
        }
    }
}
