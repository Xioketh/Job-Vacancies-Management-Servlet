/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Kethaka
 */
@WebServlet(urlPatterns = {"/assignedData"})
public class assignedData extends HttpServlet {

    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/jobfinder", "root", "");

            pst = con.prepareStatement("SELECT * FROM `assigneddata`");
            rs = pst.executeQuery();
            
            out.println("<head><link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM\" crossorigin=\"anonymous\">\n"
                    + "<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz\" crossorigin=\"anonymous\"></script></head>");

            out.println("<body class=\" me-0\">");

            
            out.println("<div class=\"row me-0\">");
            out.println("<div class=\"col-10 border border-0 \">");
            out.println("<h2 class=\"mt-2 ms-2 text-primary\";>Assigned Data</h2>");
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
//            out.println("<center><h4 class=\" ms-2\";>Students Request Details</h4></center>");
            out.println("</td></tr>");
            out.println("<tr>");
            out.println("<th scope=\"col\">ID</th>");
            out.println("<th>Student name</th>");
            out.println("<th scope=\"col\">Register No</th>");
            out.println("<th scope=\"col\">Company name</th>");
            out.println("<th scope=\"col\">Language</th>");
            out.println("<th scope=\"col\">Job Field</th>");
            out.println("<th scope=\"col\">information</th>");

            out.println("</tr>");
            out.println("</thead>");
            while (rs.next()) {
                out.println("<tbody>");
                out.println("<tr>");
                out.println("<td>" + rs.getString("id") + "</td> ");
                out.println("<td>" + rs.getString("Sname") + "</td> ");
                out.println("<td>" + rs.getString("reg") + "</td> ");
                out.println("<td>" + rs.getString("companyName") + "</td> ");
                out.println("<td>" + rs.getString("language") + "</td> ");
                out.println("<td>" + rs.getString("feild") + "</td> ");
                out.println("<td>" + rs.getString("info") + "</td> ");
                out.println("</tr>");
                out.println("</tbody>");

            }
            out.println("</table>");

            out.println("</div>");
            out.println("</div>");
            
            out.println("<div class=\"col-1 ms-5 mt-5 rounded-3 w-15\">");
            
            
            out.println("</div>");
            out.println("<div class=\"col-4 ms-5 mt-5 rounded-3 w-15 \">");
            //form
            out.println("<form action=\"download\" method=\"POST\">\n" +
"\n" +
"            <div class=\"form-group mb-3 mt-3\">\n" +
"                <h4>Download PDF</h4>\n" +
"            </div>\n" +
"        \n" +
"            <div class=\"row mt-2\">\n" +
"                <div class=\"form-group col-4\">\n" +
"                    <label>Assign Data ID</label>\n" +
"                    <input type=\"text\" class=\"form-control mb-3\" name=\"id\" required>\n" +
"                </div>\n" +
"            </div>\n" +
"\n" +
"            <button class=\"btn btn-success\" type=\"submit\">Download</button>\n" +
"        \n" +
"        </form> ");
            out.println("</div>");
            
            out.println("<div class=\"col-1 ms-5 mt-5 rounded-3\">"); 
            out.println("</div>");
            
            out.println("</div>");
            out.println("</body>");

            
            
        }
             catch (Exception e) {
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
