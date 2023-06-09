import java.util.LinkedList;

public class AnswerNaire {

    private final String questionNaireId;
    private final String creatorId;
    private final LinkedList<Enter> enters;
    private final LinkedList<Single> singles;
    private final LinkedList<Multiple> multiples;

    public String getQuestionNaireId() {

        return questionNaireId;

    }

    public String getCreatorId() {

        return creatorId;

    }

    public void AddQuestion_Enter(Enter question) {

        enters.add(question);

    }

    public void AddQuestion_Single(Single question) {

        singles.add(question);

    }

    public void AddQuestion_Multiple(Multiple question) {

        multiples.add(question);

    }

    public LinkedList<Enter> getEnters() {

        return enters;

    }

    public LinkedList<Multiple> getMultiples() {

        return multiples;

    }

    public LinkedList<Single> getSingles() {

        return singles;

    }

    public AnswerNaire(String QuestionNaireId, String CreatorId) {

        questionNaireId = QuestionNaireId;

        creatorId = CreatorId;

        enters = new LinkedList<>();

        singles = new LinkedList<>();

        multiples = new LinkedList<>();

    }

    private abstract static class Question {

        private final String Id;

        public Question(String id) {

            Id = id;

        }

        public String getId() {

            return Id;

        }

    }

    public static class Enter extends Question {

        public Enter(String questionId, String answerContent) {

            super(questionId);

            AnswerCont = answerContent;

        }

        private final String AnswerCont;

        public String getAnswerCont() {

            return AnswerCont;

        }
    }

    public static class Multiple extends Question {

        public Multiple(String questionId, String[] selectOptionIds) {

            super(questionId);

            SelectOptionIds = selectOptionIds;

        }

        private final String[] SelectOptionIds;

        public String[] getSelectOptionIds() {

            return SelectOptionIds;

        }
    }

    public static class Single extends Question {

        public Single(String questionId, String selectOptionId) {

            super(questionId);

            SelectOptionId = selectOptionId;

        }

        private final String SelectOptionId;

        public String getSelectOptionId() {

            return SelectOptionId;

        }
    }

}