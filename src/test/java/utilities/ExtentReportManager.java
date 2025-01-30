package utilities;

import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testcases.BaseClass;

public class ExtentReportManager implements ITestListener {
	
	public ExtentSparkReporter sparkreporter;
	public ExtentReports reports;
	public ExtentTest test;
	
	String repName;
	
	public void onStart(ITestContext testcontext) {
	    
		String TimeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); //Specify TimeStamp for the report 
		repName = "Test-Report-"+TimeStamp+".html"; // Adding timestamp into report...
		sparkreporter = new ExtentSparkReporter(".\\reports\\"+repName);
		
		sparkreporter.config().setDocumentTitle("OpenCart Automation Report");
		sparkreporter.config().setReportName("Functional Testing");
		sparkreporter.config().setTheme(Theme.DARK);
		
		reports = new ExtentReports();
		reports.attachReporter(sparkreporter);
		reports.setSystemInfo("Application", "OpenCart");
		reports.setSystemInfo("Module", "Admin");
		reports.setSystemInfo("Sub Module", "Customers");
		reports.setSystemInfo("User Name", System.getProperty("user.name"));
		reports.setSystemInfo("Environment", "QA");
		
		String os = testcontext.getCurrentXmlTest().getParameter("os");
		reports.setSystemInfo("Operating System", os);
		
		String browser = testcontext.getCurrentXmlTest().getParameter("browser");
		reports.setSystemInfo("browser", browser);
		
		
		List<String> includegroups = testcontext.getCurrentXmlTest().getIncludedGroups();
		if(!includegroups.isEmpty())
		{
			reports.setSystemInfo("Groups", includegroups.toString());
		}
		
		
	  }
	

	public void onTestSuccess(ITestResult result) {
	    
		test = reports.createTest(result.getTestClass().getName());
			test.assignCategory(result.getMethod().getGroups());
			
			test.log(Status.PASS, result.getName()+" Test Executed Successfully");
		
	  }
	
	public void onTestFailure(ITestResult result) {
		test = reports.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.FAIL, result.getName()+" Test Failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		try {
		String Imagepath = new BaseClass().CaptureScreenshot(result.getName());
		test.addScreenCaptureFromPath(Imagepath);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	  }
	
	public void onTestSkipped(ITestResult result) {
		
		test = reports.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.SKIP, result.getName()+" Test Skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
	   
	  }
	
	public void onFinish(ITestContext testcontext) {
		
		reports.flush();
		
		String reportpathinfo = System.getProperty("user.dir")+"\\reports\\"+repName;
		File extentreport = new File (reportpathinfo);
		
		try {
			Desktop.getDesktop().browse(extentreport.toURI());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	    
	  }
	

}
