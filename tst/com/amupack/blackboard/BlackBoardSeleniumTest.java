package com.amupack.blackboard;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BlackBoardSeleniumTest {
	final ChromeDriver driver = new ChromeDriver();
	final WebDriverWait wait = new WebDriverWait(driver, 30);
	
	@Test
	public void goGoogleTest() {
		driver.get("https://courses.cityu.edu/");
		
		WebElement userName = driver.findElementById("userNameInput");
		userName.sendKeys("vaidyanathanamrutha@cityuniversity.edu");
		
		WebElement next = driver.findElementById("nextButton");
		next.click();
		
		WebElement password = driver.findElementById("passwordInput");
		password.sendKeys("Dante0512#");
		
		WebElement submit = driver.findElementById("submitButton");
		submit.click();
		
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id("agree_button")));
		element.click();
		
		WebElement navLink = driver.findElementById("global-nav-link");
		navLink.click();
		
		WebElement course = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[3]/div[2]/div/div[3]/div[2]/ul[1]/li[1]/a")));
		course.click();
		
		WebElement assignment = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[5]/div[2]/nav/div/div[2]/div[1]/div[2]/ul/li[5]")));
		assignment.click();
		
		parseThroughAssignments();
		
	}

	private void parseThroughAssignments() {
		List<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		tabs.forEach(tab -> {
			driver.switchTo().window(tab);
			WebElement container = driver.findElementById("content_listContainer");
			List<WebElement> assignments = container.findElements(By.tagName("li"));
			assignments.forEach(element -> {
				WebElement link = element.findElement(By.tagName("a"));
				link.sendKeys(Keys.CONTROL + "t");				
			});
		});	
	}
}
