package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Homepage;
import pageObjects.Login;
import pageObjects.MyAccount;

public class TC002_LoginTest extends BaseClass {

	@Test(groups = {"Regression", "Master"})
	public void LoginComplete()
	{
		logger.info("*** Started TC002_LoginTest ***");
		try {
		
		Homepage HP = new Homepage(driver);
		
		HP.clickmyaccount();
		logger.info("My Account Clicked");
		
		
		HP.clicklogin();
		logger.info("Selected Login Option");
		
		
		Login LG = new Login(driver);
		LG.enterEmail(P.getProperty("email"));
		LG.enterPassword(P.getProperty("password"));
		
		logger.info("Email & Password entered");
		
		LG.clickloginCTA();
		
		logger.info("Login CTA Clicked");
		
		MyAccount MAC = new MyAccount(driver);
		boolean TargetPage = MAC.isMyAccountpageExists();
		Assert.assertTrue(TargetPage);
		
		}
		catch(Exception e)
		{
			Assert.fail();
		}
	
		logger.info("*** Finished TC002_LoginTest ***");
		
	}
	
}
