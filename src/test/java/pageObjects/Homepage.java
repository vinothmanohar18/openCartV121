package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Homepage extends BasePage {
	WebDriver driver;
	
	public Homepage(WebDriver driver)
	{
		super(driver);
	}
	
	//Locators....
	
	@FindBy(xpath="//span[normalize-space()='My Account']") WebElement MyAccount;
	@FindBy(xpath="//a[normalize-space()='Register']")WebElement Register;
	@FindBy(xpath="//a[text()='Login']") WebElement Login;
	
	//Actions..
	
	public void clickmyaccount()
	{
		MyAccount.click();
	}
	
	public void clickregister()
	{
		Register.click();
	}
	
	public void clicklogin() 
	{
		Login.click();
	}
	
}
