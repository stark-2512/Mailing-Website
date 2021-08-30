import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class delete_trash extends HttpServlet
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
            System.out.print("driver "+e);
        }
    }
    public void doGet(HttpServletRequest req,HttpServletResponse rep)
    {
        try
        {
            PrintWriter out = rep.getWriter();
            
            HttpSession sc=req.getSession();
            String logged_in_email=(String)sc.getAttribute("logged_in");
            
            String response[]=req.getParameterValues("checkbox");
            
            for (String resp : response) 
            {
                
                
                ps=co.prepareStatement("delete from trash where id_num=?");
                ps.setInt(1,Integer.parseInt(resp));
                ps.executeUpdate();
                ps.close();
                
                
            }
                
        rep.sendRedirect("trash");
        
        }catch(Exception e)
        {
            System.out.print("Error Sent: "+e);
        }
    }
}


