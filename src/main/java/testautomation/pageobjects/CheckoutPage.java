package testautomation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testautomation.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
	private WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		// initialization
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[placeholder='Select Country']")
	private WebElement selectCountryField;

	@FindBy(xpath = "//button[contains(@class,'ta-item')][2]")
	private WebElement selectCountry;

	@FindBy(css = ".action__submit")
	private WebElement orderSubmit;

	By countryList = By.cssSelector(".ta-item");

	public void cardDetails() {
		// personal details
	}

	public void selectCountry(String countryName) {
		Actions a = new Actions(driver);
		a.sendKeys((selectCountryField), countryName).build().perform();
		waitForElementToAppear(countryList);
		selectCountry.click();
	}

	public OrderCornfirmationPage submitOrder() {
		orderSubmit.click();
		OrderCornfirmationPage confirm = new OrderCornfirmationPage(driver);
		return confirm;
	}

}
