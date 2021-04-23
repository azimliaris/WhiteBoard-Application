import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.GridLayout;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextPane;
import javax.swing.Timer;


@SuppressWarnings("serial")
public class FrameTeacher extends JFrame
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
    private JFrame frame;
    private JLabel lblNewLabel_1;
    
    
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public FrameTeacher(String name) throws IOException
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
        
        JButton btnNewButton = new JButton("ATTENDANCE");
        btnNewButton.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {	
        		
        		frame = new JFrame();
        		frame.setTitle("Attendance List");
        		frame.setBounds(100, 100, 225, 400);
        		frame.getContentPane().setLayout(null);
        		frame.setVisible(true);
        		
        		JTextPane textPane = new JTextPane();
        		textPane.setBounds(10, 11, 189, 339);
        		frame.getContentPane().add(textPane);
        		textPane.setEditable(false);

        		try {
					textPane.setText(readAndStore());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		
        	}
        });
        btnNewButton.setBounds(564, 5, 110, 23);
        widgetPadder.add(btnNewButton);
        getContentPane().add( panel);
        
        JLabel lblNewLabel = new JLabel("Time Left: ");
        lblNewLabel.setBounds(275, 5, 82, 23);
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
    
    public String readAndStore() throws IOException {
    	
    	BufferedReader reader = new BufferedReader(new FileReader(fileLocation));
    	StringBuilder stringBuilder = new StringBuilder();
    	String line = null;
    	String ls = System.getProperty("line.separator");
    	while ((line = reader.readLine()) != null) {
    		stringBuilder.append(line);
    		stringBuilder.append(ls);
    	}
    	// delete the last new line separator
    	stringBuilder.deleteCharAt(stringBuilder.length() - 1);
    	reader.close();

    	String content = stringBuilder.toString();
    	
    	return content;
    	
    }
    
    public long getDif() {
    	
    	String line3 = null;
		try {
			line3 = Files.readAllLines(Paths.get(fileLocation)).get(3);
			System.out.println(line3);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	String line5 = null;
		try {
			line5 = Files.readAllLines(Paths.get(fileLocation)).get(5);
			System.out.println(line5);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Date date1 = null;
		try {
			date1 = new SimpleDateFormat("HH:mm:ss").parse(line3);
		} catch (ParseException e) {
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