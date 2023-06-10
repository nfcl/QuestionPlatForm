import DruidUtil.DruidUtil;
import Sessions.QuestionNaireInfo;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class Servlet_GetQuestionNaireList extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int PerPageListItemNum = 10;

        int QuestionNaireNum = 0;

        Connection conn;

        PreparedStatement stmt;

        ResultSet rs;

        try {

            conn = DruidUtil.getDataSource().getConnection();

            stmt = conn.prepareStatement("" +
                    "SELECT " +
                    "   COUNT(questionnaire_Id)" +
                    "FROM " +
                    "   questionnaire"
            );

            rs = stmt.executeQuery();

            rs.next();

            QuestionNaireNum = rs.getInt(1);

            rs.close();

            stmt.close();

            int CurrentPageNum = 1;

            int MaxPageNum = (QuestionNaireNum - 1) / PerPageListItemNum + 1;

            if (request.getParameter("CurrentPageNum") != null) {

                CurrentPageNum = Integer.parseInt(request.getParameter("CurrentPageNum"));

                if (CurrentPageNum < 1) {

                    CurrentPageNum = 1;

                } else if (CurrentPageNum > MaxPageNum) {

                    CurrentPageNum = MaxPageNum;

                }

            }
            //查询指定范围内的问卷
            stmt = conn.prepareStatement("" +
                    "SELECT " +
                    "   questionnaire.questionnaire_Id," +
                    "   questionnaire.questionnaire_Name, " +
                    "   user.user_Name, " +
                    "   questionnaire.questionnaire_Starttime " +
                    "FROM " +
                    "   questionnaire," +
                    "   user " +
                    "WHERE " +
                    "   questionnaire.user_Id = user.user_Id " +
                    "LIMIT ?,?;");

            stmt.setInt(1, (CurrentPageNum - 1) * PerPageListItemNum);
            stmt.setInt(2, PerPageListItemNum);

            rs = stmt.executeQuery();

            ArrayList<QuestionNaireInfo> NaireList = new ArrayList<QuestionNaireInfo>();

            while (rs.next()) {

                NaireList.add(
                        new QuestionNaireInfo(
                                rs.getString(1),
                                rs.getString(2),
                                rs.getString(3),
                                rs.getString(4)
                        )
                );

            }

            rs.close();

            stmt.close();

            conn.close();

            request.setAttribute("MaxPageNum", MaxPageNum);
            request.setAttribute("CurrentPageNum", CurrentPageNum);
            request.setAttribute("QuestionNaireInfos", NaireList);

            request.getRequestDispatcher(request.getParameter("forward")).forward(request, response);

        } catch (Exception e) {

            throw new ServletException(e.getMessage());

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        doGet(req, resp);

    }
}
