import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

public class Servlet_PostAnswerNaire extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获得问卷ID
        String QN_Id = request.getParameter("QuestionNaireId");
        //获得输出流
        PrintWriter out = response.getWriter();

        out.println(QN_Id);

        AnswerNaire answerNaire = null;

        try {

            answerNaire = new AnswerNaire(QN_Id);

            answerNaire.QueryAnswer(request);

            answerNaire.RecordAnswerNaire(request.getSession().getAttribute("User_Id").toString());

        } catch (Exception e) {

            throw new RuntimeException(e);

        }

    }

}
