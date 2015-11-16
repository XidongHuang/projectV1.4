package tony.project.language.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import tony.project.language.domain.ScoresDetail;
import tony.project.language.formatter.JSONHashMap;
import tony.project.language.interfaces.ScoresDetailDM;

public class testFormat {

	private ScoresDetailDM scoresDetailDM = new ScoresDetail(); 
	private Map<String, String> item = null;
	private ArrayList<HashMap<String, String>> resultJSON = null;
	private ObjectMapper mapper = null;
	
	
	
	
	@Test
	public void testList(){
		
		ArrayList<String> a = new ArrayList<>();
		System.out.println(a);
		System.out.println(a==null);
		
		
	}
	
	
	
	/*
	 *  Format JSON into 
	 * 
	 * 
	 */
	
	@Test
	public void uploadFormat(){
		
		File file = null;
		FileInputStream fileInputStream = null;
		
		
		
		file = new File("/home/tony/workspace/projectV1.4/WebContent/json/15WinL2Wri.json");
		try {
		
			fileInputStream = new FileInputStream(file);

			
			byte[] data = new byte[(int) file.length()];
			fileInputStream.read(data);
			String str = new String(data, "UTF-8");
			System.out.println(str);
			
			
			System.out.println(str);
			ArrayList<HashMap<String, String>> result = uploadingJSONFormat(str);
			
			System.out.println(result);

			
		
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
			if(fileInputStream != null){
			
				try {
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			
			}
		}
		
		
	}
	
	private ArrayList<HashMap<String, String>> uploadingJSONFormat(String jsonStr){
		mapper = new ObjectMapper();
		ArrayList<HashMap<String, String>> result = null;
		resultJSON = new ArrayList<>();
		
		try {
			
			System.out.println(jsonStr);
			result = mapper.readValue(jsonStr, ArrayList.class);
			System.out.println(result);
			Iterator<HashMap<String, String>> arrayListIterator = result.iterator();
			
			while(arrayListIterator.hasNext()){
				
				item = new JSONHashMap<String, String>();
				
				HashMap<String, String> itemMaps = arrayListIterator.next();
					System.out.println(itemMaps);
					Iterator<Entry<String, String>> i = itemMaps.entrySet().iterator();
					
					while(i.hasNext()){
						Entry<String, String> entry = i.next();
						item.put(entry.getKey(), entry.getValue());
						
					}
					
				resultJSON.add((HashMap<String, String>) item);
				
			}
			
			
			
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		return resultJSON;
	}
	
	
	
	
	
	
	/*==================================================================================
	 Parse data searched from database into table acceptable JSON format 
	 reason: scoreDetails Object has Map<String, String> field which cannot be parsed by 
	 		 Bootstrap Table
	====================================================================================*/
//	@Test
//	public void listFormat(){
//		
//		String filterConditionName = "CourseCode";
//		String filterConditionValue = "15WinL1Gar";
//		
//		resultJSON = new ArrayList<>();
//		
//		List<ScoresDetail> scoresDetails = scoresDetailDM.loadScoresDetails(filterConditionName, filterConditionValue);
//		
//		
//		String resultJSON = resultListToJSON(scoresDetails);
//		
//		System.out.println(resultJSON);
//		
//	}
//	
//	private String resultListToJSON(List resultList){
//		
//		Iterator<ScoresDetail> listIterator = resultList.iterator();
//		
//		while(listIterator.hasNext()){
//			item = new JSONHashMap<>();
//			
//			ScoresDetail s = listIterator.next();
//			item.put("StudentID", s.getStudentID().toString());
//			item.put("CourseCode", s.getCourseCode());
//			item.put("FirstName", s.getFirstName());
//			item.put("Surname", s.getSurname());
//			
//			Iterator g = s.getGrades().entrySet().iterator();
//			while(g.hasNext()){
//				
//				Map.Entry<String, String> gMap = (Entry<String, String>) g.next();
//				item.put(gMap.getKey(), gMap.getValue());
//				
//			}
//			
//			resultJSON.add(item);
//		}
//		
//		return resultJSON.toString();
//	}
	
	
}
