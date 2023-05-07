import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Servlet_GetQuestionNaireInfo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获得请求的问卷Id
        String QN_Id = request.getParameter("QN_Id");
        //获得输出流
        PrintWriter out = response.getWriter();

        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection conn = null;
        try {

            conn = DruidUtil.getDataSource().getConnection();

            stmt = null;

            rs = null;

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

            while(rs.next()){

                out.println("   <li>");

                int QuestionId = rs.getInt(1);
                String QuestionCont = rs.getString(2);
                String QuestionKind = rs.getString(3);

                out.println(QuestionId);
                out.println("   <br>");
                out.println(QuestionCont);
                out.println("   <br>");
                out.println(QuestionKind);

                out.println("   </li>");

            }

            out.println("</ul>");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {

            try {

                if(rs != null){

                    rs.close();

                }

                if(stmt != null) {

                    stmt.close();
                }

                if(conn != null){

                    conn.close();

                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }

    }
}
