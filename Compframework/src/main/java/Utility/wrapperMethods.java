package Utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
public class wrapperMethods {
	public static void deleteRecordfromExcel()
	{
		try
	    {
	    Workbook wb = WorkbookFactory.create(new FileInputStream(System.getProperty("user.dir") +"\\src\\test\\resources\\XpathFraming.xls"));
	    Sheet sheet = wb.getSheetAt(0);
	    Workbook wb2 = new HSSFWorkbook();
	    wb2 = wb;
	    Row row;
	    row = sheet.getRow(0);
	    int getLastCell=row.getLastCellNum()-1;
	    int lastIndex = sheet.getLastRowNum();
	    for (int i=1; i<=lastIndex; i++)
	    {
	      row=sheet.getRow(i);
	      sheet.removeRow(row);
	    }
	    FileOutputStream fileOut = new FileOutputStream(System.getProperty("user.dir") +"\\src\\test\\resources\\XpathFraming.xls");
	    wb2.write(fileOut);
	    fileOut.close();
	    }
	    catch (Exception e)
	    {
	        e.printStackTrace();
	    }
	}
	

	
	
}
