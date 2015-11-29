import java.util.HashMap;
import java.util.Map;

/**
 * Created by Thomas on 29/11/2015.
 */
public class Backtrack {


    static Map<Integer,String> res = new HashMap<>();

    /**
     *  BackTrack core and display
     *  TODO separate display
     *
     * @param dept
     * @param letters
     */
    static void write(int dept,String[] letters){
        for (int i = 0; i < letters.length; i++) {
            res.put(dept, letters[i]);
            if(dept > 1){
                write(dept-1,letters);
            }

            //display
            if(dept <= 1) {
                for (int j = res.size(); j > 0 ; j--) {
                    System.out.print(res.get(j));
                }
                System.out.println();
            }
        }
    }
}
