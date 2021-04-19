package week4.homeassignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MergeContacts {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();

		driver.get("http://leaftaps.com/opentaps/control/main");

		driver.manage().window().maximize();
		
		driver.findElement(By.id("username")).sendKeys("DemoSalesManager");
		driver.findElement(By.id("password")).sendKeys("crmsfa");
		driver.findElement(By.className("decorativeSubmit")).click();
		driver.findElement(By.linkText("CRM/SFA")).click();
		driver.findElement(By.linkText("Leads")).click();
		driver.findElement(By.linkText("Merge Leads")).click();

		// Moving on to the new window by selecting the icon available near From Lead
		
		
		  driver.findElement(By.
		  xpath("//input[@id = 'partyIdFrom']/following-sibling::a")).click();
		  Set<String> allWindows = driver.getWindowHandles(); List<String> parWindow =
		  new ArrayList<String>(allWindows); String FromWindow = parWindow.get(1);
		  driver.switchTo().window(FromWindow);
		  
		  // Enter the From lead ID
		  driver.findElement(By.name("id")).sendKeys("10355");
		  driver.findElement(By.xpath("//button[text() = 'Find Leads']")).click();
		  
		  // Click First Resulting lead
		  
		  Thread.sleep(2000);
		  driver.findElement(By.xpath("//a[@class = 'linktext'][1]")).click();
		  
		  // Switch back to primary window
		  
		  driver.switchTo().window(parWindow.get(0));
		  
		  // Click on Icon near To Lead and move to new window
		  
		  Thread.sleep(2000);
		  driver.findElement(By.xpath("//input[@id = 'partyIdTo']/following-sibling::a"
		  )).click(); Set<String> allWindows1 = driver.getWindowHandles(); List<String>
		  parWindow1 = new ArrayList<String>(allWindows1); String ToWindow =
		  parWindow1.get(1); driver.switchTo().window(ToWindow);
		  
		  // Enter the lead ID 
		  driver.findElement(By.name("id")).sendKeys("10356");
		  driver.findElement(By.xpath("//button[text() = 'Find Leads']")).click();
		  
		  // Click First Resulting lead 
		  Thread.sleep(2000);
		  driver.findElement(By.xpath("//a[@class = 'linktext'][1]")).click();
		  
		  // Switch back to primary window
		  
		  driver.switchTo().window(parWindow1.get(0));
		  
		  // Click Merge 
		  driver.findElement(By.className("buttonDangerous")).click();
		  
		  // Accept Alert
		  
		  driver.switchTo().alert().accept();
		 
		  // Click Find Leads link
		  driver.findElement(By.xpath("//a[text() = 'Find Leads']")).click();
		  
		  // Enter From Lead ID 
		  
		  driver.findElement(By.name("id")).sendKeys("10355");
		 

		// Click Find Leads button

		driver.findElement(By.xpath("//button[text() = 'Find Leads']")).click();
		
		Thread.sleep(2000);
		
		String finalMsg = driver.findElement(By.xpath("//div[text()='No records to display']")).getText();
		
		
		
		
		
		String str = "No records to display";
		
		if (finalMsg.equalsIgnoreCase(str)){
			
			System.out.println("Leads are merged succesfully:" + finalMsg);
			
		} else System.out.println("Leads are not merged");
		
		driver.close();

	}}


