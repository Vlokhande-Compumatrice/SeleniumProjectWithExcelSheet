package utility;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HighlightElement {
	WebDriver driver;
	public HighlightElement(WebDriver driver) {
		this.driver = driver;
		
	}
	
	public void highlighElement(WebElement element) throws Exception {
		((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'", element);
		Thread.sleep(1000);
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].style.border='3px solid white'", element);
	}

}
