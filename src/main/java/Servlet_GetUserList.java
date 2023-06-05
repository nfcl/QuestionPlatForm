import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Servlet_GetUserList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获得输出流
        PrintWriter out = response.getWriter();

        ResultSet rs = null;

        PreparedStatement stmt = null;

        Connection conn = null;

        try {

            conn = DruidUtil.getDataSource().getConnection();

            stmt = conn.prepareStatement("" +
                "SELECT user_Id,user_Name " +
                "FROM user"
            );

            rs = stmt.executeQuery();

            while(rs.next()){

                out.println("" +
                    "<li class=\"UserList-li\">" +
                    rs.getString(2) +
                    "<input type=\"button\" value=\"删除\" onclick=\"DropUser("+rs.getString(1)+")\">" +
                    "</li>"
                );

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
