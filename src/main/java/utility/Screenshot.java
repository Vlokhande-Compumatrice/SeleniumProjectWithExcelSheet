package utility;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshot {
	WebDriver driver;

	public Screenshot(WebDriver driver) {
		this.driver = driver;
	}

	public static void takeScreenshot(WebDriver driver, String ScreenshotName) throws Exception {

		TakesScreenshot ts = (TakesScreenshot) driver;

		File Source = ts.getScreenshotAs(OutputType.FILE);

		FileUtils.copyFile(Source,

				new File("src/test/resources/screenshots/" + ScreenshotName + ".png"));

		System.out.println(

				"Please open Screenshots folder to see \"" + ScreenshotName + "\" screenshot.");

	}
	
	public static String getScreenshot(WebDriver driver, String screenshot) throws Exception {
		String path;
		TakesScreenshot ts = (TakesScreenshot) driver;

		File Source = ts.getScreenshotAs(OutputType.FILE);

		FileUtils.copyFile(Source,

				new File(screenshot + ".png"));
//		path="src/test/resources/errorscreenshots/" + ScreenshotName + ".png";
		path=screenshot + ".png";
		return path;
		
	}
}
