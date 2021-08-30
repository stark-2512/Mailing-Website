import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class delete extends HttpServlet
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
                String from = null,mail_to = null,sub = null,body = null;
                System.out.print(resp);
                ps=co.prepareStatement("select * from inbox where id_num=?");
                ps.setInt(1,Integer.parseInt(resp));
                rs=ps.executeQuery();
                
                while(rs.next())
                {
                    from=rs.getString(2);
                    mail_to=rs.getString(3);
                    sub=rs.getString(4);
                    body=rs.getString(5);
                }
                ps.close();
                rs.close();
                
                ps=co.prepareStatement("insert into trash(mail_from,mail_to,mail_sub,mail_body) values(?,?,?,?)");
                ps.setString(1,from);
                ps.setString(2,mail_to);
                ps.setString(3,sub);
                ps.setString(4,body);
                ps.executeUpdate();
                ps.close();
                
                ps=co.prepareStatement("delete from inbox where id_num=?");
                ps.setInt(1,Integer.parseInt(resp));
                ps.executeUpdate();
                ps.close();
                
                
            }
                
        rep.sendRedirect("inbox");
        
        }catch(Exception e)
        {
            System.out.print("Error : "+e);
        }
    }
}


