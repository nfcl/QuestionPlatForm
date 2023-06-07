package Sessions;

import jakarta.servlet.http.HttpServletRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QuestionNaire {

    private String QuestionNaireTitle;

    private String QuestionNaireCreator;

    private ArrayList<Enter> Question_Enter;

    private ArrayList<Single> Question_Single;

    private ArrayList<Multiple> Question_Multiple;

    public QuestionNaire(HttpServletRequest request) throws Exception {

        Init(request);

    }

    public void Init(HttpServletRequest request) throws Exception {

        QuestionNaireTitle = request.getParameter("Sessions.QuestionNaire-Title");

        if (QuestionNaireTitle == null || QuestionNaireTitle.isEmpty()) {

            throw new Exception("问卷标题为空");

        }

        QuestionNaireCreator = request.getSession().getAttribute("User_Id").toString();

        if (QuestionNaireCreator == null || QuestionNaireCreator.isEmpty()) {

            throw new Exception("问卷创建者为空");

        }

        int QuestionNum = Integer.parseInt(request.getParameter("QuestionNum"));

        if (QuestionNum == 0) {

            throw new Exception("问卷没有问题");

        }

        Question_Enter = new ArrayList<Enter>();
        Question_Single = new ArrayList<Single>();
        Question_Multiple = new ArrayList<Multiple>();

        String kind;

        for (int i = 0; i < QuestionNum; ++i) {

            kind = request.getParameter("Sessions.QuestionNaire-Question-Kind-" + i);

            if (kind == null || kind.isEmpty()) {

                throw new Exception("问题类型为空");

            }

            switch (kind) {

                case "Enter": {

                    Enter new_Question = new Enter();

                    new_Question.QuestionTitle = request.getParameter("Sessions.QuestionNaire-Question-Title-" + i);

                    if (new_Question.QuestionTitle == null || new_Question.QuestionTitle.isEmpty()) {

                        throw new Exception("问题" + i + "标题为空");

                    }

                    Question_Enter.add(new_Question);

                    break;
                }

                case "Single": {

                    Single new_Question = new Single();

                    new_Question.QuestionTitle = request.getParameter("Sessions.QuestionNaire-Question-Title-" + i);

                    if (new_Question.QuestionTitle == null || new_Question.QuestionTitle.isEmpty()) {

                        throw new Exception("问题" + i + "标题为空");

                    }

                    new_Question.Opptions = request.getParameterValues("Sessions.QuestionNaire-OptionCont-" + i);

                    for (int j = 0; j < new_Question.Opptions.length; ++j) {

                        if (new_Question.Opptions[j] == null || new_Question.Opptions[j].isEmpty()) {

                            throw new Exception("问题" + i + "的单选项" + j + "为空");

                        }

                    }

                    Question_Single.add(new_Question);

                    break;
                }

                case "Multiple": {

                    Multiple new_Question = new Multiple();

                    new_Question.QuestionTitle = request.getParameter("Sessions.QuestionNaire-Question-Title-" + i);

                    if (new_Question.QuestionTitle == null || new_Question.QuestionTitle.isEmpty()) {

                        throw new Exception("问题" + i + "标题为空");

                    }

                    new_Question.Opptions = request.getParameterValues("Sessions.QuestionNaire-OptionCont-" + i);

                    for (int j = 0; j < new_Question.Opptions.length; ++j) {

                        if (new_Question.Opptions[j] == null || new_Question.Opptions[j].isEmpty()) {

                            throw new Exception("问题" + i + "的多选项" + j + "为空");

                        }

                    }

                    Question_Multiple.add(new_Question);

                    break;
                }

            }

        }

    }

    public void RecordQuestionNaire() throws SQLException {

        Connection conn;

        PreparedStatement stmt;

        ResultSet rs;

        conn = DruidUtil.DruidUtil.getDataSource().getConnection();

        stmt = conn.prepareStatement("" +
                "INSERT INTO questionnaire(" +
                "   user_Id," +
                "   questionnaire_Starttime," +
                "   questionnaire_Endtime," +
                "   questionnaire_Name" +
                ") " +
                "VALUES(" +
                "   ?," +
                "   NOW()," +
                "   DATE_ADD(NOW(),INTERVAL 1 YEAR)," +
                "   ?" +
                ")"
        );

        stmt.setString(1, QuestionNaireCreator);

        stmt.setString(2, QuestionNaireTitle);

        stmt.executeUpdate();

        stmt.close();

        stmt = conn.prepareStatement("SELECT LAST_INSERT_ID()");

        rs = stmt.executeQuery();

        rs.next();

        String QuestionNaireId = rs.getString(1);

        rs.close();

        stmt.close();

        for (int i = 0; i < Question_Enter.size(); ++i) {

            stmt = conn.prepareStatement("" +
                    "INSERT INTO question(" +
                    "   question_Cont," +
                    "   question_Kind," +
                    "   questionnaire_Id" +
                    ")" +
                    "VALUES(" +
                    "   ?," +
                    "   ?," +
                    "   ?" +
                    ")"
            );

            stmt.setString(1, Question_Enter.get(i).QuestionTitle);

            stmt.setString(2, "Enter");

            stmt.setString(3, QuestionNaireId);

            stmt.executeUpdate();

            stmt.close();

        }

        String QuestionId;

        for (int i = 0; i < Question_Single.size(); ++i) {

            stmt = conn.prepareStatement("" +
                    "INSERT INTO question(" +
                    "   question_Cont," +
                    "   question_Kind," +
                    "   questionnaire_Id" +
                    ")" +
                    "VALUES(" +
                    "   ?," +
                    "   ?," +
                    "   ?" +
                    ")"
            );

            stmt.setString(1, Question_Single.get(i).QuestionTitle);

            stmt.setString(2, "Single");

            stmt.setString(3, QuestionNaireId);

            stmt.executeUpdate();

            stmt.close();

            stmt = conn.prepareStatement("SELECT LAST_INSERT_ID()");

            rs = stmt.executeQuery();

            rs.next();

            QuestionId = rs.getString(1);

            rs.close();

            stmt.close();

            for (int j = 0; j < Question_Single.get(i).Opptions.length; ++j) {

                stmt = conn.prepareStatement("" +
                        "INSERT INTO questionoption(" +
                        "   question_Id," +
                        "   option_Cont" +
                        ")" +
                        "VALUES(" +
                        "   ?," +
                        "   ?" +
                        ")"
                );

                stmt.setString(1, QuestionId);

                stmt.setString(2, Question_Single.get(i).Opptions[j]);

                stmt.executeUpdate();

                stmt.close();

            }

        }

        for (int i = 0; i < Question_Multiple.size(); ++i) {

            stmt = conn.prepareStatement("" +
                    "INSERT INTO question(" +
                    "   question_Cont," +
                    "   question_Kind," +
                    "   questionnaire_Id" +
                    ")" +
                    "VALUES(" +
                    "   ?," +
                    "   ?," +
                    "   ?" +
                    ")"
            );

            stmt.setString(1, Question_Multiple.get(i).QuestionTitle);

            stmt.setString(2, "Multiple");

            stmt.setString(3, QuestionNaireId);

            stmt.executeUpdate();

            stmt.close();

            stmt = conn.prepareStatement("SELECT LAST_INSERT_ID()");

            rs = stmt.executeQuery();

            rs.next();

            QuestionId = rs.getString(1);

            rs.close();

            stmt.close();

            for (int j = 0; j < Question_Multiple.get(i).Opptions.length; ++j) {

                stmt = conn.prepareStatement("" +
                        "INSERT INTO questionoption(" +
                        "   question_Id," +
                        "   option_Cont" +
                        ")" +
                        "VALUES(" +
                        "   ?," +
                        "   ?" +
                        ")"
                );

                stmt.setString(1, QuestionId);

                stmt.setString(2, Question_Multiple.get(i).Opptions[j]);

                stmt.executeUpdate();

                stmt.close();

            }

        }

        conn.close();

    }

    private class Question {

        public String QuestionTitle;

    }

    private class Enter extends Question {

    }

    private class Single extends Question {

        private String[] Opptions;

    }

    private class Multiple extends Question {

        private String[] Opptions;

    }

}
