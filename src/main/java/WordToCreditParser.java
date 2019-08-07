public class WordToCreditParser {
    public String[] parser(String questionStatement) {
        String[] parsedQuestionStatement = questionStatement.split(" ");
        int indexOfIs = -1;
        for(int i=0; i < parsedQuestionStatement.length; i++){
            if(parsedQuestionStatement[i].equals("is")){
                indexOfIs = i;
                break;
            }
        }

        String[] tokens = new String[indexOfIs+1];

        for(int i =0; i < tokens.length -1 ; i++){
            tokens[i] = parsedQuestionStatement[i];
        }

        tokens[tokens.length-1] = parsedQuestionStatement[indexOfIs+1];

        return tokens;
    }
}
