package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccount extends BasePage {
	
	
	public MyAccount(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//h2[text()='My Account']") WebElement LoginComfirmation;
	
	@FindBy(xpath="//div[@class='list-group']//a[text()='Logout']") WebElement logoutlink;
	
	//Verify Condition
	public boolean isMyAccountpageExists()
	{
		try {
		return (LoginComfirmation.isDisplayed());
		}
		catch(Exception e)
		{
			return(false);
		}
		
	}

	public void clicklogout()
	{
		logoutlink.click();
	}

}
