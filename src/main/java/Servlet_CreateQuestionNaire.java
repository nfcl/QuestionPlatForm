import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class Servlet_CreateQuestionNaire extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获得输出流
        PrintWriter out = response.getWriter();

        QuestionNaire naire = null;

        try {

            naire = new QuestionNaire(request);

        } catch (Exception e) {

            out.println(e.getMessage());

            throw new RuntimeException(e);

        }

        try {

            naire.RecordQuestionNaire();

        } catch (SQLException e) {

            out.println(e.getMessage());

            throw new RuntimeException(e);
        }

    }
}
