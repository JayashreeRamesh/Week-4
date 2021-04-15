package week4.homeassignments;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PepperFry {

	public static void main(String[] args) throws InterruptedException, IOException {

		WebDriverManager.chromedriver().setup();

		ChromeOptions options = new ChromeOptions();

		options.addArguments("--disable-notifications");

		ChromeDriver driver = new ChromeDriver();

		driver.get("https://www.pepperfry.com/");

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		// Mouseover on Furniture and click Office Chairs under Chairs

		WebElement furElement = driver.findElement(By.linkText("Furniture"));

		Actions builder = new Actions(driver);

		builder.moveToElement(furElement).perform();

		driver.findElement(By.linkText("Office Chairs")).click();

		// click Executive Chairs

		driver.findElement(By.xpath("//h5[text() = 'Executive Chairs']")).click();

		// Change the minimum Height to 50 in under Dimensions

		driver.findElement(By.xpath("//input[@class = 'clipFilterDimensionHeightValue']")).clear();

		driver.findElement(By.xpath("//input[@class = 'clipFilterDimensionHeightValue']")).sendKeys("50", Keys.TAB);

		// Add "Galician High Back Executive Chair In Black Colour" chair to Wishlist

		Thread.sleep(2000);
		// driver.findElement(By.xpath("//a[text()= 'Galician High Back Executive Chair
		// in Black Colour']"));

		driver.findElement(By.xpath("//a[@data-productname = 'Galician High Back Executive Chair in Black Colour']"))
				.click();

		// Mouseover on Bedroom and Click Study tables

		WebElement bedRElement = driver.findElement(By.linkText("Bedroom"));

		Actions builder1 = new Actions(driver);

		builder1.moveToElement(bedRElement).perform();

		driver.findElement(By.linkText("Study Tables")).click();

		// Select Spacewood as Brand

		driver.findElement(By.xpath("//a[@class='clip__filter-search-icon']")).click();
		Thread.sleep(2000);
		driver.findElement(By.name("Brand search")).sendKeys("Spacewood");

		driver.findElement(By.xpath("//label[@for= 'brandsnameSpacewood']")).click();

		// Select Price as 7000 to 8000
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//input[@data-key='price']/following-sibling::label)[2]")).click();

		// Add "SOS Carter Study Table In Lorraine Walnut & Silver Grey Finish to Wishlist
		Thread.sleep(2000);
		driver.findElement(
				By.xpath("//a[@data-productname = 'SOS Carter Study Table in Lorraine walnut & silver grey Finish']"))
				.click();

		// Verify the number of items in the Wishlist

		String noOfItems  = driver.findElement(By.xpath("//span[@class='header-nav-cnt count_alert']")).getText();

		System.out.println("Number of Items in wishlist:" + noOfItems);

		// Navigate to Wishlist

		driver.findElement(By.xpath("//div[@class = 'header-nav-item wishlist']")).click();
		
		//Closing the pop up
		driver.findElement(By.xpath("//div[@id = 'regPopUp']//a")).click();
	
		
		// Closing the frames
		
		  Thread.sleep(2000); 
		  WebElement notFrame = driver.findElement(By.xpath("//iframe[@data-notification-layout-name='banner']"));
		  driver.switchTo().frame(notFrame); 
		 
		driver.findElement(By.xpath("//span[contains(@class,'wewidgeticon we_close')]")).click();
		 
		 

		// Move Table only to Cart from Wishlist
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@data-name='SOS Carter Study Table in Lorraine walnut & silver grey...']//a[1]")).click();

		

		// Click Proceed to Pay Securely

		driver.findElement(By.xpath("//a[@class ='proceed_cta']")).click();

		// Enter Pincode as 600028 in Delivery & Assembly Details and click Go

		driver.findElement(By.id("pin_code")).sendKeys("600028");
		driver.findElement(By.id("pin_code")).click();

		// Click Place Order

		driver.findElement(By.linkText("PLACE ORDER")).click();

		// Capture a screenshot by Clicking on Order Summary

		driver.findElement(By.xpath("//div[@class = 'nCheckout__accrodian-header-right']/span")).click();

		File memory = driver.getScreenshotAs(OutputType.FILE);

		FileUtils.copyFile(memory, new File("./snaps.jpg"));

		// Close the browser

		driver.quit();

	}

}
