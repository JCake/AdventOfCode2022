import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;

public class Cleanup {

    public static boolean fullyContained(String assignments) {
        return determineContainment(assignments, Cleanup::determineIfFullyContained);
    }

    private static boolean determineIfFullyContained(int firstAssignmentLower, int firstAssignmentUpper,
                                                        int secondAssignmentLower, int secondAssignmentUpper){
        return (firstAssignmentLower >= secondAssignmentLower
                && firstAssignmentUpper <= secondAssignmentUpper)
                ||
                (secondAssignmentLower >= firstAssignmentLower
                && secondAssignmentUpper <= firstAssignmentUpper);
    }

    public static long fullyContainedCount(String input) {
        return determineContainmentCount(input, Cleanup::fullyContained);
    }

    public static boolean partiallyContained(String assignments) {
        return determineContainment(assignments, Cleanup::determineIfPartiallyContained);
    }

    private static boolean determineIfPartiallyContained(int firstAssignmentLower, int firstAssignmentUpper,
                                                        int secondAssignmentLower, int secondAssignmentUpper){
        return !(firstAssignmentUpper < secondAssignmentLower)
                && !(firstAssignmentLower > secondAssignmentUpper)
                && !(secondAssignmentUpper < firstAssignmentLower)
                && !(secondAssignmentLower > firstAssignmentUpper );
    }

    public static long partiallyContainedCount(String input) {
        return determineContainmentCount(input, Cleanup::partiallyContained);
    }

    public static boolean determineContainment(String assignments, ContainmentDeterminer determiner){
        String[] twoAssignments = assignments.split(",");

        String[] firstAssignmentStringParts = twoAssignments[0].split("-");
        int firstAssignmentLower = parseInt(firstAssignmentStringParts[0]);
        int firstAssignmentUpper = parseInt(firstAssignmentStringParts[1]);

        String[] secondAssignmentStringParts = twoAssignments[1].split("-");
        int secondAssignmentLower = parseInt(secondAssignmentStringParts[0]);
        int secondAssignmentUpper = parseInt(secondAssignmentStringParts[1]);

        return determiner.determineContainment(firstAssignmentLower, firstAssignmentUpper, secondAssignmentLower, secondAssignmentUpper);
    }

    private static long determineContainmentCount(String input, Predicate<String> containmentDeterminer){
        return Stream.of(input.split("\n")).filter(containmentDeterminer).count();
    }
}
