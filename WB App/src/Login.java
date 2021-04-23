import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Login {

	private JFrame frmLogin;
	private JTextField textField;
	private JTextField textField_1;
	
	String name;
	String validPassword = "0";
	String password;
	String endTime;
	private JTextField textField_2;
	
	static String fileLocation = System.getProperty("user.home") + "/Desktop/ATTENDANCE.txt";
	//static String fileLocation = "C:\\Users\\GAMEEKSTRA\\Desktop\\ATTENDANCE.txt"; //Please edit this

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		 try {
	            TCPServer server = new TCPServer();
	            server.start(8000);
	        } catch (Exception e) {
	            System.out.println(e.getStackTrace());
	        }
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmLogin.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setTitle("Login");
		frmLogin.setBounds(100, 100, 401, 263);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name :");
		lblNewLabel.setBounds(25, 35, 50, 20);
		frmLogin.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(65, 35, 295, 20);
		frmLogin.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Login as Student");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(textField.getText().equals("")) {
					JOptionPane.showMessageDialog(frmLogin, "Please enter your name!");
				}
				else {
					
					if(isCreated() == true) {
						name = textField.getText();
						
						frmLogin.dispose();
						@SuppressWarnings("unused")
						ClientStudent student = new ClientStudent(name);
						System.out.println(name + "heyhey");
					}
					else {
						JOptionPane.showMessageDialog(frmLogin, "Lesson has not started yet!");
					}	
				}	
			}
		});
		btnNewButton.setBounds(25, 141, 160, 72);
		frmLogin.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Login as Teacher");
		
		btnNewButton_1.setBounds(195, 141, 165, 72);
		frmLogin.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel(" Password (only teacher login) :");
		lblNewLabel_1.setBounds(25, 66, 224, 14);
		frmLogin.getContentPane().add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(206, 63, 154, 20);
		frmLogin.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Lesson End time(only teacher login):");
		lblNewLabel_2.setBounds(25, 92, 206, 14);
		frmLogin.getContentPane().add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(235, 91, 125, 20);
		frmLogin.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(textField.getText().equals("")) {
					JOptionPane.showMessageDialog(frmLogin, "Please Enter Your Name!");
				}
				else {
					if(textField_2.getText().equals("")) {
						JOptionPane.showMessageDialog(frmLogin, "Please Enter Lesson Time!");
					}
					else {
						
						name = textField.getText();
						endTime = textField_2.getText();
						
						password = textField_1.getText();
						System.out.println(password);
						System.out.println(validPassword);
						
						if(password.equals(validPassword)) {
							frmLogin.dispose();
							createFile(endTime);
							@SuppressWarnings("unused")
							ClientTeacher teacher = new ClientTeacher(name);
							System.out.println(name + "heyhey");
						}
						else if(textField_1.getText().equals("")){
							JOptionPane.showMessageDialog(frmLogin, "Please Enter Password!");
						}
						else {
							JOptionPane.showMessageDialog(frmLogin, "Invalid Password!");
						}		
						
					}
				}
			}
		});	
	}
	
	
	public static void createFile(String s) {
    	
    	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy \nHH:mm:ss");  
        Date date = new Date();  
        System.out.println(formatter.format(date));
    	
    	
    	try {
    	      File myObj = new File(fileLocation);
    	      if (myObj.createNewFile()) {
    	        System.out.println("File created: " + myObj.getName());
    	      } else {
    	        System.out.println("File already exists.");
    	      }
    	    } catch (IOException e) {
    	      System.out.println("An error occurred.");
    	      e.printStackTrace();
    	    }
    	
    	try {
		      FileWriter myWriter = new FileWriter(fileLocation);
		      myWriter.write("ATTENDANCE\n" + "Lesson Begin Time\n" + formatter.format(date) +"\n" + "Lesson End Time\n" + s + ":00\n");
		      myWriter.close();
		      System.out.println("Successfully wrote to the file.");
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
    }
	
	public boolean isCreated() {
		
		File f = new File(fileLocation);
		if(f.exists() && !f.isDirectory()) {
			return true;
		}
		else
			return false;
		
		
	}
}



