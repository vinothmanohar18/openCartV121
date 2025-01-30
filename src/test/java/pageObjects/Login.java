package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Login extends BasePage{
	
	WebDriver driver;
	
	public Login(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='input-email']") WebElement email;
	@FindBy(xpath="//input[@id='input-password']")WebElement password;
	@FindBy(xpath="//input[@value='Login']")WebElement login;
	
	
	
	public void enterEmail(String Email)
	{
		email.sendKeys(Email);
	}
	
	public void enterPassword(String Password)
	{
		password.sendKeys(Password);
	}
	
	public void clickloginCTA()
	{
		login.click();
	}

	
}
