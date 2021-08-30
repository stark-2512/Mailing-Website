import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
public class compose extends HttpServlet
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
            int c=0;
            String to=req.getParameter("to");
            //String cc=req.getParameter("cc");
            String subject=req.getParameter("subject");
            String body=req.getParameter("body");
            
            HttpSession sc=req.getSession();
            String email_logged_in=(String)sc.getAttribute("logged_in");

            ps=co.prepareStatement("select * from users where mail_id=?");
            ps.setString(1,to);
            rs=ps.executeQuery();
            
            while(rs.next())
            {
                c=c+1;
            }
            ps.close();
            rs.close();
            
            if(c!=0)
            {
                ps=co.prepareStatement("insert into sent(mail_from,mail_to,mail_sub,mail_body) values(?,?,?,?)");
                ps.setString(1,email_logged_in);
                ps.setString(2,to);
                ps.setString(3,subject);
                ps.setString(4,body);
                ps.executeUpdate();
                ps.close();
                
                ps=co.prepareStatement("insert into inbox(mail_from,mail_to,mail_sub,mail_body) values(?,?,?,?)");
                ps.setString(1,email_logged_in);
                ps.setString(2,to);
                ps.setString(3,subject);
                ps.setString(4,body);
                ps.executeUpdate();
                ps.close();
                
                rep.sendRedirect("Home.html");
            }
            else
            {
                ps=co.prepareStatement("insert into draft(mail_from,mail_to,mail_sub,mail_body) values(?,?,?,?)");
                ps.setString(1,email_logged_in);
                ps.setString(2,to);
                ps.setString(3,subject);
                ps.setString(4,body);
                ps.executeUpdate();
                ps.close();
                
                
                RequestDispatcher rd=req.getRequestDispatcher("Home.html");
                rd.include(req,rep);
                out.print("<script type=text/javascript>alert(\"To whom your are Mailing doesn't Exist!!\")</script>");
            }
        }catch(IOException | SQLException | ServletException e)
        {
            System.out.print(e);
        }
    }
}