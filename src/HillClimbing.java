import java.util.*;

public class HillClimbing {

    private static class Coordinate {
        int row;
        int col;

        public Coordinate(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coordinate that = (Coordinate) o;
            return row == that.row && col == that.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }
    }

    public static void main(String[] args){

        String[] rows = MAP.split("\n");
        List<Coordinate> possibleStartingPoints = new ArrayList<>();
        int targetRow = 0;
        int targetCol = 0;
        for(int i = 0; i < rows.length; i++){
            char[] chars = rows[i].toCharArray();
            for(int j = 0; j < chars.length; j++){
                if(rows[i].charAt(j) == 'S' || rows[i].charAt(j) == 'a'){ //  || rows[i].charAt(j) == 'a' added for part 2
                    possibleStartingPoints.add(new Coordinate(i,j));
                }
            }
            if(rows[i].contains("E")){
                targetRow = i;
                targetCol = rows[i].indexOf('E');
            }
        }

        int shortestWalk = Integer.MAX_VALUE;
        for(Coordinate start : possibleStartingPoints){
            int startRow = start.row;
            int startCol = start.col;

            List<List<Integer>> heights = new ArrayList<>();
            List<List<Integer>> stepsToReach = new ArrayList<>();

            for(String row : rows){
                List<Integer> heightsRow = new ArrayList<>();
                List<Integer> stepsRow = new ArrayList<>();
                for(char c : row.toCharArray()){
                    if(c == 'S'){
                        heightsRow.add(0);
                    } else if(c == 'E'){
                        heightsRow.add(25);
                    } else {
                        heightsRow.add(c - 97);
                    }
                    stepsRow.add(Integer.MAX_VALUE);
                }
                heights.add(heightsRow);
                stepsToReach.add(stepsRow);
            }
            stepsToReach.get(startRow).set(startCol, 0);

            Set<Coordinate> alreadyCountedCoords = new HashSet<>();
            alreadyCountedCoords.add(new Coordinate(startRow, startCol));
            Set<Coordinate> coordinatesToSearchFrom = new HashSet<>();
            coordinatesToSearchFrom.add(new Coordinate(startRow, startCol));
            int steps = 0;
            while(stepsToReach.get(targetRow).get(targetCol) == Integer.MAX_VALUE){
                Set<Coordinate> newCoords = new HashSet<>();
                for(Coordinate coord : coordinatesToSearchFrom){
                    int coordHeight = heights.get(coord.row).get(coord.col);
                    int stepsToCoord = stepsToReach.get(coord.row).get(coord.col);
                    // up
                    if(coord.row > 0){
                        updateForMove(heights, stepsToReach, newCoords, alreadyCountedCoords, coord.row - 1, coord.col, coordHeight, stepsToCoord);
                    }
                    // down
                    if(coord.row < heights.size() - 1){
                        updateForMove(heights, stepsToReach, newCoords, alreadyCountedCoords, coord.row + 1, coord.col, coordHeight, stepsToCoord);
                    }
                    // left
                    if(coord.col > 0){
                        updateForMove(heights, stepsToReach, newCoords, alreadyCountedCoords, coord.row, coord.col - 1, coordHeight, stepsToCoord);
                    }
                    // right
                    if(coord.col < heights.get(coord.row).size() - 1){
                        updateForMove(heights, stepsToReach, newCoords, alreadyCountedCoords, coord.row, coord.col + 1, coordHeight, stepsToCoord);
                    }
                }
                coordinatesToSearchFrom = newCoords;
                steps++;
                if(steps > shortestWalk){
                    break; // no point in continuing if already longer that shortest so far
                }
            }

            if(stepsToReach.get(targetRow).get(targetCol) < shortestWalk){
                shortestWalk = stepsToReach.get(targetRow).get(targetCol);
            }
        }

        System.out.println(shortestWalk);

    }

    private static void updateForMove(List<List<Integer>> heights, List<List<Integer>> stepsToReach,
                                      Set<Coordinate> newCoords, Set<Coordinate> alreadyCountedCoords,
                                      int nextRow, int nextCol, int coordHeight, int stepsToCoord) {
        Coordinate nextCoord = new Coordinate(nextRow, nextCol);
        if(alreadyCountedCoords.contains(nextCoord)){
            return;
        }

        int nextHeight = heights.get(nextRow).get(nextCol);
        if(nextHeight <= coordHeight + 1){
            if(stepsToCoord + 1 < stepsToReach.get(nextRow).get(nextCol)){
                stepsToReach.get(nextRow).set(nextCol, stepsToCoord + 1);
                newCoords.add(nextCoord);
                alreadyCountedCoords.add(nextCoord);
            }
        }
    }

    private static String MAP =
            "abaaaaacaaaacccccccccaaaaaaccccccccccccccccccccccccccccccccccaaaaaa\n" +
                    "abaaaaacaaaaccccaaaaaaaaaacccccccccccccccccccccccccccccccccccaaaaaa\n" +
                    "abaaacccaaaaccccaaaaaaaaaaacccaacccccccccccaacccccccccccccccccaaaaa\n" +
                    "abaaaacccaacccccaaaaaaaaaaaaaaaaacccccccccccacccccccccccccccccccaaa\n" +
                    "abacaacccccccccccaaaaaaaaaaaaaaaaccccccccccaacccccccccccccccccccaaa\n" +
                    "abcccacccccccccccaaaaaaaccaaaaaaaccccccccccclllcccccacccccccccccaac\n" +
                    "abccccccccccccccccaaaaaccccccccccccccccccclllllllcccccccccccccccccc\n" +
                    "abaaacccccccccccccaaaaaccccccccccccccccaakklllllllcccccccccaacccccc\n" +
                    "abaaacccccccccccacccaaaccccccccccccccccakkklpppllllccddaaacaacccccc\n" +
                    "abaaacccaaacccccaacaaaccccccccccccccccckkkkpppppllllcddddaaaacccccc\n" +
                    "abaacccaaaacccccaaaaaccccccccccccccccckkkkpppppppllmmddddddaaaacccc\n" +
                    "abaaaccaaaaccccccaaaaaacaaacccccccccckkkkpppuuuppplmmmmdddddaaacccc\n" +
                    "abaaacccaaaccccaaaaaaaacaaaaccccccckkkkkoppuuuuuppqmmmmmmdddddacccc\n" +
                    "abcccccccccccccaaaaaaaacaaaacccccjkkkkkooppuuuuuuqqqmmmmmmmddddcccc\n" +
                    "abccccccccccccccccaaccccaaaccccjjjjkoooooouuuxuuuqqqqqqmmmmmddecccc\n" +
                    "abacaaccccccccccccaacccccccccccjjjjoooooouuuxxxuvvqqqqqqqmmmeeecccc\n" +
                    "abaaaacccccccacccaccccccccccccjjjjoootuuuuuuxxxyvvvvvqqqqmmmeeecccc\n" +
                    "abaaaaacccccaaacaaacccccccccccjjjoooottuuuuuxxyyvvvvvvvqqmnneeecccc\n" +
                    "abaaaaaccaaaaaaaaaaccccccccaccjjjooottttxxxxxxyyyyyyvvvqqnnneeecccc\n" +
                    "abaaaccccaaaaaaaaaacccccccaaccjjjoootttxxxxxxxyyyyyyvvqqqnnneeecccc\n" +
                    "SbcaaccccaaaaaaaaaaccccaaaaacajjjnnntttxxxxEzzzyyyyvvvrrqnnneeccccc\n" +
                    "abcccccccaaaaaaaaaaacccaaaaaaaajjjnnntttxxxxyyyyyvvvvrrrnnneeeccccc\n" +
                    "abcccccccaaaaaaaaaaacccccaaaaccjjjnnnnttttxxyyyyywvvrrrnnneeecccccc\n" +
                    "abcccccccccaaaaaaccaccccaaaaaccciiinnnnttxxyyyyyyywwrrnnnneeecccccc\n" +
                    "abccccccccccccaaacccccccaacaaaccciiinnnttxxyywwyyywwrrnnnffeccccccc\n" +
                    "abccccccccccccaaacccccccaccaaaccciiinnnttwwwwwwwwwwwrrrnnfffccccccc\n" +
                    "abccccccccccccccccccccccccccccccciiinnnttwwwwsswwwwwrrrnnfffccccccc\n" +
                    "abaaaccaaccccccccccccccccccccccccciinnnttswwwssswwwwrrroofffacccccc\n" +
                    "abaaccaaaaaacccccccccccccccccaaacciinnntssssssssssrrrrooofffacccccc\n" +
                    "abaccccaaaaacccccccaaacccccccaaaaciinnnssssssmmssssrrrooofffacccccc\n" +
                    "abaacaaaaaaacccccccaaaaccccccaaaaciiinmmmssmmmmmoosroooooffaaaacccc\n" +
                    "abaaaaaaaaaaaccccccaaaaccccccaaacciiimmmmmmmmmmmoooooooofffaaaacccc\n" +
                    "abcaaaaaaaaaaccccccaaaaccccccccccccihhmmmmmmmhggoooooooffffaaaccccc\n" +
                    "abcccccaaacaccccccccaaccccccccccccchhhhhhhhhhhggggggggggffaaacccccc\n" +
                    "abaccccaacccccccccccaaaccccccccccccchhhhhhhhhhgggggggggggcaaacccccc\n" +
                    "abaaaccccaccccccccccaaaacccaacccccccchhhhhhhaaaaaggggggcccccccccccc\n" +
                    "abaaaccccaaacaaaccccaaaacaaaacccccccccccccccaaaacccccccccccccccaaac\n" +
                    "abaacccccaaaaaaaccccaaaaaaaaacccccccccccccccaaacccccccccccccccccaaa\n" +
                    "abaaaccccaaaaaaccccaaaaaaaaccccccccccccccccccaacccccccccccccccccaaa\n" +
                    "abccccccaaaaaaaaaaaaaaaaaaacccccccccccccccccaaccccccccccccccccaaaaa\n" +
                    "abcccccaaaaaaaaaaaaaaaaaaaaacccccccccccccccccccccccccccccccccaaaaaa";
}
