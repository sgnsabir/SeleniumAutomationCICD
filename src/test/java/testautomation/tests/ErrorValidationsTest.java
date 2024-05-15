/*
 * Due to validate negative test cases, data provider not added in .
 */
package testautomation.tests;

import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import testautomation.TestComponents.BaseTest;
import testautomation.pageobjects.CartPage;
import testautomation.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {
	@Test(groups = { "ErrorHandling" }, retryAnalyzer = testautomation.TestComponents.Retry.class)
	public void loginErrorValidation() {
		landingPage.loginApplication("esabirmostafa@gmail.com", "Work@143");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	}

	@Test
	public void ProductErrorValidation() {

		String PRODUCT_NAME = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginApplication("sabirmostafa@gmail.com", "Work@143");
		CartPage cartPage = productCatalogue.addProductToCart(PRODUCT_NAME);
		productCatalogue.goToCartPage();
		Boolean match = cartPage.verifyProducrDisplayed("ZARA COAT 33");
		Assert.assertFalse(match);
	}

}