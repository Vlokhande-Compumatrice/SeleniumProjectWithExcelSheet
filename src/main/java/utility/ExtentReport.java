package utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReport {
	

	static ExtentReports extent;
	static ExtentHtmlReporter reporter;
	static ExtentTest logger;
	
	public  ExtentReport(String testName) {
		reporter = new ExtentHtmlReporter("./reports/PhysicianPortalTest.html");
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		logger=extent.createTest(testName);
	}
	
	
	public void passStatus(String passMessage) {
		
	//	logger1.add(logger.log(Status.PASS, passMessage));
		
		logger.log(Status.PASS, passMessage);
		extent.flush();
	}

	public void failStatus(String failMessage) {

	//	logger.add(logger.log(Status.FAIL, failMessage));
		logger.log(Status.FAIL, failMessage);
		extent.flush();
	}


	

/*	public void flush() {

		extent.flush();
		}*/
	
	
	
	/*public void infoStatus(String info) {

		logger.log(Status.PASS, info);
		}*/
	
	

}