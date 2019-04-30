import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class MesoEqual {
    
    // private variables that hold the string value in STID, and map holds the stations with the 
    // same ascii average
    private HashMap<String, Integer> map;
    private String STID;

    /**
     * initializes the hashmap
     * @param Stid is the string id given
     */
    public MesoEqual(String Stid) {
        this.STID = Stid;
        this.map = new HashMap<String, Integer>();
    }
    
    /**
     * reads mesonent.txt, parses the STID and calculates the asciiAverage of all stations
     * if the asciiAverage is the same as the given STID average, then it is added to the hashmap and returned
     * @return HashMap with the STID and asciiAverage
     * @throws IOException
     */
    public HashMap<String, Integer> calAsciiEqual() throws IOException {
        int asciiAvg = new MesoAscii(STID).calAverage();
        
        String filename = "Mesonet.txt";
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String readLine;
        readLine = br.readLine();
        readLine = br.readLine();
        readLine = br.readLine();
        readLine = br.readLine();
        while (readLine != null) {  
            String newStid = readLine.substring(2, 6);
            int avg = new MesoAscii((newStid)).calAverage();
            if (avg == asciiAvg) {
                map.put(newStid, avg);
            }
            readLine = br.readLine();
        }
        br.close();
        return map;
    }
}