package com.cafe.servlet;
import com.cafe.dao.ProductDAO; import com.cafe.model.Product;
import javax.servlet.*; import javax.servlet.annotation.WebServlet; import javax.servlet.http.*; import java.io.IOException; import java.math.BigDecimal;
@WebServlet("/products")
public class ProductServlet extends HttpServlet {
 private final ProductDAO dao=new ProductDAO();
 protected void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
  try{req.setAttribute("products",dao.findAll());req.getRequestDispatcher("/products.jsp").forward(req,res);}
  catch(Exception e){throw new ServletException("Unable to load products",e);}
 }
 protected void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
  req.setCharacterEncoding("UTF-8");
  try{
   if("delete".equals(req.getParameter("action"))) dao.delete(Integer.parseInt(req.getParameter("id")));
   else{
    String name=req.getParameter("name").trim(),category=req.getParameter("category").trim();
    BigDecimal price=new BigDecimal(req.getParameter("price"));
    if(name.isEmpty()||category.isEmpty()||price.signum()<=0)throw new IllegalArgumentException("Enter valid product details.");
    int id=req.getParameter("id")==null||req.getParameter("id").isBlank()?0:Integer.parseInt(req.getParameter("id"));
    Product p=new Product(id,name,category,price,req.getParameter("available")!=null);
    if(id==0) dao.save(p); else dao.update(p);
   }
   res.sendRedirect(req.getContextPath()+"/products?success=1");
  }catch(Exception e){throw new ServletException("Could not save product. Check values and database.",e);}
 }
}
