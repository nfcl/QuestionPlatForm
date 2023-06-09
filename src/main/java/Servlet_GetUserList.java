import DruidUtil.DruidUtil;
import Sessions.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

public class Servlet_GetUserList extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        Connection conn = null;

        PreparedStatement stmt = null;

        ResultSet rs = null;

        try {

            conn = DruidUtil.getDataSource().getConnection();

            stmt = conn.prepareStatement("" +
                    "SELECT " +
                    "   user_Id," +
                    "   user_Name," +
                    "   user_IsAdmin " +
                    "FROM " +
                    "   user " +
                    "WHERE " +
                    "   user_Id != ?" +
                    "   AND user_IsAdmin = 'False'"
            );

            stmt.setString(1, ((User) request.getSession().getAttribute("User")).Id);

            rs = stmt.executeQuery();

            LinkedList<User> userList = new LinkedList<>();

            while (rs.next()) {

                userList.add(
                        new User(
                                rs.getString(1),
                                rs.getString(2),
                                rs.getString(3)
                        )
                );

            }

            rs.close();

            stmt.close();

            conn.close();

            request.setAttribute("UserList", userList);

            request.getRequestDispatcher("./UserManager.jsp").forward(request, response);

        } catch (Exception e) {

            throw new ServletException(e.getMessage());

        }

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        doPost(req, resp);

    }
}
