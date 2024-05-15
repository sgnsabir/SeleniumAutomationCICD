package testautomation.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testautomation.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		// initialization
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cart
	// h3"));
	// driver.findElement(By.cssSelector(".totalRow button")).click();
	// pageFactory
	@FindBy(css = ".cart h3")
	List<WebElement> cartProducts;

	@FindBy(css = ".totalRow button")
	WebElement checkoutEle;

	public Boolean verifyProducrDisplayed(String productName) {
		Boolean match = cartProducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}

	public CheckoutPage goToCheckout() {
		checkoutEle.click();
		CheckoutPage payment = new CheckoutPage(driver);
		return payment;
	}

}
