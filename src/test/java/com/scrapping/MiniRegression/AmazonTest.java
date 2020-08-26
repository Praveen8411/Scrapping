package Tests;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import Pages.AmazonPage;
import io.appium.java_client.MobileElement;

public class AmazonTest extends BaseClass {
	
	AmazonPage ap;
	
	public void search(String productname,int lowestPrice,int highestprice) {
		
try {
	MobileElement ele = driver.findElement(By.id("nav-search-keywords"));
	ele.click();
	ele.sendKeys(productname);
	ele.sendKeys(Keys.ENTER);
	driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	List<MobileElement> profile = driver.findElements(By.xpath("//span[@class='a-price-whole']"));
	for(int i=0; i<profile.size(); i++) {
		String s = profile.get(i).getText();
		String s1 = s.replace(",", "");
		int price = Integer.valueOf(s1);
		if( price > lowestPrice && price < highestprice) {
			profile.get(i).click();
			break;
		}
	}
	MobileElement ele1 = driver.findElement(By.id("add-to-cart-button"));
	JavascriptExecutor executor = (JavascriptExecutor)driver;
	executor.executeScript("arguments[0].click();", ele1);
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
}
	@Test
	public void searchProducts() {
		try {
			driver.get("https://www.amazon.in");
			search("iphone",65000,75000);
			search("iphone watch",1000,20000);
			MobileElement ele1 = driver.findElement(By.xpath("//span[@class='nav-cart-count']"));
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", ele1);
		}
		catch(Exception e){
			e.printStackTrace();
			}
		}
}
