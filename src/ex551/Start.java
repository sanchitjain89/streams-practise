package ex551;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Start {

    Trader raoul = new Trader("Raoul", "Cambridge");
    Trader mario = new Trader("Mario","Milan");
    Trader alan = new Trader("Alan","Cambridge");
    Trader brian = new Trader("Brian","Cambridge");

    List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
    );

    public static void main(String[] args) {
        Start obj = new Start();
        obj.questions();
    }

    public void questions(){
        // 1
        List<Transaction> collect = transactions.stream()
                .filter(txn -> txn.getYear() == 2011)
                .sorted((a, b) -> a.getValue() - b.getValue())
                .collect(Collectors.toList());

        System.out.println(collect);

        // 2
        List<String> cities = transactions.stream()
                .map(txn -> txn.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());

        System.out.println(cities);

        // 3
        List<String> tradersFilterCriteria = transactions.stream()
                .filter(txn -> txn.getTrader().getCity().equalsIgnoreCase("cambridge"))
                .map(txn -> txn.getTrader().getName())
                .sorted()
                .collect(Collectors.toList());

        System.out.println(tradersFilterCriteria);

        // 7
        Optional<Transaction> highestTxn = transactions
                .stream()
                .reduce((a, b) -> a.getValue() > b.getValue() ? a : b);

        System.out.println("Highest txn is " + highestTxn.get().getValue());

    }


}
