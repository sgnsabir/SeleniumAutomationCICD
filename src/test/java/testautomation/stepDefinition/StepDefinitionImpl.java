package testautomation.stepDefinition;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import testautomation.TestComponents.BaseTest;
import testautomation.pageobjects.CartPage;
import testautomation.pageobjects.CheckoutPage;
import testautomation.pageobjects.LandingPage;
import testautomation.pageobjects.OrderCornfirmationPage;
import testautomation.pageobjects.ProductCatalogue;

public class StepDefinitionImpl extends BaseTest {

	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public OrderCornfirmationPage confirmationPage;

	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_page() throws IOException {
		landingPage = launchApplication();
	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String username, String password) {
		productCatalogue = landingPage.loginApplication(username, password);
	}

	@When("^I add product (.+) to Cart$")
	public void i_add_product_to_cart(String productName) {
		productCatalogue.addProductToCart(productName);
	}

	@When("^Checkout (.+) and submit the order$")
	public void Checkout_and_submit_the_order(String producName) {
		CartPage cartPage = productCatalogue.addProductToCart(producName);
		productCatalogue.goToCartPage();
		Boolean match = cartPage.verifyProducrDisplayed(producName);
		Assert.assertTrue(match);
		CheckoutPage payment = cartPage.goToCheckout();
		payment.selectCountry("Ban");
		confirmationPage = payment.submitOrder();
	}
	
	@Then("{string} message is displayed on ConfirmationPage.")
	public void message_displayed_confirmationPage(String string) {
		String confirmMessage = confirmationPage.confirmMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();		
	}

	@Then("{string} message is displayed")
	public void message_is_displayed(String string) {
		Assert.assertEquals(string, landingPage.getErrorMessage());
		driver.close();		
	}
	//checking ci cd
}
