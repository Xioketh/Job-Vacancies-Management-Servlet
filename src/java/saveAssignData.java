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
@WebServlet(urlPatterns = {"/saveAssignData"})
public class saveAssignData extends HttpServlet {
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    PreparedStatement pst1;
    ResultSet rs1;
    
    PreparedStatement pst2;
    ResultSet rs2;
    
    PreparedStatement pst3;
    ResultSet rs3;
    
        String name;
        String reg;
        String language;
        String field;
        String cname;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            try {

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            String sid = request.getParameter("sid");
            String jid = request.getParameter("jid");
            String info = request.getParameter("info");
            
                
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/jobfinder", "root", "");
            
            
            //1
            pst = con.prepareStatement("SELECT * FROM `studentdetails` WHERE id="+"'" + sid + "'");
            rs = pst.executeQuery();
            
            
           if (rs.next()) {
            name=rs.getString("name");
            reg=rs.getString("reg");
            language=rs.getString("language");
            field=rs.getString("field");
            }
           
           
           //2
           pst1 = con.prepareStatement("SELECT * FROM `jobs` WHERE id="+"'" + jid + "'");
           rs1 = pst1.executeQuery();
           if (rs1.next()) {
            cname=rs1.getString("companyname");
            } 
                     
            //3
            pst2 = con.prepareStatement("INSERT INTO `assigneddata`(`Sname`,`reg`,`companyName`,`language`,`feild`,`info`)VALUES('" + name + "','" + reg + "','" + cname + "','" + language + "','" + field + "','" + info + "')");        
            pst2.executeUpdate();
            
            
            //4
            pst3 = con.prepareStatement("DELETE FROM `studentdetails` WHERE id ="+"'" + sid + "'");        
            pst3.executeUpdate();
            
            
            out.println("<head><link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM\" crossorigin=\"anonymous\">\n"
                    + "        <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz\" crossorigin=\"anonymous\"></script></head>");

            
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
            out.println("  window.location.href = 'admin Main.html';");
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

           
           
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
