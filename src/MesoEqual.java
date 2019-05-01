import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
        while (readLine != null) {  
            String newStid = readLine.substring(0, 4);
            int avg = new MesoAscii((newStid)).calAverage();
            if (avg == asciiAvg) {
                map.put(newStid, avg);
            }
            readLine = br.readLine();
        }
        br.close();
        return map;
    }
    public ArrayList<String> hammingDistAll(String num, ArrayList<String> stidList) {
        ArrayList<String> oneDistance = new ArrayList<String>();
        ArrayList<String> twoDistance = new ArrayList<String>();
        ArrayList<String> threeDistance = new ArrayList<String>();
        ArrayList<String> fourDistance = new ArrayList<String>();
        String[] stringLetters = STID.split("");
        for (int i = 0; i < stidList.size(); i++) {
            int hammingCounter = 0;
            String[] stidLetters = stidList.get(i).split("");
            for(int j = 0; j < stringLetters.length; j++) {
                if(!stringLetters[j].equalsIgnoreCase(stidLetters[j])){
                    hammingCounter += 1;
                }
            }
            if (hammingCounter == 1) {
                oneDistance.add(stidList.get(i));
            }
            else if (hammingCounter == 2) {
                twoDistance.add(stidList.get(i));
            }
            else if (hammingCounter == 3) {
                threeDistance.add(stidList.get(i));       
            }
            else if (hammingCounter == 4){
                fourDistance.add(stidList.get(i));
            }
        }
        if (num.equals("1")) {
            return oneDistance;
        }
        else if (num.equals("2")) {
            return twoDistance;
        }
        else if (num.equals("3")) {
            return threeDistance;
        }
        else {
            return fourDistance;
        }
    }
}