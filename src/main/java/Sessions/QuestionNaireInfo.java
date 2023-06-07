package Sessions;

public class QuestionNaireInfo {

    private String Id;

    private String Title;

    private String Creator;

    private String StartTime;


    public QuestionNaireInfo(
            String id,
            String title,
            String creator,
            String startTime
    ) {

        Id = id;
        Title = title;
        Creator = creator;
        StartTime = startTime;

    }

    public String getId() {
        return Id;
    }

    public String getTitle() {
        return Title;
    }

    public String getCreator() {
        return Creator;
    }

    public String getStartTime() {
        return StartTime;
    }
}
