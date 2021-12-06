package com.pfseven.eshop.servicies;

import com.pfseven.eshop.models.CategoryID;
import com.pfseven.eshop.models.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Scanner;

public class CustomerService {
    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    public void newCustomerInput(){
        Customer customer = new Customer();

        Scanner in = new Scanner(System.in);

        logger.info("Enter user first name ");
        customer.setFirstName(in.nextLine());
        logger.info("Enter user last name ");
        customer.setLastName(in.nextLine());
        do {
            logger.info("Enter user Category ID: one of the following B2B, B2C, B2G ");
            String string = in.nextLine();
            if (string.equals("B2B")||string.equals("b2b")){
                customer.setCategoryID(CategoryID.B2B);
                break;
            }else if(string.equals("B2C")||string.equals("b2c")){
                customer.setCategoryID(CategoryID.B2C);
                break;
            }else if(string.equals("B2G")||string.equals("b2g")){
                customer.setCategoryID(CategoryID.B2G);
                break;
            }
            logger.info("WRONG ... Try again...");
        }while(true);

        logger.info("Customer name {} {} , with categoryID {}", customer.getFirstName(), customer.getLastName(), customer.getCategoryID());
    }
}
