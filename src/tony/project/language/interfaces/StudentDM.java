package tony.project.language.interfaces;

import tony.project.language.domain.Student;

public interface StudentDM {

	public void saveAStudent(Student student);
	
	public Student loadAStudent(Integer studentID);
	
	public void deleteAStudent(Integer studentID);
	
	
}
