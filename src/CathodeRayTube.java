import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Integer.parseInt;

public class CathodeRayTube {

    private static final List<Integer> cyclesToCheck =
            Arrays.asList(20,60,100,140,180,220);
    public static void main(String[] args){
        String[] instructions = INST_STR.split("\n");
        List<String> screen = new ArrayList<>();
        String currentScreenRow = "";
        int cycle = 0;
        int x = 1;
        int sum = 0;
        for(String inst : instructions){
            if("noop".equalsIgnoreCase(inst)){
                cycle++;
                currentScreenRow += updateScreen(cycle, x);
                if(cyclesToCheck.contains(cycle)) {
                    sum += (x * cycle);
                } if(cycle % 40 == 0) {
                    screen.add(currentScreenRow);
                    currentScreenRow = "";
                }

            }else {
                cycle++;
                currentScreenRow += updateScreen(cycle, x);
                if(cyclesToCheck.contains(cycle)){
                    sum += (x * cycle);
                } if(cycle % 40 == 0) {
                    screen.add(currentScreenRow);
                    currentScreenRow = "";
                }
                cycle++;
                currentScreenRow += updateScreen(cycle, x);
                if(cyclesToCheck.contains(cycle)){
                    sum += (x * cycle);
                } if(cycle % 40 == 0) {
                    screen.add(currentScreenRow);
                    currentScreenRow = "";
                }
                x += parseInt(inst.split(" ")[1]);

            }
        }
        System.out.println(sum);
        for(String row : screen){
            System.out.println(row);
        }

    }

    private static String updateScreen(int cycle, int x) {
        // Cycle 1 -- Sprite is at x-1, x, x+1, pixel being drawn is 0
        int pixel = cycle - 1;
        if(x - 1 <= (pixel % 40) && (pixel % 40) <= x + 1){
            return "#";
        } else {
            return ".";
        }
    }

    private static final String INST_STR =
            "addx 1\n" +
                    "addx 4\n" +
                    "noop\n" +
                    "noop\n" +
                    "noop\n" +
                    "addx 5\n" +
                    "addx 3\n" +
                    "noop\n" +
                    "addx 2\n" +
                    "noop\n" +
                    "noop\n" +
                    "noop\n" +
                    "noop\n" +
                    "addx 3\n" +
                    "addx 5\n" +
                    "addx 2\n" +
                    "addx 1\n" +
                    "noop\n" +
                    "addx 5\n" +
                    "addx -1\n" +
                    "addx 5\n" +
                    "noop\n" +
                    "addx 3\n" +
                    "noop\n" +
                    "addx -40\n" +
                    "noop\n" +
                    "addx 38\n" +
                    "addx -31\n" +
                    "addx 3\n" +
                    "noop\n" +
                    "addx 2\n" +
                    "addx -7\n" +
                    "addx 8\n" +
                    "addx 2\n" +
                    "addx 5\n" +
                    "addx 2\n" +
                    "addx 3\n" +
                    "addx -2\n" +
                    "noop\n" +
                    "noop\n" +
                    "noop\n" +
                    "addx 5\n" +
                    "addx 2\n" +
                    "noop\n" +
                    "addx 3\n" +
                    "addx 2\n" +
                    "noop\n" +
                    "addx 3\n" +
                    "addx -36\n" +
                    "noop\n" +
                    "noop\n" +
                    "addx 5\n" +
                    "noop\n" +
                    "noop\n" +
                    "addx 8\n" +
                    "addx -5\n" +
                    "addx 5\n" +
                    "addx 2\n" +
                    "addx -15\n" +
                    "addx 16\n" +
                    "addx 4\n" +
                    "noop\n" +
                    "addx 1\n" +
                    "noop\n" +
                    "noop\n" +
                    "addx 4\n" +
                    "addx 5\n" +
                    "addx -30\n" +
                    "addx 35\n" +
                    "addx -1\n" +
                    "addx 2\n" +
                    "addx -36\n" +
                    "addx 5\n" +
                    "noop\n" +
                    "noop\n" +
                    "addx -2\n" +
                    "addx 5\n" +
                    "addx 2\n" +
                    "addx 3\n" +
                    "noop\n" +
                    "addx 2\n" +
                    "noop\n" +
                    "noop\n" +
                    "addx 5\n" +
                    "noop\n" +
                    "addx 14\n" +
                    "addx -13\n" +
                    "addx 5\n" +
                    "addx -14\n" +
                    "addx 18\n" +
                    "addx 3\n" +
                    "addx 2\n" +
                    "addx -2\n" +
                    "addx 5\n" +
                    "addx -40\n" +
                    "noop\n" +
                    "addx 32\n" +
                    "addx -25\n" +
                    "addx 3\n" +
                    "noop\n" +
                    "addx 2\n" +
                    "addx 3\n" +
                    "addx -2\n" +
                    "addx 2\n" +
                    "addx 2\n" +
                    "noop\n" +
                    "addx 3\n" +
                    "addx 5\n" +
                    "addx 2\n" +
                    "addx 9\n" +
                    "addx -36\n" +
                    "addx 30\n" +
                    "addx 5\n" +
                    "addx 2\n" +
                    "addx -25\n" +
                    "addx 26\n" +
                    "addx -38\n" +
                    "addx 10\n" +
                    "addx -3\n" +
                    "noop\n" +
                    "noop\n" +
                    "addx 22\n" +
                    "addx -16\n" +
                    "addx -1\n" +
                    "addx 5\n" +
                    "addx 3\n" +
                    "noop\n" +
                    "addx 2\n" +
                    "addx -20\n" +
                    "addx 21\n" +
                    "addx 3\n" +
                    "addx 2\n" +
                    "addx -24\n" +
                    "addx 28\n" +
                    "noop\n" +
                    "addx 5\n" +
                    "addx 3\n" +
                    "noop\n" +
                    "addx -24\n" +
                    "noop";

    private static final String SAMPLE_INST_STR =
            "addx 15\n" +
                    "addx -11\n" +
                    "addx 6\n" +
                    "addx -3\n" +
                    "addx 5\n" +
                    "addx -1\n" +
                    "addx -8\n" +
                    "addx 13\n" +
                    "addx 4\n" +
                    "noop\n" +
                    "addx -1\n" +
                    "addx 5\n" +
                    "addx -1\n" +
                    "addx 5\n" +
                    "addx -1\n" +
                    "addx 5\n" +
                    "addx -1\n" +
                    "addx 5\n" +
                    "addx -1\n" +
                    "addx -35\n" +
                    "addx 1\n" +
                    "addx 24\n" +
                    "addx -19\n" +
                    "addx 1\n" +
                    "addx 16\n" +
                    "addx -11\n" +
                    "noop\n" +
                    "noop\n" +
                    "addx 21\n" +
                    "addx -15\n" +
                    "noop\n" +
                    "noop\n" +
                    "addx -3\n" +
                    "addx 9\n" +
                    "addx 1\n" +
                    "addx -3\n" +
                    "addx 8\n" +
                    "addx 1\n" +
                    "addx 5\n" +
                    "noop\n" +
                    "noop\n" +
                    "noop\n" +
                    "noop\n" +
                    "noop\n" +
                    "addx -36\n" +
                    "noop\n" +
                    "addx 1\n" +
                    "addx 7\n" +
                    "noop\n" +
                    "noop\n" +
                    "noop\n" +
                    "addx 2\n" +
                    "addx 6\n" +
                    "noop\n" +
                    "noop\n" +
                    "noop\n" +
                    "noop\n" +
                    "noop\n" +
                    "addx 1\n" +
                    "noop\n" +
                    "noop\n" +
                    "addx 7\n" +
                    "addx 1\n" +
                    "noop\n" +
                    "addx -13\n" +
                    "addx 13\n" +
                    "addx 7\n" +
                    "noop\n" +
                    "addx 1\n" +
                    "addx -33\n" +
                    "noop\n" +
                    "noop\n" +
                    "noop\n" +
                    "addx 2\n" +
                    "noop\n" +
                    "noop\n" +
                    "noop\n" +
                    "addx 8\n" +
                    "noop\n" +
                    "addx -1\n" +
                    "addx 2\n" +
                    "addx 1\n" +
                    "noop\n" +
                    "addx 17\n" +
                    "addx -9\n" +
                    "addx 1\n" +
                    "addx 1\n" +
                    "addx -3\n" +
                    "addx 11\n" +
                    "noop\n" +
                    "noop\n" +
                    "addx 1\n" +
                    "noop\n" +
                    "addx 1\n" +
                    "noop\n" +
                    "noop\n" +
                    "addx -13\n" +
                    "addx -19\n" +
                    "addx 1\n" +
                    "addx 3\n" +
                    "addx 26\n" +
                    "addx -30\n" +
                    "addx 12\n" +
                    "addx -1\n" +
                    "addx 3\n" +
                    "addx 1\n" +
                    "noop\n" +
                    "noop\n" +
                    "noop\n" +
                    "addx -9\n" +
                    "addx 18\n" +
                    "addx 1\n" +
                    "addx 2\n" +
                    "noop\n" +
                    "noop\n" +
                    "addx 9\n" +
                    "noop\n" +
                    "noop\n" +
                    "noop\n" +
                    "addx -1\n" +
                    "addx 2\n" +
                    "addx -37\n" +
                    "addx 1\n" +
                    "addx 3\n" +
                    "noop\n" +
                    "addx 15\n" +
                    "addx -21\n" +
                    "addx 22\n" +
                    "addx -6\n" +
                    "addx 1\n" +
                    "noop\n" +
                    "addx 2\n" +
                    "addx 1\n" +
                    "noop\n" +
                    "addx -10\n" +
                    "noop\n" +
                    "noop\n" +
                    "addx 20\n" +
                    "addx 1\n" +
                    "addx 2\n" +
                    "addx 2\n" +
                    "addx -6\n" +
                    "addx -11\n" +
                    "noop\n" +
                    "noop\n" +
                    "noop";
}
