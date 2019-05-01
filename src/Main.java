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


public class Main
{
    private JPanel beegPanel;

    private ArrayList<String> stidList;


    public Main() throws IOException {
     
        stidList = new ArrayList<String>();
        beegPanel = new JPanel(new GridLayout(10,1));



        JFrame frame = new JFrame("Project5");
        frame.setSize(new Dimension(500,800));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.setLayout(new GridLayout(1,2));
        JPanel custom = new JPanel();
        setUpGUI();
        
        
        frame.add(beegPanel);
        frame.add(custom);
        frame.setVisible(true);
    }
    private void setUpGUI() throws IOException { 
        
        JPanel panel1 = new JPanel(new GridLayout(1,2));   
        JPanel panel2 = new JPanel();
        
        JSlider slider = new JSlider();
        slider.setMinimum(1);
        slider.setMaximum(4);
        slider.setMajorTickSpacing(1);
        slider.setSnapToTicks(true);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        
        JLabel enterDist = new JLabel("Enter Hamming Dist:");
        JTextField enterText = new JTextField(10);
        enterText.setEditable(false);
        
        panel1.add(enterDist);
        panel1.add(enterText);
        ChangeListener listener = new ChangeListener()
        {
           public void stateChanged(ChangeEvent event)
           {
              JSlider source = (JSlider) event.getSource();
              enterText.setText("" + source.getValue());
           }
        };
        slider.addChangeListener(listener);
        panel2.add(slider);
        
        
        beegPanel.add(panel1);
        beegPanel.add(panel2);

        
        // Next half
        JButton showStation = new JButton("Show Station");
        showStation.setPreferredSize (new Dimension(10, 10));
        
        JPanel stations = new JPanel(new GridLayout(1,1));
        JTextArea hammDistBox = new JTextArea(3,12);
        JPanel panel3 = new JPanel(new GridLayout(1,2));
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


        panel3.add(compareWithButton);
        panel3.add(compareWith);
        beegPanel.add(showStation);
        beegPanel.add(scroll);
        beegPanel.add(panel3);
        beegPanel.add(stations);
        
        //Next section
        JButton calcHD = new JButton("Calculate HD");
        JPanel panel4 = new JPanel(new GridLayout(1,2));

        
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
        panel4.add(dist0);
        panel4.add(dist0Text);
        panel4.add(dist1);
        panel4.add(dist1Text);
        panel4.add(dist2);
        panel4.add(dist2Text);
        panel4.add(dist3);
        panel4.add(dist3Text);
        panel4.add(dist4);
        panel4.add(dist4Text);
        beegPanel.add(calcHD);
        beegPanel.add(panel4);
        
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
        JPanel panel5 = new JPanel(new GridLayout(2,2));
        panel5.add(addStation);
        panel5.add(addedStation);
        beegPanel.add(panel5);
        
        //Creative part
        
    }
    
    
    
    public static void main(String[] args) throws IOException {
        new Main();
    }
}
