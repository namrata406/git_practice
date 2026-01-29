package namratadata.SeleniumFrameworkDesign;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ecommerce {

	public static void main(String[] args) throws InterruptedException {

		String productName = "ZARA COAT 3";
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
		driver.findElement(By.id("userEmail")).sendKeys("namrata4062021@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Singh123@");
		driver.findElement(By.id("login")).click();
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

		// List<WebElement>purchaseList =
		// driver.findElements(By.xpath("//button[@class='btn w-10 rounded']"));
		// List<String>price =
		// products.stream().map(s->s.getText()).collect(Collectors.toList());
		// price.forEach(a->System.out.println(a));
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst()
				.orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		// here we use explicit wait until text is visible so, here we use
		// visibilityOfElement
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("div[aria-label='Product Added To Cart']")));
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		// cart has more than 1 item
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		// here we use anyMatch instead of filter because it gives boolean value true or
		// false
		boolean match = cartProducts.stream().anyMatch(s -> s.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		//this statement is not run because of spinner
		//driver.findElement(By.cssSelector(".totalRow button")).click()
		
		By spinner = By.cssSelector("ngx-spinner"); 
		wait.until(ExpectedConditions.invisibilityOfElementLocated(spinner));
		WebElement checkoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".totalRow button")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkoutButton);
		try {
		    checkoutButton.click();
		} catch (ElementClickInterceptedException e) {
		    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkoutButton);
		}
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")),"india").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".btnn.action__submit")).click();
		driver.close();
		driver.quit();
		driver.quit(1);
		driver.quit(2);
		driver.quit(3);
		

	


		// driver.close();

	}

}
