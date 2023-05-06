import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class Servlet_LookThroughQuestionnaires extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        int FirstItemIndex = Integer.parseInt(request.getParameter("FirstIndex"));

        int LastItemIndex = Integer.parseInt(request.getParameter("LastIndex"));

        if(FirstItemIndex>LastItemIndex){

            throw new IOException("起始下标大于结束下标");

        }
        else if(FirstItemIndex < 0){

            throw new IOException("起始下标小于0");

        }

        PrintWriter out = response.getWriter();

        Connection conn = null;

        ResultSet rs = null;

        PreparedStatement ps = null;

        List<Questionnaire> naires = null;

        try {

            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/questionplatform", "root", "159357zjy");

            //查询指定范围内的问卷
            ps = conn.prepareStatement("SELECT " +
                    "questionnaire.questionnaire_Id," +
                    "user.user_Id, " +
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
            ps.setInt(2,LastItemIndex);

            rs = ps.executeQuery();

            naires = new LinkedList<>();

            while(rs.next()){

                naires.add(new Questionnaire(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getDate(5).toString()));

            }

            for (Questionnaire item:naires) {
                out.println(
                        "<li class=\"HomePageQnLi\">\n" +
                        "   <h1 class=\"HomePageQnName\">"+item.Qn_Name+"</h1>\n" +
                        "   <div class=\"HomePageQnUser\">"+item.User_Name+"</div>\n" +
                        "   <div class=\"HomePageQnStartTime\">"+item.Qn_StartTime+"</div>\n" +
                        "</li>\n"
                );
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
