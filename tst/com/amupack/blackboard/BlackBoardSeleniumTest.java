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
	static List<Announcement> announcementList = new ArrayList<>();
	
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
		
		WebElement ISEC500_ON_course = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"CourseNavMenuSection.Course-content\"]/ul[1]/li[1]/a")));
		ISEC500_ON_course.click();
		System.out.println("Clicked on course nav menu section");
		
		WebElement announcements = driver.findElements(By.xpath("//*[starts-with(@id, 'paletteItem:_')]")).get(0);
		announcements.click(); 
		
//		List<WebElement> elements = driver.findElements(By.className("clearfix"));
//		for (WebElement e : elements) {
//			System.out.println(e.getText());
//		}
//		
//		String text= driver.findElementByClassName("item clearfix").getText();
//		System.out.println(text);
		List<WebElement> elements = driver.findElements(By.xpath("//*[starts-with(@id, 'announcementList:_')]"));
		for (WebElement e : elements) {
			String contentOfWebElement = e.toString();
			
			Announcement a = new Announcement("", "", contentOfWebElement);
			announcementList.add(a);
		}
		
		//parseThroughAssignments();
		
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
