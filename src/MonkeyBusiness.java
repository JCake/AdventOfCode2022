import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class MonkeyBusiness {

    private static class Monkey {
        List<BigInteger> items = new ArrayList<>();
        Function<BigInteger, BigInteger> operation;

        BigInteger divisibilityTest;
        int trueMonkeyIndex;
        int falseMonkeyIndex;

        long inspectionCount = 0;

        public Monkey(List<BigInteger> items, Function<BigInteger, BigInteger> operation,
                      BigInteger divisibilityTest,
                      int trueMonkeyIndex, int falseMonkeyIndex) {
            this.items = items;
            this.operation = operation;
            this.divisibilityTest = divisibilityTest;
            this.trueMonkeyIndex = trueMonkeyIndex;
            this.falseMonkeyIndex = falseMonkeyIndex;
        }
    }

    public static void main(String[] args){
        double worryDivider = 1.0; // Would use for part 1, setting to 3
        int rounds = 10000;


        String[] monkeyStrings = STARTING.split("\n\n");
        long mulOfDiv = 1;
        for(String monkeyString : monkeyStrings){
            String divisor = monkeyString.split("\n")[3].split("divisible by ")[1];
            mulOfDiv *= parseLong(divisor);
        }
        final long multipleOfDivisors = mulOfDiv;

        List<Monkey> monkeyList = new ArrayList<>();
        for(String monkeyString : monkeyStrings){
            String[] steps = monkeyString.split("\n");
            String[] itemsStrs = steps[1].split("items: ")[1].split(", ");
            List<BigInteger> items = new ArrayList<>(Arrays.stream(itemsStrs).map(i -> BigInteger.valueOf(parseLong(i))).toList());
            String[] opParts = steps[2].split("new = ")[1].split(" ");
            Function<BigInteger, BigInteger> operation = aLong -> {
                BigInteger otherNum;
                if("old".equalsIgnoreCase(opParts[2])){
                    otherNum = aLong;
                } else {
                    otherNum = BigInteger.valueOf(parseLong(opParts[2]));
                }
                if("*".equals(opParts[1])){
                    return aLong.multiply(otherNum).mod(BigInteger.valueOf(multipleOfDivisors));
                } else {
                    return aLong.add(otherNum).mod(BigInteger.valueOf(multipleOfDivisors));
                }
            };
            BigInteger divisibilityTest = BigInteger.valueOf(parseLong(steps[3].split("divisible by ")[1]));
            int trueIndex = parseInt(steps[4].split("monkey ")[1]);
            int falseIndex = parseInt(steps[5].split("monkey ")[1]);
            monkeyList.add(new Monkey(items, operation, divisibilityTest, trueIndex, falseIndex));

        }

        for(int round = 1; round <= rounds; round++){
            for(Monkey monkey: monkeyList){
                for(BigInteger item : monkey.items){
                    BigInteger updatedItem = monkey.operation.apply(item);
                    if(updatedItem.mod(monkey.divisibilityTest).equals(BigInteger.ZERO)){
                        monkeyList.get(monkey.trueMonkeyIndex).items.add(updatedItem);
                    } else {
                        monkeyList.get(monkey.falseMonkeyIndex).items.add(updatedItem);
                    }
                }
                monkey.inspectionCount += monkey.items.size();
                monkey.items = new ArrayList<>();
            }
        }

        monkeyList.forEach(m -> m.items.forEach(item -> System.out.println(item)));
        monkeyList.forEach(m -> System.out.println(m.inspectionCount));

        monkeyList.sort(Comparator.comparingLong(m -> m.inspectionCount));

        System.out.println(monkeyList.get(monkeyList.size() - 1).inspectionCount * monkeyList.get(monkeyList.size() - 2).inspectionCount);
    }

    private static String STARTING =
            "Monkey 0:\n" +
                    "  Starting items: 66, 71, 94\n" +
                    "  Operation: new = old * 5\n" +
                    "  Test: divisible by 3\n" +
                    "    If true: throw to monkey 7\n" +
                    "    If false: throw to monkey 4\n" +
                    "\n" +
                    "Monkey 1:\n" +
                    "  Starting items: 70\n" +
                    "  Operation: new = old + 6\n" +
                    "  Test: divisible by 17\n" +
                    "    If true: throw to monkey 3\n" +
                    "    If false: throw to monkey 0\n" +
                    "\n" +
                    "Monkey 2:\n" +
                    "  Starting items: 62, 68, 56, 65, 94, 78\n" +
                    "  Operation: new = old + 5\n" +
                    "  Test: divisible by 2\n" +
                    "    If true: throw to monkey 3\n" +
                    "    If false: throw to monkey 1\n" +
                    "\n" +
                    "Monkey 3:\n" +
                    "  Starting items: 89, 94, 94, 67\n" +
                    "  Operation: new = old + 2\n" +
                    "  Test: divisible by 19\n" +
                    "    If true: throw to monkey 7\n" +
                    "    If false: throw to monkey 0\n" +
                    "\n" +
                    "Monkey 4:\n" +
                    "  Starting items: 71, 61, 73, 65, 98, 98, 63\n" +
                    "  Operation: new = old * 7\n" +
                    "  Test: divisible by 11\n" +
                    "    If true: throw to monkey 5\n" +
                    "    If false: throw to monkey 6\n" +
                    "\n" +
                    "Monkey 5:\n" +
                    "  Starting items: 55, 62, 68, 61, 60\n" +
                    "  Operation: new = old + 7\n" +
                    "  Test: divisible by 5\n" +
                    "    If true: throw to monkey 2\n" +
                    "    If false: throw to monkey 1\n" +
                    "\n" +
                    "Monkey 6:\n" +
                    "  Starting items: 93, 91, 69, 64, 72, 89, 50, 71\n" +
                    "  Operation: new = old + 1\n" +
                    "  Test: divisible by 13\n" +
                    "    If true: throw to monkey 5\n" +
                    "    If false: throw to monkey 2\n" +
                    "\n" +
                    "Monkey 7:\n" +
                    "  Starting items: 76, 50\n" +
                    "  Operation: new = old * old\n" +
                    "  Test: divisible by 7\n" +
                    "    If true: throw to monkey 4\n" +
                    "    If false: throw to monkey 6";


    private static String SAMPLE_STARTING =
            "Monkey 0:\n" +
                    "  Starting items: 79, 98\n" +
                    "  Operation: new = old * 19\n" +
                    "  Test: divisible by 23\n" +
                    "    If true: throw to monkey 2\n" +
                    "    If false: throw to monkey 3\n" +
                    "\n" +
                    "Monkey 1:\n" +
                    "  Starting items: 54, 65, 75, 74\n" +
                    "  Operation: new = old + 6\n" +
                    "  Test: divisible by 19\n" +
                    "    If true: throw to monkey 2\n" +
                    "    If false: throw to monkey 0\n" +
                    "\n" +
                    "Monkey 2:\n" +
                    "  Starting items: 79, 60, 97\n" +
                    "  Operation: new = old * old\n" +
                    "  Test: divisible by 13\n" +
                    "    If true: throw to monkey 1\n" +
                    "    If false: throw to monkey 3\n" +
                    "\n" +
                    "Monkey 3:\n" +
                    "  Starting items: 74\n" +
                    "  Operation: new = old + 3\n" +
                    "  Test: divisible by 17\n" +
                    "    If true: throw to monkey 0\n" +
                    "    If false: throw to monkey 1";
}
