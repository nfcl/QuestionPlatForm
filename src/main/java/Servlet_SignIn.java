import java.io.*;
import java.sql.*;

import jakarta.servlet.http.*;

public class Servlet_SignIn extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String account = request.getParameter("account");
        String password = request.getParameter("password");

        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        response.setCharacterEncoding("utf-8");

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        try {

            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/questionplatform", "root", "159357zjy");
            stmt = connection.prepareStatement("select count(*) from user where uAccount=? and uPassword=?");
            stmt.setString(1, account);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

            rs.next();

            if (rs.getInt(1) == 1) {
                out.println("<div style='text-align : center'>登录成功！</div>");
            }
            else{

                out.println("<div style='text-align : center'>用户名或密码错误，请重新输入！</div>");
            }

            rs.close();
            stmt.close();
            connection.close();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

}
