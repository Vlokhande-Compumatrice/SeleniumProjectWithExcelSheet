package NumberList.com.ListOfObgyns;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class VerifyListOfObgyns {

	WebDriver driver;

	@Test
	public void verifyLoginPage() throws Exception {
		ObgynsDoctorsDetails obgynsdetails = new ObgynsDoctorsDetails(driver);
		obgynsdetails.startBrowser();
		obgynsdetails.LogInToBlueCrossBlueShielOfTexas();
		obgynsdetails.doctorsandhospitals();
		obgynsdetails.CollectInfo();

	}

}
