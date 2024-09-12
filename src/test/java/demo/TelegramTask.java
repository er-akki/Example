package demo;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TelegramTask {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://dashboard.hubz.io");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		WebElement frame = driver.findElement(By.xpath("//iframe[@id='telegram-login-hubz_dashboard_bot']"));
		driver.switchTo().frame(frame);
		Actions act = new Actions(driver);
		WebElement btn = driver.findElement(By.xpath("//button[text()='Log in with Telegram']"));

		act.moveToElement(btn).build().perform();
		act.click(btn).build().perform();

		Set<String> window = driver.getWindowHandles();

		for (String string : window) {
			driver.switchTo().window(string);
			if (driver.getCurrentUrl().contains(
					"https://oauth.telegram.org/auth?bot_id=7273128116&origin=https%3A%2F%2Fdashboard.hubz.io&embed=1&request_access=write&lang=en&return_to=https%3A%2F%2Fdashboard.hubz.io%2F")) {
			
				driver.findElement(By.xpath("//input[@id='login-phone']")).sendKeys("7809876560");
				driver.findElement(By.xpath("//span[text()='Next']")).click();
				System.out.println("The task is completed.......");
			}
		}
		driver.quit();
	}
}

