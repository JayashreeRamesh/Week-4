package week4.homeassignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Amazon {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();

		ChromeOptions options = new ChromeOptions();

		options.addArguments("--disable-notifications");

		ChromeDriver driver = new ChromeDriver();

		driver.get("https://www.amazon.in/");

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		// Type "one plus 7 pro mobiles" in Search Box and Enter

		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("one plus 9 pro mobiles", Keys.ENTER);

		// Print the price of the first resulting mobile

		String priceMobile = driver.findElement(By.xpath("//span[@class = 'a-price'][1]")).getText();

		// Click on the Mobile (First resulting) image

		driver.findElement(By.xpath("//img[@class = 's-image'][1]")).click();

		// Switch to the new window

		Set<String> allwindows = driver.getWindowHandles();

		List<String> firstWindow = new ArrayList<String>(allwindows);

		String nextWindow = firstWindow.get(1);

		driver.switchTo().window(nextWindow);

		// Print the number of customer ratings

		String custRatings = driver.findElement(By.xpath("//span[contains(text(), 'ratings')][1]")).getText();
		int intCustRatings = Integer.parseInt(custRatings.replaceAll("\\D", ""));
		System.out.println("Customer Ratings:" + intCustRatings);

		// Click 'Add to Cart'

		driver.findElement(By.id("add-to-cart-button")).click();

		// Confirm "Added to Cart" text message appeared

		
		String cartMessage = "Added to Cart";
		
		String verifyDisplayed = driver.findElement(By.xpath("(//h4[text()='Added to Cart'])[2]")).getText();
		
		// System.out.println("Verify Message:" +verifyDisplayed);

		if (verifyDisplayed.equals(cartMessage)) {

			System.out.println("Added to cart message is verified successfully");
		}

		else
			System.out.println("Added to cart message is not displayed");

		// Click on Proceed to checkout

		driver.findElement(By.xpath("//input[@aria-labelledby= 'attach-sidesheet-checkout-button-announce']")).click();

		// Confirm the title is "Amazon Sign In"

		String pageTitle = driver.getTitle();

		String title = "Amazon Sign In";

		if (pageTitle.equals(title)) {

			System.out.println("Page title is verified");
		} else
			System.out.println("Page title is not verified");

		// Click Continue (without entering mobile number/email)

		driver.findElement(By.id("continue")).click();

		// Verify the error message: Enter your email or mobile phone number

		String orgMessage = "Enter your email or mobile phone number";

		String actualdisplayed = driver.findElement(By.xpath("(//div[@class='a-alert-content'])[2]")).getText();

		if (actualdisplayed.equals(orgMessage)) {

			System.out.println("Displayed error message is verified");
		}

		else
			System.out.println("Displayed error message is not verified");

		driver.quit();
	}

}
