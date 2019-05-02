import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MesoEqual {
    

    private String STID;


    /**
     * initializes the hashmap
     * @param Stid is the string id given
     */
    public MesoEqual(String Stid) {
        this.STID = Stid;
    }
    
    /**
     * Takes in an arraylist of ID's and calculates the asciiAverage of all of them; if they have the same average
     * as the given string, then the string is assigned to an arraylist that is returned
     * @return arraylist of strings that hold all stations with same average
     * @throws IOException
     */
    public ArrayList<String> calAsciiEqual(ArrayList<String> stidList) throws IOException {
        int asciiAvg = new MesoAscii(STID).calAverage();
        ArrayList<String> returned = new ArrayList<String>();
        for (String stid : stidList) {
            int avg = new MesoAscii((stid)).calAverage();
            if (avg == asciiAvg) {
                returned.add(stid);
            }
        }
        return returned;
    }
    
    /**
     * Calculates the hamming distance between a given arraylist strings and a given string
     * returns the arraylist based on if the given num is 1,2,3,or 4
     * @param num: given number value as a string
     * @param stidList: given list of STID's
     * @returns an arraylist of strings
     */
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
    public ArrayList<Integer> hammingDistAllNum(ArrayList<String> stidList) {
        ArrayList<Integer> hammingDist = new ArrayList<Integer>();
        int zeroDistance = 0;
        int oneDistance = 0;
        int twoDistance = 0;
        int threeDistance = 0;
        int fourDistance = 0;
        String[] stringLetters = STID.split("");
        for (int i = 0; i < stidList.size(); i++) {
            int hammingCounter = 0;
            String[] stidLetters = stidList.get(i).split("");
            for(int j = 0; j < stringLetters.length; j++) {
                if(!stringLetters[j].equalsIgnoreCase(stidLetters[j])){
                    hammingCounter += 1;
                }
            }
            if (hammingCounter == 0) {
                zeroDistance++;
            }
            else if (hammingCounter == 1) {
                oneDistance++;
            }
            else if (hammingCounter == 2) {
                twoDistance++;
            }
            else if (hammingCounter == 3) {
                threeDistance++;
            }
            else if (hammingCounter == 4){
                fourDistance++;
            }
        }
        hammingDist.add(zeroDistance);
        hammingDist.add(oneDistance);
        hammingDist.add(twoDistance);
        hammingDist.add(threeDistance);
        hammingDist.add(fourDistance);
        return hammingDist;
    }
}