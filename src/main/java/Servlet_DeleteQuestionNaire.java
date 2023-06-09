import DruidUtil.DruidUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Servlet_DeleteQuestionNaire extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        String QuestionNaireId = request.getParameter("QuestionNaireId");

        Connection conn;

        PreparedStatement stmt;

        try {

            conn = DruidUtil.getDataSource().getConnection();

            stmt = conn.prepareStatement("" +
                    "DELETE FROM " +
                    "   questionnaire " +
                    "WHERE " +
                    "   questionnaire_Id = ?"
            );

            stmt.setString(1, QuestionNaireId);

            stmt.executeUpdate();

            stmt.close();

            conn.close();

            response.sendRedirect("Servlet_GetQuestionNaireList?forward=QuestionNaireManager.jsp");

        } catch (Exception e) {

            throw new ServletException(e.getMessage());

        }

    }
}
