import DruidUtil.DruidUtil;
import Sessions.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Servlet_SignIn extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        try {

            String account = request.getParameter("Account");

            if (account==null||account.isEmpty()) {

                throw new Exception("账号为空");

            }

            String password = request.getParameter("Password");

            if (password==null||password.isEmpty()) {

                throw new Exception("密码为空");

            }

            //通过连接池获得连接
            Connection connection = DruidUtil.getDataSource().getConnection();
            //设置SQL语句
            PreparedStatement stmt = connection.prepareStatement("" +
                    "SELECT " +
                    "   user_Id," +
                    "   user_Name," +
                    "   user_IsAdmin " +
                    "FROM user " +
                    "WHERE user_Account=? AND user_Password=?"
            );
            stmt.setString(1, account);
            stmt.setString(2, password);
            //查询结果
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                User user = new User();

                user.Id = rs.getString(1);
                user.Name = rs.getString(2);
                user.IsAdmin = rs.getString(3);
                request.getSession().setAttribute("User", user);
                response.sendRedirect("./SignIn.jsp");

            } else {

                throw new Exception("账号或密码错误");

            }

            rs.close();
            stmt.close();
            connection.close();

        } catch (Exception e) {

            request.setAttribute("SignInFalseReason",e.getMessage());
            request.getRequestDispatcher("./SignIn.jsp").forward(request,response);

        }

    }

}