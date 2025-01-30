package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage{
	
	WebDriver driver;
	
	public AccountRegistrationPage(WebDriver driver)
	{
		super(driver);
	}
	
	//Locators..
	
	@FindBy(xpath="//input[@id='input-firstname']")WebElement txtfirstname;
	@FindBy(xpath="//input[@id='input-lastname']")WebElement txtlastname;
	@FindBy(xpath="//input[@id='input-email']")WebElement txtemail;
	@FindBy(xpath="//input[@id='input-telephone']")WebElement txttelephone;
	@FindBy(xpath="//input[@id='input-password']")WebElement txtpassword;
	@FindBy(xpath="//input[@id='input-confirm']")WebElement txtconfirmpassword;
	@FindBy(xpath="//input[@name='agree']")WebElement chkagree;
	@FindBy(xpath="//input[@value='Continue']")WebElement btncontinue;
	@FindBy(xpath="//h1[text()='Your Account Has Been Created!']") WebElement ConfirmMsg;



//Action...
public void Firstname(String firstname)
{
	txtfirstname.sendKeys(firstname);
}

public void Lastname(String lastname)
{
	txtlastname.sendKeys(lastname);
}

public void Email(String Email)
{
	txtemail.sendKeys(Email);
}

public void Telephone(String number)
{
	txttelephone.sendKeys(number);
}

public void Password(String password)
{
	txtpassword.sendKeys(password);
}

public void Confirmpassword(String confirmpassword)
{
	txtconfirmpassword.sendKeys(confirmpassword);
}

public void ClickAgree()
{
	chkagree.click();
}

public void ClickContinue()
{
	btncontinue.click();
}

public String GetConfirmMessage()
{
	try {
		return(ConfirmMsg.getText());
	}
	catch(Exception e)
	{
		return(e.getMessage());
	}
	
}



}
