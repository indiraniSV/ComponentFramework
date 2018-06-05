package XMLGenerator;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class XMLGeneratorClass {
	 public static HashMap<String,String> orMap= new HashMap<String,String>();
	 public static WebDriver driver;
	 public String currClass;
	 @Test
	 public void keywordTest() throws Exception
	 {
		String filename =  "C:/Users/bh04272/Documents/SoftVision/src/test/resources/AutoMateDOMTesting.xls";
		Workbook wb;
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
							addMethods(cellText);
					}
					break;
				case "button":
					currClass = row.getCell(2).getStringCellValue();
					if (row.getCell(2).getStringCellValue().equalsIgnoreCase("YES")) {
							addMethods(cellText);
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
	
	 public static void InitiateURL() throws Exception
	 {
	 File ieFile = new File(System.getProperty("user.dir") + "\\drivers\\IEDriverServer.exe");
	 System.setProperty("webdriver.ie.driver", ieFile.getAbsolutePath());
	 driver = new InternetExplorerDriver();

 	 driver.get("http://spimail.spi.com/");
	 driver.manage().window().maximize();
	 Thread.sleep(5000);
	 }

	public static void addMethods(String controlname) throws Exception
	{
		System.out.println("Control Name : " + controlname);

		InitiateURL();
		driver.findElement(By.xpath(orMap.get("TextBox"))).sendKeys("indirani.s");
		
	}

	@BeforeTest
    public static HashMap<String,String> orReader()
    {
          String folderName = System.getProperty("user.dir") +"\\src\\test\\resources";
          String filename = folderName + "\\AutoMateDOMTesting.xls";
		   String elementName="";
          String elementValue="";
          try{
          Workbook dl= WorkbookFactory.create(new File(filename));
          Sheet ws=dl.getSheet("CompListing");
          for(Row rw: ws)
          {
                elementName=rw.getCell(3).getStringCellValue();
                elementValue=rw.getCell(8).getStringCellValue();
                System.out.println("OR Map"+elementName + elementValue);
                orMap.put(elementName, elementValue);
          }
          }
          catch(Exception e)
          {
          e.printStackTrace();
          }
          return orMap;
    }

}
