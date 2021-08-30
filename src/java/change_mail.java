import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
public class change_mail extends HttpServlet
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
            String nid=req.getParameter("nid");
            String confirm=req.getParameter("confirm");
            
            HttpSession sc=req.getSession();
            String email_logged_in=(String)sc.getAttribute("logged_in");
                ps=co.prepareStatement("select * from users where mail_id=?");
                ps.setString(1,nid);
                rs=ps.executeQuery();

                while(rs.next())
                {
                    c=c+1;
                }
                ps.close();
                rs.close();
                
                
                if(c==0)
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
                    
                    if(confirm.equals(copass))
                    {
                        ps=co.prepareStatement("update users set mail_id=? where mail_id=?");
                        ps.setString(1,nid);
                        ps.setString(2,email_logged_in);
                        ps.executeUpdate();
                        ps.close();
                        
                        ps=co.prepareStatement("update inbox set mail_from=? where mail_from=?");
                        ps.setString(1,nid);
                        ps.setString(2,email_logged_in);
                        ps.executeUpdate();
                        ps.close();
                        
                        ps=co.prepareStatement("update inbox set mail_to=? where mail_to=?");
                        ps.setString(1,nid);
                        ps.setString(2,email_logged_in);
                        ps.executeUpdate();
                        ps.close();
                        
                        ps=co.prepareStatement("update sent set mail_from=? where mail_from=?");
                        ps.setString(1,nid);
                        ps.setString(2,email_logged_in);
                        ps.executeUpdate();
                        ps.close();
                        
                        ps=co.prepareStatement("update sent set mail_to=? where mail_to=?");
                        ps.setString(1,nid);
                        ps.setString(2,email_logged_in);
                        ps.executeUpdate();
                        ps.close();
                        
                        ps=co.prepareStatement("update draft set mail_from=? where mail_from=?");
                        ps.setString(1,nid);
                        ps.setString(2,email_logged_in);
                        ps.executeUpdate();
                        ps.close();
                        
                        ps=co.prepareStatement("update draft set mail_to=? where mail_to=?");
                        ps.setString(1,nid);
                        ps.setString(2,email_logged_in);
                        ps.executeUpdate();
                        ps.close();
                        
                        ps=co.prepareStatement("update trash set mail_from=? where mail_from=?");
                        ps.setString(1,nid);
                        ps.setString(2,email_logged_in);
                        ps.executeUpdate();
                        ps.close();
                        
                        ps=co.prepareStatement("update trash set mail_to=? where mail_to=?");
                        ps.setString(1,nid);
                        ps.setString(2,email_logged_in);
                        ps.executeUpdate();
                        ps.close();
                        
                        rep.sendRedirect("Home.html");
                    }
                    
                    else
                    {
                        RequestDispatcher rd=req.getRequestDispatcher("change_mail.html");
                        rd.include(req,rep);
                        out.print("<script type=text/javascript>alert(\"Wrong Password!!\")</script>");
                    }
                }
                else
                {
                    
                    RequestDispatcher rd=req.getRequestDispatcher("Change_Pass.html");
                    rd.include(req,rep);
                    out.print("<script type=text/javascript>alert(\"ID Exist\")</script>");
                }
            
            
            
        }catch(IOException | SQLException | ServletException | NullPointerException e)
        {
            System.out.print(e);
        }
    }
}