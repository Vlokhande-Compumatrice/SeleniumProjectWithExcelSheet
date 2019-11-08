package utility;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ExistElement {

	WebDriver driver;

	public  ExistElement(WebDriver driver)
	{
		this.driver=driver;
	}

	public boolean existElementBytext(By element) 
	{ScrollElement scrollelement = new ScrollElement(driver);
		 driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

		try {
			scrollelement.scrollByVisibleElement(driver.findElement(element));

			driver.findElement(element).getText();

		} catch (Exception e) {
			return false;
		}
		return true;
	}
}