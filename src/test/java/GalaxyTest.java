
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class GalaxyTest {

    @Test
    void canParseWordToRomanNumeralStatement() {
        WordToRomanParser wordToRomanParser = new WordToRomanParser();
        assertArrayEquals(new String[]{"glob","I"}, wordToRomanParser.parser("glob is I"));
        assertArrayEquals(new String[]{"prok","V"}, wordToRomanParser.parser("prok is V"));
        assertArrayEquals(new String[]{"pish","X"}, wordToRomanParser.parser("pish is X"));
        assertArrayEquals(new String[]{"tegj","L"}, wordToRomanParser.parser("tegj is L"));
    }

    @Test
    void canParseCreditsStatement() {
        WordToCreditParser wordToCreditParser = new WordToCreditParser();
        assertArrayEquals(new String[]{"glob","glob","Silver","34"},
                wordToCreditParser.parser("glob glob Silver is 34 Credits"));

        assertArrayEquals(new String[]{"glob","prok","Gold","57800"},
                wordToCreditParser.parser("glob prok Gold is 57800 Credits"));

        assertArrayEquals(new String[]{"pish","pish","Iron","3910"},
                wordToCreditParser.parser("pish pish Iron is 3910 Credits"));
    }

    @Test
    void canParseQuestionStatement() {
        QuestionStatementParser questionStatementParser = new QuestionStatementParser();
        assertArrayEquals(new String[]{"pish","tegj","glob","glob"},
                questionStatementParser.parser("how much is pish tegj glob glob ?") );
    }

    @Test
    void canRomanToArabicParser() {

        RomanToArabicConversion romanToIntegerConversion = new RomanToArabicConversion();
        assertEquals(1904, romanToIntegerConversion.converter("MCMIV"));
        assertEquals(1903, romanToIntegerConversion.converter("MCMIII"));
        assertEquals(2, romanToIntegerConversion.converter("II"));
    }

    @Test
    void canWordToRomanParser() throws Exception {
        MerchantGalaxy galaxy = new MerchantGalaxy();
        galaxy.query("glob is I");
        assertEquals(2, galaxy.wordToRomanNumberConverter(new String[]{"glob","glob"}));
    }

    @Test
    void checkMetalMap() throws Exception {
        MerchantGalaxy galaxy = new MerchantGalaxy();
        galaxy.query("glob is I");
        galaxy.query("glob glob Silver is 34 Credits");

        assertEquals(17, galaxy.metalToCreditMap.get("Silver"));
    }

    @Test
    void isHandleQuestionTest() throws Exception {
        MerchantGalaxy galaxy = new MerchantGalaxy();
        galaxy.query("glob is I");
        galaxy.query("prok is V");
        galaxy.query("pish is X");
        galaxy.query("tegj is L");
        galaxy.query("glob glob Silver is 34 Credits");
        galaxy.query("glob prok Gold is 57800 Credits");
        galaxy.query("pish pish Iron is 3910 Credits");

        assertEquals("42.0", galaxy.handleQuestion("how much is pish tegj glob glob ?"));
        assertEquals("68.0", galaxy.handleQuestion("how many Credits is glob prok Silver ?"));
        assertEquals("57800.0", galaxy.handleQuestion("how many Credits is glob prok Gold ?"));
        assertEquals("782.0", galaxy.handleQuestion("how many Credits is glob prok Iron ?"));
        assertEquals("I have no idea what you are talking about",
                galaxy.handleQuestion("how much wood could a woodchuck chuck if a woodchuck could chuck wood ?"));
    }
}
