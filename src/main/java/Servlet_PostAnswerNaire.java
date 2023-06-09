import DruidUtil.DruidUtil;
import Sessions.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

public class Servlet_PostAnswerNaire extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String QN_Id;
        String UserId;

        try {

            //获得问卷ID
            QN_Id = request.getParameter("QuestionNaireId");

            if (QN_Id == null || QN_Id.isEmpty()) {

                throw new Exception();

            }

        } catch (Exception e) {

            throw new ServletException("无效的问卷ID");

        }

        try {

            //获得填写用户ID
            UserId = ((User) request.getSession().getAttribute("User")).Id;

            if (UserId == null || UserId.isEmpty()) {

                throw new Exception();

            }

        } catch (Exception e) {

            throw new ServletException("无效的用户ID");

        }

        AnswerNaire answerNaire = null;

        Connection conn;

        PreparedStatement stmt;

        ResultSet rs;

        try {

            answerNaire = new AnswerNaire(QN_Id, UserId);

            conn = DruidUtil.getDataSource().getConnection();

            stmt = conn.prepareStatement("" +
                    "SELECT " +
                    "   question.question_Id," +
                    "   question.question_Kind " +
                    "FROM" +
                    "   question " +
                    "WHERE" +
                    "   question.questionnaire_Id = ?"
            );

            stmt.setString(1, answerNaire.getQuestionNaireId());

            rs = stmt.executeQuery();

            String questionId;
            String questionKind;

            while (rs.next()) {

                questionId = rs.getString(1);
                questionKind = rs.getString(2);

                switch (questionKind) {

                    case "Enter": {

                        answerNaire.AddQuestion_Enter(
                                new AnswerNaire.Enter(
                                        questionId,
                                        request.getParameter("Answer-" + questionId)
                                )
                        );

                        break;

                    }
                    case "Single": {

                        answerNaire.AddQuestion_Single(
                                new AnswerNaire.Single(
                                        questionId,
                                        request.getParameter("Answer-" + questionId)

                                )
                        );

                        break;

                    }
                    case "Multiple": {

                        answerNaire.AddQuestion_Multiple(
                                new AnswerNaire.Multiple(
                                        questionId,
                                        request.getParameterValues("Answer-" + questionId)

                                )
                        );

                        break;

                    }

                }

            }

            rs.close();

            stmt.close();

            String answerNaireId;

            stmt = conn.prepareStatement("" +
                    "INSERT INTO answernaire(" +
                    "   questionnaire_Id, " +
                    "   user_Id," +
                    "   finish_time" +
                    ") " +
                    "VALUES(" +
                    "   ?," +
                    "   ?," +
                    "   NOW()" +
                    ")"
            );

            stmt.setString(1, answerNaire.getQuestionNaireId());

            stmt.setString(2, answerNaire.getCreatorId());

            stmt.executeUpdate();

            stmt.close();

            stmt = conn.prepareStatement("SELECT LAST_INSERT_ID()");

            rs = stmt.executeQuery();

            rs.next();

            answerNaireId = rs.getString(1);

            rs.close();

            stmt.close();

            LinkedList<AnswerNaire.Enter> question_Enter = answerNaire.getEnters();

            for (AnswerNaire.Enter item : question_Enter) {

                stmt = conn.prepareStatement("" +
                        "INSERT INTO answer(" +
                        "   answernaire_Id, " +
                        "   question_Id" +
                        ") " +
                        "VALUES(" +
                        "   ?," +
                        "   ?" +
                        ")"
                );

                stmt.setString(1, answerNaireId);
                stmt.setString(2, item.getId());

                stmt.executeUpdate();

                stmt.close();

                stmt = conn.prepareStatement("" +
                        "INSERT INTO answer_enter(" +
                        "   answer_Id, " +
                        "   answer_Cont" +
                        ") " +
                        "VALUES(" +
                        "   LAST_INSERT_ID()," +
                        "   ?" +
                        ")"
                );

                stmt.setString(1, item.getAnswerCont());

                stmt.executeUpdate();

                stmt.close();

            }

            LinkedList<AnswerNaire.Single> question_Single = answerNaire.getSingles();

            for (AnswerNaire.Single item : question_Single) {

                stmt = conn.prepareStatement("" +
                        "INSERT INTO answer(" +
                        "   answernaire_Id, " +
                        "   question_Id" +
                        ") " +
                        "VALUES(" +
                        "   ?," +
                        "   ?" +
                        ")"
                );

                stmt.setString(1, answerNaireId);
                stmt.setString(2, item.getId());

                stmt.executeUpdate();

                stmt.close();

                stmt = conn.prepareStatement("" +
                        "INSERT INTO answer_option(" +
                        "   answer_Id, " +
                        "   questionoption_Id" +
                        ") " +
                        "VALUES(" +
                        "   LAST_INSERT_ID()," +
                        "   ?" +
                        ")"
                );

                stmt.setString(1, item.getSelectOptionId());

                stmt.executeUpdate();

                stmt.close();

            }

            String answerId;

            String[] options;

            LinkedList<AnswerNaire.Multiple> question_Multiple = answerNaire.getMultiples();

            for (AnswerNaire.Multiple item : question_Multiple) {

                stmt = conn.prepareStatement("" +
                        "INSERT INTO answer(" +
                        "   answernaire_Id, " +
                        "   question_Id" +
                        ") " +
                        "VALUES(" +
                        "   ?," +
                        "   ?" +
                        ")"
                );

                stmt.setString(1, answerNaireId);
                stmt.setString(2, item.getId());

                stmt.executeUpdate();

                stmt.close();

                stmt = conn.prepareStatement("SELECT LAST_INSERT_ID()");

                rs = stmt.executeQuery();

                rs.next();

                answerId = rs.getString(1);

                rs.close();

                stmt.close();

                options = item.getSelectOptionIds();

                for (String option : options) {

                    stmt = conn.prepareStatement("" +
                            "INSERT INTO answer_option(" +
                            "   answer_Id, " +
                            "   questionoption_Id" +
                            ") " +
                            "VALUES (" +
                            "   ?," +
                            "   ?" +
                            ")"
                    );

                    stmt.setString(1, answerId);

                    stmt.setString(2, option);

                    stmt.executeUpdate();

                    stmt.close();

                }

            }

            conn.close();

        } catch (Exception e) {

            throw new ServletException(e.getMessage());

        }

        response.sendRedirect("./Servlet_LookThroughQuestionnaires");

    }

}