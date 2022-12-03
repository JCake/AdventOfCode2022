import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.IntStream.of;

public class RockPaperScissors {

    private static final Map<String, Integer> GAME_TO_OUTCOME = new HashMap<>(){{
        this.put("A X", 4);
        this.put("A Y", 8);
        this.put("A Z", 3);
        this.put("B X", 1);
        this.put("B Y", 5);
        this.put("B Z", 9);
        this.put("C X", 7);
        this.put("C Y", 2);
        this.put("C Z", 6);
    }};

    private static final Map<String, String> STRATEGY_TO_GAME = new HashMap<>(){{
        this.put("A X", "A Z");
        this.put("A Y", "A X");
        this.put("A Z", "A Y");
        // Outcomes unchanged for v Paper
        this.put("C X", "C Y");
        this.put("C Y", "C Z");
        this.put("C Z", "C X");
    }};

    public static int shoot(String games){
        return sumOfActions(Stream.of(games.split("\n")));
    }

    public static int thinkAndShoot(String games){
        return sumOfActions(Stream.of(games.split("\n"))
                .map((game) -> STRATEGY_TO_GAME.getOrDefault(game, game)));
    }

    private static int sumOfActions(Stream<String> actions){
        return actions.map((game) -> GAME_TO_OUTCOME.getOrDefault(game, 0))
                .flatMapToInt(IntStream::of)
                .sum();
    }
}
