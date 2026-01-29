package rahulshettyacademy;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;

public class ErrorHandeling extends BaseTest {
	@Test(retryAnalyzer = Retry.class)
	public void ErrorLogin() {
		System.out.println("Hey I am here");
		landingPage.loginWithinPage("namrata4062021@gmail.com","Singh");
	    driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
	    driver.close();
	}
	

}
