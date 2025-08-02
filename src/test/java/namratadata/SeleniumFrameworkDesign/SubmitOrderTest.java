package namratadata.SeleniumFrameworkDesign;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;

public class SubmitOrderTest extends BaseTest {
	//hey i want to check github and jekins integration
	@Test(dataProvider = "getData",groups = {"Purchase"})
	public void submitOrder(HashMap<String,String>input) throws InterruptedException, IOException
	{
		//String productName = "ZARA COAT 3";		
		ProductCatalogue productCatalogue = landingPage.loginWithinPage(input.get("email"),input.get("pass"));
		productCatalogue.addToCart(input.get("productName"));
		CartPage cartPage = productCatalogue.gotoCartPage();
		Assert.assertTrue(cartPage.matchOfProduct(input.get("productName")));
		PaymentPage payment = cartPage.CheckOutButton();
		payment.selectCountry();
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		//WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".btnn.action__submit")));
		//Thread.sleep(2000);
		//payment.placeOrder();
		//Assert.assertTrue(payment.grabText().equalsIgnoreCase("Thankyou for the order."));

	}
	 @DataProvider
	public Object[][] getData() throws IOException {
		 //this is the Hashmap Method(Key:value)
			/*
			 * HashMap<String,String>map = new HashMap<String,String>(); map.put("email",
			 * "namrata4062021@gmail.com"); map.put("pass", "Singh123@");
			 * map.put("productName", "ZARA COAT 3"); HashMap<String,String>map1 = new
			 * HashMap<String,String>(); map1.put("email", "namrata4062021@gmail.com");
			 * map1.put("pass", "Singh123@"); map1.put("productName", "ADIDAS ORIGINAL");
			 */
		 //this is the array
		//return new Object [][] {{"namrata4062021@gmail.com","Singh123@","ZARA COAT 3"},{"namrata4062021@gmail.com","Singh123@","ADIDAS ORIGINAL"}};
		 //json method
		 List<HashMap<String,String>>data = getJsonDataToMap();
		 return new Object [][] {{data.get(0)},{data.get(1)}};
		
	}
	

}
