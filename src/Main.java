import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Main class constructs the GUI and creates it
 * @author Aaron Craig
 *
 */
public class Main
{
    /**
     * frame is the overall frame
     * stidList holds the stations in mesonet.txt
     */
    private JFrame frame;
    private ArrayList<String> stidList;


    /**
     * Constructs the GUI 
     * @throws IOException
     */
    public Main() throws IOException {
     
        stidList = new ArrayList<String>();

        frame = new JFrame("Project5");
        frame.setSize(new Dimension(600,700));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.setLayout(new GridBagLayout());
        JPanel custom = new JPanel();
        setUpGUI();
        
        
        frame.setVisible(true);
    }
    /**
     * Constructs all components of the GUI and adds them to the frame
     * @throws IOException
     */
    private void setUpGUI() throws IOException { 
        
        //Creates a slider with 4 ticks
        JSlider slider = new JSlider();
        slider.setMinimum(1);
        slider.setMaximum(4);
        slider.setMajorTickSpacing(1);
        slider.setSnapToTicks(true);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        
        //Creates a label and textfield that are uneditable
        JLabel enterDist = new JLabel("Enter Hamming Dist:");
        JTextField enterText = new JTextField(10);
        enterText.setEditable(false);
        
        //Adds a listener to the slider, that changes the textfield based on the slider
        ChangeListener listener = new ChangeListener()
        {
           public void stateChanged(ChangeEvent event)
           {
              JSlider source = (JSlider) event.getSource();
              enterText.setText("" + source.getValue());
           }
        };
        slider.addChangeListener(listener);

        //Creates a gridbag panel and constraints
        JPanel gridPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.WEST;
        
        //sets constraints for slider
        c.gridx = 0;
        c.gridy = 10;
        c.weightx = 0.1;
        c.insets = new Insets(5,5,5,5);
        gridPanel.add(slider,c);
        
        //sets constraints for textfield
        c.gridx = 1;
        c.gridy = 0;
        gridPanel.add(enterText,c);
        
        //sets constraints for label
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.1;
        gridPanel.add(enterDist, c);

        // Next half
        JButton showStation = new JButton("Show Station");
        JTextArea hammDistBox = new JTextArea(3,12);
        JLabel compareWithButton = new JLabel("Compare With:");
       
        String filename = "Mesonet.txt";
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String readLine;
        readLine = br.readLine();
        while (readLine != null) {  
        String newStid = readLine.substring(0, 4);
            stidList.add(newStid);
            readLine = br.readLine();
        }
        br.close();
        
        String[] array = stidList.toArray(new String[stidList.size()]);
        JComboBox<String> compareWith = new JComboBox(array); 
       
        showStation.addActionListener((e) -> {
            MesoEqual hammDist = new MesoEqual((String)compareWith.getSelectedItem());
            ArrayList<String> resultBox = new ArrayList<String>();
            if (enterText.getText().equals("1")) {
                resultBox = hammDist.hammingDistAll("1", stidList);
            }
            else if (enterText.getText().equals("2")) {
                resultBox = hammDist.hammingDistAll("2", stidList);
            }
            else if (enterText.getText().equals("3")) {
                resultBox = hammDist.hammingDistAll("3", stidList);
            }
            else {
                resultBox = hammDist.hammingDistAll("4", stidList);
            }
            hammDistBox.setText(null);
            for(String a : resultBox){
                hammDistBox.append(a + "\n");
             }
        });
        JScrollPane scroll = new JScrollPane (hammDistBox);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        c.gridx = 0;
        c.gridy =15;
        c.weightx = 0.1;
        gridPanel.add(showStation, c); 
        c.gridx = 0;
        c.gridy =20;
        c.ipady = 150;
        c.ipadx = 70;
        c.weightx = 0.1;
        gridPanel.add(scroll, c);
        c.ipady = 0;
        c.ipadx = 0;
        c.gridx = 0;
        c.gridy = 25;
        c.weightx = 0.1;
        gridPanel.add(compareWithButton, c);
        c.gridx = 1;
        c.gridy =25;
        c.weightx = 0.1;
        gridPanel.add(compareWith, c);

        //Next section
        JButton calcHD = new JButton("Calculate HD");
   
        JLabel dist0 = new JLabel("Distance 0");
        JLabel dist1 = new JLabel("Distance 1");
        JLabel dist2 = new JLabel("Distance 2");
        JLabel dist3 = new JLabel("Distance 3");
        JLabel dist4 = new JLabel("Distance 4");
        JTextField dist0Text = new JTextField(10);
        dist0Text.setEditable(false);
        JTextField dist1Text = new JTextField(10);
        dist1Text.setEditable(false);
        JTextField dist2Text = new JTextField(10);
        dist2Text.setEditable(false);
        JTextField dist3Text = new JTextField(10);
        dist3Text.setEditable(false);
        JTextField dist4Text = new JTextField(10);
        dist4Text.setEditable(false);
     
        calcHD.addActionListener((e) -> {
            MesoEqual hammDist = new MesoEqual((String)compareWith.getSelectedItem());
            ArrayList<Integer> distances = new ArrayList<Integer>();
            distances = hammDist.hammingDistAllNum(stidList);
            dist0Text.setText(distances.get(0).toString());
            dist1Text.setText(distances.get(1).toString());
            dist2Text.setText(distances.get(2).toString());
            dist3Text.setText(distances.get(3).toString());
            dist4Text.setText(distances.get(4).toString());
        });

        c.gridx = 0;
        c.gridy =30;
        c.weightx = 0.1;
        gridPanel.add(calcHD, c);
        c.gridx = 0;
        c.gridy =35;
        c.weightx = 0.1;
        gridPanel.add(dist0, c);
        c.gridx = 1;
        c.gridy =35;
        c.weightx = 0.1;
        gridPanel.add(dist0Text, c);
        c.gridx = 0;
        c.gridy =40;
        c.weightx = 0.1;
        gridPanel.add(dist1, c);
        c.gridx = 1;
        c.gridy =40;
        c.weightx = 0.1;
        gridPanel.add(dist1Text, c);
        c.gridx = 0;
        c.gridy =45;
        c.weightx = 0.1;
        gridPanel.add(dist2, c);
        c.gridx = 1;
        c.gridy =45;
        c.weightx = 0.1;
        gridPanel.add(dist2Text, c);
        c.gridx = 0;
        c.gridy =50;
        c.weightx = 0.1;
        gridPanel.add(dist3, c);
        c.gridx = 1;
        c.gridy =50;
        c.weightx = 0.1;
        gridPanel.add(dist3Text, c);
        c.gridx = 0;
        c.gridy =55;
        c.weightx = 0.1;
        gridPanel.add(dist4, c);
        c.gridx = 1;
        c.gridy =55;
        c.weightx = 0.1;
        gridPanel.add(dist4Text, c);

        //Next
        JButton addStation = new JButton("Add station");
        JTextField addedStation = new JTextField(10);
        addStation.addActionListener((e) -> {
            boolean toAdd = true;
            for(String stid : stidList){
                if (addedStation.getText().equals(stid)) {
                    toAdd = false;
                }
             }
            if (toAdd == true) {
                stidList.add(addedStation.getText());
                compareWith.addItem((addedStation.getText())); 
            }
            
        });

        c.gridx = 0;
        c.gridy =60;
        c.weightx = 0.1;
        gridPanel.add(addStation, c);
        c.gridx = 1;
        c.gridy =60;
        c.weightx = 0.1;
        gridPanel.add(addedStation, c);
        
        //Creative part
        JButton asciiAvg = new JButton("Calculate ASCII average:");
        JTextField asciiText = new JTextField(10);
        JTextArea asciiBox = new JTextArea(3,12);
        asciiText.setEditable(false);
        JLabel sameAvg = new JLabel("Stations with same average:");
        JPanel gridPanel2 = new JPanel(new GridBagLayout());
        c.anchor = GridBagConstraints.EAST;
        

        asciiAvg.addActionListener((e) -> {
            MesoEqual asciiAvID = new MesoEqual((String)compareWith.getSelectedItem());
            MesoAscii asciiAv = new MesoAscii((String)compareWith.getSelectedItem());
            int avg = asciiAv.calAverage();
            asciiText.setText("" + avg);
            ArrayList<String> resultAvg;
            try
            {
                resultAvg = asciiAvID.calAsciiEqual(stidList);
                asciiBox.setText(null);
                for(String a : resultAvg){
                    asciiBox.append(a + "\n");
                 }
            } catch (IOException e1)
            {
                e1.printStackTrace();
            }
        });
        JScrollPane scroller = new JScrollPane (asciiBox);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
             
        c.gridx = 2;
        c.gridy = 10;
        c.weightx = 0.05;
        gridPanel2.add(asciiAvg, c);
        c.gridx = 2;
        c.gridy = 15;
        c.weightx = 0.05;
        gridPanel2.add(asciiText, c);
        c.gridx = 2;
        c.gridy = 20;
        c.weightx = 0.05;
        gridPanel2.add(sameAvg, c);
        c.gridx = 2;
        c.gridy = 25;
        c.weightx = 0.05;
        c.ipady = 150;
        c.ipadx = 70;
        gridPanel2.add(scroller, c);
        
        
        //puts panel in frame
        GridBagConstraints c1 = new GridBagConstraints();
        c1.weighty = 1;
        c1.weightx = 1;
        c1.anchor = GridBagConstraints.NORTHWEST;
        frame.add(gridPanel,c1);
        c1.anchor = GridBagConstraints.NORTHEAST;
        frame.add(gridPanel2, c1);
    }
    
    
    /**
     * Constructs the main object
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        new Main();
    }
}
