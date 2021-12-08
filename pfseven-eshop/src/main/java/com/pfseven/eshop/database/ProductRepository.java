package com.pfseven.eshop.database;

import com.pfseven.eshop.model.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductRepository {
   private DatabasePF7Project test;


   public ProductRepository(DatabasePF7Project test1){
      this.test = test1;
   }



   public Product getProductfromID (int id) throws SQLException {
      Product product = new Product();
      PreparedStatement statement = this.test.getDBConnection().prepareStatement("SELECT * FROM PRODUCT WHERE(product_ID = ?)");
      statement.setString(1, String.valueOf(id));
      ResultSet resultSet =  statement.executeQuery();
      resultSet.next();
      System.out.println(resultSet.getString("price"));

      return product;
   }
}
