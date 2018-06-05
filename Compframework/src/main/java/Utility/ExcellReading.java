package Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Community.componentsDevelopment;

public class ExcellReading {
public static String currClass;
public static int counter = 0;
public static String FilePath = System.getProperty("user.dir") +"\\src\\test\\resources\\XpathFraming.xls";
/*public void readExcel() throws BiffException, IOException {
	FileInputStream fs = new FileInputStream(FilePath);
	Workbook wb = Workbook.getWorkbook(fs);
	Sheet sh = wb.getSheet("CompListing");
	int totalNoOfRows = sh.getRows();
	int totalNoOfCols = sh.getColumns();

	for (int row = 0; row < totalNoOfRows; row++) {

		for (int col = 0; col < totalNoOfCols; col++) {
			System.out.print(sh.getCell(col, row).getContents() + "\t");
		}
		System.out.println();
	}
}
*/
public static void main(String args[]) throws Exception {
	org.json.JSONObject excelContents = excelToJson();
	//System.out.println(excelContents.length());
	JSONArray JArray = excelContents.getJSONArray("Sheet 0");
      for (int j = 0; j < JArray.length(); j++) {
        JSONObject jObj = (JSONObject) JArray.get(j);
        String componentType = jObj.getString("Component Type");
        String xpathValue = jObj.getString("Xpath");
        String visibleType = jObj.getString("Visible");
        System.out.println(componentType + ":" + xpathValue +":" + visibleType);
        String filename = System.getProperty("user.dir") +"\\src\\test\\resources\\XpathFraming.xls";
		try {

		    File fileName = new File(filename);
		    Workbook Wb = WorkbookFactory.create(fileName);
			Sheet sheet = Wb.getSheet("Configure");
			rowloop: for (Row row : sheet) {
				Cell cell0 = row.getCell(2);
				Cell cells = row.getCell(0);
				if (cell0 == null) {
					continue;
				}
				String cellText = cells.getStringCellValue();
				System.out.println("*****************: "+ cellText);
				switch (cellText.toLowerCase()) {
				case "text box":
					currClass = row.getCell(2).getStringCellValue();
					System.out.println("&&&&&&&&&&&&&&&&&: "+ row.getCell(2).getStringCellValue());
					if (row.getCell(2).getStringCellValue().equalsIgnoreCase("YES")) {
						
						//addTextBoxMethods(cellText);
							if(componentsDevelopment.addTextBoxMethods(cellText))
							{
								boolean status=true;
								System.out.println("Row value ********"+ row.getRowNum());
								int sval = row.getRowNum();
								componentsDevelopment.writeStatusResult(status,sval);
							}
					}
					break;
				default:
					break;
				}
			}
		} catch (EncryptedDocumentException | InvalidFormatException
				| IOException e) {
			e.printStackTrace();
		}
      }
}
public static JSONObject excelToJson() throws InterruptedException, InvalidFormatException, IOException, JSONException {
    File fileName = new File(FilePath);
    Workbook Wb = WorkbookFactory.create(fileName);
    int noOfSheets = Wb.getNumberOfSheets();
    org.json.JSONObject Json = new org.json.JSONObject();
    for (int i = 0; i < noOfSheets; i++) {
      JSONArray JSheet = new JSONArray();
      Sheet sheet = Wb.getSheetAt(i);
      boolean firstRow = true;
      for (Row rows : sheet) {
        if (firstRow) {
          firstRow = false;
          continue;
        }
        org.json.JSONObject Jrow = new org.json.JSONObject();
        for (Cell cell : rows) {
          DataFormatter df = new DataFormatter();
          Row Heading = sheet.getRow(0);
          Jrow.put("" + Heading.getCell(cell.getColumnIndex()), df.formatCellValue(cell));
        }
        JSheet.put(Jrow);
      }
      Json.put("Sheet " + i, JSheet);
    }
    return Json;
  }
}