import DruidUtil.DruidUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Servlet_DropUser extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        Connection conn = null;

        PreparedStatement stmt = null;

        try {

            conn = DruidUtil.getDataSource().getConnection();

            stmt = conn.prepareStatement("" +
                    "DELETE FROM " +
                    "   `user` " +
                    "WHERE " +
                    "   user_Id = ?"
            );

            stmt.setString(1, request.getParameter("UserId"));

            stmt.executeUpdate();

            stmt.close();

            conn.close();

        } catch (Exception e) {

            throw new ServletException(e.getMessage());

        }

    }
}
