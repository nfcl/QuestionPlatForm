package Sessions;

import java.util.LinkedList;

public class QuestionNaire {

    private final String Id;
    private final String Title;
    private final String CreatorName;
    private final String StartTime;
    private final String EndTime;
    private final LinkedList<Enter> Question_Enter;
    private final LinkedList<Single> Question_Single;
    private final LinkedList<Multiple> Question_Multiple;

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

    public static class Option {

        private final String Id;

        private final String Content;

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

        private final String Id;
        private final String Title;

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

        private final LinkedList<Option> Options;

        public Single(String id, String title, LinkedList<Option> options) {

            super(id, title);

            Options = options;

        }

        public LinkedList<Option> GetOptions() {

            return Options;

        }
    }

    public static class Multiple extends Question {

        private final LinkedList<Option> Options;

        public Multiple(String id, String title, LinkedList<Option> options) {

            super(id, title);

            Options = options;

        }

        public LinkedList<Option> GetOptions() {

            return Options;

        }
    }

}
