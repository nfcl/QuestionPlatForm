import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Servlet_DropUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ResultSet rs = null;

        PreparedStatement stmt = null;

        Connection conn = null;

        try {

            conn = DruidUtil.getDataSource().getConnection();

            stmt = conn.prepareStatement("DELETE FROM `user` WHERE user_Id=?");

            stmt.setString(1,request.getParameter("UserId"));

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
