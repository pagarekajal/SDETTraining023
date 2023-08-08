package MavenProject;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SampleTest { 

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
	    
	    @Test(priority=2,description="Select Register Patient Option")
	    public void selectRegisterPatient() throws InterruptedException {
	    	Thread.sleep(2000);
	    	WebElement element=driver.findElement(By.xpath("//div[@id='apps']/a[5]"));
	    	WebDriverWait wait=new WebDriverWait(driver,20);
	    	wait.until(ExpectedConditions.visibilityOf(element));
	    	element.click();
	        System.out.println("Register patient option selected successfully");

	    }
	    @Test(priority=3,description="Add Patient details")
	    public void registerPatient() throws InterruptedException {
	    	Thread.sleep(2000);
	    	driver.findElement(By.xpath("//input[@name='givenName']")).sendKeys("Kejo");
	    	driver.findElement(By.xpath("//input[@name='familyName']")).sendKeys("Pagare");
	    	driver.findElement(By.id("next-button")).click();
	    	driver.findElement(By.xpath("//select[@name='gender']//option[1]")).click();
	    	driver.findElement(By.id("next-button")).click();
	    	driver.findElement(By.id("birthdateDay-field")).sendKeys("2");
	    	WebElement ele=driver.findElement(By.id("birthdateMonth-field"));
	    	Select select=new Select(ele);
	    	select.deselectByValue("June");
	    	driver.findElement(By.id("birthdateYear-field")).sendKeys("1980");
	    	driver.findElement(By.id("next-button")).click();
	    	driver.findElement(By.xpath("//input[@class='focused']")).sendKeys("Magarpatta");
	    	driver.findElement(By.xpath("//input[@name='cityVillage']")).sendKeys("Pune");
	    	driver.findElement(By.xpath("//input[@name='stateProvince']")).sendKeys("Maharashtra");
	    	driver.findElement(By.xpath("//input[@name='country']")).sendKeys("India");
	    	driver.findElement(By.xpath("//input[@name='postalCode']")).sendKeys("411099");
	    	driver.findElement(By.id("next-button")).click();
	    	driver.findElement(By.xpath("//input[@name='phoneNumber']")).sendKeys("123456678");
	    	driver.findElement(By.id("next-button")).click();
	    	driver.findElement(By.id("next-button")).click();
	    	driver.findElement(By.xpath("//input[@class='submitButton confirm right']")).click();
	        System.out.println("Patient details added successfully");

	    }
	    @Test(priority=4,description="Search register patient")
	    public void searchPatient() throws InterruptedException {
	    	driver.findElement(By.xpath("//i[@class='icon-home small']")).click();
	    	Thread.sleep(2000);
	    	driver.findElement(By.xpath("//div[@id='apps']/a[1]")).click();
	    	driver.findElement(By.xpath("//input[@placeholder='Search by ID or Name']")).sendKeys("Kejo");
	    	Thread.sleep(2000);
	    	driver.findElement(By.xpath("//tbody")).getText();
	        System.out.println("Registered patient searched successfully");

	    }

	    @AfterTest
	    public void closeBrowser() {
	    	driver.quit();
	    }
	 
}
