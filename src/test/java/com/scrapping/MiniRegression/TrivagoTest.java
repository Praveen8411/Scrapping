package com.scrapping.MiniRegression;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.scrapping.Library.AppLibrary;
import com.scrapping.Library.TestBase;
import com.scrapping.PageObject.TrivagoPage;

public class TrivagoTest extends TestBase {
	
	private TrivagoPage tp;
	private String name;
	private String checkINDay;
	private String checkOutDay;
	private String hotelName;
	private int flag;
	WebDriver driver ;
	
	@BeforeClass
	public void setUp() throws Exception {
		// Get the AppLibrary Object which is used to call the methods
		appLibrary = new AppLibrary("TrivagoTest");
		tp = new TrivagoPage(appLibrary);
		name = "Pune";
		checkINDay = "15";
		checkOutDay = "20";
		hotelName = "JW Marriott Pune";
		flag= 0;
	}
	
	public void launchApp() throws Exception {
		driver = appLibrary.getDriverInstance();
		appLibrary.launchApp();
	}
	
	@Test(priority=1)
	public void enterDetails() throws Exception {
		launchApp();
		//tp.selectCurrency(curr);
		tp.enterName(name);
		//tp.selectDateHardCode();
		tp.checkIN(checkINDay);
		tp.checkOut(checkOutDay);
		tp.rotateSlider(driver);
		tp.storeData(flag);
		tp.createExcelFile();
		tp.enterName(hotelName);
		tp.compareExcel();
		tp.saveImage();
	}

}
