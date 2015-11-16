package tony.project.language.interfaces;

import java.io.InputStream;
import java.util.ArrayList;

public interface ExcelToJSONOM {

	public ArrayList<String> getAttributeNames(InputStream fileInputStream);

	public String getJSON(InputStream fileInputStream);
	
}
