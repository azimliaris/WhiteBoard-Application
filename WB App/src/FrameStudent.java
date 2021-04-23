import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.GridLayout;
import java.awt.Color;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;
import javax.swing.Timer;


@SuppressWarnings("serial")
public class FrameStudent extends JFrame
{
	static String fileLocation = System.getProperty("user.home") + "/Desktop/ATTENDANCE.txt";
	//static String fileLocation = "C:\\Users\\GAMEEKSTRA\\Desktop\\ATTENDANCE.txt"; //Please edit this
	
    private DrawPanel panel;
    @SuppressWarnings("rawtypes")
	private JComboBox colors; 
    
    
    private String colorOptions[]=
    {"Black","Blue","Cyan","Dark Gray","Gray","Green","Light Gray",
        "Magenta","Orange","Pink","Red","White","Yellow"};
    
    
    private Color colorArray[]=
    {Color.BLACK , Color.BLUE , Color.CYAN , Color.darkGray , Color.GRAY , 
        Color.GREEN, Color.lightGray , Color.MAGENTA , Color.ORANGE , 
    Color.PINK , Color.RED , Color.WHITE , Color.YELLOW};
    
    @SuppressWarnings("rawtypes")
	private JComboBox shapes; 
    private String shapeOptions[]=
    {"Line","Rectangle","Oval"};
    private JCheckBox filled; 
    private JPanel widgetJPanel; 
    private JPanel widgetPadder;
    private JPanel usersJPanel;
    private JLabel lblNewLabel_1;
    
    
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public FrameStudent(String name) throws IOException, ParseException
    {   	
        super("Welcome " + name);
  
        JLabel statusLabel = new JLabel( "" );
        panel = new DrawPanel(statusLabel);
        panel.setBounds(0, 33, 704, 428);
        
       
        colors = new JComboBox( colorOptions );
        shapes = new JComboBox( shapeOptions );
        filled = new JCheckBox( "Filled" );
        widgetJPanel = new JPanel();
        widgetJPanel.setBounds(20, 5, 245, 23);
        usersJPanel = new JPanel();
        usersJPanel.setBounds(704, 33, -20, 428);
        usersJPanel.setLayout(new GridLayout(5, 1, 20, 2));
        getContentPane().setLayout(null);
        widgetJPanel.setLayout( new GridLayout( 1, 6, 10, 5 ) );
        widgetPadder = new JPanel();
        widgetPadder.setBounds(0, 0, 684, 33);
        widgetPadder.setLayout(null);
        widgetJPanel.add( colors );
        widgetJPanel.add( shapes );                 
        widgetJPanel.add( filled );
        widgetPadder.add( widgetJPanel );
     
        getContentPane().add( widgetPadder);
        
        getContentPane().add( panel);
        
        JLabel lblNewLabel = new JLabel("Time Left: ");
        lblNewLabel.setBounds(275, 5, 89, 23);
        widgetPadder.add(lblNewLabel);
        

        ItemListenerHandler handler = new ItemListenerHandler();
        colors.addItemListener( handler );
        shapes.addItemListener( handler );
        filled.addItemListener( handler );
        
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setSize( 700, 500 );
        setVisible( true );
        
        setTimer();
        
    } 
    
    
    
    private class ItemListenerHandler implements ItemListener
    {
        public void itemStateChanged( ItemEvent event )
        {
            
            if ( event.getSource() == filled )
            {
                boolean checkFill=filled.isSelected() ? true : false; //
                panel.setCurrentShapeFilled(checkFill);
            }
            
            
            if ( event.getStateChange() == ItemEvent.SELECTED )
            {
                
                if ( event.getSource() == colors)
                {
                    panel.setCurrentShapeColor
                        (colorArray[colors.getSelectedIndex()]);
                }
                
                
                else if ( event.getSource() == shapes)
                {
                    panel.setCurrentShapeType(shapes.getSelectedIndex());
                }
            }
            
        } 
    }
    
    public long getDif() {
    	
    	String line5 = null;
		try {
			line5 = Files.readAllLines(Paths.get(fileLocation)).get(5);
			System.out.println(line5);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	Date date2 = null;
		try {
			date2 = new SimpleDateFormat("HH:mm:ss").parse(line5);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();
		String str = dtf.format(now);
		System.out.println(dtf.format(now));  
		
		Date date1 = null;
		try {
			date1 = new SimpleDateFormat("HH:mm:ss").parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	long diff = date2.getTime() - date1.getTime();
    	
    	long seconds = TimeUnit.MILLISECONDS.toSeconds(diff);
    	
    	return seconds;
    	
    }
    
    public void setTimer() {
    	
    	lblNewLabel_1 = new JLabel("0");
        lblNewLabel_1.setBounds(347, 5, 153, 23);
        widgetPadder.add(lblNewLabel_1);
    	
	    Timer timer = new Timer(1000, new ActionListener() {
	    	long count = getDif();
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	
	        	count--;
	        	
	        	int seconds = (int) count;
	            int p1 = seconds % 60;
	            int p2 = seconds / 60;
	            int p3 = p2 % 60;
	            p2 = p2 / 60;
	            
	        	if (count != 0) {
	        		lblNewLabel_1.setText(p2 + ":" + p3 + ":" + p1);
	        	} else {
	        		((Timer) (e.getSource())).stop();
	        		JOptionPane.showMessageDialog(widgetPadder, "Lesson is over!");
	        		System.exit(0);
	        	}
	        }
	    });
	    timer.setInitialDelay(0);
	    timer.start();		
        
		
	}
   
}