package TestIng;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;



public class Tests {

	public static WebDriver driver;
	public ExtentHtmlReporter htmlreporter;
	 public ExtentReports extent;
	 public ExtentTest test;
	
	@BeforeSuite
	public void Prerequiste(){
		System.out.println(" 1st This is pre requesite");
		  htmlreporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/myreport.html");
		
		  htmlreporter.config().setDocumentTitle("Automation report");
		  htmlreporter.config().setReportName("Functional report");
		  htmlreporter.config().setTheme(Theme.DARK);
		  
		  extent = new ExtentReports();
		  
		  extent.attachReporter(htmlreporter); 
		  extent.setSystemInfo("Hostname","LocalHost"); 
		  extent.setSystemInfo("OS", "Windows 10");
		  extent.setSystemInfo("Tester name", "Sekar");
		 
	}
		
	@BeforeClass
	public void setup() {
		System.out.println("2nd This comes after pre requesite");
		System.setProperty("webdriver.chrome.driver","E:\\New folder\\sekar\\Allure_Reporting_TestNG\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.nopcommerce.com/");
	}
	
	@Test(priority=1)
	public void first() {
		
		test = extent.createTest("first");
		System.out.println("This is first method");
		boolean display = 	driver.findElement(By.xpath("//div[@class='header-logo']//a//img")).isDisplayed();
		Assert.assertEquals(display, true);
	}
	
	@Test(priority=2)
	public void second() {
		
		test = extent.createTest("logintest");
		System.out.println("second test case ");
		driver.findElement(By.xpath("//a[@class='ico-login']")).click();
		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys("sehkar21@gmail.com");
		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("csksehkar1");
		driver.findElement(By.xpath("//input[@class='button-1 login-button']")).click();
		
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		Assert.assertEquals(driver.getTitle(), "nopCommerce demo store.j Login");
	}
	
	@Test(priority=3)
	public void third(){
		
		test = extent.createTest("Registration");
		System.out.println("third test case");
		throw new SkipException("skipping this test");
		
	}
	
	@AfterClass
	public void teardown(){
		driver.quit();
	}
	
	@AfterSuite
	public void flush() {
		extent.flush();
	}
}
