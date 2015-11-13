package tony.project.language.interfaces;

import tony.project.language.domain.Course;

public interface CourseDM {

	public void saveACourse(Course course);
	
	public Course loadACourse(String courseCode);
	
	public void deleteCourse(String courseCode);
}
