public interface ContainmentDeterminer {

    boolean determineContainment(int firstAssignmentLower, int firstAssignmentUpper,
                                          int secondAssignmentLower, int secondAssignmentUpper);
}
