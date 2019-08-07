public class WordToRomanParser {

    public String[] parser(String inputString) {
        String[] parsedInputString = inputString.split(" ");

        return new String[]{parsedInputString[0], parsedInputString[2]};
    }
}
