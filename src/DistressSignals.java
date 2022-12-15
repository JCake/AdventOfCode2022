import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Integer.parseInt;

public class DistressSignals {


    // TODO sorting algorithm!
    public static int decoderKey(String input){
        String[] lines = input.split("\n");
        List<String> linesToSort = new ArrayList<>();
        for(String line : lines){
            if(!"".equals(line)){
                linesToSort.add(line);
            }
        }
        linesToSort.add("[[2]]");
        linesToSort.add("[[6]]");

        linesToSort.sort((a,b) -> {
            Boolean rightOrder = isRightOrder(a,b);
            if(rightOrder == null){
                return 0;
            } else if(rightOrder){
                return -1;
            } else {
                return 1;
            }
        });

        return (linesToSort.indexOf("[[2]]") + 1) * (linesToSort.indexOf("[[6]]") + 1);

    }


    public static int rightOrderIndexSum(String input) {
        String[] pairs = input.split("\n\n");
        int indexSum = 0;
        for(int i = 0; i < pairs.length; i++){
            String[] leftAndRight = pairs[i].split("\n");
            Boolean rightOrder = isRightOrder(leftAndRight[0], leftAndRight[1]);
            if(rightOrder != null && rightOrder){
                indexSum += (i+1);
            }
        }
        return indexSum;
    }
    public static Boolean isRightOrder(String left, String right) {
        List<String> leftItems = getAsList(left);
        List<String> rightItems = getAsList(right);
        for(int i = 0; i < leftItems.size() && i < rightItems.size(); i++){
            if(leftItems.get(i).startsWith("[") || rightItems.get(i).startsWith("[")){
                Boolean nestedResult = isRightOrder(leftItems.get(i), rightItems.get(i));
                if(nestedResult == null){
                    continue;
                } else {
                    return nestedResult;
                }
            }
            int leftValue = parseInt(leftItems.get(i));
            int rightValue = parseInt(rightItems.get(i));
            if(leftValue < rightValue){
                return true;
            }
            if(rightValue < leftValue){
                return false;
            }
        }
        if(leftItems.size() < rightItems.size()){
            return true;
        } else if(leftItems.size() > rightItems.size()){
            return false;
        } else {
            return null;
        }
    }

    private static List<String> getAsList(String str) {
        if(str.startsWith("[")){
            String[] characters = str.substring(1, str.length() - 1).split("");
            List<String> itemsInList = new ArrayList<>();
            String item = "";
            int bracketCount = 0;
            for(int i = 0; i < characters.length; i++){
                if (",".equals(characters[i]) && bracketCount == 0) {
                    itemsInList.add(item);
                    item = "";
                }
                else {
                    if("[".equals(characters[i])){
                        bracketCount++;
                    }else if("]".equals(characters[i])){
                        bracketCount--;
                    }
                    item += characters[i];
                }
            }
            if(!"".equals(item)){
                itemsInList.add(item);
            }
            return itemsInList;
        } else {
            List<String> oneItemList = new ArrayList<>();
            oneItemList.add(str);
            return oneItemList;
        }

    }

}
