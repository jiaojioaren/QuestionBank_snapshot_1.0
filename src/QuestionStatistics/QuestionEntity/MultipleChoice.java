package QuestionStatistics.QuestionEntity;

public class MultipleChoice {
    private int id;
    private String MainTableId;
    private String questionStem;
    private String questionOptions;
    private String questionAnswer;

    public MultipleChoice(String questionAnswer, String questionOptions, String questionStem, String mainTableId, int id) {
        this.questionAnswer = questionAnswer;
        this.questionOptions = questionOptions;
        this.questionStem = questionStem;
        MainTableId = mainTableId;
        this.id = id;
    }

    public String getMainTableId() {
        return MainTableId;
    }

    public void setMainTableId(String mainTableId) {
        MainTableId = mainTableId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestionStem() {
        return questionStem;
    }

    public void setQuestionStem(String questionStem) {
        this.questionStem = questionStem;
    }

    public String getQuestionOptions() {
        return questionOptions;
    }

    public void setQuestionOptions(String questionOptions) {
        this.questionOptions = questionOptions;
    }

    public String getQuestionAnswer() {
        return questionAnswer;
    }

    public void setQuestionAnswer(String questionAnswer) {
        this.questionAnswer = questionAnswer;
    }
}
