import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Integer.min;
import static java.lang.Integer.parseInt;

public class Valves {

    private static class Route {
        String previousLocation;
        String location;
        Set<String> openValves;
        int currentFlow;
        int totalFlow;

        public Route(String previousLocation, String location, Set<String> openValves, int currentFlow, int totalFlow) {
            this.previousLocation = previousLocation;
            this.location = location;
            this.openValves = openValves;
            this.currentFlow = currentFlow;
            this.totalFlow = totalFlow;
        }
    }

    private static class DualRoute extends Route {
        String elephantPreviousLocation;
        String elephantLocation;

        public DualRoute(String elephantPreviousLocation, String elephantLocation,
                         String yourPreviousLocation, String yourLocation,
                         Set<String> openValves, int currentFlow, int totalFlow) {
            super(yourPreviousLocation, yourLocation, openValves, currentFlow, totalFlow);
            this.elephantPreviousLocation = elephantPreviousLocation;
            this.elephantLocation = elephantLocation;

        }
    }

    public static void main(String[] args){
        String[] lines = INPUT.split("\n");
        Map<String, Integer> flowRates = new HashMap<>();
        Map<String, Set<String>> connections = new HashMap<>();
        for(String line : lines){
            String valve = line.split(" ")[1];
            Integer flowRate = parseInt(line.split("=")[1].split(";")[0]);
            String[] tunnelLeads = line.contains("valves") ?
                    line.split(" valves ")[1].split(", "):
                    new String[]{line.split(" valve ")[1]};
            flowRates.put(valve,flowRate);
            connections.put(valve, new HashSet<>(Arrays.asList(tunnelLeads)));
        }


        int minute = 0;
        List<Route> routes = new ArrayList<>();
        routes.add(new Route("AA","AA", new HashSet<>(), 0, 0));
        while(minute < 30){
            List<Route> newRoutes = buildNewRoutesSingleRoute(flowRates, connections, routes);
            final int thisMinute = minute;
            if(thisMinute > 27){
                routes = newRoutes.stream().filter(route ->
                                route.totalFlow > (thisMinute - 5) * 35 + (thisMinute - 15) * 30
                                        + (thisMinute - 24) * 24 + (thisMinute - 27) * 20)
                        .collect(Collectors.toList());
            }
            else if(thisMinute > 24){
                routes = newRoutes.stream().filter(route ->
                        route.totalFlow > (thisMinute - 5) * 30 + (thisMinute - 15) * 25 + (thisMinute - 24) * 20)
                        .collect(Collectors.toList());
            }
            else if(thisMinute > 15){
                routes = newRoutes.stream().filter(route -> route.totalFlow > (thisMinute - 5) * 25 + (thisMinute - 15) * 20).collect(Collectors.toList());
            } else {
                routes = newRoutes.stream().filter(route -> route.totalFlow > (thisMinute - 5) * 20).collect(Collectors.toList());
            }

            System.out.println(newRoutes.size() + " Possible Routes");
            minute++;

        }

        System.out.println(routes.stream().map(route -> route.totalFlow).max(Integer::compare));

    }

    // Used for part 1:
    private static List<Route> buildNewRoutesSingleRoute(Map<String, Integer> flowRates, Map<String, Set<String>> connections, List<Route> routes) {
        List<Route> newRoutes = new ArrayList<>();
        for(Route route : routes) {
            if(!route.openValves.contains(route.location) && flowRates.get(route.location) > 0){
                Set<String> openValves = new HashSet<>(route.openValves);
                openValves.add(route.location);
                newRoutes.add(new Route(route.location, route.location, openValves,
                        route.currentFlow + flowRates.get(route.location),
                        route.totalFlow + route.currentFlow));
            }
            Set<String> movementOptions = connections.get(route.location);
            for(String newLocation : movementOptions){
                if(!newLocation.equals(route.previousLocation)) {
                    newRoutes.add(new Route(route.location, newLocation, new HashSet<>(route.openValves),
                            route.currentFlow, route.totalFlow + route.currentFlow));
                }
            }
        }
        return newRoutes;
    }

    private static final String SAMPLE_INPUT =
            "Valve AA has flow rate=0; tunnels lead to valves DD, II, BB\n" +
            "Valve BB has flow rate=13; tunnels lead to valves CC, AA\n" +
            "Valve CC has flow rate=2; tunnels lead to valves DD, BB\n" +
            "Valve DD has flow rate=20; tunnels lead to valves CC, AA, EE\n" +
            "Valve EE has flow rate=3; tunnels lead to valves FF, DD\n" +
            "Valve FF has flow rate=0; tunnels lead to valves EE, GG\n" +
            "Valve GG has flow rate=0; tunnels lead to valves FF, HH\n" +
            "Valve HH has flow rate=22; tunnel leads to valve GG\n" +
            "Valve II has flow rate=0; tunnels lead to valves AA, JJ\n" +
            "Valve JJ has flow rate=21; tunnel leads to valve II";

    private static final String INPUT = "Valve EF has flow rate=22; tunnels lead to valves FK, HT, DE\n" +
            "Valve WT has flow rate=0; tunnels lead to valves XJ, XR\n" +
            "Valve RQ has flow rate=0; tunnels lead to valves VG, AV\n" +
            "Valve HF has flow rate=17; tunnels lead to valves EO, PQ, GX\n" +
            "Valve ZH has flow rate=0; tunnels lead to valves VG, RU\n" +
            "Valve AV has flow rate=0; tunnels lead to valves RQ, VQ\n" +
            "Valve AH has flow rate=12; tunnels lead to valves DF, FC, DE, MV, YC\n" +
            "Valve PQ has flow rate=0; tunnels lead to valves CF, HF\n" +
            "Valve DP has flow rate=18; tunnels lead to valves RD, OP, DR\n" +
            "Valve RU has flow rate=16; tunnels lead to valves ZH, VJ, AQ, SG\n" +
            "Valve AQ has flow rate=0; tunnels lead to valves RU, WE\n" +
            "Valve KO has flow rate=0; tunnels lead to valves VQ, HQ\n" +
            "Valve EY has flow rate=0; tunnels lead to valves WE, VQ\n" +
            "Valve RC has flow rate=14; tunnels lead to valves QK, BL, EO\n" +
            "Valve AA has flow rate=0; tunnels lead to valves XV, MS, BG, RT, HQ\n" +
            "Valve IH has flow rate=0; tunnels lead to valves VQ, VJ\n" +
            "Valve CK has flow rate=0; tunnels lead to valves SG, KG\n" +
            "Valve BG has flow rate=0; tunnels lead to valves DY, AA\n" +
            "Valve UJ has flow rate=0; tunnels lead to valves AF, OY\n" +
            "Valve HQ has flow rate=0; tunnels lead to valves AA, KO\n" +
            "Valve XV has flow rate=0; tunnels lead to valves AA, YL\n" +
            "Valve BL has flow rate=0; tunnels lead to valves DY, RC\n" +
            "Valve YL has flow rate=0; tunnels lead to valves WE, XV\n" +
            "Valve RT has flow rate=0; tunnels lead to valves VG, AA\n" +
            "Valve MV has flow rate=0; tunnels lead to valves AH, OM\n" +
            "Valve WE has flow rate=5; tunnels lead to valves AQ, YL, OM, ZU, EY\n" +
            "Valve HN has flow rate=0; tunnels lead to valves OP, XJ\n" +
            "Valve UR has flow rate=0; tunnels lead to valves NZ, OY\n" +
            "Valve FK has flow rate=0; tunnels lead to valves OY, EF\n" +
            "Valve GE has flow rate=0; tunnels lead to valves DF, XE\n" +
            "Valve GX has flow rate=0; tunnels lead to valves HF, DY\n" +
            "Valve YC has flow rate=0; tunnels lead to valves QC, AH\n" +
            "Valve XR has flow rate=0; tunnels lead to valves DY, WT\n" +
            "Valve MS has flow rate=0; tunnels lead to valves AA, DR\n" +
            "Valve EO has flow rate=0; tunnels lead to valves HF, RC\n" +
            "Valve VQ has flow rate=9; tunnels lead to valves NZ, KO, EY, AV, IH\n" +
            "Valve DY has flow rate=23; tunnels lead to valves XR, GX, BL, BG\n" +
            "Valve XJ has flow rate=24; tunnels lead to valves QK, HN, WT\n" +
            "Valve RD has flow rate=0; tunnels lead to valves VG, DP\n" +
            "Valve ZU has flow rate=0; tunnels lead to valves VG, WE\n" +
            "Valve AF has flow rate=0; tunnels lead to valves KG, UJ\n" +
            "Valve DR has flow rate=0; tunnels lead to valves MS, DP\n" +
            "Valve NZ has flow rate=0; tunnels lead to valves VQ, UR\n" +
            "Valve DE has flow rate=0; tunnels lead to valves EF, AH\n" +
            "Valve OP has flow rate=0; tunnels lead to valves DP, HN\n" +
            "Valve QK has flow rate=0; tunnels lead to valves XJ, RC\n" +
            "Valve CF has flow rate=20; tunnel leads to valve PQ\n" +
            "Valve FC has flow rate=0; tunnels lead to valves KH, AH\n" +
            "Valve KG has flow rate=25; tunnels lead to valves HT, AF, KH, CK\n" +
            "Valve XE has flow rate=11; tunnel leads to valve GE\n" +
            "Valve OY has flow rate=7; tunnels lead to valves FK, UJ, UR, QC\n" +
            "Valve OM has flow rate=0; tunnels lead to valves MV, WE\n" +
            "Valve QC has flow rate=0; tunnels lead to valves YC, OY\n" +
            "Valve DF has flow rate=0; tunnels lead to valves AH, GE\n" +
            "Valve KH has flow rate=0; tunnels lead to valves KG, FC\n" +
            "Valve SG has flow rate=0; tunnels lead to valves CK, RU\n" +
            "Valve VG has flow rate=3; tunnels lead to valves ZH, ZU, RQ, RD, RT\n" +
            "Valve HT has flow rate=0; tunnels lead to valves KG, EF\n" +
            "Valve VJ has flow rate=0; tunnels lead to valves IH, RU";
}
