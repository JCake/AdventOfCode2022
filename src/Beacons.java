import java.util.*;

import static java.lang.Integer.parseInt;

public class Beacons {

    private static class Coord {
        int x;
        int y;

        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coord coord = (Coord) o;
            return x == coord.x && y == coord.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    private static class CoordPair {
        Coord sensor;
        Coord beacon;

        int distance;

        public CoordPair(Coord sensor, Coord beacon) {
            this.sensor = sensor;
            this.beacon = beacon;
            this.distance = Math.abs(sensor.x - beacon.x) + Math.abs(sensor.y - beacon.y);
        }
    }

    public static void main(String[] args){
        String[] lines = INPUT.split("\n");
        List<CoordPair> coordPairList = new ArrayList<>();
        for(String line : lines){
            String[] words = line.split(" ");
            Coord sensor = new Coord(getNumber(words[2]), getNumber(words[3]));
            Coord beacon = new Coord(getNumber(words[8]), getNumber(words[9]));
            coordPairList.add(new CoordPair(sensor, beacon));
        }

        Set<Integer> marksInRow = new HashSet<>();
        for(CoordPair coordPair : coordPairList){
            int distanceToSpotInRow = Math.abs(coordPair.sensor.y - ROW);
            int columnsAway = 0;
            while(distanceToSpotInRow + columnsAway <= coordPair.distance){
                marksInRow.add(coordPair.sensor.x - columnsAway);
                marksInRow.add(coordPair.sensor.x + columnsAway);
                columnsAway++;
            }
        }
        for(CoordPair coordPair : coordPairList){
            if(coordPair.beacon.y == ROW){
                marksInRow.remove(coordPair.beacon.x);
            }
        }

        System.out.println(marksInRow.size());

        System.out.println(findResult(coordPairList));


    }

    private static long findResult(List<CoordPair> pairs){
        for(int x = 0; x <= MAX; x++){ // Got through 31693 with less efficient
            for(int y = 0; y <= MAX; y++){
                int coveredPlus = coveredPlus(pairs, x, y);
                if(coveredPlus == -1){
                    return (4000000L * x + y);
                } else {
                    y += coveredPlus;
                }
            }
        }
        return 0;
    }

    /**
     *
     * @param pairs
     * @param x
     * @param y
     * @return
     *  -1 if x,y not covered by any pair
     *  0 if x,y exactly covered by at least one pair
     *  number of additional spots covered if not exact beacon hit
     */
    private static int coveredPlus(List<CoordPair> pairs, int x, int y){
        for(CoordPair coordPair : pairs){
            int candidateDistanceBetween = Math.abs(coordPair.sensor.x - x) + Math.abs(coordPair.sensor.y - y);
            if(candidateDistanceBetween <= coordPair.distance){
                return coordPair.distance - candidateDistanceBetween;
            }
        }
        return -1;
    }
    private static int getNumber(String word) {
        word = word.replaceAll("[,:]","");
        return parseInt(word.split("=")[1]);
    }

    private static final int SAMPLE_ROW = 10;
    private static final int ROW = 2000000;

    private static final int SAMPLE_MAX = 20;
    private static final int MAX = 4000000;

    private static final String INPUT =
            "Sensor at x=2150774, y=3136587: closest beacon is at x=2561642, y=2914773\n" +
                    "Sensor at x=3983829, y=2469869: closest beacon is at x=3665790, y=2180751\n" +
                    "Sensor at x=2237598, y=3361: closest beacon is at x=1780972, y=230594\n" +
                    "Sensor at x=1872170, y=78941: closest beacon is at x=1780972, y=230594\n" +
                    "Sensor at x=3444410, y=3965835: closest beacon is at x=3516124, y=3802509\n" +
                    "Sensor at x=3231566, y=690357: closest beacon is at x=2765025, y=1851710\n" +
                    "Sensor at x=3277640, y=2292194: closest beacon is at x=3665790, y=2180751\n" +
                    "Sensor at x=135769, y=50772: closest beacon is at x=1780972, y=230594\n" +
                    "Sensor at x=29576, y=1865177: closest beacon is at x=255250, y=2000000\n" +
                    "Sensor at x=3567617, y=3020368: closest beacon is at x=3516124, y=3802509\n" +
                    "Sensor at x=1774477, y=148095: closest beacon is at x=1780972, y=230594\n" +
                    "Sensor at x=1807041, y=359900: closest beacon is at x=1780972, y=230594\n" +
                    "Sensor at x=1699781, y=420687: closest beacon is at x=1780972, y=230594\n" +
                    "Sensor at x=2867703, y=3669544: closest beacon is at x=3516124, y=3802509\n" +
                    "Sensor at x=1448060, y=201395: closest beacon is at x=1780972, y=230594\n" +
                    "Sensor at x=3692914, y=3987880: closest beacon is at x=3516124, y=3802509\n" +
                    "Sensor at x=3536880, y=3916422: closest beacon is at x=3516124, y=3802509\n" +
                    "Sensor at x=2348489, y=2489095: closest beacon is at x=2561642, y=2914773\n" +
                    "Sensor at x=990761, y=2771300: closest beacon is at x=255250, y=2000000\n" +
                    "Sensor at x=1608040, y=280476: closest beacon is at x=1780972, y=230594\n" +
                    "Sensor at x=2206669, y=1386195: closest beacon is at x=2765025, y=1851710\n" +
                    "Sensor at x=3932320, y=3765626: closest beacon is at x=3516124, y=3802509\n" +
                    "Sensor at x=777553, y=1030378: closest beacon is at x=255250, y=2000000\n" +
                    "Sensor at x=1844904, y=279512: closest beacon is at x=1780972, y=230594\n" +
                    "Sensor at x=2003315, y=204713: closest beacon is at x=1780972, y=230594\n" +
                    "Sensor at x=2858315, y=2327227: closest beacon is at x=2765025, y=1851710\n" +
                    "Sensor at x=3924483, y=1797070: closest beacon is at x=3665790, y=2180751\n" +
                    "Sensor at x=1572227, y=3984898: closest beacon is at x=1566446, y=4774401\n" +
                    "Sensor at x=1511706, y=1797308: closest beacon is at x=2765025, y=1851710\n" +
                    "Sensor at x=79663, y=2162372: closest beacon is at x=255250, y=2000000\n" +
                    "Sensor at x=3791701, y=2077777: closest beacon is at x=3665790, y=2180751\n" +
                    "Sensor at x=2172093, y=3779847: closest beacon is at x=2561642, y=2914773\n" +
                    "Sensor at x=2950352, y=2883992: closest beacon is at x=2561642, y=2914773\n" +
                    "Sensor at x=3629602, y=3854760: closest beacon is at x=3516124, y=3802509\n" +
                    "Sensor at x=474030, y=3469506: closest beacon is at x=-452614, y=3558516";


    private static final String SAMPLE_INPUT =
            "Sensor at x=2, y=18: closest beacon is at x=-2, y=15\n" +
            "Sensor at x=9, y=16: closest beacon is at x=10, y=16\n" +
            "Sensor at x=13, y=2: closest beacon is at x=15, y=3\n" +
            "Sensor at x=12, y=14: closest beacon is at x=10, y=16\n" +
            "Sensor at x=10, y=20: closest beacon is at x=10, y=16\n" +
            "Sensor at x=14, y=17: closest beacon is at x=10, y=16\n" +
            "Sensor at x=8, y=7: closest beacon is at x=2, y=10\n" +
            "Sensor at x=2, y=0: closest beacon is at x=2, y=10\n" +
            "Sensor at x=0, y=11: closest beacon is at x=2, y=10\n" +
            "Sensor at x=20, y=14: closest beacon is at x=25, y=17\n" +
            "Sensor at x=17, y=20: closest beacon is at x=21, y=22\n" +
            "Sensor at x=16, y=7: closest beacon is at x=15, y=3\n" +
            "Sensor at x=14, y=3: closest beacon is at x=15, y=3\n" +
            "Sensor at x=20, y=1: closest beacon is at x=15, y=3";
}
