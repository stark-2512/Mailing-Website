import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class signup extends HttpServlet
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
        int c=0;
        try
        {
            PrintWriter pw=rep.getWriter();
            String name=req.getParameter("name");
            String pass=req.getParameter("pass");
            String email=(req.getParameter("email")+"@sarj.com");
            String dob=req.getParameter("dob");
            
            rep.setContentType("text/html");
            
                ps=co.prepareStatement("select * from users where mail_id=?");
                ps.setString(1,email);
                rs=ps.executeQuery();
                while(rs.next())
                {
                    c=c+1;
                }
                rs.close();
                ps.close();
                    
                if(c!=0)
                {
                    
                    pw.print("E_mail Exist try Another");
                    RequestDispatcher rd=req.getRequestDispatcher("Sign-Up.html");
                    rd.include(req,rep);
                    
                }
                else
                {
                  
                    ps=co.prepareStatement("insert into users(mail_id,password,name,dob) values(?,?,?,?)");
                    ps.setString(1,email);
                    ps.setString(2,pass);
                    ps.setString(3,name);
                    ps.setString(4,dob);
                    
                    ps.executeUpdate();
                    ps.close();
                    rep.sendRedirect("Login.html");
                
                }
                
            
        }catch(Exception e)
        {
            System.out.print("Signup "+e);
        }
    }
}