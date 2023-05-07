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

        PrintWriter out = response.getWriter();

        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        response.setCharacterEncoding("utf-8");

        response.setContentType("text/html;charset=UTF-8");

        try {

            connection = DruidUtil.getDataSource().getConnection();
            stmt = connection.prepareStatement("select count(*) from user where user_Account=?");
            stmt.setString(1, account);
            rs = stmt.executeQuery();

            rs.next();

            if (rs.getInt(1) == 1) {
                out.println("FALSE\nReason=账号已存在");
            }
            else{

                rs.close();

                stmt.close();

                stmt = connection.prepareStatement("insert into user (user_Name, user_Account, user_Password) values (?,?,?)");

                stmt.setString(1,name);
                stmt.setString(2,account);
                stmt.setString(3,password);

                stmt.executeUpdate();

                stmt.close();

                stmt = connection.prepareStatement("SELECT user_Id FROM user WHERE user_Account = ?");

                stmt.setString(1,account);

                rs = stmt.executeQuery();

                if(rs.next()){

                    out.println("TRUE\nId="+rs.getString(1));

                }
                else{

                    out.println("FALSE\nReason=由于未知原因向数据库插入新账号记录失败");

                }

            }

            rs.close();
            stmt.close();
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
