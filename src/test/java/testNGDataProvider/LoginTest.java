package testNGDataProvider;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Util.testUtil;

public class LoginTest {
	WebDriver driver;
	String baseURL = "http://www.demo.guru99.com/V4/";
	//String driverPath  = "C:\\Users\\TED GERRAD ARIAGA\\eclipse-workspace\\software\\geckodriver2.exe"; 
	String driverPath = "C:\\Users\\TED GERRAD ARIAGA\\eclipse-workspace\\software\\chromedriver3.exe";
	
	@BeforeMethod 
	public void setUp(){
		//System.setProperty("webdriver.gecko.driver", driverPath);
		System.setProperty("webdriver.chrome.driver", driverPath);
		//driver = new FirefoxDriver(); 
		driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS); 
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get(baseURL);
	}
	
	@DataProvider
	public Object[][] getLoginData(){
		Object data [][] = testUtil.getTestData("demodata");
		return data;
	}
	
	@Test(dataProvider = "getLoginData") 
	public void loginTest(String UserID, String Password){
		driver.findElement(By.xpath("/html/body/form/table/tbody/tr[1]/td[2]/input")).sendKeys(UserID);
		driver.findElement(By.xpath("/html/body/form/table/tbody/tr[2]/td[2]/input")).sendKeys(Password);
		driver.findElement(By.cssSelector("body > form:nth-child(21) > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(2) > input:nth-child(1)")).click();
		System.out.println(driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[3]/td")).getText());
		System.out.println(driver.findElement(By.xpath("/html/head/title")).getText());
	}
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
}
