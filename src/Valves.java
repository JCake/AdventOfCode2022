import java.util.*;

import static java.lang.Integer.min;
import static java.lang.Integer.parseInt;

public class Valves {

    private static class FlowCheck {
        int maxTotalOutput = 0;
        int stepsToMax = 1;
        String nextLocation;
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

        String location = "AA";
        int minute = 0;
        int currentFlow = 0;
        int totalFlow = 0;
        Set<String> openFlows = new HashSet<>();
        while(minute < 30){
            Set<String> movementOptions = connections.get(location);
            // TODO account for multiple steps before opening
            FlowCheck flowCheck = new FlowCheck();
            flowCheck.nextLocation = location;
            for(String option : movementOptions){
                if(minute < 29){
                    updateFlowCheck(openFlows, option, flowRates, minute, flowCheck, 1);
                    if(minute < 28){
                        for(String twoStepOption: connections.get(option)){
                            updateFlowCheck(openFlows, twoStepOption, flowRates, minute, flowCheck, 2);
                            if(minute < 27){
                                for(String threeStepOption : connections.get(twoStepOption)){
                                    updateFlowCheck(openFlows, threeStepOption, flowRates, minute, flowCheck, 3);
                                    if(minute < 26){
                                        for(String fourStepOption : connections.get(threeStepOption)){
                                            updateFlowCheck(openFlows, fourStepOption, flowRates, minute, flowCheck, 4);
                                            if(minute < 25){
                                                for(String fiveStepOption : connections.get(fourStepOption)){
                                                    updateFlowCheck(openFlows, fiveStepOption, flowRates, minute, flowCheck, 5);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

            }
            System.out.println("Moving to " + flowCheck.nextLocation + " in " + flowCheck.stepsToMax + " steps");
            if(!Objects.equals(flowCheck.nextLocation, location)){
                for(int i = 0; i < flowCheck.stepsToMax; i++){
                    minute++; // movement
                    totalFlow += currentFlow; // flow increases during this movement
                    System.out.println("Minute " + minute + " flow rate " + currentFlow + " location " + location);
                }

                location = flowCheck.nextLocation;
                minute++; // time spent to open valve
                totalFlow += currentFlow; // flow increases while opening valve
                openFlows.add(location);
                currentFlow += flowRates.get(location);
                System.out.println("Minute " + minute + " flow rate " + currentFlow + " location " + location);
            } else {
                minute++; // time advances
                totalFlow += currentFlow; // flow increases while you do nothing
                System.out.println("Minute " + minute + " flow rate " + currentFlow + " location " + location);
            }

        }

        System.out.println(totalFlow);

    }

    private static void updateFlowCheck(Set<String> openFlows, String option, Map<String, Integer> flowRates, int minute, FlowCheck flowCheck, int stepsToMax) {
        if(!openFlows.contains(option)){
            int possibleNextFlow = flowRates.get(option);
            int possibleTotalOutput = possibleNextFlow * (30 - minute - stepsToMax - 1);
            if(possibleTotalOutput > flowCheck.maxTotalOutput){
                flowCheck.maxTotalOutput = possibleTotalOutput;
                flowCheck.nextLocation = option;
                flowCheck.stepsToMax = stepsToMax;
            }
        }
    }

    private static final String INPUT =
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
}
