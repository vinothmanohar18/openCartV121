package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrderReviewPage extends BasePage{
	
	public OrderReviewPage(WebDriver driver)
	{
		super(driver);
	}
	
	//Order Review Address Selection....
	
	@FindBy(xpath="//div[text()=' Add address ']") WebElement AddAddressCTA;
	@FindBy(xpath="//div[@class='address1 pointer'][1]") WebElement SelectAddress;
	@FindBy(xpath="//div[text()=' Deliver to this address ']") WebElement DeliveryCTA;
	@FindBy(xpath="//button[text()=' Make Payment ']")WebElement MakePaymentCTA;
	
	
	public void ClickAddAddress()
	{
		AddAddressCTA.click();
	}
	
	public void ClickAddressbox()
	{
		SelectAddress.click();
	}
	public void ClickDeliveryCTA()
	{
		DeliveryCTA.click();
	}
	
	public void ClickMakePayment()
	{
		MakePaymentCTA.click();
	}

}
