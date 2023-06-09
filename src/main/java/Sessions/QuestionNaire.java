package Sessions;

import java.util.LinkedList;

public class QuestionNaire {

    private String Id;
    private String Title;
    private String CreatorName;
    private String StartTime;
    private String EndTime;
    private LinkedList<Enter> Question_Enter;
    private LinkedList<Single> Question_Single;
    private LinkedList<Multiple> Question_Multiple;

    public QuestionNaire(
            String id,
            String title,
            String creatorName,
            String startTime,
            String endTime
    ) {

        Id = id;
        Title = title;
        CreatorName = creatorName;
        StartTime = startTime;
        EndTime = endTime;

        Question_Enter = new LinkedList<>();
        Question_Single = new LinkedList<>();
        Question_Multiple = new LinkedList<>();

    }

    public void AddQuestion_Enter(Enter question) {

        Question_Enter.add(question);

    }

    public void AddQuestion_Single(Single question) {

        Question_Single.add(question);

    }

    public void AddQuestion_Multiple(Multiple question) {

        Question_Multiple.add(question);

    }

    public String getId() {
        return Id;
    }

    public String getTitle() {
        return Title;
    }

    public String getCreatorName() {
        return CreatorName;
    }

    public String getStartTime() {
        return StartTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public LinkedList<Enter> getQuestions_Enter(){
        return Question_Enter;
    }

    public LinkedList<Single> getQuestions_Single(){
        return Question_Single;
    }

    public LinkedList<Multiple> getQuestions_Multiple(){
        return Question_Multiple;
    }

//    public QuestionNaire(HttpServletRequest request) throws Exception {
//
//        Init(request);
//
//    }
//
//    public void Init(HttpServletRequest request) throws Exception {
//
//        Title = request.getParameter("Sessions.QuestionNaire-Title");
//
//        if (Title == null || Title.isEmpty()) {
//
//            throw new Exception("问卷标题为空");
//
//        }
//
//        CreatorName = request.getSession().getAttribute("User_Id").toString();
//
//        if (CreatorName == null || CreatorName.isEmpty()) {
//
//            throw new Exception("问卷创建者为空");
//
//        }
//
//        int QuestionNum = Integer.parseInt(request.getParameter("QuestionNum"));
//
//        if (QuestionNum == 0) {
//
//            throw new Exception("问卷没有问题");
//
//        }
//
//        Question_Enter = new ArrayList<Enter>();
//        Question_Single = new ArrayList<Single>();
//        Question_Multiple = new ArrayList<Multiple>();
//
//        String kind;
//
//        for (int i = 0; i < QuestionNum; ++i) {
//
//            kind = request.getParameter("Sessions.QuestionNaire-Question-Kind-" + i);
//
//            if (kind == null || kind.isEmpty()) {
//
//                throw new Exception("问题类型为空");
//
//            }
//
//            switch (kind) {
//
//                case "Enter": {
//
//                    Enter new_Question = Enter.createEnter();
//
//                    new_Question.Title = request.getParameter("Sessions.QuestionNaire-Question-Title-" + i);
//
//                    if (new_Question.Title == null || new_Question.Title.isEmpty()) {
//
//                        throw new Exception("问题" + i + "标题为空");
//
//                    }
//
//                    Question_Enter.add(new_Question);
//
//                    break;
//                }
//
//                case "Single": {
//
//                    Single new_Question = Single.createSingle();
//
//                    new_Question.Title = request.getParameter("Sessions.QuestionNaire-Question-Title-" + i);
//
//                    if (new_Question.Title == null || new_Question.Title.isEmpty()) {
//
//                        throw new Exception("问题" + i + "标题为空");
//
//                    }
//
//                    new_Question.Options = request.getParameterValues("Sessions.QuestionNaire-OptionCont-" + i);
//
//                    for (int j = 0; j < new_Question.Options.length; ++j) {
//
//                        if (new_Question.Options[j] == null || new_Question.Options[j].isEmpty()) {
//
//                            throw new Exception("问题" + i + "的单选项" + j + "为空");
//
//                        }
//
//                    }
//
//                    Question_Single.add(new_Question);
//
//                    break;
//                }
//
//                case "Multiple": {
//
//                    Multiple new_Question = Multiple.createMultiple();
//
//                    new_Question.Title = request.getParameter("Sessions.QuestionNaire-Question-Title-" + i);
//
//                    if (new_Question.Title == null || new_Question.Title.isEmpty()) {
//
//                        throw new Exception("问题" + i + "标题为空");
//
//                    }
//
//                    new_Question.Options = request.getParameterValues("Sessions.QuestionNaire-OptionCont-" + i);
//
//                    for (int j = 0; j < new_Question.Options.length; ++j) {
//
//                        if (new_Question.Options[j] == null || new_Question.Options[j].isEmpty()) {
//
//                            throw new Exception("问题" + i + "的多选项" + j + "为空");
//
//                        }
//
//                    }
//
//                    Question_Multiple.add(new_Question);
//
//                    break;
//                }
//
//            }
//
//        }
//
//    }
//
//    public void RecordQuestionNaire() throws SQLException {
//
//        Connection conn;
//
//        PreparedStatement stmt;
//
//        ResultSet rs;
//
//        conn = DruidUtil.getDataSource().getConnection();
//
//        stmt = conn.prepareStatement("" +
//                "INSERT INTO questionnaire(" +
//                "   user_Id," +
//                "   questionnaire_Starttime," +
//                "   questionnaire_Endtime," +
//                "   questionnaire_Name" +
//                ") " +
//                "VALUES(" +
//                "   ?," +
//                "   NOW()," +
//                "   DATE_ADD(NOW(),INTERVAL 1 YEAR)," +
//                "   ?" +
//                ")"
//        );
//
//        stmt.setString(1, CreatorName);
//
//        stmt.setString(2, Title);
//
//        stmt.executeUpdate();
//
//        stmt.close();
//
//        stmt = conn.prepareStatement("SELECT LAST_INSERT_ID()");
//
//        rs = stmt.executeQuery();
//
//        rs.next();
//
//        String QuestionNaireId = rs.getString(1);
//
//        rs.close();
//
//        stmt.close();
//
//        for (int i = 0; i < Question_Enter.size(); ++i) {
//
//            stmt = conn.prepareStatement("" +
//                    "INSERT INTO question(" +
//                    "   question_Cont," +
//                    "   question_Kind," +
//                    "   questionnaire_Id" +
//                    ")" +
//                    "VALUES(" +
//                    "   ?," +
//                    "   ?," +
//                    "   ?" +
//                    ")"
//            );
//
//            stmt.setString(1, Question_Enter.get(i).Title);
//
//            stmt.setString(2, "Enter");
//
//            stmt.setString(3, QuestionNaireId);
//
//            stmt.executeUpdate();
//
//            stmt.close();
//
//        }
//
//        String QuestionId;
//
//        for (int i = 0; i < Question_Single.size(); ++i) {
//
//            stmt = conn.prepareStatement("" +
//                    "INSERT INTO question(" +
//                    "   question_Cont," +
//                    "   question_Kind," +
//                    "   questionnaire_Id" +
//                    ")" +
//                    "VALUES(" +
//                    "   ?," +
//                    "   ?," +
//                    "   ?" +
//                    ")"
//            );
//
//            stmt.setString(1, Question_Single.get(i).Title);
//
//            stmt.setString(2, "Single");
//
//            stmt.setString(3, QuestionNaireId);
//
//            stmt.executeUpdate();
//
//            stmt.close();
//
//            stmt = conn.prepareStatement("SELECT LAST_INSERT_ID()");
//
//            rs = stmt.executeQuery();
//
//            rs.next();
//
//            QuestionId = rs.getString(1);
//
//            rs.close();
//
//            stmt.close();
//
//            for (int j = 0; j < Question_Single.get(i).Options.length; ++j) {
//
//                stmt = conn.prepareStatement("" +
//                        "INSERT INTO questionoption(" +
//                        "   question_Id," +
//                        "   option_Cont" +
//                        ")" +
//                        "VALUES(" +
//                        "   ?," +
//                        "   ?" +
//                        ")"
//                );
//
//                stmt.setString(1, QuestionId);
//
//                stmt.setString(2, Question_Single.get(i).Options[j]);
//
//                stmt.executeUpdate();
//
//                stmt.close();
//
//            }
//
//        }
//
//        for (int i = 0; i < Question_Multiple.size(); ++i) {
//
//            stmt = conn.prepareStatement("" +
//                    "INSERT INTO question(" +
//                    "   question_Cont," +
//                    "   question_Kind," +
//                    "   questionnaire_Id" +
//                    ")" +
//                    "VALUES(" +
//                    "   ?," +
//                    "   ?," +
//                    "   ?" +
//                    ")"
//            );
//
//            stmt.setString(1, Question_Multiple.get(i).Title);
//
//            stmt.setString(2, "Multiple");
//
//            stmt.setString(3, QuestionNaireId);
//
//            stmt.executeUpdate();
//
//            stmt.close();
//
//            stmt = conn.prepareStatement("SELECT LAST_INSERT_ID()");
//
//            rs = stmt.executeQuery();
//
//            rs.next();
//
//            QuestionId = rs.getString(1);
//
//            rs.close();
//
//            stmt.close();
//
//            for (int j = 0; j < Question_Multiple.get(i).Options.length; ++j) {
//
//                stmt = conn.prepareStatement("" +
//                        "INSERT INTO questionoption(" +
//                        "   question_Id," +
//                        "   option_Cont" +
//                        ")" +
//                        "VALUES(" +
//                        "   ?," +
//                        "   ?" +
//                        ")"
//                );
//
//                stmt.setString(1, QuestionId);
//
//                stmt.setString(2, Question_Multiple.get(i).Options[j]);
//
//                stmt.executeUpdate();
//
//                stmt.close();
//
//            }
//
//        }
//
//        conn.close();
//
//    }

    public static class Option {

        private String Id;

        private String Content;

        public Option(String id, String content) {

            Id = id;

            Content = content;

        }

        public String getId() {
            return Id;
        }

        public String getContent() {
            return Content;
        }
    }

    private static class Question {

        private String Id;
        private String Title;

        public Question(String id, String title) {

            Id = id;
            Title = title;

        }

        public String getId() {

            return Id;

        }

        public String getTitle() {

            return Title;

        }

    }

    public static class Enter extends Question {

        public Enter(String id, String title) {

            super(id, title);

        }

    }

    public static class Single extends Question {

        private LinkedList<Option> Options;

        public Single(String id, String title, LinkedList<Option> options) {

            super(id, title);

            Options = options;

        }

        public LinkedList<Option> GetOptions() {

            return Options;

        }
    }

    public static class Multiple extends Question {

        private LinkedList<Option> Options;

        public Multiple(String id, String title, LinkedList<Option> options) {

            super(id, title);

            Options = options;

        }

        public LinkedList<Option> GetOptions() {

            return Options;

        }
    }

}
