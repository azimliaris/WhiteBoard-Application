import java.io.IOException;

public class ClientTeacher {
	
	String teacherName;

	public ClientTeacher() {
		// TODO Auto-generated constructor stub
	}
	
	public ClientTeacher(String name) {
		
		teacherName = name;
		
		try {
			@SuppressWarnings("unused")
			FrameTeacher paintGui = new FrameTeacher(teacherName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}