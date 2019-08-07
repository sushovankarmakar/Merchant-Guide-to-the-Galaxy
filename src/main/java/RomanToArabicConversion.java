import java.util.HashMap;

public class RomanToArabicConversion {

    static HashMap romanToIntegerMap = new HashMap<Character, Integer>(){{
        put('I',1);
        put('V',5);
        put('X',10);
        put('L',50);
        put('C',100);
        put('D',500);
        put('M',1000);
    }};

    public int converter(String romanString) {
        int integerValue = 0;

        for(int i=0; i < romanString.length(); i++){
            int value1 = (int) romanToIntegerMap.get(romanString.charAt(i));

            if( i+1 < romanString.length()){
                int value2 = (int) romanToIntegerMap.get(romanString.charAt(i+1));

                if(value1 >= value2)
                    integerValue += value1;
                else {
                    integerValue += (value2 - value1);
                    i++;
                }
            }
            else
                integerValue += value1;
        }

        return integerValue;
    }
}
