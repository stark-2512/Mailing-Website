import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class trash extends HttpServlet
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
            
            ps=co.prepareStatement("select * from trash where mail_from=? or mail_to=?");
            ps.setString(1,logged_in_email);
            ps.setString(2,logged_in_email);
            rs=ps.executeQuery();
                            out.println("<!DOCTYPE html>\n" +
"<html style=\"font-size: 16px;\">\n" +
"  <head>\n" +
"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
"    <meta charset=\"utf-8\">\n" +
"    <meta name=\"keywords\" content=\"\">\n" +
"    <meta name=\"description\" content=\"\">\n" +
"    <meta name=\"page_type\" content=\"np-template-header-footer-from-plugin\">\n" +
"    <title>Inbox</title>\n" +
"    <link rel=\"stylesheet\" href=\"nicepage2.css\" media=\"screen\">\n" +
"<link rel=\"stylesheet\" href=\"Inbox.css\" media=\"screen\">\n" +
"    <script class=\"u-script\" type=\"text/javascript\" src=\"jquery2.js\" defer=\"\"></script>\n" +
"    <script class=\"u-script\" type=\"text/javascript\" src=\"nicepage2.js\" defer=\"\"></script>\n" +
"    <meta name=\"generator\" content=\"Nicepage 3.18.2, nicepage.com\">\n" +
"    <link id=\"u-theme-google-font\" rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i|Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i\">\n" +
"    \n" +
"    \n" +
"    <script type=\"application/ld+json\">{\n" +
"		\"@context\": \"http://schema.org\",\n" +
"		\"@type\": \"Organization\",\n" +
"		\"name\": \"Mailing Website\"\n" +
"}</script>\n" +
"    <meta name=\"theme-color\" content=\"#478ac9\">\n" +
"    <meta property=\"og:title\" content=\"Inbox\">\n" +
"    <meta property=\"og:type\" content=\"website\">\n" +
"  </head>\n" +
"  <body class=\"u-body u-palette-1-base\"><header class=\"u-clearfix u-custom-color-1 u-header u-sticky u-header\" id=\"sec-838e\"><div class=\"u-clearfix u-sheet u-sheet-1\">\n" +
"        <nav class=\"u-hidden-lg u-hidden-xl u-menu u-menu-dropdown u-offcanvas u-offcanvas-shift u-menu-1\" data-submenu-level=\"on-click\">\n" +
"          <div class=\"menu-collapse\" style=\"font-size: 1.25rem; letter-spacing: 0px; font-weight: 700; text-transform: uppercase;\">\n" +
"            <a class=\"u-button-style u-custom-active-border-color u-custom-border u-custom-border-color u-custom-border-radius u-custom-borders u-custom-hover-border-color u-custom-left-right-menu-spacing u-custom-padding-bottom u-custom-text-active-color u-custom-text-color u-custom-text-hover-color u-custom-top-bottom-menu-spacing u-nav-link u-text-active-palette-1-base u-text-hover-palette-2-base\" href=\"#\" style=\"font-size: calc(1em + 6px);\">\n" +
"              <svg><use xmlns:xlink=\"http://www.w3.org/1999/xlink\" xlink:href=\"#menu-hamburger\"></use></svg>\n" +
"              <svg version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\"><defs><symbol id=\"menu-hamburger\" viewBox=\"0 0 16 16\" style=\"width: 16px; height: 16px;\"><rect y=\"1\" width=\"16\" height=\"2\"></rect><rect y=\"7\" width=\"16\" height=\"2\"></rect><rect y=\"13\" width=\"16\" height=\"2\"></rect>\n" +
"</symbol>\n" +
"</defs></svg>\n" +
"            </a>\n" +
"          </div>\n" +
"          <div class=\"u-custom-menu u-nav-container\">\n" +
"            <ul class=\"u-nav u-spacing-2 u-unstyled u-nav-1\"><li class=\"u-nav-item\"><a class=\"u-border-2 u-border-active-custom-color-4 u-border-hover-custom-color-3 u-button-style u-nav-link u-radius-27 u-text-active-custom-color-3 u-text-custom-color-4 u-text-hover-custom-color-3\" href=\"Compose.html\" style=\"padding: 10px 72px;\">Compose</a>\n" +
"</li><li class=\"u-nav-item\"><a class=\"u-border-2 u-border-active-custom-color-4 u-border-hover-custom-color-3 u-button-style u-nav-link u-radius-27 u-text-active-custom-color-3 u-text-custom-color-4 u-text-hover-custom-color-3\" style=\"padding: 10px 72px;\">Inbox</a>\n" +
"</li><li class=\"u-nav-item\"><a class=\"u-border-2 u-border-active-custom-color-4 u-border-hover-custom-color-3 u-button-style u-nav-link u-radius-27 u-text-active-custom-color-3 u-text-custom-color-4 u-text-hover-custom-color-3\" style=\"padding: 10px 72px;\">Sent</a>\n" +
"</li><li class=\"u-nav-item\"><a class=\"u-border-2 u-border-active-custom-color-4 u-border-hover-custom-color-3 u-button-style u-nav-link u-radius-27 u-text-active-custom-color-3 u-text-custom-color-4 u-text-hover-custom-color-3\" style=\"padding: 10px 72px;\">Home</a>\n" +
"</li><li class=\"u-nav-item\"><a class=\"u-border-2 u-border-active-custom-color-4 u-border-hover-custom-color-3 u-button-style u-nav-link u-radius-27 u-text-active-custom-color-3 u-text-custom-color-4 u-text-hover-custom-color-3\" style=\"padding: 10px 72px;\">Trash</a>\n" +
"</li><li class=\"u-nav-item\"><a class=\"u-border-2 u-border-active-custom-color-4 u-border-hover-custom-color-3 u-button-style u-nav-link u-radius-27 u-text-active-custom-color-3 u-text-custom-color-4 u-text-hover-custom-color-3\" style=\"padding: 10px 72px;\">Create New Account</a>\n" +
"</li><li class=\"u-nav-item\"><a class=\"u-border-2 u-border-active-custom-color-4 u-border-hover-custom-color-3 u-button-style u-nav-link u-radius-27 u-text-active-custom-color-3 u-text-custom-color-4 u-text-hover-custom-color-3\" style=\"padding: 10px 72px;\">Log Out</a>\n" +
"</li><li class=\"u-nav-item\"><a class=\"u-border-2 u-border-active-custom-color-4 u-border-hover-custom-color-3 u-button-style u-nav-link u-radius-27 u-text-active-custom-color-3 u-text-custom-color-4 u-text-hover-custom-color-3\" href=\"About.html\" style=\"padding: 10px 72px;\">About</a>\n" +
"</li><li class=\"u-nav-item\"><a class=\"u-border-2 u-border-active-custom-color-4 u-border-hover-custom-color-3 u-button-style u-nav-link u-radius-27 u-text-active-custom-color-3 u-text-custom-color-4 u-text-hover-custom-color-3\" href=\"Page-1.html\" style=\"padding: 10px 72px;\">Inbox</a>\n" +
"</li><li class=\"u-nav-item\"><a class=\"u-border-2 u-border-active-custom-color-4 u-border-hover-custom-color-3 u-button-style u-nav-link u-radius-27 u-text-active-custom-color-3 u-text-custom-color-4 u-text-hover-custom-color-3\" href=\"Page-1.html\" style=\"padding: 10px 72px;\">Inbox</a>\n" +
"</li><li class=\"u-nav-item\"><a class=\"u-border-2 u-border-active-custom-color-4 u-border-hover-custom-color-3 u-button-style u-nav-link u-radius-27 u-text-active-custom-color-3 u-text-custom-color-4 u-text-hover-custom-color-3\" href=\"Page-1.html\" style=\"padding: 10px 72px;\">Inbox</a>\n" +
"</li></ul>\n" +
"          </div>\n" +
"          <div class=\"u-custom-menu u-nav-container-collapse\">\n" +
"            <div class=\"u-black u-container-style u-inner-container-layout u-sidenav u-sidenav-1\" data-offcanvas-width=\"310\">\n" +
"              <div class=\"u-sidenav-overflow\">\n" +
"                <div class=\"u-menu-close u-menu-close-1\"></div>\n" +
"                <ul class=\"u-align-center u-nav u-popupmenu-items u-spacing-0 u-unstyled u-nav-2\"><li class=\"u-nav-item\"><a class=\"u-button-style u-nav-link\" href=\"Compose.html\" style=\"padding: 10px 72px;\">Compose</a>\n" +
"</li><li class=\"u-nav-item\"><a class=\"u-button-style u-nav-link\" style=\"padding: 10px 72px;\">Inbox</a>\n" +
"</li><li class=\"u-nav-item\"><a class=\"u-button-style u-nav-link\" style=\"padding: 10px 72px;\">Sent</a>\n" +
"</li><li class=\"u-nav-item\"><a class=\"u-button-style u-nav-link\" style=\"padding: 10px 72px;\">Home</a>\n" +
"</li><li class=\"u-nav-item\"><a class=\"u-button-style u-nav-link\" style=\"padding: 10px 72px;\">Trash</a>\n" +
"</li><li class=\"u-nav-item\"><a class=\"u-button-style u-nav-link\" style=\"padding: 10px 72px;\">Create New Account</a>\n" +
"</li><li class=\"u-nav-item\"><a class=\"u-button-style u-nav-link\" style=\"padding: 10px 72px;\">Log Out</a>\n" +
"</li><li class=\"u-nav-item\"><a class=\"u-button-style u-nav-link\" href=\"About.html\" style=\"padding: 10px 72px;\">About</a>\n" +
"</li></ul>\n" +
"              </div>\n" +
"            </div>\n" +
"            <div class=\"u-custom-color-4 u-menu-overlay u-opacity u-opacity-30\"></div>\n" +
"          </div>\n" +
"        </nav>\n" +
"        <h3 class=\"u-align-left u-custom-font u-font-georgia u-text u-text-1\">SARJ Mail&nbsp;<span class=\"u-icon u-icon-1\"><svg class=\"u-svg-content\" viewBox=\"0 0 512.002 512.002\" x=\"0px\" y=\"0px\" style=\"width: 1em; height: 1em;\"><g><path style=\"fill:#FFBD51;\" d=\"M495.041,91.628c4.052,3.952,6.323,9.38,6.293,15.04v298.667c0.03,5.66-2.241,11.088-6.293,15.04   l-47.04-47.04L310.188,235.521l180.48-139.52L495.041,91.628z\"></path><path style=\"fill:#FFBD51;\" d=\"M64.001,373.334l-46.933,46.933l-0.107,0.107c-4.052-3.952-6.323-9.38-6.293-15.04V106.668   c-0.027-4.257,1.238-8.423,3.627-11.947l7.04,1.28l180.48,139.52L64.001,373.334z\"></path>\n" +
"</g><g><path style=\"fill:#FFE451;\" d=\"M17.068,420.268l46.933-46.933l137.813-137.813l54.187,41.813l54.187-41.813l137.813,137.813   l47.04,47.04c-3.952,4.052-9.38,6.323-15.04,6.293h-448c-5.66,0.03-11.088-2.241-15.04-6.293L17.068,420.268z\"></path><path style=\"fill:#FFE451;\" d=\"M480.001,85.334c5.66-0.03,11.088,2.242,15.04,6.293l-4.373,4.373l-180.48,139.52l-54.187,41.813   l-54.187-41.813L21.334,96.001l-7.04-1.28c3.98-5.872,10.613-9.388,17.707-9.387H480.001z\"></path>\n" +
"</g><path style=\"fill:#4C5B66;\" d=\"M502.678,84.182c-5.951-6.123-14.139-9.558-22.677-9.515h-448  c-10.623-0.009-20.558,5.254-26.517,14.048c-3.602,5.291-5.514,11.551-5.483,17.952v298.667c-0.069,8.449,3.296,16.564,9.323,22.485  c5.951,6.123,14.139,9.558,22.677,9.515h448c17.632-0.099,31.901-14.368,32-32V106.668C512.07,98.219,508.705,90.104,502.678,84.182  z M256.001,263.862L38.774,96.001h434.453L256.001,263.862z M487.404,412.929c-1.946,1.99-4.62,3.1-7.403,3.072h-448  c-2.877,0.007-5.629-1.174-7.605-3.264c-1.978-1.953-3.082-4.623-3.061-7.403V109.473l164.405,127.04L56.46,365.793l15.083,15.083  l131.2-131.2l46.731,36.117c3.84,2.964,9.195,2.964,13.035,0l46.731-36.117l131.2,131.2l15.083-15.083l-129.28-129.28  l164.427-127.04v295.861C490.672,408.207,489.491,410.955,487.404,412.929L487.404,412.929z\"></path></svg><img></span>\n" +
"        </h3>\n" +
"        <nav class=\"u-dropdown-icon u-hidden-md u-hidden-sm u-hidden-xs u-menu u-menu-dropdown u-offcanvas u-menu-2\">\n" +
"          <div class=\"menu-collapse\" style=\"font-size: 1.25rem;\">\n" +
"            <a class=\"u-button-style u-custom-active-color u-custom-border-radius u-custom-text-active-color u-custom-text-color u-custom-text-hover-color u-nav-link\" href=\"#\">\n" +
"              <svg><use xmlns:xlink=\"http://www.w3.org/1999/xlink\" xlink:href=\"#menu-hamburger\"></use></svg>\n" +
"              <svg version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\"><defs><symbol id=\"menu-hamburger\" viewBox=\"0 0 16 16\" style=\"width: 16px; height: 16px;\"><rect y=\"1\" width=\"16\" height=\"2\"></rect><rect y=\"7\" width=\"16\" height=\"2\"></rect><rect y=\"13\" width=\"16\" height=\"2\"></rect>\n" +
"</symbol>\n" +
"</defs></svg>\n" +
"            </a>\n" +
"          </div>\n" +
"          <div class=\"u-custom-menu u-nav-container\">\n" +
"            <ul class=\"u-nav u-spacing-15 u-unstyled u-nav-3\"><li class=\"u-nav-item\"><a class=\"u-border-2 u-border-custom-color-4 u-border-hover-custom-color-3 u-button-style u-nav-link u-radius-50 u-text-active-custom-color-3 u-text-custom-color-4 u-text-hover-custom-color-3\" href=\"Home.html\" style=\"padding: 10px 60px;\">Home</a>\n" +
"</li><li class=\"u-nav-item\"><a class=\"u-border-2 u-border-custom-color-4 u-border-hover-custom-color-3 u-button-style u-nav-link u-radius-50 u-text-active-custom-color-3 u-text-custom-color-4 u-text-hover-custom-color-3\" href=\"About.html\" style=\"padding: 10px 60px;\">About</a>\n" +
"</li><li class=\"u-nav-item\"><a class=\"u-border-2 u-border-custom-color-4 u-border-hover-custom-color-3 u-button-style u-nav-link u-radius-50 u-text-active-custom-color-3 u-text-custom-color-4 u-text-hover-custom-color-3\" style=\"padding: 10px 60px;\">Account</a><div class=\"u-nav-popup\"><ul class=\"u-h-spacing-35 u-nav u-unstyled u-v-spacing-10 u-nav-4\"><li class=\"u-nav-item\"><a class=\"u-button-style u-custom-color-3 u-hover-custom-color-4 u-nav-link u-text-custom-color-4 u-text-hover-custom-color-3\">Create New Account</a>\n" +
"</li><li class=\"u-nav-item\"><a class=\"u-button-style u-custom-color-3 u-hover-custom-color-4 u-nav-link u-text-custom-color-4 u-text-hover-custom-color-3\">Log Out</a>\n" +
"</li></ul>\n" +
"</div>\n" +
"</li></ul>\n" +
"          </div>\n" +
"          <div class=\"u-custom-menu u-nav-container-collapse\">\n" +
"            <div class=\"u-black u-container-style u-inner-container-layout u-opacity u-opacity-95 u-sidenav\">\n" +
"              <div class=\"u-sidenav-overflow\">\n" +
"                <div class=\"u-menu-close\"></div>\n" +
"                <ul class=\"u-align-center u-nav u-popupmenu-items u-unstyled u-nav-5\"><li class=\"u-nav-item\"><a class=\"u-button-style u-nav-link\" href=\"Home.html\" style=\"padding: 10px 60px;\">Home</a>\n" +
"</li><li class=\"u-nav-item\"><a class=\"u-button-style u-nav-link\" href=\"About.html\" style=\"padding: 10px 60px;\">About</a>\n" +
"</li><li class=\"u-nav-item\"><a class=\"u-button-style u-nav-link\" style=\"padding: 10px 60px;\">Account</a><div class=\"u-nav-popup\"><ul class=\"u-h-spacing-35 u-nav u-unstyled u-v-spacing-10 u-nav-6\"><li class=\"u-nav-item\"><a class=\"u-button-style u-nav-link\">Create New Account</a>\n" +
"</li><li class=\"u-nav-item\"><a class=\"u-button-style u-nav-link\">Log Out</a>\n" +
"</li></ul>\n" +
"</div>\n" +
"</li></ul>\n" +
"              </div>\n" +
"            </div>\n" +
"            <div class=\"u-black u-menu-overlay u-opacity u-opacity-70\"></div>\n" +
"          </div>\n" +
"        </nav>\n" +
"      </div></header>\n" +
"    <section class=\"u-clearfix u-custom-color-1 u-section-1\" id=\"sec-d0f9\">\n" +
"      <div class=\"u-clearfix u-sheet u-sheet-1\">\n" +
"        <h3 class=\"u-text u-text-default u-text-1\">Inbox<br>\n" +
"        </h3>\n" +
"        <div class=\"u-form u-form-1\">\n" +
"          <form action=\"delete_trash\" method=\"GET\" class=\"u-clearfix u-form-custom-backend u-form-spacing-10 u-form-vertical u-inner-form\" source=\"custom\" name=\"form\" style=\"padding: 10px;\" redirect=\"true\">\n" +
"            <input type=\"hidden\" id=\"siteId\" name=\"siteId\" value=\"1636713274\">\n" +
"            <input type=\"hidden\" id=\"pageId\" name=\"pageId\" value=\"820810463\">");
        while(rs.next())
        {
            out.println("<div class=\"u-form-checkbox u-form-group u-form-group-1\">\n" +
"              <input type=\"checkbox\" id=\"checkbox-bd16\" name=\"checkbox\" value="+rs.getString(1)+">\n" +
"              <label for=\"checkbox-bd16\" class=\"u-label u-label-1\">\n" +
"                <a href=message_trash_open?id="+rs.getString(1)+" class=\"u-active-none u-align-left u-border-2 u-border-palette-1-base u-btn u-btn-rectangle u-button-style u-hover-none u-none u-text-custom-color-4 u-text-hover-custom-color-3 u-btn-3\">"+rs.getString(3) +" : " +rs.getString(4)+"</a>\n" +
"              </label>\n" +
"            </div>");
            
        }
        out.print("<div class=\"u-align-center u-form-group u-form-submit\">\n" +
"              <a href=\"#\" class=\"u-btn u-btn-round u-btn-submit u-button-style u-custom-color-3 u-hover-custom-color-4 u-radius-30 u-text-custom-color-4 u-text-hover-custom-color-3 u-btn-2\">Delete</a>\n" +
"              <input type=\"submit\" value=\"submit\" class=\"u-form-control-hidden\">\n" +
"            </div>\n" +
"            \n" +
"            <input type=\"hidden\" value=\"\" name=\"recaptchaResponse\">\n" +
"          </form>\n" +
"        </div>\n" +
"        \n" +
"      </div>\n" +
"    </section>\n" +
"    \n" +
"    \n" +
"    <footer class=\"u-align-center u-clearfix u-footer u-grey-80 u-footer\" id=\"sec-b98e\"><div class=\"u-clearfix u-sheet u-sheet-1\">\n" +
"        <p class=\"u-small-text u-text u-text-variant u-text-1\">Sample text. Click to select the text box. Click again or double click to start editing the text.</p>\n" +
"      </div></footer>\n" +
"    <section class=\"u-backlink u-clearfix u-grey-80\">\n" +
"      <a class=\"u-link\" href=\"https://nicepage.com/website-templates\" target=\"_blank\">\n" +
"        <span>Website Templates</span>\n" +
"      </a>\n" +
"      <p class=\"u-text\">\n" +
"        <span>created with</span>\n" +
"      </p>\n" +
"      <a class=\"u-link\" href=\"https://nicepage.com/wysiwyg-html-editor\" target=\"_blank\">\n" +
"        <span>WYSIWYG HTML Editor</span>\n" +
"      </a>. \n" +
"    </section>\n" +
"  </body>\n" +
"</html>");
                
                
                
            
            ps.close();
            rs.close();

                
            
        }catch(IOException | SQLException e)
        {
            System.out.print("Error : "+e);
        }
    }
}


