package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;



public class Base {
public WebDriver Driver;
public Properties prop;


public WebDriver init_Driver(String browser) {
	if(browser.equals("chrome")) {
		
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\chromedriver.exe");
		Driver=new ChromeDriver();
	}else if(prop.getProperty("headless").equals("yes")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless");
			Driver=new ChromeDriver(options);
			System.out.println("method 00Started");
		}
	return Driver;
}



public Properties init_properties() {
	Properties prop = new Properties();
	try {
		FileInputStream InputFile = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\main\\java\\utility\\cofig.properties");
		prop.load(InputFile);
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return prop;
}
}
