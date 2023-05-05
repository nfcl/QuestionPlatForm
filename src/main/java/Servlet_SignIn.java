import java.io.*;
import jakarta.servlet.http.*;

public class Servlet_SignIn extends HttpServlet {

    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String account = request.getParameter("account");
        String password = request.getParameter("password");

        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1> 账号 : " + account + "</h1>");
        out.println("<h1> 密码 : " + password + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {

    }

}
