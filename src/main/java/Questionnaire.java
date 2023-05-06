public class Questionnaire {

    public int Qn_Id;

    public int User_Id;

    public String User_Name;

    public String Qn_Name;

    public String Qn_StartTime;

    public Questionnaire(int qid,int uid,String uname,String qname,String qtime){

        Qn_Id=qid;
        User_Id = uid;
        User_Name=uname;
        Qn_Name=qname;
        Qn_StartTime = qtime;

    }

}
