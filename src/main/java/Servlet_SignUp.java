import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class Servlet_SignUp extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String name = request.getParameter("name");
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
            stmt = connection.prepareStatement("select count(*) from user where uAccount=?");
            stmt.setString(1, account);
            rs = stmt.executeQuery();

            rs.next();

            if (rs.getInt(1) == 1) {
                out.println("<div style='text-align : center'>账号已存在！</div>");
            }
            else{

                stmt = connection.prepareStatement("insert into user (uName, uAccount, uPassword) values (?,?,?)");

                stmt.setString(1,name);
                stmt.setString(2,account);
                stmt.setString(3,password);

                stmt.executeUpdate();

                out.println("<div style='text-align : center'>注册成功！</div>");
            }

            rs.close();
            stmt.close();
            connection.close();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

}
