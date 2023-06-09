import DruidUtil.DruidUtil;
import Sessions.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.jakartaee.commons.lang3.tuple.ImmutablePair;
import org.apache.tomcat.jakartaee.commons.lang3.tuple.Pair;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

public class Servlet_CreateQuestionNaire extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        try {

            String questionNaireTitle = request.getParameter("QuestionNaire-Title");

            String questionNaireCreatorId = ((User) request.getSession().getAttribute("User")).Id;

            int questionNum = 0;

            LinkedList<String> question_Enter = new LinkedList<>();
            LinkedList<Pair<String, String[]>> question_Single = new LinkedList<>();
            LinkedList<Pair<String, String[]>> question_Multiple = new LinkedList<>();

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

                        question_Single.add(
                                new ImmutablePair<>(
                                        request.getParameter("QuestionNaire-Question-Title-" + i),
                                        request.getParameterValues("QuestionNaire-OptionCont-" + i)
                                )
                        );

                        break;
                    }
                    case "Multiple": {

                        question_Multiple.add(
                                new ImmutablePair<>(
                                        request.getParameter("QuestionNaire-Question-Title-" + i),
                                        request.getParameterValues("QuestionNaire-OptionCont-" + i)
                                )
                        );

                        break;
                    }

                }

            }

            Connection conn;

            PreparedStatement stmt;

            ResultSet rs;

            conn = DruidUtil.getDataSource().getConnection();

            stmt = conn.prepareStatement("" +
                    "INSERT INTO questionnaire(" +
                    "   questionnaire_Name," +
                    "   user_Id, " +
                    "   questionnaire_Starttime, " +
                    "   questionnaire_Endtime, " +
                    "   questionnaire_Limittime " +
                    ") " +
                    "VALUES(" +
                    "   ?," +
                    "   ?," +
                    "   NOW()," +
                    "   DATE_ADD(NOW(), interval 1 year)," +
                    "   NULL" +
                    ")"
            );

            stmt.setString(1, questionNaireTitle);
            stmt.setString(2, questionNaireCreatorId);

            stmt.executeUpdate();

            stmt.close();

            stmt = conn.prepareStatement("SELECT LAST_INSERT_ID()");

            rs = stmt.executeQuery();

            rs.next();

            String questionNaireId = rs.getString(1);

            rs.close();

            stmt.close();

            for (String enter : question_Enter) {

                stmt = conn.prepareStatement("" +
                        "INSERT INTO question(" +
                        "   question_Cont, " +
                        "   question_Kind, " +
                        "   questionnaire_Id" +
                        ") " +
                        "VALUES(" +
                        "   ?," +
                        "   ?," +
                        "   ?" +
                        ")"
                );

                stmt.setString(1, enter);
                stmt.setString(2, "Enter");
                stmt.setString(3, questionNaireId);

                stmt.executeUpdate();

                stmt.close();

            }

            String questionId;
            String[] questionOptions;

            for (Pair<String, String[]> single : question_Single) {

                stmt = conn.prepareStatement("" +
                        "INSERT INTO question(" +
                        "   question_Cont, " +
                        "   question_Kind, " +
                        "   questionnaire_Id" +
                        ") " +
                        "VALUES(" +
                        "   ?," +
                        "   ?," +
                        "   ?" +
                        ")"
                );

                stmt.setString(1, single.getKey());
                stmt.setString(2, "Single");
                stmt.setString(3, questionNaireId);

                stmt.executeUpdate();

                stmt.close();

                stmt = conn.prepareStatement("SELECT LAST_INSERT_ID()");

                rs = stmt.executeQuery();

                rs.next();

                questionId = rs.getString(1);

                rs.close();

                stmt.close();

                questionOptions = single.getValue();

                for (String option : questionOptions) {

                    stmt = conn.prepareStatement("" +
                            "INSERT INTO questionoption(" +
                            "   question_Id, " +
                            "   option_Cont" +
                            ") " +
                            "VALUES(" +
                            "   ?," +
                            "   ?" +
                            ")"
                    );

                    stmt.setString(1, questionId);
                    stmt.setString(2, option);

                    stmt.executeUpdate();

                    stmt.close();

                }

            }

            for (Pair<String, String[]> multiple : question_Multiple) {

                stmt = conn.prepareStatement("" +
                        "INSERT INTO question(" +
                        "   question_Cont, " +
                        "   question_Kind, " +
                        "   questionnaire_Id" +
                        ") " +
                        "VALUES(" +
                        "   ?," +
                        "   ?," +
                        "   ?" +
                        ")"
                );

                stmt.setString(1, multiple.getKey());
                stmt.setString(2, "Multiple");
                stmt.setString(3, questionNaireId);

                stmt.executeUpdate();

                stmt.close();

                stmt = conn.prepareStatement("SELECT LAST_INSERT_ID()");

                rs = stmt.executeQuery();

                rs.next();

                questionId = rs.getString(1);

                rs.close();

                stmt.close();

                questionOptions = multiple.getValue();

                for (String option : questionOptions) {

                    stmt = conn.prepareStatement("" +
                            "INSERT INTO questionoption(" +
                            "   question_Id, " +
                            "   option_Cont" +
                            ") " +
                            "VALUES(" +
                            "   ?," +
                            "   ?" +
                            ")"
                    );

                    stmt.setString(1, questionId);
                    stmt.setString(2, option);

                    stmt.executeUpdate();

                    stmt.close();

                }

            }

            conn.close();

            response.sendRedirect("./Servlet_LookThroughQuestionnaires");

        } catch (Exception e) {

            throw new ServletException(e.getMessage());
        }

    }
}