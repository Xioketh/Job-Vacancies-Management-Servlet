
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
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
@WebServlet(urlPatterns = {"/usersmain"})
public class usersmain extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            String stu = request.getParameter("stu");

            RequestDispatcher dispatcher = request.getRequestDispatcher("/studentData.html");
            dispatcher.forward(request, response);

        } catch (Exception e) {
        }
    }

}
