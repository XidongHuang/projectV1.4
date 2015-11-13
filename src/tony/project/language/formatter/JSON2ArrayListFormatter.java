package tony.project.language.formatter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import tony.project.language.interfaces.JSON2ArrayFormatterOM;

public class JSON2ArrayListFormatter implements JSON2ArrayFormatterOM {
	
	private ObjectMapper mapper = null;
	private ArrayList<HashMap<String, String>> resultJSON = null;
	private Map<String, String> item = null;

	@Override
	public ArrayList<HashMap<String, String>> uploadingJSONFormat(String jsonStr) {
		mapper = new ObjectMapper();
		ArrayList<HashMap<String, String>> result = null;
		resultJSON = new ArrayList<>();

		try {

			System.out.println(jsonStr);
			result = mapper.readValue(jsonStr, ArrayList.class);
			System.out.println(result);
			Iterator<HashMap<String, String>> arrayListIterator = result.iterator();

			while (arrayListIterator.hasNext()) {

				item = new JSONHashMap<String, String>();

				HashMap<String, String> itemMaps = arrayListIterator.next();
				System.out.println(itemMaps);
				Iterator<Entry<String, String>> i = itemMaps.entrySet().iterator();

				while (i.hasNext()) {
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

}
