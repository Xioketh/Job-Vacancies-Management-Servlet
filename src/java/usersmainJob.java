/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Kethaka
 */
@WebServlet(urlPatterns = {"/usersmainJob"})
public class usersmainJob extends HttpServlet {

   
   
     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                
        try {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            
          RequestDispatcher dispatcher = request.getRequestDispatcher("/sendJobData.html");
          dispatcher.forward(request, response);
        

            
            
            
        } catch (Exception e) {
        }
    }

}
