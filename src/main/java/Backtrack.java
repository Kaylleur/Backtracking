import org.json.simple.JSONArray;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Thomas on 29/11/2015.
 */
public class Backtrack {

    private Map<Integer,String> res = new HashMap<>();

    private String[] letters;
    private int splitter = 0;
    private String file = null;
    private boolean consoleOutput = true;
    private boolean jsonWrite = false;
    private List<String> lines;
    private Writer writer;

    public Backtrack( String[] letters, int splitter) {
        this.letters = letters;
        this.splitter = splitter;
        lines = new ArrayList<>();
    }

    public Backtrack( String[] letters) {
        this.letters = letters;
    }

    public String[] getLetters() {
        return letters;
    }

    public void setLetters(String[] letters) {
        this.letters = letters;
    }

    public int getSplitter() {
        return splitter;
    }

    /**
     * set the splitter, to divide the line into multiple group
     * @param splitter
     */
    public void setSplitter(int splitter) {
        this.splitter = splitter;
    }

    /**
     * Enter name of the file
     * @return
     */
    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
        FileOutputStream writer;
        try {
            writer = new FileOutputStream(getFile());
            writer.write("".getBytes());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isConsoleOutput() {
        return consoleOutput;
    }

    public boolean writeToFile() {
        return file != null || file != "";
    }

    public void setConsoleOutput(boolean consoleOutput) {
        this.consoleOutput = consoleOutput;
    }

    public boolean isJsonWrite() {
        return jsonWrite;
    }

    public void setJsonWrite(boolean jsonWrite) {
        this.jsonWrite = jsonWrite;
    }

    /**
     *  BackTrack core and display
     * recursive /!\
     * @param dept length of the each string
     */
    void generate(int dept){
        for (int i = 0; i < letters.length; i++) {
            res.put(dept, letters[i]);
            if(dept > 1){
                generate(dept - 1);
            }

            //save
            if(dept <= 1) {
               saveLine();
            }
        }
    }

    private void saveLine(){
        String line = "";
        for (int j = res.size(); j > 0 ; j--) {
            if(splitter != 0 && j%splitter == 0 && j != res.size()) line += " ";
            line += res.get(j);
        }
        lines.add(line);
    }


    public void save(){
        try {
            if(writeToFile()) {
                System.out.println();
                writer = new BufferedWriter(new FileWriter(getFile(), true));
            }

            for(String line : lines){
                if(consoleOutput){
                    System.out.println(line);
                }

                if(writeToFile() && !jsonWrite) {
                    writer.append("\"" + line + "\"");
                    writer.append(System.getProperty("line.separator"));
                }
            }

            if(jsonWrite && writeToFile()){
                writer.flush();
                JSONArray json = new JSONArray();
                json.addAll(lines);
                writer.write(json.toJSONString());
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {writer.close();} catch (Exception ex) {/*ignore*/}
        }
    }
}
