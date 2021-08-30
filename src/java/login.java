import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class login extends HttpServlet
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
            
                int c=0;
                String email=req.getParameter("email");
                String pass=req.getParameter("pass");

                ps=co.prepareStatement("select * from users where mail_id=? and password=?");
                ps.setString(1,email);
                ps.setString(2,pass);
                rs=ps.executeQuery();
                while(rs.next())
                {
                    c=c+1;
                }
                rs.close();
                ps.close();
                
                if(c!=0)
                {
                    HttpSession sc=req.getSession();
                    sc.setAttribute("logged_in",email);
                    sc.setAttribute("logged_in_pass",pass);
                    rep.sendRedirect("Home.html");
                   
                }
                else
                {
                    RequestDispatcher rd=req.getRequestDispatcher("Login.html");
                    rd.include(req,rep);
                    out.print("<script type=text/javascript>alert('Username or Password Exist')</script>");  
                    
                }
                
            
        }catch(IOException | SQLException | ServletException e)
        {
            System.out.print("Error : "+e);
        }
    }
}


