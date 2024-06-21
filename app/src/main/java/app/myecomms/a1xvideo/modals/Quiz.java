package app.myecomms.a1xvideo.modals;

public class Quiz {
    public String quizQuestion;
    public String quizOptionA;
    public String quizOptionB;
    public String quizOptionC;
    public String quizQuestionAnswer;

    public Quiz(String quizQuestion, String quizOptionA, String quizOptionB, String quizOptionC, String quizQuestionAnswer) {
        this.quizQuestion = quizQuestion;
        this.quizOptionA = quizOptionA;
        this.quizOptionB = quizOptionB;
        this.quizOptionC = quizOptionC;
        this.quizQuestionAnswer = quizQuestionAnswer;
    }
}
