import jakarta.servlet.http.HttpServletRequest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnswerNaire {

    private String questionNaireId;

    private ArrayList<Enter> enters;
    private ArrayList<Single> singles;
    private ArrayList<Multiple> multiples;

    private abstract class Question{

        public String QuestionId;

    }

    private class Enter extends Question{

        public Enter(String Qn_Id) {

            QuestionId = Qn_Id;

        }

        private String AnswerCont;

    }

    private class Multiple extends Question{

        public Multiple(String Qn_Id) {

            QuestionId = Qn_Id;

        }

        private String[] SelectOptionIds;

    }

    private class Single extends Question{

        public Single(String Qn_Id) {

            QuestionId = Qn_Id;

        }

        private String SelectOptionId;

    }

    public AnswerNaire(String QN_Id) throws SQLException {

        Init(QN_Id);

    }

    public void Init(String QN_Id) throws SQLException {

        questionNaireId = QN_Id;

        if(enters!=null){

            enters.clear();

        }
        else{

            enters = new ArrayList<Enter>();

        }
        if(singles!=null){

            singles.clear();

        }
        else{

            singles = new ArrayList<Single>();

        }
        if(multiples!=null){

            multiples.clear();

        }
        else{

            multiples = new ArrayList<Multiple>();

        }

        ResultSet rs = null;

        PreparedStatement stmt = null;

        Connection conn = null;

        conn = DruidUtil.getDataSource().getConnection();

        //首先获得问卷的信息
        stmt = conn.prepareStatement("" +
                "SELECT " +
                "   questionnaire_Id " +
                "FROM questionnaire " +
                "WHERE questionnaire_Id = ?"
        );

        stmt.setString(1, QN_Id);

        rs = stmt.executeQuery();

        if(rs.next()){

            rs.close();

            stmt.close();

            stmt = conn.prepareStatement("" +
                    "SELECT " +
                    "   question_Id," +
                    "   question_Kind " +
                    "FROM question " +
                    "WHERE questionnaire_Id = ?"
            );

            stmt.setString(1,QN_Id);

            rs = stmt.executeQuery();

            String question_Id;

            String question_Kind;

            while(rs.next()){

                question_Id = rs.getString(1);
                question_Kind = rs.getString(2);

                switch(question_Kind){

                    case "Enter":{

                        enters.add(new Enter(question_Id));

                        break;
                    }
                    case "Single":{

                        singles.add(new Single(question_Id));

                        break;
                    }
                    case "Multiple":{

                        multiples.add(new Multiple(question_Id));

                        break;
                    }

                }

            }

        }
        else{

            throw new RuntimeException("无效的问卷ID");

        }

        rs.close();
        stmt.close();
        conn.close();

    }

    public void QueryAnswer(HttpServletRequest request) throws Exception {

        for(int i = 0;i<enters.size();++i){

            enters.get(i).AnswerCont = request.getParameter("Answer-"+enters.get(i).QuestionId);

            if(enters.get(i).AnswerCont == null || enters.get(i).AnswerCont.isEmpty()){

                throw new Exception("空值");

            }

        }

        for(int i = 0;i<singles.size();++i){

            singles.get(i).SelectOptionId = request.getParameter("Answer-"+singles.get(i).QuestionId);

            if(singles.get(i).SelectOptionId == null || singles.get(i).SelectOptionId.isEmpty()){

                throw new Exception("空值");

            }

        }

        for(int i = 0;i<multiples.size();++i){

            multiples.get(i).SelectOptionIds = request.getParameterValues("Answer-"+multiples.get(i).QuestionId);

            if(multiples.get(i).SelectOptionIds == null || multiples.get(i).SelectOptionIds.length == 0){

                throw new Exception("空值");

            }

        }

    }

    public void RecordAnswerNaire(String EnterUser) throws SQLException {

        //首先插入一条答卷的记录

        ResultSet rs = null;

        PreparedStatement stmt = null;

        Connection conn = null;

        conn = DruidUtil.getDataSource().getConnection();

        stmt = conn.prepareStatement("" +
            "INSERT INTO answernaire(" +
            "   user_Id," +
            "   questionnaire_Id," +
            "   finish_time" +
            ")" +
            "VALUES(" +
            "   ?," +
            "   ?," +
            "   NOW()" +
            ")"
        );

        stmt.setString(1,EnterUser);

        stmt.setString(2,questionNaireId);

        stmt.executeUpdate();

        stmt.close();

        stmt = conn.prepareStatement("SELECT LAST_INSERT_ID()");

        rs = stmt.executeQuery();

        rs.next();

        String AnswerNaireId = rs.getString(1);

        rs.close();

        stmt.close();

        String AnswerId;

        for(int i=0;i<enters.size();++i){

            stmt = conn.prepareStatement("" +
                "INSERT INTO answer(" +
                "   answernaire_Id," +
                "   question_Id" +
                ")" +
                "VALUES(" +
                "   ?," +
                "   ?" +
                ")"
            );

            stmt.setString(1,AnswerNaireId);
            stmt.setString(2,enters.get(i).QuestionId);

            stmt.executeUpdate();

            stmt.close();

            stmt = conn.prepareStatement("SELECT LAST_INSERT_ID()");

            rs = stmt.executeQuery();

            rs.next();

            AnswerId = rs.getString(1);

            rs.close();

            stmt.close();

            stmt = conn.prepareStatement("" +
                "INSERT INTO answer_enter(" +
                "   answer_Id," +
                "   answer_Cont" +
                ")" +
                "VALUES(" +
                "   ?," +
                "   ?" +
                ")"
            );

            stmt.setLong(1, Long.parseLong(AnswerId));

            stmt.setString(2,enters.get(i).AnswerCont);

            stmt.executeUpdate();

            stmt.close();

        }

        for(int i=0;i<singles.size();++i){

            stmt = conn.prepareStatement("" +
                "INSERT INTO answer(" +
                "   answernaire_Id," +
                "   question_Id" +
                ")" +
                "VALUES(" +
                "   ?," +
                "   ?" +
                ")"
            );

            stmt.setString(1,AnswerNaireId);
            stmt.setString(2,singles.get(i).QuestionId);

            stmt.executeUpdate();

            stmt.close();

            stmt = conn.prepareStatement("SELECT LAST_INSERT_ID()");

            rs = stmt.executeQuery();

            rs.next();

            AnswerId = rs.getString(1);

            rs.close();

            stmt.close();

            stmt = conn.prepareStatement("" +
                "INSERT INTO answer_option(" +
                "   answer_Id," +
                "   questionoption_Id" +
                ")" +
                "VALUES(" +
                "   ?," +
                "   ?" +
                ")"
            );

            stmt.setLong(1, Long.parseLong(AnswerId));

            stmt.setString(2,singles.get(i).SelectOptionId);

            stmt.executeUpdate();

            stmt.close();

        }

        for(int i=0;i<multiples.size();++i){

            stmt = conn.prepareStatement("" +
                "INSERT INTO answer(" +
                "   answernaire_Id," +
                "   question_Id" +
                ")" +
                "VALUES(" +
                "   ?," +
                "   ?" +
                ")"
            );

            stmt.setString(1,AnswerNaireId);
            stmt.setString(2,multiples.get(i).QuestionId);

            stmt.executeUpdate();

            stmt.close();

            stmt = conn.prepareStatement("SELECT LAST_INSERT_ID()");

            rs = stmt.executeQuery();

            rs.next();

            AnswerId = rs.getString(1);

            rs.close();

            stmt.close();

            for(int j = 0;j<multiples.get(i).SelectOptionIds.length;++j){

                stmt = conn.prepareStatement("" +
                    "INSERT INTO answer_option(" +
                    "   answer_Id," +
                    "   questionoption_Id" +
                    ")" +
                    "VALUES(" +
                    "   ?," +
                    "   ?" +
                    ")"
                );

                stmt.setLong(1, Long.parseLong(AnswerId));

                stmt.setLong(2, Long.parseLong(multiples.get(i).SelectOptionIds[j]));

                stmt.executeUpdate();

                stmt.close();

            }

        }

        conn.close();

    }

}