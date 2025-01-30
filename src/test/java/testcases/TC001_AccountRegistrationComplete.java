package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.Homepage;

public class TC001_AccountRegistrationComplete extends BaseClass {

	
	@Test(groups = {"Sanity", "Master"})
	public void AccountRegistration() throws InterruptedException
	{
		try {
		logger.info("***TC001_AccountRegistrationComplete Started***");
		
		Homepage LP = new Homepage(driver);
		LP.clickmyaccount();
		
		logger.info("My Account link clicked");
		
		LP.clickregister();
		logger.info("Registration link clicked");
		
		AccountRegistrationPage ARP = new AccountRegistrationPage(driver);
		
		logger.info("Started to enter customer details");
		
		ARP.Firstname(Aplabeticvalue().toUpperCase());
		ARP.Lastname(Aplabeticvalue().toUpperCase());
		ARP.Email(Aplabeticvalue()+"@gmail.com");
		ARP.Telephone(Numericvalue());
		
		String password = AlphaNumeric();
		ARP.Password(password);
		ARP.Confirmpassword(password);
		ARP.ClickAgree();
		ARP.ClickContinue();
		
		logger.info("customer details completed");
		
		String message = ARP.GetConfirmMessage();
		
		if(message.equals("Your Account Has Been Created!!!"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("Testcase failed");
			logger.debug("debug logs");
		}
		
		
		}
		catch(Exception e)
		{
			Assert.assertTrue(false);
			
		}
		logger.info("Confirmation message verified");
	}
	
	

}
