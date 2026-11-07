package com.cafe.servlet;
import com.cafe.dao.*; import javax.servlet.*; import javax.servlet.annotation.WebServlet; import javax.servlet.http.*; import java.io.IOException;
@WebServlet("/orders")
public class OrderServlet extends HttpServlet {
 private final OrderDAO orders=new OrderDAO(); private final ProductDAO products=new ProductDAO();
 protected void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
  try{req.setAttribute("products",products.findAll());req.setAttribute("orders",orders.findAll());req.getRequestDispatcher("/orders.jsp").forward(req,res);}
  catch(Exception e){throw new ServletException("Unable to load orders",e);}
 }
 protected void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
  try{orders.placeOrder(Integer.parseInt(req.getParameter("productId")),Integer.parseInt(req.getParameter("quantity")));res.sendRedirect(req.getContextPath()+"/orders?success=1");}
  catch(Exception e){throw new ServletException("Unable to place order. Check product and quantity.",e);}
 }
}
