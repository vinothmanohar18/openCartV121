package testcases;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;


public class BaseClass {
	public static WebDriver driver;
	public Logger logger;
	public Properties P;
	
	
	@SuppressWarnings("deprecation")
	@BeforeClass (groups = {"Sanity", "Regression", "Master"})
	@Parameters({"os","browser"})
	public void Setup(String os, String br) throws IOException
	{
		FileInputStream file = new FileInputStream("./src//test//resources//config.properties");
		P = new Properties();
		P.load(file);
		logger = LogManager.getLogger(this.getClass());
		
		if(P.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities cap = new DesiredCapabilities();
			
			//OS
			if(os.equalsIgnoreCase("windows"))
			{
				cap.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("Mac"))
			{
				cap.setPlatform(Platform.MAC);
			}
			else
			{
				System.out.println("No Matching OS");
				return;
			}
			
			//browser
			switch(br.toLowerCase())
			{
			case "chrome": cap.setBrowserName("chrome"); break;
			case "Edge": cap.setBrowserName("MicrosoftEdge"); break;
			default: System.out.println("No Maching browser"); return;
			}
			
			driver = new RemoteWebDriver(new URL("http://172.31.98.207:4444/wd/hub"),cap);
			
		}
		if(P.getProperty("execution_env").equalsIgnoreCase("local"))	
		{
		switch(br.toLowerCase())
		{
		case "chrome": driver = new ChromeDriver();break;
		case "edge": driver = new EdgeDriver();break;
		case "firefox": driver = new FirefoxDriver();break;
		case "default": System.out.println("Invalid Browser");return;
		}
	
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(P.getProperty("appurl"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	
	}}
	
	@AfterClass(groups = {"Sanity", "Regression", "Master"})
	public void teardown () 
	{
		driver.quit();
	}
	
	public String Aplabeticvalue()
	{
		String randomAlpha = RandomStringUtils.randomAlphabetic(5);
			return(randomAlpha);
	}
	public String Numericvalue()
	{
		String randomNum = RandomStringUtils.randomNumeric(3);
		return(randomNum);
	}
	
	public String AlphaNumeric()
	{
		String randomAlpha = RandomStringUtils.randomAlphabetic(5);
		String randomNum = RandomStringUtils.randomNumeric(3);
		return(randomAlpha+"@"+randomNum);
	}
	
	public String CaptureScreenshot(String tname)
	{
		String Timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		
		TakesScreenshot takescreenshot = (TakesScreenshot)driver;
		File sourcefile = takescreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetfilepath = System.getProperty("user.dir")+"\\Screenshots\\"+tname+"_"+Timestamp;
		File targetfile = new File(targetfilepath);
		sourcefile.renameTo(targetfile);
		return targetfilepath;
	}

}
