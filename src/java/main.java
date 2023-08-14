
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
@WebServlet(urlPatterns = {"/main"})
public class main extends HttpServlet {



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            String name = request.getParameter("name");
            String password = request.getParameter("password");
            
            
            if(name.equals("admin") && password.equals("admin")){
                RequestDispatcher dispatcher = request.getRequestDispatcher("/admin Main.html");
                dispatcher.forward(request, response);
//                out.println(name);
            }else if(name.equals("user") && password.equals("user")){
                  RequestDispatcher dispatcher = request.getRequestDispatcher("/usersMain.html");
                dispatcher.forward(request, response);
            }else{
                out.println("login Fail!");
            }
         
         
            
        } catch (Exception e) {
        }
     
      
    }

}
