package testautomation.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testautomation.AbstractComponents.AbstractComponent;

public class OrderCornfirmationPage extends AbstractComponent {
	WebDriver driver;

	public OrderCornfirmationPage(WebDriver driver) {
		super(driver);
		// initialization
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	// driver.findElement(By.cssSelector(".hero-primary"))
	// pageFactory

	@FindBy(css = ".hero-primary")
	WebElement confirmationMessage;

	@FindBy(css = "label[routerlink='/dashboard/myorders']")
	WebElement orderHistoryLink;

	public String confirmMessage() {
		waitForWebElementToAppear(confirmationMessage);
		return confirmationMessage.getText();
	}
	// will validate orderhistoryLink

}
