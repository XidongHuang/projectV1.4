package tony.project.language.domain;

import java.util.List;

import javax.management.AttributeList;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;

import tony.project.language.initial.Initial;
import tony.project.language.interfaces.InstructorDM;

@DynamoDBTable(tableName="Instructor")
public class Instructor extends RootObject<Instructor> implements InstructorDM {

	private Integer staffID;
	private String instructorName;
	private List<Integer> levelID;
	private List<String> courses;
	private Boolean upload;
	private Boolean evaluation;
	
	@DynamoDBHashKey(attributeName="StaffID")
	public Integer getStaffID() {return staffID;}
	public void setStaffID(Integer staffID) {this.staffID = staffID;}
	
	@DynamoDBAttribute(attributeName="InstructorName")
	public String getInstructorName() {return instructorName;}
	public void setInstructorName(String instructorName) {this.instructorName = instructorName;}
	
	
	@DynamoDBAttribute(attributeName="LevelID")
	public List<Integer> getLevelID() {return levelID;}
	public void setLevelID(List<Integer> levelID) {this.levelID = levelID;}
	
	@DynamoDBAttribute(attributeName="Courses")
	public List<String> getCourses() {return courses;}
	public void setCourses(List<String> courses) {this.courses = courses;}
	
	@DynamoDBAttribute(attributeName="Upload")
	public Boolean getUpload() {return upload;}
	public void setUpload(Boolean upload) {this.upload = upload;}
	
	
	@DynamoDBAttribute(attributeName="Evaluation")
	public Boolean getEvaluation() {return evaluation;}
	public void setEvaluation(Boolean evaluation) {this.evaluation = evaluation;}
	
	
	
	public Instructor(Integer staffID, String instructorName, List<Integer> levelID, List<String> courses,
			Boolean upload, Boolean evaluation) {
		super();
		this.staffID = staffID;
		this.instructorName = instructorName;
		this.levelID = levelID;
		this.courses = courses;
		this.upload = upload;
		this.evaluation = evaluation;
	}
	
	public Instructor() {
		super();
	}
	
	
	@Override
	public String toString() {
		return "Instructor [staffID=" + staffID + ", instructorName=" + instructorName + ", levelID=" + levelID
				+ ", courses=" + courses + ", upload=" + upload + ", evaluation=" + evaluation + "]";
	}
	
	
	@Override
	public void savaAInstructor(Instructor instructor) {
		
		saveByMapper(instructor);
	}
	
	
	@Override
	public Instructor loadAnInstructor(Integer staffID) {
		
		
		return loadByMapper(staffID);
	}
	
	
	@Override
	public void deleteAnInstructor(Integer staffID) {
		
		deleteByMapper(staffID);
		
	}
	
	@Override
	public List<Instructor> loadInstructorByUpload(Integer upload){
		
		Condition condition = new Condition()
				.withComparisonOperator(ComparisonOperator.EQ)
				.withAttributeValueList(new AttributeValue().withN(Integer.toString(upload)));
		
		List<Instructor> instructors = scanByUpload(condition);
		
		return instructors;
		
	}

	private List<Instructor> scanByUpload(Condition condition){
		
		DynamoDBMapper mapper = Initial.getMapper();
		
		DynamoDBScanExpression scanExpression = 
				new DynamoDBScanExpression();	
		
		scanExpression.addFilterCondition("Upload", condition);

		List<Instructor> scanResult = mapper.scan(Instructor.class, scanExpression);
	
		
		return scanResult;
	}
	
	
}
