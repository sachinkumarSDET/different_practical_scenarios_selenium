package practice_wide_range_of_scenarios_selenium;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class dynamic_webtable_records {

	@Test
	public void webtable_sceanrio() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("https://www.icc-cricket.com/homepage");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement Rankings_link =  driver.findElement(By.xpath("//div[@class='main-navigation__desktop-navigation']/descendant::button[normalize-space(text())='Rankings']"));
		Actions action = new Actions(driver);
		action.moveToElement(Rankings_link).perform();
		WebElement team_rankings_link =  driver.findElement(By.xpath("//a[@href='/rankings/mens/team-rankings']"));
		team_rankings_link.click();
		WebElement Test_link = driver.findElement(By.xpath("//a[text()='Test']"));
		Test_link.click();
		List<WebElement> POS_of_table =  driver.findElements(By.xpath("//table/tbody/tr/td[contains(@class, 'pos')]"));
		List<WebElement> team_names_of_table =  driver.findElements(By.xpath("//table/tbody/tr/td/span[@class='u-hide-phablet']"));
		List<WebElement> team_ratings_of_table =  driver.findElements(By.xpath("//table/tbody/tr/td[contains(@class, 'rating')]"));
		String POS = null;
		String team_name = null;
		String team_rating = null;
		System.out.println(POS_of_table.size());
		System.out.println(team_names_of_table.size());
		System.out.println(team_ratings_of_table.size());
		try {
			for (int i = 0; i < POS_of_table.size()-1; i++) {
				POS = POS_of_table.get(i).getText();
				for (int j = i; j <team_names_of_table.size()-1; j++) {
					team_name = team_names_of_table.get(j).getText();
					break;
				}
				for (int k = i; k < team_ratings_of_table.size()-1; k++) {
					team_rating = team_ratings_of_table.get(k).getText();
					break;
				}
				System.out.println(POS+"  "+team_name+"  "+team_rating);
			}
		} finally {
			driver.quit();
		}
		
		
	}
}
