package chapter16.savingobjectsandText;

public class QuizCard {

    private String questions;
    private String answers;

    public QuizCard(String questions, String answers) {
        this.questions = questions;
        this.answers = answers;
    }


    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "QuizCard{" +
                "questions='" + questions + '\'' +
                ", answers='" + answers + '\'' +
                '}';
    }
}
