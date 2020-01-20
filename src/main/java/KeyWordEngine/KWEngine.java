package KeyWordEngine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import test.Base;

public class KWEngine {

	
	public WebDriver driver;
	public Properties prop;
	
	public static Workbook book;
	public static Sheet sheet;
	
	String LocatorName=null;
	String LocatorValue=null;
	public Base base;
	public WebElement element;
	
	public final String SCENARIO_SHEET_PATH="C:\\Users\\Mufazzal\\eclipse-workspace\\GoogleAccounts\\XcelKWFIles\\MCT_Scenarios.xlsx";
	
	public void startExecution(String sheetName) {
		System.out.println("method Started");
		FileInputStream File=null;
		try {
			File = new FileInputStream(SCENARIO_SHEET_PATH);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
		try {
			book=WorkbookFactory.create(File);
			
		} catch (EncryptedDocumentException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		sheet= book.getSheet(sheetName); 
		
		int k =0;
		for(int i=0;i<=sheet.getLastRowNum();i++) {
			try {
			String Locators =sheet.getRow(i+1).getCell(k+1).toString().trim();
			if(!Locators.equalsIgnoreCase("NA")) {
				
				LocatorName=Locators.split("=")[0].trim();
				LocatorValue=Locators.split("=")[1].trim();
			}
			
			
			String action =sheet.getRow(i+1).getCell(k+2).toString().trim();
			String value =sheet.getRow(i+1).getCell(k+3).toString().trim();
			
			switch(action) {
			case "Open Browser":
				
				base = new Base();
				prop= base.init_properties();
				if(value.isEmpty()||value.equals("NA")) {
					
				
				driver=base.init_Driver(prop.getProperty("browser"));
				}else {
					driver =base.init_Driver(value);
				}
				
				break;
				
			case "enter url":
				if(value.isEmpty()||value.equals("NA")) {
					driver.get(prop.getProperty("url"));
				}else {
					driver.get(value);
				}
				break;
				
			case "quit":
				driver.quit();
				break;
				
			default:
				break;
			}
			
			
			switch(LocatorName) {
			case "id":
				element = driver.findElement(By.id(LocatorValue));
				if(action.equalsIgnoreCase("sendkeys")) {
					element.clear();
					element.sendKeys(value);
				}else if(action.equalsIgnoreCase("click")) {
					element.click();
				}
				LocatorName=null;
				break;
				
			/*case "xpath":
				element = driver.findElement(By.id(LocatorValue));
				if(action.equalsIgnoreCase("sendkeys")) {
					element.clear();
					element.sendKeys(value);
				}else if(action.equalsIgnoreCase("click")) {
					element.click();
				}else
				break;*/
			}
			}catch(Exception e) {
				
			}
		}
	}

}
