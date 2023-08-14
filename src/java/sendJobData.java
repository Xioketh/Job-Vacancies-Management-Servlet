

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

/**
 *
 * @author Kethaka
 */
@WebServlet(urlPatterns = {"/sendJobData"})
public class sendJobData extends HttpServlet {

    

     Connection con;
    PreparedStatement pst;
    ResultSet rs;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        try {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/jobfinder", "root", "");
        
            String cname = request.getParameter("name");
            String ex = request.getParameter("ex");
            String field = request.getParameter("field");
            String language=request.getParameter("language");
            String other=request.getParameter("other");
//            
            pst = con.prepareStatement("INSERT INTO `jobs`(`companyname`,`ex`,`jobfor`,`language`,`other`)VALUES('" + cname + "','" + ex + "','" + field + "','" + language + "','" + other + "')");
//                      
            pst.executeUpdate();

            out.println("<head><link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM\" crossorigin=\"anonymous\">\n" +
"        <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz\" crossorigin=\"anonymous\"></script></head>");
            
            out.println("<div class=\"row me-0\">");
            out.println("<div class=\"col-10 border border-0 \">");
//            out.println("<h2 class=\"mt-2 ms-2 text-primary\";>Assign Students to Jobs</h2>");
            out.println("</div>");

            out.println("<div class=\"col-1 border border-0 mt-3\">");

            out.println("<button class=\"btn border border-1\" onclick=\"redirectToPage1()\">HOME");
            out.println("</button>");
            out.println("</div>");

            out.println("<div class=\"col-1 border border-0 mt-3 \">");
            out.println("<button class=\"btn border border-2 border-danger\" onclick=\"redirectToPage()\">LOG OUT");
            out.println("</button>");
            
            out.println("<script>");
            out.println("function redirectToPage() {");
            out.println("  window.location.href = 'index.html';");
            out.println("}");
            out.println("function redirectToPage1() {");
            out.println("  window.location.href = 'usersMain.html';");
            out.println("}");
            out.println("</script>");
            
            out.println("</div>");
            
            out.println("</div>");
            out.println("       <div class=\"container border border-0 position-absolute\" style=\"left: 50px;\">\n"
                    + "\n"
                    + "                <div class=\"card border border-0 mt-3\">\n"
                    + "                    <h3 class=\"text-success\">Successful!</h3>\n"
                    + "                    <!-- <button class=\"btn btn-success\" style=\"width: 400px;\">Successfull</button> -->\n"
                    + "                </div>\n"
                    + "\n"
                    + "            </div>");
        
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(sendData.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, response);
        } catch (SQLException ex) {
            Logger.getLogger(sendData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
