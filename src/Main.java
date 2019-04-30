import java.awt.Dimension;
import java.awt.GridLayout;

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
    private JPanel beegPanel = new JPanel(new GridLayout(8,1));

    public Main() {
        JFrame frame = new JFrame("Project5");
        frame.setSize(new Dimension(500,800));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.setLayout(new GridLayout(1,1));
        JPanel custom = new JPanel();
        setUpSlider();
        setUpShowStation();
        
        JComboBox compareWith = new JComboBox();
        
        frame.add(beegPanel);
        frame.add(custom);
        frame.setVisible(true);
    }
    private void setUpSlider() {
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
    }
    private void setUpShowStation() {
        JButton showStation = new JButton("Show Station");
        JPanel stations = new JPanel(new GridLayout(1,1));
        stations.add(showStation);
        
        
        
        beegPanel.add(stations);
    }
    
    
    
    public static void main(String[] args) {
        new Main();
    }
}
