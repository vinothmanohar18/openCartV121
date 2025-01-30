package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Homepage;
import pageObjects.Login;
import pageObjects.MyAccount;
import utilities.DataProviders;

public class TC003_LogintestDDT extends BaseClass {
	
	@Test(dataProvider="Logindata", dataProviderClass = DataProviders.class )
	public void verify_loginDDT(String email, String pwd ,String exp)
	{
	try {

	logger.info("*** Started TC003_LogintestDDT ***");
	
	//Homepage 
	Homepage HP = new Homepage(driver);
	HP.clickmyaccount();
	
	logger.info("My Account Clicked");
	
	HP.clicklogin();
	logger.info("Selected Login Option");
	
	//Login page 
	Login LG = new Login(driver);
	LG.enterEmail(email);
	LG.enterPassword(pwd);
	
	logger.info("Email & Password entered");
	
	LG.clickloginCTA();
	
	logger.info("Login CTA Clicked");
	
	
	//MyAccount
	MyAccount MAC = new MyAccount(driver);
	boolean TargetPage = MAC.isMyAccountpageExists();
	
	//case 1: Valid data  -> login -> test pass -> logout
	//        Valid data -> login failed -> test fail
	//case 2: Invalid data -> login - > test failed
	//		: Invalid data -> login failed -> test pass
	
	if(exp.equalsIgnoreCase("Valid"))
	{
		if(TargetPage==true)
		{
		MAC.clicklogout();
		Assert.assertTrue(true);
	}
	else 
	{
		Assert.assertTrue(false);
	}
	
	if(exp.equalsIgnoreCase("Invalid"))
	
		{
		if(TargetPage == true)
			MAC.clicklogout();
			Assert.assertTrue(false);
		}
		else 
		{
			Assert.assertTrue(true);
		}
	}
	}catch(Exception e)
	{
		Assert.fail();
	}
	logger.info("*** Completed TC003_LogintestDDT ***");
	}
	
	
	}

