package com.cafe.model;
import java.math.BigDecimal;
public class Product {
 private int id; private String name; private String category; private BigDecimal price; private boolean available;
 public Product(){}
 public Product(int id,String name,String category,BigDecimal price,boolean available){
  this.id=id;this.name=name;this.category=category;this.price=price;this.available=available;
 }
 public int getId(){return id;} public void setId(int v){id=v;}
 public String getName(){return name;} public void setName(String v){name=v;}
 public String getCategory(){return category;} public void setCategory(String v){category=v;}
 public BigDecimal getPrice(){return price;} public void setPrice(BigDecimal v){price=v;}
 public boolean isAvailable(){return available;} public void setAvailable(boolean v){available=v;}
}
