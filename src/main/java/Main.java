/**
 * Created by Thomas on 25/11/2015.
 */
public class Main {

    static String[] binaries = new String[]{"0","1"};
    static String[] abcd = new String[]{"a","b","c","d","e"};
    static String[] hexa = new String[]{"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"};

    public static void main(String[] args) {

        int length = 4;
        Backtrack backtrack = new Backtrack(binaries,2);
        backtrack.generate(length);

        /*output option*/
        backtrack.setConsoleOutput(false);
        backtrack.setFile("output.json");
        backtrack.setJsonWrite(true);

        backtrack.save();


    }


}