package lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class ScratchPad {

    public void example(){
        List<String> str = Arrays.asList("a","b","A","B");
        str.sort((s1, s2) -> s1.compareToIgnoreCase(s2));

        str.sort(String::compareToIgnoreCase);

        Function<String, Integer> stringToInteger = (String s) -> Integer.parseInt(s);

        Function<String, Integer> strToInt = Integer::parseInt;
    }
}
