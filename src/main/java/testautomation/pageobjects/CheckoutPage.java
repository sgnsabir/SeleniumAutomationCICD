package testautomation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testautomation.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		// initialization
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	/*
	 * For future reference
	 * driver.findElement(By.cssSelector("[placeholder='Select Country']")
	 * driver.findElement(By.cssSelector(".totalRow button")).click();
	 * driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]")).click
	 * (); driver.findElement(By.cssSelector(".action__submit")).click(); //Edit
	 * personal information
	 * driver.findElement(By.xpath("//input[contains(@value,'4542')]")).clear();
	 * driver.findElement(By.xpath("//input[contains(@value,'4542')]")).
	 * sendKeys("4242 4242 4242 4242");
	 * driver.findElement(By.xpath("//select[@class='input ddl'][1]")).click();
	 * driver.findElement(By.xpath("//select/option[5]")).click();
	 * driver.findElement(By.xpath("//select[@class='input ddl'][2]")).click();
	 * driver.findElement(By.xpath("//select/option[20]")).click();
	 * driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys("195");
	 * driver.findElement(By.xpath("(//input[@type='text'])[3]")).sendKeys("SABIR");
	 * driver.findElement(By.xpath("(//input[@type='text'])[4]")).sendKeys("FREE");
	 */
	// pageFactory

	@FindBy(css = "[placeholder='Select Country']")
	WebElement selectCountryField;

	@FindBy(xpath = "//button[contains(@class,'ta-item')][2]")
	WebElement selectCountry;

	@FindBy(css = ".action__submit")
	WebElement orderSubmit;

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
