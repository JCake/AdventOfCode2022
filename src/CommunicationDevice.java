import java.util.Arrays;
import java.util.HashSet;

public class CommunicationDevice {
    public static int findStartOfMarker(String input, int digits) {
        for(int i = 0; i < input.length() - digits; i++){
            int uniqueCharacterCount = new HashSet<>(Arrays.asList(input.substring(i, i+digits).split(""))).size();
            if(uniqueCharacterCount == digits){
                return i + digits;
            }
        }
        return input.length();
    }
}
