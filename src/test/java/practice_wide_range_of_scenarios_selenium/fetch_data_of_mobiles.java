package practice_wide_range_of_scenarios_selenium;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class fetch_data_of_mobiles {

	@Test
	public void fetch_mobiles_data() throws InterruptedException {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("https://www.flipkart.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement close_HD_option = driver.findElement(By.xpath("//div[@class='JFPqaw']/span[@class='_30XB9F']"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(close_HD_option));
		close_HD_option.click();
		Actions action = new Actions(driver);
		WebElement mobiles_link = driver.findElement(By.xpath("//div[@class='YBLJE4']//span[text()='Mobiles']"));
		mobiles_link.click();
		WebElement Electronics_option = driver.findElement(By.xpath("//span[text()='Electronics']"));
		action.moveToElement(Electronics_option).perform();
		WebElement Mi_mobiles_link =  driver.findElement(By.xpath("//div[@class='_1fwVde']/a[text()='Mi']"));
		Mi_mobiles_link.click();
		Thread.sleep(5000);
		List<WebElement> names_of_mobiles =  driver.findElements(By.xpath("//div[@class='_4rR01T']"));
		List<WebElement> prices_of_mobiles = driver.findElements(By.xpath("//div[@class='_30jeq3 _1_WHN1']"));
		Map<String, String> map = new HashMap<String, String>();
		String mobile_name = null;
		String mobile_price = null;
		System.out.println("Count of mobiles in page = "+names_of_mobiles.size());
		for (int i = 0; i < names_of_mobiles.size() -1; i++) {
			mobile_name =  names_of_mobiles.get(i).getText();
			for (int j = i; j < names_of_mobiles.size() -1; j++) {
				mobile_price =  prices_of_mobiles.get(j).getText();
				break;
			}
			map.put(mobile_name, mobile_price);
		}
		for (Map.Entry<String, String> entry : map.entrySet()) {
			String key = entry.getKey();
			String val = entry.getValue();
			System.out.println(key+" = "+val);
		}
		driver.quit();
	}
}
