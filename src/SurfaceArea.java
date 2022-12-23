import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

public class SurfaceArea {
    public static int calculate(String input) {
        String[] cubesStrs = input.split("\n");
        List<List<Integer>> cubes = new ArrayList<>();
        int sidesToRemove = 0;
        for(String cubeStr : cubesStrs){
            List<String> coordStrs = Arrays.asList(cubeStr.split(","));
            List<Integer> coords = coordStrs.stream()
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            long nearAnExistingCube = cubes.stream().filter(
                    (otherCoords -> touching(otherCoords, coords))).count();
            for(int i = 0; i < nearAnExistingCube; i++){
                sidesToRemove += 2;
            }
            cubes.add(coords);
        }
        return cubes.size() * 6 - sidesToRemove;
    }

    private static boolean touching(List<Integer> otherCoords, List<Integer> coords) {
        return check(otherCoords, coords, 0, 1, 2)
                || check(otherCoords, coords, 1, 2, 0)
                || check(otherCoords, coords, 0, 2, 1);
    }

    private static boolean check(List<Integer> otherCoords, List<Integer> coords,
                                 int equalIndex1, int equalIndex2, int offByOneIndex) {
        return (otherCoords.get(equalIndex1).intValue() == coords.get(equalIndex1)
                && otherCoords.get(equalIndex2).intValue() == coords.get(equalIndex2)
                && Math.abs(otherCoords.get(offByOneIndex) - coords.get(offByOneIndex)) == 1);
    }
}
