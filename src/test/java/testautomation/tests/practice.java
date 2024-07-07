package testautomation.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class practice {

	@Test
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String PRODUCT_NAME = "ZARA COAT 3";

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client/");
		driver.findElement(By.id("userEmail")).sendKeys("sabirmostafa@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Work@143");
		driver.findElement(By.id("login")).click();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".card-body")));
		List<WebElement> products = driver.findElements(By.cssSelector(".card-body"));

		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(PRODUCT_NAME)).findFirst()
				.orElse(null);
		prod.findElement(By.cssSelector("button.btn.w-10")).click();

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("toast-container")));
		// asked the locator from developer
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = cartProducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(PRODUCT_NAME));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		// Edit personal information
		driver.findElement(By.xpath("//input[contains(@value,'4542')]")).clear();
		driver.findElement(By.xpath("//input[contains(@value,'4542')]")).sendKeys("4242 4242 4242 4242");
		driver.findElement(By.xpath("//select[@class='input ddl'][1]")).click();
		driver.findElement(By.xpath("//select/option[5]")).click();
		driver.findElement(By.xpath("//select[@class='input ddl'][2]")).click();
		driver.findElement(By.xpath("//select/option[20]")).click();
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys("195");
		driver.findElement(By.xpath("(//input[@type='text'])[3]")).sendKeys("SABIR");
		driver.findElement(By.xpath("(//input[@type='text'])[4]")).sendKeys("FREE");
		// Coupon validation
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		Assert.assertTrue(driver.findElement(By.xpath("(//p)[2]")).getText().contains("Invalid"));

		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "ban").build().perform();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".ta-item")));
		driver.findElement(By.xpath("//button[contains(@class, 'ta-item')][2]")).click();
		driver.findElement(By.cssSelector("a.action__submit")).click();

		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

		driver.close();
	}

}
