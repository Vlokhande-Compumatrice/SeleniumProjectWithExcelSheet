package utility;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ScrollElement {
 WebDriver driver;
	public ScrollElement(WebDriver driver) {
		this.driver = driver;
	}
	public void scrollByVisibleElement(WebElement Element) throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", Element);
		Thread.sleep(2000);	
	}
	
	public void scrollByXYAxis(WebElement Element) throws Exception 
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var elementTop = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, elementTop-(viewPortHeight/2));";
		js.executeScript(scrollElementIntoMiddle, Element);
		Thread.sleep(2000);
	}
}
