import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.IntStream.of;

public class Calories {
    public static int topThreeSum(String input) {
        return Stream.of(input.split("\n\n")).map(elf ->
                    Stream.of(elf.split("\n")).flatMapToInt(item -> of(Integer.parseInt(item))).sum()
               ).sorted((a,b)-> b - a).toList().subList(0, 3).stream().flatMapToInt(IntStream::of).sum();
    }
}
