package tony.project.language.domain;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import tony.project.language.interfaces.CourseDM;

@DynamoDBTable(tableName="Course")
public class Course extends RootObject<Course> implements CourseDM {

	private String courseCode;
	private String semester;
	private String courseName;
	private Integer levelID;
	private Integer instructorID;
	private Integer studentAmount;
	private List<Integer> students;
	private Double attendance;
	private Double passRate;
	private Integer failStudentAmount;
	
	@DynamoDBHashKey(attributeName="CourseCode")
	public String getCourseCode() {return courseCode;}
	public void setCourseCode(String courseCode) {this.courseCode = courseCode;}
	
	@DynamoDBAttribute(attributeName="Semester")
	public String getSemester() {return semester;}
	public void setSemester(String semester) {this.semester = semester;}
	
	@DynamoDBAttribute(attributeName="CourseName")
	public String getCourseName() {return courseName;}
	public void setCourseName(String courseName) {this.courseName = courseName;}
	
	@DynamoDBAttribute(attributeName="LevelID")
	public Integer getLevelID() {return levelID;}
	public void setLevelID(Integer levelID) {this.levelID = levelID;}
	
	@DynamoDBAttribute(attributeName="InstructorID")
	public Integer getInstructorID() {return instructorID;}
	public void setInstructorID(Integer instructorID) {this.instructorID = instructorID;}
	
	@DynamoDBAttribute(attributeName="StudentAmount")
	public Integer getStudentAmount() {return studentAmount;}
	public void setStudentAmount(Integer studentAmount) {this.studentAmount = studentAmount;}
	
	@DynamoDBAttribute(attributeName="Students")
	public List<Integer> getStudents() {return students;}
	public void setStudents(List<Integer> students) {this.students = students;}
	
	@DynamoDBAttribute(attributeName="Attendance")
	public Double getAttendance() {return attendance;}
	public void setAttendance(Double attendance) {this.attendance = attendance;}
	
	@DynamoDBAttribute(attributeName="PassRate")
	public Double getPassRate() {return passRate;}
	public void setPassRate(Double passRate) {this.passRate = passRate;}
	
	
	@DynamoDBAttribute(attributeName="FailStudentAmount")
	public Integer getFailStudentAmount() {return failStudentAmount;}
	public void setFailStudentAmount(Integer failStudentAmount) {this.failStudentAmount = failStudentAmount;}
	
	
	
	public Course(String courseCode, String semester, String courseName, Integer levelID, Integer instructorID,
			Integer studentAmount, List<Integer> students, Double attendance, Double passRate,
			Integer failStudentAmount) {
		super();
		this.courseCode = courseCode;
		this.semester = semester;
		this.courseName = courseName;
		this.levelID = levelID;
		this.instructorID = instructorID;
		this.studentAmount = studentAmount;
		this.students = students;
		this.attendance = attendance;
		this.passRate = passRate;
		this.failStudentAmount = failStudentAmount;
	}
	
	public Course() {
		super();
	}
	
	
	@Override
	public String toString() {
		return "Course [courseCode=" + courseCode + ", semester=" + semester + ", courseName=" + courseName
				+ ", levelID=" + levelID + ", instructorID=" + instructorID + ", studentAmount=" + studentAmount
				+ ", students=" + students + ", attendance=" + attendance + ", passRate=" + passRate
				+ ", failStudentAmount=" + failStudentAmount + "]";
	}
	
	
	
	@Override
	public void saveACourse(Course course) {

		saveByMapper(course);
	}
	
	@Override
	public Course loadACourse(String courseCode) {
		
		
		return loadByMapper(courseCode);
	}
	
	@Override
	public void deleteCourse(String courseCode) {
		
		
		deleteByMapper(courseCode);
	}
	
	
	
	
	
	
}
