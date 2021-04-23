import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;

public class ClientStudent {
	
	String studentName;

	public ClientStudent() {
		// TODO Auto-generated constructor stub
	}
	
	public ClientStudent(String name) {
		
		studentName = name;
		
		try {
			@SuppressWarnings("unused")
			FrameStudent paintGui = new FrameStudent(studentName);
			whenAppendToFileUsingFileWriter_thenCorrect(studentName);
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void whenAppendToFileUsingFileWriter_thenCorrect(String name)
			  throws IOException {
			  
			    FileWriter fw = new FileWriter("C:\\Users\\GAMEEKSTRA\\Desktop\\ATTENDANCE.txt", true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    bw.write("\n" + name);
			    bw.newLine();
			    bw.close();
			     
			}

}
