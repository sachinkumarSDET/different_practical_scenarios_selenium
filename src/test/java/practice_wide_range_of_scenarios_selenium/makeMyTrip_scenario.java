package practice_wide_range_of_scenarios_selenium;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import java.util.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class makeMyTrip_scenario {

	/*
	 * MakeMyTrip Scenario:
	 * Launch Application
	 * select round trip
     * From Bengaluru
	 * To Pune
	 * Departure day - same day
	 * Return day - Next day
	 * Click on search
	 * Fetch flight names
	 */
	
	@Test
	public void featch_details_of_roundTrip() throws InterruptedException {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		options.addArguments("--incognito");
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.makemytrip.com/");
		try {
			driver.findElement(By.xpath("//span[@class='commonModal__close']")).click();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		finally {
			driver.findElement(By.xpath("//li[text()='Round Trip']")).click();
			driver.findElement(By.id("fromCity")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@placeholder='From']")).sendKeys("Bengaluru");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//ul[@class='react-autosuggest__suggestions-list']/li")).click();
			driver.findElement(By.id("toCity")).click();
			driver.findElement(By.xpath("//input[@placeholder='To']")).sendKeys("Pune");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//ul[@class='react-autosuggest__suggestions-list']/li")).click();
		}
		driver.findElement(By.xpath("//div[@class='DayPicker-Months']/div[1]//div[contains(@class, 'DayPicker-Day--today')]")).click();
		driver.findElement(By.xpath("//div[@class='DayPicker-Months']/div[1]//div[contains(@class, 'DayPicker-Day--today')]/following-sibling::div")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[.='Search']")).click();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//span[@class='bgProperties icon20 overlayCrossIcon']")))).click();
		System.out.println(driver.findElement(By.xpath("(//div[@class='paneView']/p/b)[1]")).getText());
		List<WebElement> flight_names = driver.findElements(By.xpath("//div[@class='paneView']//div[@class='listingCardWrap']//span[@class='boldFont blackText']"));
		for (WebElement flight_name : flight_names) {
			System.out.println(flight_name.getText());
		}
	 driver.quit();
	}
}
