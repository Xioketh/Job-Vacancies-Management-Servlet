
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
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
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

/**
 *
 * @author Kethaka
 */
@WebServlet(urlPatterns = {"/download"})
public class download extends HttpServlet {

    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    String sname;
    String reg;
    String companyName;
    String language;
    String feild;
    String info;

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
        processRequest(request, response);

        try {

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            String id;
            id = request.getParameter("id");

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/jobfinder", "root", "");

            pst = con.prepareStatement("SELECT * FROM `assigneddata` WHERE id=" + "'" + id + "'");
            rs = pst.executeQuery();

            if (rs.next()) {
                sname = rs.getString("Sname");
                reg = rs.getString("reg");
                companyName = rs.getString("companyName");
                language = rs.getString("language");
                feild = rs.getString("feild");
                info = rs.getString("info");
                
          PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

     
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(100, 700);
            contentStream.showText("                        Assign Students Data");
            contentStream.newLine();
            contentStream.moveTextPositionByAmount(0, -50);
            contentStream.showText("Name: " + sname);
            contentStream.newLine();
            contentStream.moveTextPositionByAmount(0, -15);
            contentStream.showText("Registration: " + reg);
            contentStream.newLine();
            contentStream.moveTextPositionByAmount(0, -15);
            contentStream.showText("Company Name: " + companyName);
            contentStream.newLine();
            contentStream.moveTextPositionByAmount(0, -15);
            contentStream.showText("Language: " + language);
            contentStream.newLine();
            contentStream.moveTextPositionByAmount(0, -15);
            contentStream.showText("Field: " + feild);
            contentStream.newLine();
            contentStream.moveTextPositionByAmount(0, -15);
            contentStream.showText("Info: " + info);
            contentStream.newLine();
            contentStream.moveTextPositionByAmount(0, -25);
            contentStream.showText("Thank You");
            contentStream.endText();
            contentStream.close();

                String filePath = "C:\\Users\\Kethaka\\Desktop\\file.pdf";
                

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=\"file.pdf\"");


            document.save(new File(filePath));
             System.out.println("PDF generated successfully. File saved at: " + filePath);
            document.close();
            }  

        } catch (Exception e) {
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
