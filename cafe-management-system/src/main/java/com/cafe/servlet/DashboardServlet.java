package com.cafe.servlet;
import com.cafe.dao.*; import javax.servlet.*; import javax.servlet.annotation.WebServlet; import javax.servlet.http.*; import java.io.IOException;
@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
 protected void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
  try {req.setAttribute("productCount",new ProductDAO().findAll().size());OrderDAO dao=new OrderDAO();req.setAttribute("orderCount",dao.getOrderCount());req.setAttribute("totalSales",dao.getTotalSales());req.getRequestDispatcher("/dashboard.jsp").forward(req,res);}
  catch(Exception e){throw new ServletException("Unable to load dashboard",e);}
 }
}
