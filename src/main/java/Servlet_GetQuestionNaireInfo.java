import DruidUtil.DruidUtil;
import Sessions.QuestionNaire;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class Servlet_GetQuestionNaireInfo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        //获得请求的问卷Id
        String QN_Id = request.getParameter("QN_Id");

        ResultSet rs = null;

        PreparedStatement stmt = null;

        Connection conn = null;

        QuestionNaire questionNaire = null;

        try {

            conn = DruidUtil.getDataSource().getConnection();

            //首先获得问卷的信息
            stmt = conn.prepareStatement("" +
                    "SELECT " +
                    "   questionnaire.questionnaire_Name," +
                    "   user.user_Name," +
                    "   questionnaire.questionnaire_Starttime," +
                    "   questionnaire.questionnaire_Endtime " +
                    "FROM " +
                    "   questionnaire,user " +
                    "WHERE " +
                    "   questionnaire.user_Id = user.user_Id " +
                    "   AND questionnaire_Id = ?"
            );

            stmt.setString(1, QN_Id);

            rs = stmt.executeQuery();

            //获得问卷信息顺便检查ID是否合法
            if (rs.next()) {

                questionNaire = new QuestionNaire(
                        QN_Id,
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
                );

            } else {

                throw new Exception("无效的问卷ID");

            }

            rs.close();

            stmt.close();
            //获得问卷的所有题目信息
            stmt = conn.prepareStatement("" +
                    "SELECT " +
                    "   question_Id," +
                    "   question_Cont," +
                    "   question_Kind " +
                    "FROM " +
                    "   question " +
                    "WHERE " +
                    "   questionnaire_Id = ?"
            );

            stmt.setString(1, QN_Id);

            rs = stmt.executeQuery();

            String Question_Id;
            String Question_Title;
            String Question_Kind;
            LinkedList<QuestionNaire.Option> Question_Options;

            PreparedStatement OptionStmt;
            ResultSet OptionRs;

            while (rs.next()) {

                Question_Id = rs.getString(1);
                Question_Title = rs.getString(2);
                Question_Kind = rs.getString(3);

                switch (Question_Kind) {

                    case "Enter": {

                        questionNaire.AddQuestion_Enter(
                                new QuestionNaire.Enter(
                                        Question_Id,
                                        Question_Title
                                )
                        );

                        break;
                    }
                    case "Single": {

                        OptionStmt = conn.prepareStatement("" +
                                "SELECT" +
                                "   option_Id, " +
                                "   option_Cont " +
                                "FROM " +
                                "   questionoption " +
                                "WHERE " +
                                "   question_Id = ?"
                        );

                        OptionStmt.setString(1, Question_Id);

                        OptionRs = OptionStmt.executeQuery();

                        Question_Options = new LinkedList<>();

                        while (OptionRs.next()) {

                            Question_Options.add(
                                    new QuestionNaire.Option(
                                            OptionRs.getString(1),
                                            OptionRs.getString(2)
                                    )
                            );

                        }

                        OptionRs.close();
                        OptionStmt.close();

                        questionNaire.AddQuestion_Single(
                                new QuestionNaire.Single(
                                        Question_Id,
                                        Question_Title,
                                        Question_Options
                                )
                        );

                        Question_Options = null;

                        break;
                    }
                    case "Multiple": {

                        OptionStmt = conn.prepareStatement("" +
                                "SELECT " +
                                "   option_Id, " +
                                "   option_Cont " +
                                "FROM " +
                                "   questionoption " +
                                "WHERE " +
                                "   question_Id = ?"
                        );

                        OptionStmt.setString(1, Question_Id);

                        OptionRs = OptionStmt.executeQuery();

                        Question_Options = new LinkedList<>();

                        while (OptionRs.next()) {

                            Question_Options.add(
                                    new QuestionNaire.Option(
                                            OptionRs.getString(1),
                                            OptionRs.getString(2)
                                    )
                            );

                        }

                        OptionRs.close();
                        OptionStmt.close();

                        questionNaire.AddQuestion_Multiple(
                                new QuestionNaire.Multiple(
                                        Question_Id,
                                        Question_Title,
                                        Question_Options
                                )
                        );

                        Question_Options = null;

                        break;
                    }

                }

            }

            rs.close();
            stmt.close();
            conn.close();

            request.setAttribute("QuestionNaire", questionNaire);

            request.getRequestDispatcher("QuestionNaireInfo.jsp").forward(request, response);

        } catch (Exception e) {

            throw new ServletException(e.getMessage());

        }

    }

}
