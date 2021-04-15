package week4.homeassignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NykaaMap {

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();

		ChromeOptions options = new ChromeOptions();

		options.addArguments("--disable-notifications");

		ChromeDriver driver = new ChromeDriver();

		// 1. Launch URL: "https://www.nykaa.com/"
		driver.get("https://www.nykaa.com/");

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		// Step 2 : Enter text as Perfumes in Search Bar and press Enter

		driver.findElement(By.name("search-suggestions-nykaa")).sendKeys("Perfumes", Keys.ENTER);

		// Step 3 : Get The Names Of all the Perfumes Displayed

		List<WebElement> perNames = driver.findElements(By.xpath("//div[@class = 'm-content__product-list__title']"));

		List<WebElement> perPrice = driver.findElements(By.xpath("//span[@class = 'post-card__content-price-offer']"));

		System.out.println(perNames);
		System.out.println(perPrice);

		// Step 4 : Click on the Highest Price Perfume using MAP

		String perfumePrice, perfumeName;

		Map<Integer, String> perfumeDetails = new HashMap<Integer, String>();

		for (int i = 0; i < perPrice.size(); i++) {

			perfumeName = perNames.get(i).getText();

			perfumePrice = perPrice.get(i).getText();

			int int_PerfumePrice = Integer.parseInt(perfumePrice.replaceAll("\\D", ""));

			perfumeDetails.put(int_PerfumePrice, perfumeName);

		}
		System.out.println(perfumeDetails);

		Set<Integer> allPerfumePrice = perfumeDetails.keySet();
		List<Integer> sortPerfumePrice = new ArrayList<Integer>(allPerfumePrice);

		Collections.sort(sortPerfumePrice);

		System.out.println(sortPerfumePrice);

		int sortedPerfumePrice = sortPerfumePrice.get(sortPerfumePrice.size() - 1);

		System.out.println(sortedPerfumePrice);

		// Click on the highest price perfumes

		driver.findElement(By.xpath("//span[@title='RELEVANCE']")).click();
		driver.findElement(By.xpath("//span[text()='price: high to low']")).click();

		// Click on Add To Bag

		WebElement highPricePerfume = driver.findElement(By.xpath("//img[@class = 'listing-img'][1]"));
		Actions mouseHover = new Actions(driver);
		mouseHover.moveToElement(highPricePerfume).perform();
		driver.findElement(By.xpath("//button[text()='ADD TO BAG']")).click();

		// verify Message Product Added To the bag
		String orgMessage = "Item added to cart";
		String verfiyMessage = driver.findElement(By.xpath("//div[text() = 'Item added to cart']")).getText();
		if (verfiyMessage.equals(orgMessage)) {
			System.out.println("Message displayed is correct");
		} else
			System.out.println("Message displayed is incorrect");

		// Click on bag Icon
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[@class = 'AddBagIcon']")).click();

		// Get the Grand Total Value

		String grandTotal = driver.findElement(By.xpath("//div[@class='value medium-strong']")).getText();
		int intgrandTotal = Integer.parseInt(grandTotal.replaceAll("\\D", ""));
		System.out.println("Grand Total:" + intgrandTotal);

		// Click on Proceed
		
		driver.findElement(By.xpath("//button[@type='button']//span[1]")).click();
	
		// Click on Continue as Guest

		driver.findElement(By.xpath("//button[text()= 'CONTINUE AS GUEST']")).click();

		// Fill all the Fields in Address
		driver.findElement(By.xpath("//input[@name = 'name']")).sendKeys("Name");
		driver.findElement(By.xpath("//input[@name = 'email']")).sendKeys("email@gmail.com");
		driver.findElement(By.xpath("//input[@name = 'phoneNumber']")).sendKeys("9876543121");
		driver.findElement(By.xpath("//input[@name = 'pinCode']")).sendKeys("600028");
		driver.findElement(By.xpath("//textarea[@class = 'textarea-control prl10']")).sendKeys("Address1", Keys.ARROW_DOWN,
				"Address2", Keys.ARROW_DOWN, "Address3", Keys.ARROW_DOWN);
		driver.findElement(By.xpath("//button[text() = 'SHIP TO THIS ADDRESS']")).click();

		// Click on Paynow
		driver.findElement(By.xpath("//button[@class = 'btn fill full big proceed']")).click();

		// Get the Error Message displayed in Red Color
		String error1 = driver.findElement(By.xpath("(//span[@class='field-error'][1])")).getText();
		String error2 = driver.findElement(By.xpath("(//span[@class='field-error'])[2]")).getText();
		String error3 = driver.findElement(By.xpath("(//span[@class='field-error'])[3]")).getText();
		
		System.out.println("Red Error 1 :" +error1 );
		System.out.println("Red Error 2 :" +error2 );
		System.out.println("Red Error 3 :" +error3 );

		// close the current browser
		driver.close();

	}

}
