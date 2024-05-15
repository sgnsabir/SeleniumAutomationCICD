package testautomation.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testautomation.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {
	WebDriver driver;

	public OrderPage(WebDriver driver) {
		super(driver);
		// initialization
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// pageFactory
	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> orderedProductNames;

	public Boolean VerifyOrderDisplay(String productName) {
		Boolean match = orderedProductNames.stream()
				.anyMatch(orderedProduct -> orderedProduct.getText().equalsIgnoreCase(productName));
		return match;

	}

}
