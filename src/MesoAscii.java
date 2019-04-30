public class MesoAscii {

    //private variable that holds the string stid value
    private String stid;

    public MesoAscii(String station) {
        this.stid = station;
     }
    
    /**
     * calculates the average ascii value of a 4 letter station ID
     * @returns the average (math.round) of the ascii value  between the 4 letters
     */
    int calAverage()
    {
        int result = 0;
        double sum = 0;
        double average = 0;
        for (int i = 0; i < 4; i ++) {
            sum += (int)stid.charAt(i);
        }
        average = sum / 4;
        result = (int)(Math.round(average));
        return result;
    }
    
}