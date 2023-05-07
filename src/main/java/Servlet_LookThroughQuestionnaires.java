import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class Servlet_LookThroughQuestionnaires extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        int FirstItemIndex = Integer.parseInt(request.getParameter("FirstIndex"));

        int PerPageListItemNum = Integer.parseInt(request.getParameter("PerPageListItemNum"));

        if(FirstItemIndex < 0){

            throw new IOException("起始下标小于0");

        }

        PrintWriter out = response.getWriter();

        try {

            Connection conn = DruidUtil.getDataSource().getConnection();

            //查询指定范围内的问卷
            PreparedStatement ps = conn.prepareStatement("SELECT " +
                    "questionnaire.questionnaire_Id," +
                    "user.user_Name, " +
                    "questionnaire.questionnaire_Name, " +
                    "questionnaire.questionnaire_Starttime " +
                    "FROM " +
                    "questionnaire," +
                    "user " +
                    "WHERE " +
                    "questionnaire.user_Id = user.user_Id " +
                    "LIMIT ?,?;");

            ps.setInt(1,FirstItemIndex);
            ps.setInt(2,PerPageListItemNum);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                out.println(
                    "<li class=\"HomePageQnLi\" onclick=\"ClickA('QuestionNaireInfoPageA');ShowQuestionNaireInfo("+rs.getInt(1)+")\">\n" +
                    "   <h1 class=\"HomePageQnName\">"+rs.getString(3)+"</h1>\n" +
                    "   <div class=\"HomePageQnUser\">"+rs.getString(2)+"</div>\n" +
                    "   <div class=\"HomePageQnStartTime\">"+rs.getTimestamp(4)+"</div>\n" +
                    "</li>\n"
                );

            }

            rs.close();

            ps.close();

            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
