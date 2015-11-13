package tony.project.language.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import tony.project.language.domain.ScoresDetail;
import tony.project.language.domain.Staff;
import tony.project.language.initial.Initial;
import tony.project.language.interfaces.ScoresDetailDM;
import tony.project.language.interfaces.StaffDM;

public class testScan {

	@Test
	public void utScan(){
		ScoresDetailDM scoresDetailDM = new ScoresDetail();
		
		
		List<ScoresDetail> scanResult = scoresDetailDM.loadScoresDetails("FirstName", "Najla");
		System.out.println(scanResult);
		
		
	}
	
	
	@Test
	public void staffScan(){
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		scanExpression.addFilterCondition("Authority", new Condition().withComparisonOperator(ComparisonOperator.EQ)
				.withAttributeValueList(new AttributeValue().withN(Integer.toString(1))));
		
		DynamoDBMapper mapper = Initial.getMapper();
		List<Staff> staffs = mapper.scan(Staff.class, scanExpression);
		
		
		
		
		System.out.println();
		for(Staff staff:staffs){
			System.out.println(staff);
		}
		
	}
	
	
}
