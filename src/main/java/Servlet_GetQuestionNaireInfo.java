import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Servlet_GetQuestionNaireInfo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //获得请求的问卷Id
        String QN_Id = request.getParameter("QN_Id");
        //获得输出流
        PrintWriter out = response.getWriter();

        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection conn = null;
        try {

            conn = DruidUtil.getDataSource().getConnection();

            //首先获得问卷的信息
            stmt = conn.prepareStatement("" +
                    "SELECT " +
                    "questionnaire.questionnaire_Name," +
                    "questionnaire.questionnaire_Starttime," +
                    "questionnaire.questionnaire_Endtime," +
                    "user.user_Name " +
                    "FROM questionnaire,user " +
                    "WHERE questionnaire.user_Id=user.user_Id AND questionnaire_Id = ?"
            );

            stmt.setString(1, QN_Id);

            rs = stmt.executeQuery();

            //获得问卷信息顺便检查ID是否合法
            if (rs.next()) {

                out.println("" +
                        "<h1 id=\"QuestionNaireInfoPage-Title\">" + rs.getString(1) + "</h1>\n" +
                        "   <div style=\"display: flex;justify-content: space-between\">\n" +
                        "       <span class=\"QuestionNaireInfoPage-UserName\" style=\"opacity: 0\">" + rs.getString(4) + "</span>\n" +
                        "       <span class=\"QuestionNaireInfoPage-Time\">" + rs.getTimestamp(2) + " —— " + rs.getTimestamp(3) + "</span>\n" +
                        "       <span class=\"QuestionNaireInfoPage-UserName\">" + rs.getString(4) + "</span>\n" +
                        "   </div>\n" +
                        "<hr class=\"DivideLine\">"
                );

            } else {

                throw new IOException("无效的问卷ID");

            }

            rs.close();

            stmt.close();

            out.println("<form action=\"\" method=\"post\">");

            out.println("<ul id=\"QuestionList\">");

            //获得问卷的所有题目信息
            stmt = conn.prepareStatement("" +
                "SELECT " +
                    "question_Id," +
                    "question_Cont," +
                    "question_Kind " +
                "FROM question " +
                "WHERE questionnaire_Id = ?"
            );

            stmt.setString(1,QN_Id);

            rs = stmt.executeQuery();

            int Question_index = 0;

            StringBuilder singleQuestion;

            while(rs.next()){

                int QuestionId = rs.getInt(1);
                String QuestionCont = rs.getString(2);
                String QuestionKind = rs.getString(3);

                singleQuestion = new StringBuilder("" +
                        "<li id=\"QuestionNaireInfoPage-Question-" + Question_index + "-" + QuestionId + "\" class=\"QuestionNaireInfoPage-Question\" >" +
                        "<div class=\"QuestionNaireInfoPage-QuestionTitle\">" + QuestionCont + "</div>" +
                        "<div class=\"QuestionNaireInfoPage-QuestionAnswerRegion\">"
                );

                switch(QuestionKind){

                    case "Enter":{

                        singleQuestion.append("<div class=\"QuestionNaireInfoPage-QuestionAnswer-Enter\">");

                        singleQuestion.append("<input class=\"QuestionNaireInfoPage-QuestionAnswer-Enter-textField\" type=\"textFiled\" />");

                        singleQuestion.append("</div>");

                        break;
                    }
                    case "Single":{

                        int SingleIndex = 0;

                        ResultSet SingleRs = null;
                        PreparedStatement SingleStmt = null;

                        SingleStmt = conn.prepareStatement("" +
                                "SELECT questionoption.question_Id, questionoption.option_Cont " +
                                "FROM questionoption " +
                                "WHERE questionoption.question_Id = ?"
                        );

                        SingleStmt.setInt(1,QuestionId);

                        SingleRs = SingleStmt.executeQuery();

                        while(SingleRs.next()){

                            singleQuestion.append("<div class=\"QuestionNaireInfoPage-QuestionAnswer-Single\">");

                            singleQuestion.append("<input name=\"QuestionNaireInfoPage-QuestionAnswer-Single-").append(QuestionId).append("\" id=\"QuestionNaireInfoPage-QuestionAnswer-Single-").append(SingleIndex).append("-").append(SingleRs.getString(1)).append("\" class=\"QuestionNaireInfoPage-QuestionAnswer-Single-Radio\" type=\"radio\" />").append(SingleRs.getString(2));

                            singleQuestion.append("</div>");

                            SingleIndex += 1;

                        }

                        SingleRs.close();
                        SingleStmt.close();

                        break;
                    }
                    case "Multiple":{

                        int MultipleIndex = 0;

                        ResultSet MultipleRs = null;
                        PreparedStatement MultipleStmt = null;

                        MultipleStmt = conn.prepareStatement("" +
                                "SELECT questionoption.question_Id, questionoption.option_Cont " +
                                "FROM questionoption " +
                                "WHERE questionoption.question_Id = ?"
                        );

                        MultipleStmt.setInt(1,QuestionId);

                        MultipleRs = MultipleStmt.executeQuery();

                        while(MultipleRs.next()){

                            singleQuestion.append("<div class=\"QuestionNaireInfoPage-QuestionAnswer-Multiple\">");

                            singleQuestion.append("<input id=\"QuestionNaireInfoPage-QuestionAnswer-Single-").append(MultipleIndex).append("-").append(MultipleRs.getString(1)).append("\" class=\"QuestionNaireInfoPage-QuestionAnswer-Multiple-CheckBox\" type=\"checkBox\" />").append(MultipleRs.getString(2));

                            singleQuestion.append("</div>");

                            MultipleIndex += 1;

                        }

                        MultipleRs.close();
                        MultipleStmt.close();

                        break;
                    }

                }

                singleQuestion.append("</div></li>");

                out.println(singleQuestion);

                Question_index += 1;

            }

            out.println("</ul>");

            out.println("<input type=\"submit\" value=\"提交\">");

            out.println("</form>");

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
