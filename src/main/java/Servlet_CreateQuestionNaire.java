import Sessions.QuestionNaire;
import Sessions.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.util.LinkedList;

public class Servlet_CreateQuestionNaire extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        try {

            String questionNaireTitle = request.getParameter("QuestionNaire-Title");

            String questionNaireCreatorId = ((User) request.getSession().getAttribute("User")).Id;

            int questionNum = 0;

            LinkedList<String> question_Enter = new LinkedList<>();
            LinkedList<String> question_Single = new LinkedList<>();
            LinkedList<String[]> question_Single_Option = new LinkedList<>();
            LinkedList<String> question_Multiple = new LinkedList<>();
            LinkedList<String[]> question_Multiple_Option = new LinkedList<>();

            try {

                questionNum = Integer.parseInt(request.getParameter("QuestionNum"));

                if (questionNum == 0) {

                    throw new Exception();

                }

            } catch (Exception e) {

                throw new Exception("问卷为空");

            }

            for (int i = 0; i < questionNum; ++i) {

                switch (request.getParameter("QuestionNaire-Question-Kind-" + i)) {

                    case "Enter": {

                        question_Enter.add(request.getParameter("QuestionNaire-Question-Title-" + i));

                        break;
                    }
                    case "Single": {

                        question_Single.add(request.getParameter("QuestionNaire-Question-Title-" + i));
                        question_Single_Option.add(request.getParameterValues("QuestionNaire-OptionCont-" + i));

                        break;
                    }
                    case "Multiple": {

                        question_Multiple.add(request.getParameter("QuestionNaire-Question-Title-" + i));
                        question_Multiple_Option.add(request.getParameterValues("QuestionNaire-OptionCont-" + i));

                        break;
                    }

                }

            }

            response.sendRedirect("./Servlet_LookThroughQuestionnaires");

        } catch (Exception e) {

            throw new ServletException(e.getMessage());
        }

    }
}