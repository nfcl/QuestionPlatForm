import java.io.*;
import java.sql.*;

import jakarta.servlet.http.*;

import javax.sql.DataSource;

public class Servlet_SignIn extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String account = request.getParameter("account");
        String password = request.getParameter("password");

        PrintWriter out = response.getWriter();

        //设置输出字符集,否则中文可能会乱码
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");

        try {

            //通过连接池获得连接
            Connection connection = DruidUtil.getDataSource().getConnection();
            //设置SQL语句
            PreparedStatement stmt = connection.prepareStatement("" +
                    "SELECT " +
                    "   user.user_Id," +
                    "   user.user_Name " +
                    "FROM user " +
                    "WHERE user_Account=? AND user_Password=?"
            );
            stmt.setString(1, account);
            stmt.setString(2, password);
            //查询结果
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){

                out.println(String.format("Id:%s&Name:%s",rs.getString(1),rs.getString(2)));

            }
            else{

                out.println("-1");

            }

            rs.close();
            stmt.close();
            connection.close();

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

    }

}
