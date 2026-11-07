package com.cafe.dao;
import com.cafe.model.CafeOrder; import com.cafe.util.DBUtil;
import java.math.BigDecimal; import java.sql.*; import java.util.*;
public class OrderDAO {
 public void placeOrder(int productId,int quantity) throws SQLException {
  if(quantity<1) throw new IllegalArgumentException("Quantity must be at least 1.");
  String select="SELECT name,price,available FROM products WHERE id=?";
  String insert="INSERT INTO orders(product_id,product_name,quantity,unit_price,total) VALUES(?,?,?,?,?)";
  try(Connection c=DBUtil.getConnection()){
   c.setAutoCommit(false);
   try {
    String name; BigDecimal price;
    try(PreparedStatement p=c.prepareStatement(select)){
     p.setInt(1,productId);try(ResultSet r=p.executeQuery()){
      if(!r.next() || !r.getBoolean("available")) throw new SQLException("Product not found or unavailable.");
      name=r.getString("name");price=r.getBigDecimal("price");
     }
    }
    BigDecimal total=price.multiply(BigDecimal.valueOf(quantity));
    try(PreparedStatement p=c.prepareStatement(insert)){
     p.setInt(1,productId);p.setString(2,name);p.setInt(3,quantity);p.setBigDecimal(4,price);p.setBigDecimal(5,total);p.executeUpdate();
    }
    c.commit();
   } catch(SQLException | RuntimeException e){c.rollback();throw e;}
  }
 }
 public List<CafeOrder> findAll() throws SQLException {
  List<CafeOrder> list=new ArrayList<>();
  try(Connection c=DBUtil.getConnection();PreparedStatement p=c.prepareStatement("SELECT * FROM orders ORDER BY order_time DESC");ResultSet r=p.executeQuery()){
   while(r.next()) list.add(new CafeOrder(r.getInt("id"),r.getString("product_name"),r.getInt("quantity"),r.getBigDecimal("unit_price"),r.getBigDecimal("total"),r.getTimestamp("order_time")));
  } return list;
 }
 public BigDecimal getTotalSales() throws SQLException {
  try(Connection c=DBUtil.getConnection();PreparedStatement p=c.prepareStatement("SELECT COALESCE(SUM(total),0) FROM orders");ResultSet r=p.executeQuery()){
   r.next();return r.getBigDecimal(1);
  }
 }
 public int getOrderCount() throws SQLException {
  try(Connection c=DBUtil.getConnection();PreparedStatement p=c.prepareStatement("SELECT COUNT(*) FROM orders");ResultSet r=p.executeQuery()){
   r.next();return r.getInt(1);
  }
 }
}
