public class QuestionStatementParser {
    public String[] parser(String questionStatement) {
        String[] splitedQuestion = questionStatement.trim().split(" is ");
        String[] words = splitedQuestion[1].split("\\?| ");
        return words;
    }
}
