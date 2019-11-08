package NumberList.com.ListOfObgyns;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import utility.ExcelUtility;
import utility.HighlightElement;
import utility.ScrollElement;

public class ObgynsDoctorsDetails {

	WebDriver driver;

	static int counter = 1;

	By UserName = By.xpath("//input[@id='username']");
	By Password = By.xpath("//input[@id='password']");
	By LogInBtn = By.xpath("//span[@class='btnOrangeLg']//input");
	By DoctorsAndHospitals = By.xpath("//a[contains(text(),'Doctors & Hospitals')]");
	By FindDoctorOrHospital = By.xpath("//span[contains(text(),'Find a Doctor or Hospital >')]");
	By CategorySearch = By.xpath("//input[@placeholder='Search']");
	By DoctorsName = By.xpath("//a[@queryparamshandling=\"preserve\" and @class=\"search-result-link\"]");
	By Speciality = By.xpath("(//h5)");
	By Location = By.xpath("//div[@class='location-address']");
	By PhoneNumber = By.xpath("//div[contains(@class,'contact-phone ng-star-inserted')]");
	By NextButton = By.xpath("//i[@aria-label='Go to next page']");

	public ObgynsDoctorsDetails(WebDriver driver) // Constructor
	{
		this.driver = driver;
	}

	public void LogInToBlueCrossBlueShielOfTexas() throws Exception {
		HighlightElement highlight = new HighlightElement(driver);
		ExcelUtility excel = new ExcelUtility("src\\test\\resources\\ExcelFile\\ObgynsData.xlsx");

		try {
			highlight.highlighElement(
					driver.findElement(By.xpath("//a[contains(text(),'Blue Cross and Blue Shield of Texas')]")));
			driver.findElement(By.xpath("//a[contains(text(),'Blue Cross and Blue Shield of Texas')]")).click();
			Thread.sleep(2000);
		} catch (Exception e) {

		}

		highlight.highlighElement(driver.findElement(UserName));
		driver.findElement(UserName).sendKeys(excel.getTestData("LogInDetails", 1, 0));
		highlight.highlighElement(driver.findElement(Password));
		driver.findElement(Password).sendKeys(excel.getTestData("LogInDetails", 1, 1));
		highlight.highlighElement(driver.findElement(LogInBtn));
		driver.findElement(LogInBtn).click();
		Thread.sleep(2000);

		try {
			// driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			highlight.highlighElement(driver.findElement(By.xpath("//a[contains(text(),'thanks')]")));
			driver.findElement(By.xpath("//a[contains(text(),'thanks')]")).click();

		} catch (Exception e) {

		}

		String pageTitle = driver.getTitle();
		if (pageTitle.equalsIgnoreCase("Blue Cross Blue Shield - Home")) {
			System.out.println("Successfully LogIn done");
		} else {
			System.out.println("Login failed");
		}
	}

	public void doctorsandhospitals() throws Exception {
		HighlightElement highlight = new HighlightElement(driver);
		ExcelUtility excel = new ExcelUtility("src\\test\\resources\\ExcelFile\\ObgynsData.xlsx");

		highlight.highlighElement(driver.findElement(DoctorsAndHospitals));
		driver.findElement(DoctorsAndHospitals).click();
		Thread.sleep(2000);

		try {
			// driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			highlight.highlighElement(driver.findElement(By.xpath("//a[contains(text(),'thanks')]")));
			driver.findElement(By.xpath("//a[contains(text(),'thanks')]")).click();

		} catch (Exception e) {

		}

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		highlight.highlighElement(driver.findElement(FindDoctorOrHospital));
		driver.findElement(FindDoctorOrHospital).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		WindowHandler();

		highlight.highlighElement(driver.findElement(CategorySearch));
		driver.findElement(CategorySearch).sendKeys(excel.getTestData("LogInDetails", 1, 2));
		driver.findElement(CategorySearch).sendKeys(Keys.ENTER);

	}

	public void CollectInfo() throws Exception {

		HighlightElement highlight = new HighlightElement(driver);
		ScrollElement scrollelement = new ScrollElement(driver);
		ExcelUtility excel = new ExcelUtility("src\\test\\resources\\ExcelFile\\ObgynsData.xlsx");

		List<WebElement> doctorNamesList = driver.findElements(DoctorsName);

		int i = 1;
		for (WebElement doctorName_ele : doctorNamesList) {

			scrollelement.scrollByXYAxis(doctorName_ele);

			data(counter, i);
			i++;
			counter++;

		}

		while (true) {
			WebElement button = null;
			try {
				button = driver.findElement(NextButton);
				NextPage();
			} catch (NoSuchElementException ex) {
				break; // button is missing, exit the loop
			}
			if (button.isDisplayed() == false) {
				break; // button is hidden, exit the loop
			}
		}

		driver.quit();
	}

	public void NextPage() throws Exception {

		HighlightElement highlight = new HighlightElement(driver);

		ScrollElement scrollelement = new ScrollElement(driver);

		try {
			scrollelement.scrollByXYAxis(driver.findElement(NextButton));
			highlight.highlighElement(driver.findElement(NextButton));
			driver.findElement(NextButton).click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			CollectInfo();
		} catch (Exception e) {

		}

	}

	public void WindowHandler() {

		int counter = 1;
		for (String allwindows : driver.getWindowHandles()) {
			if (counter == 3) {
				driver.switchTo().window(allwindows);
			}
			counter++;
		}
	}

	public WebDriver startBrowser() throws IOException {
		System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://members.hcsc.net/wps/portal/bam");
		return driver;
	}

	public void data(int excelRow, int pageRow) throws Exception {
		HighlightElement highlight = new HighlightElement(driver);
		ScrollElement scrollelement = new ScrollElement(driver);
		ExcelUtility excel = new ExcelUtility("src\\test\\resources\\ExcelFile\\ObgynsData.xlsx");

		scrollelement.scrollByXYAxis(driver.findElement(By
				.xpath("(//a[@queryparamshandling=\"preserve\" and @class=\"search-result-link\"])[" + pageRow + "]")));
		highlight.highlighElement(driver.findElement(By
				.xpath("(//a[@queryparamshandling=\"preserve\" and @class=\"search-result-link\"])[" + pageRow + "]")));
		System.out.println(driver
				.findElement(By.xpath(
						"(//a[@queryparamshandling=\"preserve\" and @class=\"search-result-link\"])[" + pageRow + "]"))
				.getText());
		excel.writeData("DoctorsData", excelRow, 1,
				driver.findElement(By.xpath(
						"(//a[@queryparamshandling=\"preserve\" and @class=\"search-result-link\"])[" + pageRow + "]"))
						.getText());

//		scrollelement.scrollByXYAxis(driver.findElement(By.xpath("(//h5)[" + row + "]")));
		highlight.highlighElement(driver.findElement(By.xpath("(//h5)[" + pageRow + "]")));
		System.out.println(driver.findElement(By.xpath("(//h5)[" + pageRow + "]")).getText());
		excel.writeData("DoctorsData", excelRow, 2, driver.findElement(By.xpath("(//h5)[" + pageRow + "]")).getText());

//		scrollelement.scrollByXYAxis(driver.findElement(By.xpath("(//div[@class='location-address'])[" + row + "]")));
		highlight.highlighElement(driver.findElement(By.xpath("(//div[@class='location-address'])[" + pageRow + "]")));
		String location = driver.findElement(By.xpath("(//div[@class='location-address'])[" + pageRow + "]")).getText();
		excel.writeData("DoctorsData", excelRow, 3, location);
		String pin = location.substring(location.lastIndexOf(" ") + 1);
		System.out.println(pin);
		excel.writeData("DoctorsData", excelRow, 4, pin);

//		scrollelement.scrollByXYAxis(driver.findElement(By.xpath("(//div[contains(@class,'contact-phone ng-star-inserted')])[" + row + "]")));
		highlight.highlighElement(driver
				.findElement(By.xpath("(//div[contains(@class,'contact-phone ng-star-inserted')])[" + pageRow + "]")));
		String phonenm = driver
				.findElement(By.xpath("(//div[contains(@class,'contact-phone ng-star-inserted')])[" + pageRow + "]"))
				.getText();
		phonenm = phonenm.substring(7).replaceAll("-", "");
		System.out.println(phonenm);
		excel.writeData("DoctorsData", excelRow, 5, phonenm);

	}

}
