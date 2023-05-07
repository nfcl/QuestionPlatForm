import java.io.*;
import java.sql.*;

import jakarta.servlet.http.*;

import javax.sql.DataSource;

public class Servlet_SignIn extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String account = request.getParameter("account");
        String password = request.getParameter("password");

        PrintWriter out = response.getWriter();

        if(account == null || account.isEmpty()){

            out.println("<div style='text-align : center'>账号为空！</div>");

            return;

        }
        else if(password == null || password.isEmpty()){

            out.println("<div style='text-align : center'>密码为空！</div>");

            return;

        }

        //设置输出字符集,否则中文可能会乱码
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");

        try {


            DataSource ds = DruidUtil.getDataSource();
            //通过连接池获得连接
            Connection connection = ds.getConnection();
            //设置SQL语句
            PreparedStatement stmt = connection.prepareStatement("select count(*) from user where user_Account=? and user_Password=?");
            stmt.setString(1, account);
            stmt.setString(2, password);
            //查询结果
            ResultSet rs = stmt.executeQuery();

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

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
