package Community;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Utility.wrapperMethods;

public class componentsDevelopment  {
	 static ExtentReports report;
	 static ExtentTest logger; 
	 public static WebDriver driver;
	 static ExtentReports extent;
	 public static File source;
	 public static HashMap<String,String> orMap= new HashMap<String,String>();
	 public static HashMap<String,String> orMapTestdata=new HashMap<String,String>();
     public static ThreadLocal<ExtentTest> testwss = new ThreadLocal<ExtentTest>();
     public static String idxpath;
     public static String namexpath;
     public static String classxpath;
     public static String classxpathval;
     public static String idxpathval;
     public static String namexpathval;
     public static String xpathval;
     public static String browserURl;
     public String currClass;
     public static int pageKey;
     public int idxpathlength;
 	public int classxpathlength;
 	public int namexpathlength;
     public static String browser_type;
     public static String UrlValue;
     
	@BeforeTest
	public void startReport(){
	 extent = new ExtentReports (System.getProperty("user.dir") +"/test-output/AutomationReport.html", true);
	 extent.addSystemInfo("Host Name", "Component Framework").addSystemInfo("Environment", "Automation Testing").addSystemInfo("User Name", "Indirani");
	 extent.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));
	 }
	@BeforeSuite
	public void excelPDFfiledeletion() throws IOException
	{
		Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
		wrapperMethods.deleteRecordfromExcel();
	}

	//@Test(priority=1)
	public void labelControls() throws Exception
	{
		InitiateURL();
		logger=extent.startTest("Validate controls are present in the Page");
		browserURl = driver.getCurrentUrl();	
	}

	@Test(priority=1)
	public void FindControls() throws Exception
	{
	 InitiateURL();
	 browserURl = driver.getCurrentUrl();
	 pageKey=1;
	 logger=extent.startTest("Validate Label Text in the Page");
	 String bodyText = driver.findElement(By.tagName("body")).getText();
	 logger.log(LogStatus.INFO, "Found Label Name as  : " + bodyText.trim().replaceAll("[\n]{2,}", "\n") + " are available in Page");
	 logger.log(LogStatus.PASS, "Found Label Value in the Page");
	 logger=extent.startTest("Framing the XPATH");
	 List<WebElement> el = driver.findElements(By.cssSelector("*"));
	  for ( WebElement e : el ) {
	    if(e.getTagName().equals("input")) {
	    String controlName;
	    switch(e.getAttribute("type"))
	    {
	    case "text":
	    	idxpath=e.getAttribute("id");
	    	namexpath=e.getAttribute("name");
	    	classxpath=e.getAttribute("class");
	    	int idxpathlength=idxpath.length();
	    	int classxpathlength=classxpath.length();
	    	int namexpathlength=namexpath.length();
	    	if(namexpathlength>=1) 
	    	{
	    	namexpathval="//input[@name='"+namexpath+"']";
	    	xpathval=namexpathval;
	    	}
	    	else if(classxpathlength>=1)
	    	{
	    	classxpathval="//input[@class='"+classxpath+"']";
		    xpathval=classxpathval;
	    	}
	    	else if(idxpathlength>=1)
	    	{
	    	idxpathval="//input[@id='"+idxpath+"']";
		    xpathval=classxpathval;
	    	}
	    	boolean xpathvalvisible = controlsVisible(xpathval);
	    	controlName="TextBox";
	    	logger.log(LogStatus.INFO, "Found" + controlName +" Controls is available in Page");
	    	writeResult(pageKey,browserURl, controlName,idxpathval,namexpathval,classxpathval,xpathval,xpathvalvisible);
	    	break;
	    	
	    case "checkbox":
	       	idxpath=e.getAttribute("id");
	    	namexpath=e.getAttribute("name");
	    	classxpath=e.getAttribute("class");
	    	idxpathlength=idxpath.length();
	    	classxpathlength=classxpath.length();
	    	namexpathlength=namexpath.length();
	    	if(namexpathlength>=1) 
	    	{
	    	namexpathval="//input[@name='"+namexpath+"']";
	    	xpathval=namexpathval;
	    	}
	    	else if(classxpathlength>=1)
	    	{
	    	classxpathval="//input[@class='"+classxpath+"']";
		    xpathval=classxpathval;
	    	}
	    	else if(idxpathlength>=1)
	    	{
	    	idxpathval="//input[@id='"+idxpath+"']";
		    xpathval=classxpathval;
	    	}
	    	xpathvalvisible = controlsVisible(xpathval);
	    	controlName="Checkbox";
	    	logger.log(LogStatus.INFO, "Found" + controlName +" Controls is available in Page");
	    	writeResult(pageKey,browserURl, controlName,idxpathval,namexpathval,classxpathval,xpathval,xpathvalvisible);
	    	break;
	    	
	    case "password":
	      	idxpath=e.getAttribute("id");
	    	namexpath=e.getAttribute("name");
	    	classxpath=e.getAttribute("class");
	    	idxpathlength=idxpath.length();
	    	classxpathlength=classxpath.length();
	    	namexpathlength=namexpath.length();
	    	if(namexpathlength>=1) 
	    	{
	    	namexpathval="//input[@name='"+namexpath+"']";
	    	xpathval=namexpathval;
	    	}
	    	else if(classxpathlength>=1)
	    	{
	    	classxpathval="//input[@class='"+classxpath+"']";
		    xpathval=classxpathval;
	    	}
	    	else if(idxpathlength>=1)
	    	{
	    	idxpathval="//input[@id='"+idxpath+"']";
		    xpathval=idxpathval;
	    	}
	    	xpathvalvisible = controlsVisible(xpathval);
	    	controlName="password TextBox";
	    	logger.log(LogStatus.INFO, "Found" + controlName +" Controls is available in Page");
	    	writeResult(pageKey,browserURl, controlName,idxpathval,namexpathval,classxpathval,xpathval,xpathvalvisible);
	    	break;
	    	
	    case "submit":
	    	idxpath=e.getAttribute("id");
	    	namexpath=e.getAttribute("name");
	    	classxpath=e.getAttribute("class");
	    	idxpathlength=idxpath.length();
	    	classxpathlength=classxpath.length();
	    	namexpathlength=namexpath.length();
	    	if(namexpathlength>=1) 
	    	{
	    	namexpathval="//input[@name='"+namexpath+"']";
	    	xpathval=namexpathval;
	    	}
	    	else if(classxpathlength>=1)
	    	{
	    	classxpathval="//input[@class='"+classxpath+"']";
		    xpathval=classxpathval;
	    	}
	    	else if(idxpathlength>=1)
	    	{
	    	idxpathval="//input[@id='"+idxpath+"']";
		    xpathval=idxpathval;
	    	}
	    	xpathvalvisible = controlsVisible(xpathval);
	    	controlName="Button";
	    	logger.log(LogStatus.INFO, "Found" + controlName +" Controls is available in Page");
	    	writeResult(pageKey,browserURl, controlName,idxpathval,namexpathval,classxpathval,xpathval,xpathvalvisible);
	    	break;
	    	
	    case "hidden":
	    	break;
	  }
	  }
	   else if(e.getTagName().equals("select")) {
	    }  
	  }
	 }
	
	@SuppressWarnings("unused")
	@Test(priority=2)
	public void keywordTest() throws Exception
	{
		org.json.JSONObject excelContents = excelToJson();
		//System.out.println(excelContents.length());
		JSONArray JArray = excelContents.getJSONArray("Sheet 0");
	      for (int j = 0; j < JArray.length(); j++) {
	        JSONObject jObj = (JSONObject) JArray.get(j);
	        String componentType = jObj.getString("Component Type");
	        String xpathValue = jObj.getString("Xpath");
	        String visibleType = jObj.getString("Visible");
	        System.out.println(componentType + ":" + xpathValue +":" + visibleType);
	        if(visibleType.equalsIgnoreCase("True"))
	        {
	        String filename = System.getProperty("user.dir") +"\\src\\test\\resources\\InputData.xls";
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
					System.out.println("cellText value ***********: "+ cellText);
					System.out.println("componentType value ***********: "+ componentType);
					
					if(cellText.equalsIgnoreCase(componentType))
					{
					System.out.println("Converting value : "+ cellText.toLowerCase());
					switch (cellText.toLowerCase()) {
					case "textbox":
						currClass = row.getCell(2).getStringCellValue();
						System.out.println("&&&&&&&&&&&&&&&&&: "+ row.getCell(2).getStringCellValue());
						if (row.getCell(2).getStringCellValue().equalsIgnoreCase("YES")) {
							if(addTextBoxMethods(xpathValue))
								{
									boolean status=true;
									System.out.println("Row value ********"+ row.getRowNum());
									int sval = row.getRowNum();
									writeStatusResult(status,sval);
								}
						}
						break;
					case "button":
						currClass = row.getCell(2).getStringCellValue();
						if (row.getCell(2).getStringCellValue().equalsIgnoreCase("YES")) {
							if(addMethods(xpathValue))
							{
								boolean status=true;
								System.out.println("Row value ********"+ row.getRowNum());
								int sval = row.getRowNum();
								writeStatusResult(status,sval);
							}
						}
						break;
					default:
						break;
					}
					}
				}
					
			} catch (EncryptedDocumentException | InvalidFormatException
					| IOException e) {
				e.printStackTrace();
			}
	      }
	      }
	}
	public static JSONObject excelToJson() throws InterruptedException, InvalidFormatException, IOException, JSONException {
	    File fileName = new File(System.getProperty("user.dir") +"\\src\\test\\resources\\XpathFraming.xls");
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

	public boolean controlsVisible(String xpathval)
	{
		boolean xpathvalboolean = driver.findElement(By.xpath(xpathval)).isDisplayed();
		return xpathvalboolean;
	}

	public static void InitiateURL() throws Exception
	{
		 logger=extent.startTest("Initiate Browser");
		 orReaderTestData();
	     String browserName = browser_type;
		 String url=UrlValue;
		 if(browserName.equalsIgnoreCase("chrome")){
			   File ieFile = new File(System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
			   System.setProperty("webdriver.chrome.driver", ieFile.getAbsolutePath());
			   driver = new ChromeDriver();
             }
         else if(browserName.equalsIgnoreCase("ie")){
      	   File ieFile = new File(System.getProperty("user.dir") + "\\drivers\\IEDriverServer.exe");
      	   System.setProperty("webdriver.ie.driver", ieFile.getAbsolutePath());
      	   driver = new InternetExplorerDriver();
         }
		 
		 driver.manage().window().maximize(); 
		 logger.log(LogStatus.INFO, "Browser Initiated ");

		 driver.get(url);
		 Thread.sleep(5000);
		 logger.log(LogStatus.PASS, "<b>"+"Application is up and running"+ "</b>");
		 logger.log(LogStatus.INFO, "<a href=" + takeScreenshot("InitiateBrowser") +"> <img width='100' height='100' src=" + takeScreenshot("InitiateBrowser") + "> </a>");
	 }

	public static boolean addTextBoxMethods(String xpathvalue) throws Exception
	{
		System.out.println("Control Name : " + xpathvalue);
		boolean statuscheck=false;
		logger.log(LogStatus.INFO, "To validate TextBox basic validation in UI");
		WebElement ele;
		ele = driver.findElement(By.xpath(xpathvalue));
		if(ele.isDisplayed())
		{
		logger.log(LogStatus.PASS, "<b>"+"Text Box is Present"+ "</b>");
		statuscheck=true;
		}
		if(ele.isEnabled())
		{
			statuscheck=true;
			logger.log(LogStatus.PASS, "<b>"+"Text Box is enabled"+ "</b>");
			ele.sendKeys("indirani.s");
			logger.log(LogStatus.PASS, "Able to Enter value in Textbox");
			logger.log(LogStatus.INFO, " <a href=" + takeScreenshot("TextBox") +"> <img width='100' height='100' src=" + takeScreenshot("TextBox") + ">");
		}
		else{
			logger.log(LogStatus.FAIL, "<b>"+"Text Box is not enabled"+ "</b>");
			statuscheck=false;
		}
		return statuscheck;
	}

	public static boolean addMethods(String xpathValue) throws Exception
	{
		
		boolean statuscheck=false;
		System.out.println("Control Name : " + xpathValue);
		logger.log(LogStatus.INFO, "To Verify Button Visibility in UI");
		boolean dflag= driver.findElement(By.xpath(xpathValue)).isDisplayed();
		boolean eflag=driver.findElement(By.xpath(xpathValue)).isEnabled();
		String expvalue=driver.findElement(By.xpath(xpathValue)).getAttribute("value");
		logger.log(LogStatus.PASS, "<b>"+"Displayed Button Name as :  "+expvalue+"</b>");
		logger.log(LogStatus.INFO, " <a href=" + takeScreenshot("Button") +"> <img width='100' height='100' src=" + takeScreenshot("Button") + ">");
		if(dflag)
		{
			logger.log(LogStatus.PASS, "<b>"+"Button is displayed "+"</b>");
			statuscheck=true;
		}
		else
		{
			logger.log(LogStatus.FAIL, "<b>"+"Button is not displayed "+"</b>");
			statuscheck=false;
		}
		if(eflag)
		{
			logger.log(LogStatus.PASS, "<b>"+"Button is Enabled "+"</b>");
			statuscheck=true;
		}
		else
		{
			logger.log(LogStatus.FAIL, "<b>"+"Button is not Enabled "+"</b>");
			statuscheck=false;
		}
		return statuscheck;
		
	}
    public static void orReaderTestData() throws InvalidFormatException, IOException
    {
    	String folderName = System.getProperty("user.dir") +"\\src\\test\\resources";
        String filename = folderName + "\\InputData.xls";
  	    Workbook dl= WorkbookFactory.create(new File(filename));
         Sheet ws=dl.getSheet("Main");
         boolean firstRow = true;
         
         for(Row rw: ws) {
        	 if (firstRow) {
             firstRow = false;
             continue;
           }   
     	   DataFormatter formatter = new DataFormatter();
     	   Cell cell = rw.getCell(2);
     	   browser_type = formatter.formatCellValue(cell);
     	   System.out.println(browser_type);
     	   Cell url_value = rw.getCell(3);
     	   UrlValue = formatter.formatCellValue(url_value);
     	   System.out.println(UrlValue);
         }
    }
    
	@SuppressWarnings("deprecation")
	public static void writeResult(int pageKey,String browserURl, String controlName, String idval, String namexpathval, String classxpathval,String xpathval,boolean xpathvalvisible) throws Exception {
		
	    String fileLocation = System.getProperty("user.dir") + "//src//test//resources//";
	    String fileNameForm = fileLocation + "XpathFraming.xls";
	    File f = new File(fileNameForm);
	    if (f.exists()) {
	      FileInputStream fileOut = new FileInputStream(new File(fileNameForm));
	      HSSFWorkbook workbook = new HSSFWorkbook(fileOut);
	      HSSFSheet worksheet = workbook.getSheet("CompListing");
	      int row1 = worksheet.getPhysicalNumberOfRows();
	      int rs=row1-1;
	      int rowval = rs + 1;
	      
	      Row row = worksheet.createRow(rowval);
	      HSSFCell cellpagekey = (HSSFCell) row.createCell(0);
	      cellpagekey.setCellType(Cell.CELL_TYPE_STRING);
	      cellpagekey.setCellValue(pageKey);
	      
	      HSSFCell cellbrowser = (HSSFCell) row.createCell(1);
	      cellbrowser.setCellType(Cell.CELL_TYPE_STRING);
	      cellbrowser.setCellValue(browserURl);
	     
	      HSSFCell cellctrl = (HSSFCell) row.createCell(3);
	      cellctrl.setCellType(Cell.CELL_TYPE_STRING);
	      cellctrl.setCellValue(controlName);
	      
	      HSSFCell cellid = (HSSFCell) row.createCell(4);
	      cellid.setCellType(Cell.CELL_TYPE_STRING);
	      cellid.setCellValue(idval);
	
	      HSSFCell cellname = (HSSFCell) row.createCell(5);
	      cellname.setCellType(Cell.CELL_TYPE_STRING);
	      cellname.setCellValue(namexpathval);
	      
	      HSSFCell cellclass = (HSSFCell) row.createCell(6);
	      cellclass.setCellType(Cell.CELL_TYPE_STRING);
	      cellclass.setCellValue(classxpathval);
	      
	      HSSFCell cellxpath = (HSSFCell) row.createCell(7);
	      cellxpath.setCellType(Cell.CELL_TYPE_STRING);
	      cellxpath.setCellValue(xpathval);
	      
	      HSSFCell cellxpathvis = (HSSFCell) row.createCell(8);
	      cellxpathvis.setCellType(Cell.CELL_TYPE_STRING);
	      cellxpathvis.setCellValue(xpathvalvisible);
	      
	      fileOut.close();
	      FileOutputStream outFile = new FileOutputStream(new File(fileNameForm));
	      workbook.write(outFile);
	      workbook.close();
	      outFile.close();
	    }
	  }
	

	@SuppressWarnings("deprecation")
	public static void writeStatusResult(boolean status,int sval) throws Exception {
	    String fileLocation = System.getProperty("user.dir") + "//src//test//resources//";
	    String fileNameForm = fileLocation + "KeywordDriven.xls";
	    FileInputStream file = new FileInputStream(new File(fileNameForm));
        @SuppressWarnings("resource")
		HSSFWorkbook workbook = new HSSFWorkbook(file);
        HSSFSheet sheet = workbook.getSheet("Result");
        System.out.println("Write Status Result : "+ sval  +"####"+ status);
	      if(status==true)
	      {
	      Row row = sheet.getRow(sval);
	      HSSFCell cell8 = (HSSFCell) row.createCell(3);
	      cell8.setCellType(Cell.CELL_TYPE_STRING);
	      cell8.setCellValue("PASS");
	      }
	      else
	      {
	      Row row = sheet.getRow(sval);
	      HSSFCell cell8 = (HSSFCell) row.createCell(3);
	      cell8.setCellValue("FAIL");
	      }

        file.close();

        FileOutputStream outFile =new FileOutputStream(new File(fileNameForm));
        workbook.write(outFile);
        outFile.close();
       }
		
	public static String takeScreenshot(String screenName) throws HeadlessException, AWTException, IOException
	{
		String screenshotFolderPath= System.getProperty("user.dir")+"\\elementScreen";
		BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		ImageIO.write(image, "png", new File(screenshotFolderPath+"\\"+screenName+".png")); 
		String strscreen =screenshotFolderPath+"\\"+screenName+".png";
		return strscreen;
	}		
	 
	 @AfterTest
	 public void endReport() throws Exception {
		 //tearDown();
	     extent.endTest(logger);
	     extent.flush();
	 }
/*	 public void tearDown() throws Exception {
		 driver.close();
		 logger.log(LogStatus.INFO, "Browser Closed");
		 driver.quit();
	 }*/
	}

