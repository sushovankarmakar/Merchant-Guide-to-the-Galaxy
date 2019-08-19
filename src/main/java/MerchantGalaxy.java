import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;

public class MerchantGalaxy {

    Map<String, Character > wordToRomanLetterMap = new HashMap<>();
    Map<String, Float> metalToCreditMap = new HashMap<>();
    private static HashMap romanToIntegerMap = new HashMap<Character, Integer>() {{
        put('I', 1);
        put('V', 5);
        put('X', 10);
        put('L', 50);
        put('C', 100);
        put('D', 500);
        put('M', 1000);
    }};

    int romanToIntegerMap(Character romanCharacter){
        return romanToIntegerMap(romanCharacter);
    }

    public String query(String statement) throws Exception {
        if(statement.contains("?")) {
            return handleQuestion(statement);
        }
        else if(statement.contains("Credits") || statement.contains("credits")){
            return handleCreditsStatement(statement);
        }
        else{
            return handleForeignWordStatement(statement);
        }
    }

    private String handleForeignWordStatement(String statement) throws Exception {
        WordToRomanParser wordToRomanParser = new WordToRomanParser();
        String[] wordAndRoman = wordToRomanParser.parser(statement);
        /*if(romanToIntegerMap.contains(wordAndRoman[1]).charAt(0)) {
            wordToRomanLetterMap.put(wordAndRoman[0],wordAndRoman[1].charAt(0));
        }*/
         wordToRomanLetterMap.put(wordAndRoman[0], wordAndRoman[1].charAt(0));
        if(isValidLiteral(wordAndRoman[1])) {
            wordToRomanLetterMap.put(wordAndRoman[0], wordAndRoman[1].charAt(0));
        }
        else
            throw new Exception("Invalid word");

        return "";
    }

    private boolean isValidLiteral(String literal) {
        String literals = romanToIntegerMap.keySet().toString();
        return literals.contains(literal);
    }

    private String handleCreditsStatement(String statement) {
        WordToCreditParser wordToCreditParser = new WordToCreditParser();
        String[] tokens = wordToCreditParser.parser(statement);
        String metal = tokens[tokens.length - 2];
        int creditValue = Integer.parseInt(tokens[tokens.length - 1]);

        String[] words = Arrays.copyOf(tokens, tokens.length - 2);

        int romanToNumeric = wordToRomanNumberConverter(words);
        Float creditValueForMetal = creditValue / (float)romanToNumeric;
        metalToCreditMap.put(metal, creditValueForMetal);
        return "";
    }

    String handleQuestion(String statement) {

        if(statement.contains("much")){
            if(statement.contains("how much is")){
                QuestionStatementParser questionStatementParser = new QuestionStatementParser();
                String[] words = questionStatementParser.parser(statement);
                int quantityOfMetal = wordToRomanNumberConverter(words);

                return Float.toString(quantityOfMetal);
            }
            else
                return "I have no idea what you are talking about";
        }
        else if(statement.contains("many")){

            QuestionStatementParser questionStatementParser = new QuestionStatementParser();
            String[] words = questionStatementParser.parser(statement);
            String[] foreignWords = new String[words.length-1];
            for (int i=0; i < foreignWords.length; i++){
                foreignWords[i] = words[i];
            }
            int quantityOfMetal = wordToRomanNumberConverter(foreignWords);
            String metal = words[words.length-1];
            float metalValue = metalToCreditMap.get(metal);

            return Float.toString(quantityOfMetal*metalValue);
        }

        return "";
    }

    public int wordToRomanNumberConverter(String[] words) {
        RomanToArabicConversion rtac = new RomanToArabicConversion();
        String romanNumber = "";
        for(String word : words){
            romanNumber += wordToRomanLetterMap.get(word);
        }
        return rtac.converter(romanNumber);
    }
}
