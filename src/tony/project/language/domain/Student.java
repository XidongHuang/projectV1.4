package tony.project.language.domain;

import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import tony.project.language.interfaces.StudentDM;

@DynamoDBTable(tableName="Student")
public class Student extends RootObject<Student> implements StudentDM {

	private Integer studentID;
	private String firstName;
	private String lastName;
	private Integer levelID;
	private List<String> courses;
	private String nationlity;
	private String gender;
	private String email;
	private Map<String, Double> attendance;
	private Map<String, Double> scores;
	private Integer graduation;
	private Integer pass;
	
	@DynamoDBHashKey(attributeName="StudentID")
	public Integer getStudentID() {return studentID;}
	public void setStudentID(Integer studentID) {this.studentID = studentID;}
	
	@DynamoDBAttribute(attributeName="FirstName")
	public String getFirstName() {return firstName;}
	public void setFirstName(String firstName) {this.firstName = firstName;}
	
	
	@DynamoDBAttribute(attributeName="LastName")
	public String getLastName() {return lastName;}
	public void setLastName(String lastName) {this.lastName = lastName;}
	
	
	@DynamoDBAttribute(attributeName="LevelID")
	public Integer getLevelID() {return levelID;}
	public void setLevelID(Integer levelID) {this.levelID = levelID;}
	
	
	@DynamoDBAttribute(attributeName="Courses")
	public List<String> getCourses() {return courses;}
	public void setCourses(List<String> courses) {this.courses = courses;}
	
	
	@DynamoDBAttribute(attributeName="Nationality")
	public String getNationlity() {return nationlity;}
	public void setNationlity(String nationlity) {this.nationlity = nationlity;}
	
	
	@DynamoDBAttribute(attributeName="Gender")
	public String getGender() {return gender;}
	public void setGender(String gender) {this.gender = gender;}
	
	
	@DynamoDBAttribute(attributeName="Email")
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
	
	
	@DynamoDBAttribute(attributeName="Attendance")
	public Map<String, Double> getAttendance() {return attendance;}
	public void setAttendance(Map<String, Double> attendance) {this.attendance = attendance;}
	
	
	@DynamoDBAttribute(attributeName="Scores")
	public Map<String, Double> getScores() {return scores;}
	public void setScores(Map<String, Double> scores) {this.scores = scores;}
	
	
	@DynamoDBAttribute(attributeName="Graduation")
	public Integer getGraduation() {return graduation;}
	public void setGraduation(Integer graduation) {this.graduation = graduation;}
	
	
	@DynamoDBAttribute(attributeName="Pass")
	public Integer getPass() {return pass;}
	public void setPass(Integer pass) {this.pass = pass;}
	
	public Student(Integer studentID, String firstName, String lastName, 
			Integer levelID, List<String> courses,
			String nationlity, String gender, String email, 
			Map<String, Double> attendance, Map<String, Double> scores,
			Integer graduation, Integer pass) {
		
		super();
		this.studentID = studentID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.levelID = levelID;
		this.courses = courses;
		this.nationlity = nationlity;
		this.gender = gender;
		this.email = email;
		this.attendance = attendance;
		this.scores = scores;
		this.graduation = graduation;
		this.pass = pass;
	}
	
	
	public Student() {
		super();
	}
	
	
	@Override
	public String toString() {
		return "Student [studentID=" + studentID + ", firstName=" + firstName + ", lastName=" + lastName + ", levelID="
				+ levelID + ", courses=" + courses + ", nationlity=" + nationlity + ", gender=" + gender + ", email="
				+ email + ", attendance=" + attendance + ", scores=" + scores + ", graduation=" + graduation + ", pass="
				+ pass + "]";
	}
	
	
	@Override
	public void saveAStudent(Student student) {
		saveByMapper(student);
		
	}
	
	
	@Override
	public Student loadAStudent(Integer studentID) {

		
		return loadByMapper(studentID);
	}
	
	
	@Override
	public void deleteAStudent(Integer studentID) {

		deleteByMapper(studentID);
	}
	
	
}
