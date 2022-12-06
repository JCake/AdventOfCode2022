import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static java.lang.Integer.parseInt;

public class SupplyStacks {

    public static void main(String[] args){
        String[] setUpAndInstructions = INPUT.split("\n\n");
        String setUp = setUpAndInstructions[0];
        String[] setUpRows = setUp.split("\n");
        List<Stack<Character>> stacks = new ArrayList<>();
        for(int i = 0; i < setUpRows[0].length(); i+=4){
            stacks.add(new Stack<>());
        }
        for(int r = setUpRows.length - 2; r >= 0; r--){
            String row = setUpRows[r];
            for(int i = 0; i < row.length(); i+=4){
                char value = row.charAt(i + 1);
                if(value != ' '){
                    stacks.get(i/4).add(value);
                }

            }
        }
        System.out.println(stacks);

        String[] instructions = setUpAndInstructions[1].split("\n");
        for(String instruction : instructions){
            String[] instructionParts = instruction.split(" ");
            int numberToMove = parseInt(instructionParts[1]);
            int fromStack = parseInt(instructionParts[3]) - 1;
            int toStack = parseInt(instructionParts[5]) - 1;
            // Change for part 2: Have a temp stack to reverse order twice
            Stack<Character> toMove = new Stack<>();
            for(int i = 0; i < numberToMove; i++){
                toMove.push(stacks.get(fromStack).pop());
            }
            for(int i = 0; i < numberToMove; i++){
                stacks.get(toStack).push(toMove.pop());
            }
        }
        System.out.println(stacks);

        String topItems = "";
        for(Stack<Character> stack : stacks){
            topItems += stack.peek();
        }
        System.out.println(topItems);
    }

    private static final String SAMPLE_INPUT =
            "    [D]    \n" +
            "[N] [C]    \n" +
            "[Z] [M] [P]\n" +
            " 1   2   3 \n" +
            "\n" +
            "move 1 from 2 to 1\n" +
            "move 3 from 1 to 3\n" +
            "move 2 from 2 to 1\n" +
            "move 1 from 1 to 2";

    private static final String INPUT =
            "        [C] [B] [H]                \n" +
            "[W]     [D] [J] [Q] [B]            \n" +
            "[P] [F] [Z] [F] [B] [L]            \n" +
            "[G] [Z] [N] [P] [J] [S] [V]        \n" +
            "[Z] [C] [H] [Z] [G] [T] [Z]     [C]\n" +
            "[V] [B] [M] [M] [C] [Q] [C] [G] [H]\n" +
            "[S] [V] [L] [D] [F] [F] [G] [L] [F]\n" +
            "[B] [J] [V] [L] [V] [G] [L] [N] [J]\n" +
            " 1   2   3   4   5   6   7   8   9 \n" +
            "\n" +
            "move 5 from 4 to 7\n" +
            "move 8 from 5 to 9\n" +
            "move 6 from 2 to 8\n" +
            "move 7 from 7 to 9\n" +
            "move 1 from 7 to 4\n" +
            "move 2 from 7 to 4\n" +
            "move 9 from 8 to 4\n" +
            "move 16 from 9 to 7\n" +
            "move 1 from 3 to 8\n" +
            "move 15 from 4 to 5\n" +
            "move 3 from 9 to 5\n" +
            "move 2 from 3 to 5\n" +
            "move 1 from 8 to 7\n" +
            "move 3 from 1 to 7\n" +
            "move 5 from 3 to 5\n" +
            "move 13 from 7 to 2\n" +
            "move 5 from 7 to 1\n" +
            "move 7 from 2 to 6\n" +
            "move 2 from 7 to 8\n" +
            "move 3 from 6 to 5\n" +
            "move 2 from 8 to 2\n" +
            "move 2 from 6 to 1\n" +
            "move 11 from 1 to 7\n" +
            "move 2 from 2 to 9\n" +
            "move 8 from 6 to 5\n" +
            "move 2 from 9 to 6\n" +
            "move 3 from 6 to 4\n" +
            "move 1 from 4 to 7\n" +
            "move 22 from 5 to 6\n" +
            "move 13 from 6 to 9\n" +
            "move 5 from 2 to 7\n" +
            "move 6 from 5 to 8\n" +
            "move 13 from 7 to 2\n" +
            "move 2 from 4 to 6\n" +
            "move 5 from 6 to 3\n" +
            "move 2 from 7 to 5\n" +
            "move 3 from 3 to 6\n" +
            "move 2 from 6 to 2\n" +
            "move 8 from 2 to 4\n" +
            "move 2 from 4 to 7\n" +
            "move 2 from 2 to 9\n" +
            "move 5 from 4 to 5\n" +
            "move 2 from 3 to 2\n" +
            "move 1 from 5 to 4\n" +
            "move 6 from 5 to 9\n" +
            "move 1 from 7 to 3\n" +
            "move 1 from 5 to 9\n" +
            "move 5 from 5 to 1\n" +
            "move 1 from 6 to 8\n" +
            "move 1 from 5 to 8\n" +
            "move 4 from 6 to 9\n" +
            "move 8 from 8 to 9\n" +
            "move 1 from 3 to 6\n" +
            "move 4 from 1 to 7\n" +
            "move 3 from 6 to 4\n" +
            "move 7 from 2 to 6\n" +
            "move 27 from 9 to 8\n" +
            "move 3 from 4 to 7\n" +
            "move 6 from 8 to 1\n" +
            "move 1 from 4 to 6\n" +
            "move 1 from 2 to 7\n" +
            "move 7 from 6 to 3\n" +
            "move 1 from 4 to 3\n" +
            "move 4 from 1 to 6\n" +
            "move 1 from 9 to 2\n" +
            "move 1 from 2 to 4\n" +
            "move 1 from 4 to 5\n" +
            "move 3 from 9 to 4\n" +
            "move 5 from 7 to 8\n" +
            "move 2 from 5 to 6\n" +
            "move 4 from 6 to 9\n" +
            "move 10 from 8 to 3\n" +
            "move 2 from 4 to 7\n" +
            "move 3 from 1 to 7\n" +
            "move 2 from 9 to 6\n" +
            "move 6 from 3 to 1\n" +
            "move 7 from 3 to 4\n" +
            "move 2 from 1 to 9\n" +
            "move 4 from 1 to 9\n" +
            "move 1 from 3 to 6\n" +
            "move 1 from 3 to 8\n" +
            "move 2 from 9 to 5\n" +
            "move 2 from 5 to 3\n" +
            "move 3 from 3 to 1\n" +
            "move 1 from 4 to 6\n" +
            "move 5 from 7 to 6\n" +
            "move 2 from 3 to 4\n" +
            "move 2 from 8 to 1\n" +
            "move 9 from 4 to 7\n" +
            "move 4 from 9 to 3\n" +
            "move 2 from 8 to 3\n" +
            "move 1 from 1 to 4\n" +
            "move 1 from 6 to 2\n" +
            "move 1 from 2 to 9\n" +
            "move 6 from 3 to 5\n" +
            "move 2 from 1 to 3\n" +
            "move 1 from 3 to 2\n" +
            "move 1 from 2 to 9\n" +
            "move 8 from 6 to 8\n" +
            "move 2 from 6 to 3\n" +
            "move 1 from 1 to 2\n" +
            "move 7 from 7 to 9\n" +
            "move 13 from 8 to 6\n" +
            "move 1 from 2 to 8\n" +
            "move 6 from 9 to 3\n" +
            "move 1 from 1 to 6\n" +
            "move 2 from 8 to 5\n" +
            "move 5 from 3 to 4\n" +
            "move 2 from 8 to 1\n" +
            "move 8 from 5 to 2\n" +
            "move 4 from 3 to 2\n" +
            "move 5 from 8 to 4\n" +
            "move 2 from 9 to 4\n" +
            "move 4 from 4 to 7\n" +
            "move 10 from 2 to 6\n" +
            "move 1 from 2 to 9\n" +
            "move 24 from 6 to 1\n" +
            "move 17 from 1 to 8\n" +
            "move 1 from 9 to 2\n" +
            "move 2 from 4 to 9\n" +
            "move 10 from 7 to 4\n" +
            "move 1 from 2 to 5\n" +
            "move 5 from 9 to 1\n" +
            "move 1 from 7 to 6\n" +
            "move 12 from 8 to 6\n" +
            "move 1 from 7 to 5\n" +
            "move 2 from 5 to 6\n" +
            "move 16 from 6 to 8\n" +
            "move 12 from 1 to 6\n" +
            "move 2 from 1 to 7\n" +
            "move 9 from 6 to 2\n" +
            "move 2 from 4 to 1\n" +
            "move 1 from 1 to 5\n" +
            "move 7 from 4 to 6\n" +
            "move 13 from 8 to 2\n" +
            "move 5 from 8 to 2\n" +
            "move 2 from 7 to 3\n" +
            "move 2 from 4 to 9\n" +
            "move 1 from 5 to 4\n" +
            "move 3 from 9 to 8\n" +
            "move 2 from 4 to 2\n" +
            "move 2 from 3 to 8\n" +
            "move 1 from 1 to 5\n" +
            "move 1 from 4 to 8\n" +
            "move 6 from 2 to 7\n" +
            "move 1 from 5 to 8\n" +
            "move 1 from 6 to 2\n" +
            "move 7 from 6 to 8\n" +
            "move 1 from 6 to 2\n" +
            "move 24 from 2 to 1\n" +
            "move 10 from 8 to 3\n" +
            "move 4 from 8 to 2\n" +
            "move 4 from 7 to 1\n" +
            "move 5 from 2 to 9\n" +
            "move 1 from 6 to 2\n" +
            "move 10 from 3 to 1\n" +
            "move 2 from 7 to 3\n" +
            "move 2 from 3 to 7\n" +
            "move 2 from 7 to 9\n" +
            "move 35 from 1 to 5\n" +
            "move 28 from 5 to 6\n" +
            "move 2 from 2 to 7\n" +
            "move 19 from 6 to 4\n" +
            "move 3 from 1 to 2\n" +
            "move 3 from 2 to 5\n" +
            "move 23 from 4 to 7\n" +
            "move 2 from 6 to 8\n" +
            "move 4 from 7 to 6\n" +
            "move 3 from 5 to 6\n" +
            "move 13 from 7 to 4\n" +
            "move 2 from 5 to 6\n" +
            "move 2 from 9 to 4\n" +
            "move 5 from 6 to 3\n" +
            "move 6 from 4 to 5\n" +
            "move 1 from 4 to 8\n" +
            "move 4 from 4 to 6\n" +
            "move 5 from 9 to 7\n" +
            "move 2 from 8 to 7\n" +
            "move 5 from 3 to 2\n" +
            "move 4 from 5 to 2\n" +
            "move 5 from 2 to 9\n" +
            "move 4 from 8 to 4\n" +
            "move 1 from 9 to 8\n" +
            "move 2 from 2 to 6\n" +
            "move 4 from 4 to 2\n" +
            "move 3 from 2 to 3\n" +
            "move 3 from 5 to 1\n" +
            "move 2 from 3 to 2\n" +
            "move 3 from 1 to 4\n" +
            "move 1 from 9 to 4\n" +
            "move 5 from 4 to 9\n" +
            "move 2 from 4 to 3\n" +
            "move 5 from 6 to 8\n" +
            "move 1 from 9 to 7\n" +
            "move 2 from 6 to 3\n" +
            "move 1 from 4 to 5\n" +
            "move 1 from 9 to 4\n" +
            "move 6 from 8 to 6\n" +
            "move 2 from 3 to 6\n" +
            "move 2 from 9 to 4\n" +
            "move 2 from 3 to 9\n" +
            "move 1 from 3 to 1\n" +
            "move 17 from 6 to 4\n" +
            "move 1 from 1 to 8\n" +
            "move 1 from 6 to 5\n" +
            "move 1 from 9 to 2\n" +
            "move 11 from 4 to 6\n" +
            "move 9 from 4 to 5\n" +
            "move 7 from 9 to 4\n" +
            "move 2 from 5 to 2\n" +
            "move 1 from 4 to 9\n" +
            "move 5 from 2 to 1\n" +
            "move 1 from 2 to 9\n" +
            "move 4 from 4 to 9\n" +
            "move 4 from 1 to 5\n" +
            "move 1 from 1 to 7\n" +
            "move 1 from 8 to 9\n" +
            "move 8 from 7 to 8\n" +
            "move 4 from 7 to 4\n" +
            "move 9 from 5 to 2\n" +
            "move 2 from 4 to 1\n" +
            "move 11 from 6 to 8\n" +
            "move 2 from 4 to 3\n" +
            "move 2 from 4 to 8\n" +
            "move 1 from 1 to 4\n" +
            "move 3 from 2 to 8\n" +
            "move 1 from 1 to 3\n" +
            "move 3 from 3 to 9\n" +
            "move 8 from 9 to 6\n" +
            "move 1 from 4 to 8\n" +
            "move 2 from 9 to 3\n" +
            "move 5 from 6 to 9\n" +
            "move 7 from 5 to 6\n" +
            "move 2 from 3 to 4\n" +
            "move 5 from 7 to 9\n" +
            "move 2 from 4 to 5\n" +
            "move 2 from 2 to 3\n" +
            "move 10 from 9 to 5\n" +
            "move 2 from 6 to 3\n" +
            "move 6 from 2 to 7\n" +
            "move 10 from 5 to 3\n" +
            "move 6 from 7 to 1\n" +
            "move 2 from 1 to 7\n" +
            "move 4 from 3 to 9\n" +
            "move 3 from 8 to 2\n" +
            "move 2 from 7 to 5\n" +
            "move 19 from 8 to 7\n" +
            "move 4 from 5 to 9\n" +
            "move 4 from 9 to 8\n" +
            "move 1 from 2 to 5\n" +
            "move 3 from 6 to 8\n" +
            "move 1 from 5 to 9\n" +
            "move 5 from 9 to 7\n" +
            "move 6 from 3 to 8\n" +
            "move 1 from 3 to 8\n" +
            "move 2 from 3 to 2\n" +
            "move 23 from 7 to 6\n" +
            "move 10 from 8 to 4\n" +
            "move 4 from 4 to 9\n" +
            "move 4 from 2 to 6\n" +
            "move 1 from 3 to 8\n" +
            "move 4 from 8 to 4\n" +
            "move 31 from 6 to 4\n" +
            "move 9 from 4 to 5\n" +
            "move 8 from 5 to 3\n" +
            "move 1 from 6 to 7\n" +
            "move 2 from 5 to 7\n" +
            "move 4 from 9 to 2\n" +
            "move 21 from 4 to 8\n" +
            "move 4 from 2 to 9\n" +
            "move 3 from 3 to 9\n" +
            "move 2 from 7 to 9\n" +
            "move 11 from 4 to 9\n" +
            "move 1 from 8 to 5\n" +
            "move 1 from 5 to 9\n" +
            "move 9 from 9 to 3\n" +
            "move 3 from 1 to 5\n" +
            "move 2 from 5 to 8\n" +
            "move 11 from 3 to 6\n" +
            "move 4 from 6 to 3\n" +
            "move 2 from 8 to 3\n" +
            "move 10 from 9 to 6\n" +
            "move 22 from 8 to 9\n" +
            "move 1 from 1 to 8\n" +
            "move 4 from 6 to 3\n" +
            "move 2 from 7 to 6\n" +
            "move 3 from 8 to 3\n" +
            "move 14 from 3 to 2\n" +
            "move 1 from 3 to 4\n" +
            "move 1 from 2 to 4\n" +
            "move 2 from 9 to 1\n" +
            "move 1 from 5 to 7\n" +
            "move 1 from 3 to 2\n" +
            "move 14 from 6 to 5\n" +
            "move 13 from 5 to 2\n" +
            "move 1 from 5 to 6\n" +
            "move 1 from 7 to 9\n" +
            "move 8 from 9 to 4\n" +
            "move 2 from 6 to 7\n" +
            "move 23 from 2 to 4\n" +
            "move 2 from 1 to 4\n" +
            "move 2 from 2 to 5\n" +
            "move 1 from 5 to 1\n" +
            "move 1 from 7 to 2\n" +
            "move 1 from 5 to 9\n" +
            "move 16 from 9 to 5\n" +
            "move 1 from 2 to 4\n" +
            "move 13 from 5 to 3\n" +
            "move 1 from 1 to 4\n" +
            "move 1 from 7 to 1\n" +
            "move 1 from 5 to 3\n" +
            "move 2 from 5 to 7\n" +
            "move 2 from 7 to 1\n" +
            "move 9 from 3 to 2\n" +
            "move 2 from 1 to 7\n" +
            "move 1 from 1 to 9\n" +
            "move 19 from 4 to 2\n" +
            "move 1 from 9 to 7\n" +
            "move 1 from 7 to 8\n" +
            "move 23 from 2 to 8\n" +
            "move 2 from 7 to 2\n" +
            "move 12 from 4 to 5\n" +
            "move 12 from 5 to 1\n" +
            "move 5 from 2 to 9\n" +
            "move 2 from 2 to 7\n" +
            "move 5 from 8 to 1\n" +
            "move 3 from 9 to 4\n" +
            "move 1 from 2 to 8\n" +
            "move 1 from 2 to 4\n" +
            "move 4 from 8 to 1\n" +
            "move 2 from 3 to 1\n" +
            "move 2 from 7 to 5\n" +
            "move 1 from 4 to 9\n" +
            "move 8 from 4 to 7\n" +
            "move 13 from 8 to 6\n" +
            "move 1 from 3 to 1\n" +
            "move 13 from 6 to 7\n" +
            "move 13 from 7 to 6\n" +
            "move 7 from 1 to 4\n" +
            "move 5 from 7 to 3\n" +
            "move 3 from 4 to 3\n" +
            "move 13 from 6 to 1\n" +
            "move 3 from 8 to 6\n" +
            "move 8 from 3 to 8\n" +
            "move 12 from 1 to 8\n" +
            "move 1 from 3 to 5\n" +
            "move 6 from 1 to 7\n" +
            "move 3 from 6 to 8\n" +
            "move 1 from 3 to 8\n" +
            "move 1 from 9 to 2\n" +
            "move 3 from 5 to 6\n" +
            "move 1 from 7 to 3\n" +
            "move 8 from 7 to 1\n" +
            "move 2 from 6 to 2\n" +
            "move 3 from 4 to 3\n" +
            "move 2 from 9 to 2\n" +
            "move 6 from 8 to 9\n" +
            "move 5 from 2 to 5\n" +
            "move 2 from 3 to 4\n" +
            "move 5 from 5 to 4\n" +
            "move 1 from 3 to 9\n" +
            "move 8 from 4 to 5\n" +
            "move 1 from 6 to 8\n" +
            "move 2 from 1 to 4\n" +
            "move 1 from 1 to 4\n" +
            "move 3 from 1 to 5\n" +
            "move 3 from 1 to 6\n" +
            "move 7 from 1 to 9\n" +
            "move 2 from 6 to 9\n" +
            "move 1 from 3 to 5\n" +
            "move 17 from 8 to 7\n" +
            "move 17 from 7 to 6\n" +
            "move 5 from 5 to 2\n" +
            "move 5 from 2 to 1\n" +
            "move 13 from 6 to 2\n" +
            "move 1 from 1 to 4\n" +
            "move 5 from 5 to 1\n" +
            "move 1 from 1 to 5\n" +
            "move 10 from 9 to 1\n" +
            "move 13 from 1 to 8\n" +
            "move 13 from 8 to 4\n" +
            "move 5 from 6 to 7\n" +
            "move 8 from 1 to 7\n" +
            "move 1 from 1 to 3\n" +
            "move 12 from 2 to 6\n" +
            "move 1 from 3 to 8\n" +
            "move 6 from 6 to 2\n" +
            "move 2 from 5 to 1\n" +
            "move 5 from 2 to 5\n" +
            "move 2 from 5 to 9\n" +
            "move 12 from 4 to 2\n" +
            "move 1 from 6 to 2\n" +
            "move 15 from 2 to 1\n" +
            "move 1 from 8 to 6\n" +
            "move 2 from 7 to 3\n" +
            "move 2 from 4 to 2\n" +
            "move 1 from 2 to 9\n" +
            "move 1 from 2 to 6\n" +
            "move 7 from 7 to 3\n" +
            "move 1 from 4 to 1\n" +
            "move 17 from 1 to 2\n" +
            "move 3 from 6 to 4\n" +
            "move 1 from 3 to 8\n" +
            "move 3 from 9 to 6\n" +
            "move 4 from 6 to 3\n" +
            "move 13 from 2 to 9\n" +
            "move 3 from 2 to 8\n" +
            "move 2 from 5 to 1\n" +
            "move 6 from 8 to 2\n" +
            "move 1 from 6 to 2\n" +
            "move 3 from 2 to 7\n" +
            "move 3 from 1 to 6\n" +
            "move 2 from 9 to 8\n" +
            "move 6 from 9 to 8\n" +
            "move 8 from 9 to 3\n" +
            "move 7 from 7 to 4\n" +
            "move 20 from 3 to 7\n" +
            "move 4 from 6 to 8\n" +
            "move 1 from 8 to 6\n" +
            "move 2 from 6 to 4\n" +
            "move 3 from 2 to 1\n" +
            "move 2 from 9 to 6\n" +
            "move 9 from 8 to 6\n" +
            "move 3 from 1 to 9\n" +
            "move 9 from 4 to 8\n" +
            "move 1 from 5 to 6\n" +
            "move 3 from 4 to 2\n" +
            "move 1 from 5 to 3\n" +
            "move 8 from 6 to 4\n" +
            "move 4 from 9 to 3\n" +
            "move 10 from 8 to 6\n" +
            "move 5 from 2 to 3\n" +
            "move 3 from 6 to 4\n" +
            "move 10 from 3 to 1\n" +
            "move 11 from 4 to 1\n" +
            "move 1 from 8 to 2\n" +
            "move 2 from 4 to 2\n" +
            "move 1 from 4 to 9\n" +
            "move 10 from 6 to 3\n" +
            "move 21 from 1 to 5\n" +
            "move 2 from 2 to 7\n" +
            "move 1 from 9 to 6\n" +
            "move 1 from 6 to 3\n" +
            "move 1 from 6 to 7\n" +
            "move 11 from 5 to 6\n" +
            "move 1 from 2 to 8\n" +
            "move 1 from 5 to 9\n" +
            "move 11 from 6 to 3\n" +
            "move 1 from 8 to 4\n" +
            "move 1 from 4 to 1\n" +
            "move 3 from 5 to 7\n" +
            "move 1 from 1 to 5\n" +
            "move 5 from 5 to 8\n" +
            "move 23 from 7 to 9\n" +
            "move 5 from 8 to 4\n" +
            "move 1 from 5 to 2\n" +
            "move 12 from 3 to 4\n" +
            "move 6 from 3 to 6\n" +
            "move 1 from 5 to 2\n" +
            "move 8 from 9 to 2\n" +
            "move 1 from 7 to 8\n" +
            "move 2 from 7 to 9\n" +
            "move 4 from 3 to 5\n" +
            "move 1 from 5 to 9\n" +
            "move 1 from 6 to 5\n" +
            "move 4 from 6 to 5\n" +
            "move 3 from 2 to 1\n" +
            "move 3 from 1 to 3\n" +
            "move 8 from 9 to 1\n" +
            "move 4 from 2 to 9\n" +
            "move 1 from 9 to 7\n" +
            "move 14 from 4 to 8\n" +
            "move 3 from 3 to 4\n" +
            "move 1 from 5 to 8\n" +
            "move 2 from 8 to 6\n" +
            "move 2 from 6 to 7\n" +
            "move 4 from 4 to 3\n" +
            "move 12 from 9 to 1\n" +
            "move 1 from 3 to 2\n" +
            "move 6 from 8 to 2\n" +
            "move 1 from 7 to 1\n" +
            "move 5 from 2 to 3\n" +
            "move 21 from 1 to 3\n" +
            "move 5 from 5 to 4\n" +
            "move 1 from 8 to 5\n" +
            "move 2 from 2 to 7\n" +
            "move 1 from 6 to 1\n" +
            "move 2 from 9 to 2\n" +
            "move 1 from 2 to 9\n" +
            "move 1 from 1 to 5\n" +
            "move 4 from 3 to 5\n" +
            "move 7 from 8 to 1\n" +
            "move 6 from 1 to 9\n" +
            "move 1 from 2 to 5\n" +
            "move 6 from 9 to 7\n" +
            "move 8 from 3 to 4\n" +
            "move 2 from 4 to 8\n" +
            "move 1 from 1 to 6\n" +
            "move 10 from 3 to 9\n" +
            "move 12 from 4 to 2\n" +
            "move 1 from 8 to 1";
}
