package tony.project.language.domain;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import tony.project.language.interfaces.LevelDM;

@DynamoDBTable(tableName="Level")
public class Level extends RootObject<Level> implements LevelDM {

	private Integer levelID;
	private String semester;
	private String levelNO;
	private List<String> courses;
	private Integer studentAmount;
	private List<Integer> students;
	private Double failRate;
	private Integer failStudentAmount;
	private List<Integer> failStudents;
	

	
	@DynamoDBHashKey(attributeName="LevelID")
	public Integer getLevelID() {return levelID;}
	public void setLevelID(Integer levelID) {this.levelID = levelID;}
	
	
	@DynamoDBRangeKey(attributeName="Semester")
	public String getSemester() {return semester;}
	public void setSemester(String semester) {this.semester = semester;}
	
	@DynamoDBAttribute(attributeName="LevelNO")
	public String getLevelNO() {return levelNO;}
	public void setLevelNO(String levelNO) {this.levelNO = levelNO;}
	
	@DynamoDBAttribute(attributeName="Courses")
	public List<String> getCourses() {return courses;}
	public void setCourses(List<String> courses) {this.courses = courses;}
	
	@DynamoDBAttribute(attributeName="StudentAmount")
	public Integer getStudentAmount() {return studentAmount;}
	public void setStudentAmount(Integer studentAmount) {this.studentAmount = studentAmount;}
	
	@DynamoDBAttribute(attributeName="Students")
	public List<Integer> getStudents() {return students;}
	public void setStudents(List<Integer> students) {this.students = students;}
	
	@DynamoDBAttribute(attributeName="FailRate")
	public Double getFailRate() {return failRate;}
	public void setFailRate(Double failRate) {this.failRate = failRate;}
	
	@DynamoDBAttribute(attributeName="FailStudentAmount")
	public Integer getFailStudentAmount() {return failStudentAmount;}
	public void setFailStudentAmount(Integer failStudentAmount) {this.failStudentAmount = failStudentAmount;}
	
	@DynamoDBAttribute(attributeName="FailStudent")
	public List<Integer> getFailStudent() {return failStudents;}
	public void setFailStudent(List<Integer> failStudents) {this.failStudents = failStudents;}
	
	
	public Level(Integer levelID, String semester, String levelNO, List<String> courses, Integer studentAmount,
			List<Integer> students, Double failRate, Integer failStudentAmount, List<Integer> failStudent) {
		super();
		this.levelID = levelID;
		this.semester = semester;
		this.levelNO = levelNO;
		this.courses = courses;
		this.studentAmount = studentAmount;
		this.students = students;
		this.failRate = failRate;
		this.failStudentAmount = failStudentAmount;
		this.failStudents = failStudent;
	}
	
	
	public Level() {
		super();
	}
	
	
	@Override
	public String toString() {
		return "Level [levelID=" + levelID + ", semester=" + semester + ", levelNO=" + levelNO + ", courses=" + courses
				+ ", studentAmount=" + studentAmount + ", students=" + students + ", failRate=" + failRate
				+ ", failStudentAmount=" + failStudentAmount + ", failStudent=" + failStudents + "]";
	}
	@Override
	public void saveALevel(Level level) {

		saveByMapper(level);
	}
	@Override
	public Level loadALevel(Integer levelID, String semester) {

		Level level = loadByMapper(levelID, semester);
		
		return level;
	}
	@Override
	public void deleteALevel(Integer levelID, String semester) {

		deleteByMapper(levelID, semester);
	}
	
	
	
	
	
	
}
