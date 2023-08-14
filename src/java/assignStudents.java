

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

/**
 *
 * @author Kethaka
 */
@WebServlet(urlPatterns = {"/assignStudents"})
public class assignStudents extends HttpServlet {

    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    PreparedStatement pst1;
    ResultSet rs1;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/jobfinder", "root", "");

            pst = con.prepareStatement("SELECT * FROM `studentdetails`");
            rs = pst.executeQuery();

            pst1 = con.prepareStatement("SELECT * FROM `jobs`");
            rs1 = pst1.executeQuery();
            out.println("<head><link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM\" crossorigin=\"anonymous\">\n"
                    + "<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz\" crossorigin=\"anonymous\"></script></head>");

            out.println("<body class=\" me-0\">");

            
            out.println("<div class=\"row me-0\">");
            out.println("<div class=\"col-10 border border-0 \">");
            out.println("<h2 class=\"mt-2 ms-2 text-primary\";>Assign Students to Jobs</h2>");
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
            out.println("  window.location.href = 'admin Main.html';");
            out.println("}");
            out.println("</script>");
            
            out.println("</div>");
            
            out.println("</div>");
            
            
            
              
            out.println("<div class=\"row me-0\">");

            out.println("<div class=\"col-6 ms-5\">");
            out.println("<div class=\"row\">");
            //Table 1
            out.println("<table width=10% border= 1 class=\"mt-5 ms-4\">");
            out.println("<thead>");
            out.println("<tr><td colspan=4 ");
            out.println("<center><h4 class=\" ms-2\";>Students Request Details</h4></center>");
            out.println("</td></tr>");
            out.println("<tr>");
            out.println("<th scope=\"col\">ID</th>");
            out.println("<th>Student name</th>");
            out.println("<th scope=\"col\">Register No</th>");
            out.println("<th scope=\"col\">Language</th>");
            out.println("<th scope=\"col\">Frameworks</th>");
            out.println("<th scope=\"col\">Interest Field</th>");

            out.println("</tr>");
            out.println("</thead>");
            while (rs.next()) {
                out.println("<tbody>");
                out.println("<tr>");
                out.println("<td>" + rs.getString("id") + "</td> ");
                out.println("<td>" + rs.getString("name") + "</td> ");
                out.println("<td>" + rs.getString("reg") + "</td> ");
                out.println("<td>" + rs.getString("language") + "</td> ");
                out.println("<td>" + rs.getString("front") + "</td> ");
                out.println("<td>" + rs.getString("field") + "</td> ");
                out.println("</tr>");
                out.println("</tbody>");

            }
            out.println("</table>");

            out.println("</div>");

            out.println("<div class=\"row\">");

            //Table 2
            out.println("<table width=50% border= 1 class=\"mt-5 ms-4\">");
            out.println("<thead>");
            out.println("<tr><td colspan=4 ");
            out.println("<center><h4 class=\" ms-2\";>Job Vacancy Details</h4></center>");
            out.println("</td></tr>");
            out.println("<tr>");
            out.println("<th scope=\"col\">ID</th>");
            out.println("<th>Company Name</th>");
            out.println("<th scope=\"col\">Experience Level</th>");
            out.println("<th scope=\"col\">Job Field</th>");
            out.println("<th scope=\"col\">Reccomond Language</th>");
            out.println("<th scope=\"col\">Other Info</th>");

            out.println("</tr>");
            out.println("</thead>");
            while (rs1.next()) {
                out.println("<tbody>");
                out.println("<tr>");
                out.println("<td>" + rs1.getString("id") + "</td> ");
                out.println("<td>" + rs1.getString("companyname") + "</td> ");
                out.println("<td>" + rs1.getString("ex") + "</td> ");
                out.println("<td>" + rs1.getString("jobfor") + "</td> ");
                out.println("<td>" + rs1.getString("language") + "</td> ");
                out.println("<td>" + rs1.getString("other") + "</td> ");
                out.println("</tr>");
                out.println("</tbody>");

            }
            out.println("</table>");
            out.println("</div>");

            out.println("</div>");

            out.println("<div class=\"col-4 ms-5 mt-5 border border-1 border-primary rounded-3 w-15\">");

            //form
            out.println("  <form action=\"saveAssignData\" method=\"POST\">\n" +
"\n" +
"                        <div class=\"form-group mb-3 mt-3 text-center\">   \n" +
"                            <h2>Assign Form</h2>  \n" +
"                        </div>\n" +
"\n" +
"                        <div class=\"row mt-2\">\n" +
"                            <div class=\"form-group col-4 \">      \n" +
"                                <label>Student Request ID</label>\n" +
"                                <input type=\"text\" class=\"form-control mb-3\"  name=\"sid\" required>\n" +
"\n" +
"                             \n" +
"                            </div>\n" +
"                            <div class=\"form-group col-1\">      \n" +
"                            </div>\n" +
"                            <div class=\"form-group col-4 me-5\">      \n" +
"                                \n" +
"                                    <label>Job Opportunity ID</label>\n" +
"                                    <input type=\"text\" class=\"form-control mb-3\"  name=\"jid\" required>\n" +
"                                \n" +
"                            </div>\n" +
"                        </div> \n" +
"\n" +
"                        <div class=\"form-group\">   \n" +
"                            <label>Information</label>\n" +
"                            <input type=\"text\" class=\"form-control mb-3\" name=\"info\" required>\n" +
"                        </div>\n" +
"\n" +
"                        <div class=\"form-group\">   \n" +
"                            <button type=\"submit\" class=\"btn btn-primary\">Save</button>\n" +
"                        </div>\n" +
"                    </form>");
            out.println("</div>");
            out.println("</div>");
            out.println("</body>");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(assignStudents.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(assignStudents.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
