package MavenProject;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BookAppointment {
	 WebDriver driver;
	    @BeforeTest
	   public void setup() {
	        // Set up the wWebDriverManager for chrome driver
	        WebDriverManager.chromedriver().setup();
	        // Create the driver object
	        driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	        driver.manage().window().maximize();
	    }
	    @Test(priority=1,description="Launch OpenMRS url and Login into App")
	    public void launchUrl() {
	        driver.get("https://demo.openmrs.org/openmrs/login.htm");
	        driver.findElement(By.id("username")).sendKeys("Admin");
	        driver.findElement(By.id("password")).sendKeys("Admin123");
	        driver.findElement(By.xpath("//ul[@class='select']//li[1]")).click();
	        driver.findElement(By.xpath("//input[@type='submit']")).click();
	        System.out.println("Browser launched successfully");
	    }
	    @Test(priority=2,description="Search Register Patient Option")
	    public void selectRegisterPatient() throws InterruptedException {
	    	Thread.sleep(2000);
	    	WebElement element=driver.findElement(By.xpath("//div[@id='apps']/a[1]"));
	    	WebDriverWait wait=new WebDriverWait(driver,20);
	    	wait.until(ExpectedConditions.visibilityOf(element));
	    	element.click();
	    	driver.findElement(By.xpath("//input[@placeholder='Search by ID or Name']")).sendKeys("Tom");
	    	Thread.sleep(2000);
	    	driver.findElement(By.xpath("//tbody")).getText();
	    	driver.findElement(By.xpath("//tbody//tr//td[2]")).click();
	    	Thread.sleep(2000);
	        System.out.println("Register patient searched successfully");

	    }
	    
	    @Test(priority=3,description="Book Appointmet of the searched patient")
	    public void bookAppointment() throws AWTException {
	    	String actualName=driver.findElement(By.xpath("//span[@class='PersonName-givenName']")).getText();
	    	String expectedName="Tom";
	    	assertEquals(actualName, expectedName);
	    	driver.findElement(By.xpath("//ul[@class='float-left']//li[5]//a/div/div[2]")).click();
	    	WebElement ele= driver.findElement(By.xpath("//input[@id='appointment-type']"));
	    	ele.sendKeys("Surgery");
	    	WebElement ele2=driver.findElement(By.xpath("(//a[@class='ng-scope ng-binding'])[1]"));
	    	Actions act=new Actions(driver);
	    	act.moveToElement(ele2).sendKeys(Keys.ARROW_DOWN).click().build().perform();
	    	driver.findElement(By.xpath("//input[@class='confirm']")).click();
	    	
	    }
	    @AfterTest
	    public void closeBrowser() {
	    	driver.quit();
	    }
}
