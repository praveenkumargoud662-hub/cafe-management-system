package com.cafe.dao;
import com.cafe.model.Product; import com.cafe.util.DBUtil;
import java.sql.*; import java.util.*;
public class ProductDAO {
 public List<Product> findAll() throws SQLException {
  List<Product> list=new ArrayList<>();
  try(Connection c=DBUtil.getConnection();PreparedStatement p=c.prepareStatement("SELECT * FROM products ORDER BY id DESC");ResultSet r=p.executeQuery()){
   while(r.next()) list.add(new Product(r.getInt("id"),r.getString("name"),r.getString("category"),r.getBigDecimal("price"),r.getBoolean("available")));
  } return list;
 }
 public void save(Product x) throws SQLException {
  try(Connection c=DBUtil.getConnection();PreparedStatement p=c.prepareStatement("INSERT INTO products(name,category,price,available) VALUES(?,?,?,?)")){
   p.setString(1,x.getName());p.setString(2,x.getCategory());p.setBigDecimal(3,x.getPrice());p.setBoolean(4,x.isAvailable());p.executeUpdate();
  }
 }
 public void update(Product x) throws SQLException {
  String sql="UPDATE products SET name=?,category=?,price=?,available=? WHERE id=?";
  try(Connection c=DBUtil.getConnection();PreparedStatement p=c.prepareStatement(sql)){
   p.setString(1,x.getName());p.setString(2,x.getCategory());p.setBigDecimal(3,x.getPrice());p.setBoolean(4,x.isAvailable());p.setInt(5,x.getId());p.executeUpdate();
  }
 }
 public void delete(int id) throws SQLException {
  try(Connection c=DBUtil.getConnection();PreparedStatement p=c.prepareStatement("DELETE FROM products WHERE id=?")){
   p.setInt(1,id);p.executeUpdate();
  }
 }
}
