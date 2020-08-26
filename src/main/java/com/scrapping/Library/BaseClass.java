package Tests;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class BaseClass {
	
	static AppiumDriver<MobileElement> driver;

	@BeforeTest
	public void setup() {
	try{
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("chromedriverExecutable", "G:\\Appium\\appiumtests\\chromedriver.exe");
		cap.setCapability("deviceName", "POCO F1");
		cap.setCapability("udid", "bcb0d23f");
		cap.setCapability("platformName", "ANDROID");
		//cap.setCapability(MobileCapabilityType.APP, "");
		//cap.setCapability(MobileCapabilityType.VERSION, "9 PKQ1.180729.001");
		//cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9 PKQ1.180729.001");
		cap.setCapability("platformVersion", "9 PKQ1.180729.001");
		//cap.setCapability(MobileCapabilityType.VERSION, "9 PKQ1.180729.001");
		//cap.setCapability("appPackage", "com.amazon.mShop.android.shopping");
		//cap.setCapability("appActivity", "com.amazon.mShop.splashscreen.StartupActivity");
		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
		cap.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
		cap.setCapability("automatorName", "UiAutomator2");
		cap.setCapability("noSign", "true");
		cap.setCapability("appium:chromeOptions", ImmutableMap.of("w3c", false));
		
		URL url = new URL("http://127.0.0.1:4723/wd/hub");
		driver = new AppiumDriver<MobileElement>(url, cap);
	
		
	}
	catch(Exception e) {
		System.out.println("Cause is : "+e.getCause());
		System.out.println("Message is : "+e.getMessage()); 
		e.printStackTrace();
	}
		
}		
	
	@AfterTest
	public void teardown() {
		driver.close();
		driver.quit();
	}
		
	}
