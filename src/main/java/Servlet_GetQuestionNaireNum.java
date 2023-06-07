import DruidUtil.DruidUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class Servlet_GetQuestionNaireNum extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();

        try {

            Connection conn = DruidUtil.getDataSource().getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT COUNT(questionnaire.questionnaire_Id) FROM questionnaire");

            ResultSet rs = ps.executeQuery();

            rs.next();

            out.println(rs.getInt(1));

            rs.close();

            ps.close();

            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
