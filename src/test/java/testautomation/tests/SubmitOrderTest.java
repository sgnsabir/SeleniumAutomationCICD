package testautomation.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testautomation.TestComponents.BaseTest;
import testautomation.pageobjects.CartPage;
import testautomation.pageobjects.CheckoutPage;
import testautomation.pageobjects.OrderCornfirmationPage;
import testautomation.pageobjects.OrderPage;
import testautomation.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {
	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> input) {
		// String email, String password, String productName
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		CartPage cartPage = productCatalogue.addProductToCart(input.get("product"));
		productCatalogue.goToCartPage();
		Boolean match = cartPage.verifyProducrDisplayed(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage payment = cartPage.goToCheckout();

		payment.cardDetails(); // Edit card information
		payment.selectCountry("Ban");
		OrderCornfirmationPage confirmationPage = payment.submitOrder();
		String confirmMessage = confirmationPage.confirmMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}

	@Test(dependsOnMethods = { "submitOrder" }, dataProvider = "getData", groups = { "Purchase" })
	public void orderHistoryTest(HashMap<String, String> input) {
		// OrderHistory
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		OrderPage orderPage = productCatalogue.goToOrderPage();
		Assert.assertTrue(orderPage.VerifyOrderDisplay(input.get("product")));
	}

	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "//src//test//java//testautomation//data//PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

	/*
	 * public Object[][] getData() {
	 *
	 * HashMap<Object, Object> map = new HashMap<Object, Object>(); map.put("email",
	 * "shetty@gmail.com"); map.put("password", "Iamking@000"); map.put("product",
	 * "IPHONE 13 PRO");
	 *
	 * HashMap<Object, Object> map2 = new HashMap<Object, Object>();
	 * map2.put("email", "sabirmostafa@gmail.com"); map2.put("password",
	 * "Work@143"); map2.put("product", "ZARA COAT 3");
	 *
	 * return new Object[][] { { map }, { map2 } }; }
	 *
	 * @DataProvider public Object[][] getData(){ return new Object[][]
	 * {{"sabirmostafa@gmail.com","Work@143", "ZARA COAT 3"},{"anshika@gmail.com",
	 * "Iamking@000","ADIDAS ORIGINAL"}}; }
	 */

}
