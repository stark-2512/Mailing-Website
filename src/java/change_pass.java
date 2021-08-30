import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
public class change_pass extends HttpServlet
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
            String copass=null;
            PrintWriter out=rep.getWriter();
            int c=0;
            String opass=req.getParameter("opass");
            //String cc=req.getParameter("cc");
            String npass=req.getParameter("npass");
            String cpass=req.getParameter("cpass");
            
            HttpSession sc=req.getSession();
            String email_logged_in=(String)sc.getAttribute("logged_in");
            if(npass.equals(cpass))
            {
                ps=co.prepareStatement("select * from users where mail_id=?");
                ps.setString(1,email_logged_in);
                rs=ps.executeQuery();

                while(rs.next())
                {
                    copass=rs.getString(2);
                }
                ps.close();
                rs.close();

                if(opass.equals(copass))
                {
                    
                    ps=co.prepareStatement("update users set password=? where mail_id=?");
                    ps.setString(1,npass);
                    ps.setString(2,email_logged_in);
                    ps.executeUpdate();
                    ps.close();

                    rep.sendRedirect("Home.html");
                }
                else
                {
                    
                    RequestDispatcher rd=req.getRequestDispatcher("Change_Pass.html");
                    rd.include(req,rep);
                    out.print("<script type=text/javascript>alert(\"Wrong Password!!\")</script>");
                }
            }
            
            else
            {
                RequestDispatcher rd=req.getRequestDispatcher("Change_Pass.html");
                rd.include(req,rep);
                out.print("<script type=text/javascript>alert(\"Confirm Password Doesn't Match\")</script>");
            }
        }catch(IOException | SQLException | ServletException e)
        {
            System.out.print(e);
        }
    }
}