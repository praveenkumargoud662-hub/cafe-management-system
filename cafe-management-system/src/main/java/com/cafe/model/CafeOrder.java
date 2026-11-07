package com.cafe.model;
import java.math.BigDecimal;
import java.sql.Timestamp;
public class CafeOrder {
 private int id; private String productName; private int quantity; private BigDecimal unitPrice,total; private Timestamp orderTime;
 public CafeOrder(int id,String productName,int quantity,BigDecimal unitPrice,BigDecimal total,Timestamp orderTime){
  this.id=id;this.productName=productName;this.quantity=quantity;this.unitPrice=unitPrice;this.total=total;this.orderTime=orderTime;
 }
 public int getId(){return id;} public String getProductName(){return productName;}
 public int getQuantity(){return quantity;} public BigDecimal getUnitPrice(){return unitPrice;}
 public BigDecimal getTotal(){return total;} public Timestamp getOrderTime(){return orderTime;}
}
