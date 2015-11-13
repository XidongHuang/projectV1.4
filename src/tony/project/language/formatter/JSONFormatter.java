package tony.project.language.formatter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import tony.project.language.interfaces.JSONFormatterOM;

public class JSONFormatter implements JSONFormatterOM {

	private ObjectMapper mapper;
	private Map<String, String> grades;
	private Map<String, String> item;

	private List<Map<String, String>> json;

	private String jsonStr;

	@Override
	public String formatJSON(String oldJson) {

		mapper = new ObjectMapper();
		grades = new JSONHashMap<>();
		json = new ArrayList<>();

		try {

			List<Map<String, String>> j = mapper.readValue(oldJson, List.class);

			Iterator it = j.iterator();

			while (it.hasNext()) {

				item = new JSONHashMap<>();

				Map<String, String> jsonItem = (HashMap<String, String>) it.next();

				Iterator mapIt = jsonItem.entrySet().iterator();

				while (mapIt.hasNext()) {

					Map.Entry<String, String> e = (Entry<String, String>) mapIt.next();

					if (e.getKey().equals("StudentID") || e.getKey().equals("FirstName")
							|| e.getKey().equals("Surname")) {

						item.put(e.getKey(), e.getValue());

					} else {

						grades.put(e.getKey(), e.getValue());
						item.put("Grades", grades.toString());

					}

				}
				json.add(item);

			}

		} catch (JsonParseException e) {

			e.printStackTrace();

		} catch (JsonMappingException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

		jsonStr = json.toString();
		return jsonStr;
	}

}
