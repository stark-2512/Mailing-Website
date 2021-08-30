import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
public class create_new_acc extends HttpServlet
{
    Connection co;
    PreparedStatement ps;
    ResultSet rs;
    
    public void init()
    {
        try
        {
            ServletContext sc=getServletContext();
            co=(Connection)sc.getAttribute("database");
        }catch(Exception e)
        {
            System.out.print(e);
        }

    }
    public void doGet(HttpServletRequest req,HttpServletResponse rep)
    {
        
        try
        {
            
            PrintWriter out=rep.getWriter();
            
            String subject=req.getParameter("subject");
            String body=req.getParameter("body");
            
            HttpSession sc=req.getSession();
            String email_logged_in=(String)sc.getAttribute("logged_in");
            
            sc.invalidate();
            
            rep.sendRedirect("Sign-Up.html");
        
        }catch(Exception e)
        {
            System.out.print(e);
        }
    }
}