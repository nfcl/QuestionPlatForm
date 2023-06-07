import DruidUtil.DruidUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Servlet_SignUp extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            request.getSession().removeAttribute("User");

            String name = request.getParameter("Name");

            if (name == null || name.isEmpty()) {

                throw new Exception("名称为空");

            }

            String account = request.getParameter("Account");

            if (account == null || account.isEmpty()) {

                throw new Exception("账号为空");

            }

            String password = request.getParameter("Password");

            if (password == null || password.isEmpty()) {

                throw new Exception("密码为空");

            }

            connection = DruidUtil.getDataSource().getConnection();

            stmt = connection.prepareStatement("" +
                    "SELECT " +
                    "   count(user_Id) " +
                    "FROM user " +
                    "WHERE user_Account=?"
            );

            stmt.setString(1, account);

            rs = stmt.executeQuery();

            rs.next();

            if (rs.getInt(1) == 1) {

                rs.close();

                stmt.close();

                throw new Exception("账号已存在");

            } else {

                rs.close();

                stmt.close();

                stmt = connection.prepareStatement("" +
                        "INSERT INTO user (" +
                        "   user_Name," +
                        "   user_Account," +
                        "   user_Password" +
                        ") " +
                        "values (" +
                        "   ?," +
                        "   ?," +
                        "   ?" +
                        ")"
                );

                stmt.setString(1, name);
                stmt.setString(2, account);
                stmt.setString(3, password);

                stmt.executeUpdate();

                stmt.close();

                response.sendRedirect("./SignIn.jsp");

            }

            connection.close();

        } catch (Exception e) {

            request.setAttribute("SignUpFalseReason", e.getMessage());

            request.getRequestDispatcher("./SignUp.jsp").forward(request, response);

        }

    }

}
