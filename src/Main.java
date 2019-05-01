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
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class Main
{
    private JPanel beegPanel;
    private ArrayList<String> stidList;


    public Main() throws IOException {
     
        stidList = new ArrayList<String>();
        beegPanel = new JPanel(new GridLayout(12,1));

        JFrame frame = new JFrame("Project5");
        frame.setSize(new Dimension(500,800));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.setLayout(new GridLayout(1,1));
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

        JButton showStation = new JButton("Show Station");
        JPanel stations = new JPanel(new GridLayout(1,1));
        stations.add(showStation);
        

        
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
        JComboBox compareWith = new JComboBox(array);  
        stations.add(compareWith);
        
        MesoEqual hammDist = new MesoEqual(compareWith.getName());
        ArrayList<String> resultBox = new ArrayList<String>();
        if (enterText.getText().equals("1")) {
           hammDist.hammingDistAll("1", stidList);
        }

        beegPanel.add(stations);
    }
    
    
    
    public static void main(String[] args) throws IOException {
        new Main();
    }
}
