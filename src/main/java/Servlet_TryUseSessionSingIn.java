import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Servlet_TryUseSessionSingIn extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String user_Id = null;
        //获得输出流
        PrintWriter out = response.getWriter();

        try{

            user_Id = request.getSession().getAttribute("User_Id").toString();

        }
        catch (Exception e){

            return;

        }

        Connection conn;

        PreparedStatement stmt;

        ResultSet rs;

        try {

            conn = DruidUtil.getDataSource().getConnection();

            stmt = conn.prepareStatement("SELECT user_Name FROM user WHERE user_Id = ?");

            stmt.setString(1,user_Id);

            rs = stmt.executeQuery();

            if(rs.next()){

                out.println(rs.getString(1));

            }

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

    }
}
