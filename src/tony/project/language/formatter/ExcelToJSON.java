package tony.project.language.formatter;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import tony.project.language.interfaces.ExcelToJSONOM;

public class ExcelToJSON implements ExcelToJSONOM {

	private String json;

	private Map<String, String> item;
	private List<String> attributes;
	private List<String> values;
	private List<Map<String, String>> jsons;
	
//	private File file;
//	private FileInputStream fileInputStream;
	private XSSFSheet sheet;
	private XSSFWorkbook workbook;

	@Override
	public String getJSON(InputStream fileInputStream) {
		int rowCount = 0;

		try {

			
			
			workbook = new XSSFWorkbook(fileInputStream);
			sheet = workbook.getSheetAt(0);

			attributes = new ArrayList<>();
			values = new ArrayList<>();
			jsons = new ArrayList<>();

			Iterator<Row> rowIterator = sheet.rowIterator();

			while (rowIterator.hasNext()) {

				Row row = rowIterator.next();

				Iterator<Cell> cellIterator = row.cellIterator();

				while (cellIterator.hasNext()) {

					Cell cell = cellIterator.next();

					if (rowCount < 1) {

						getAttributes(cell);

					} else {

						getValues(cell);

					}

				} // finished all cells in one row
				
				
				if (rowCount > 1 && attributes.size() == values.size()) {

					getItem();
					jsons.add(item);

				} // finished putting one item in json

				
				values.clear();
				
				
				rowCount++;

			} // finished all rows in one sheet

			json = jsons.toString();
			return json;

		} catch (Exception e) {
			System.err.println(e.getMessage() + 1);

		} finally {

			
			try {
				fileInputStream.close();
			} catch (Exception e2) {

				System.err.println(e2.getMessage());
			}

		}

		return json;
	}

	private void getItem() {
		item = new JSONHashMap<String, String>();

		for (int i = 0; i < attributes.size(); i++) {

			if (attributes.get(i).equals("StudentID")) {
				String sIDStr = values.get(i);
				Double sIDDou = Double.valueOf(sIDStr);
				int sIDInt = sIDDou.intValue();
				sIDStr = String.valueOf(sIDInt);
				item.put(attributes.get(i), sIDStr);
			} else {

				item.put(attributes.get(i), values.get(i));
			}
		}
	}

	private void getValues(Cell cell) {
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_BLANK:
			values.add("");
			break;
		case Cell.CELL_TYPE_NUMERIC:

			values.add(String.valueOf(cell.getNumericCellValue()));

			break;
		case Cell.CELL_TYPE_STRING:
			values.add(cell.getStringCellValue());
			break;

		}
	}

	private void getAttributes(Cell cell) {
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_BLANK:
			attributes.add("");
			break;

		case Cell.CELL_TYPE_NUMERIC:
			attributes.add(String.valueOf((int) cell.getNumericCellValue()));
			break;

		case Cell.CELL_TYPE_STRING:
			attributes.add(cell.getStringCellValue());
			break;

		}
	}

}
