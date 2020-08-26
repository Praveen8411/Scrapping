package com.scrapping.MiniRegression;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.scrapping.Library.AppLibrary;
import com.scrapping.Library.TestBase;
import com.scrapping.PageObject.UpworkPage;

public class UpworkTest extends TestBase {
	
	private UpworkPage up;
	private String domainName;
	
	@BeforeClass
	public void setUp() throws Exception {
		// Get the AppLibrary Object which is used to call the methods
		appLibrary = new AppLibrary("UpworkTest");
		up = new UpworkPage(appLibrary);
		domainName = "QA";
	}
	
	public void launchApp() throws Exception {
		appLibrary.getDriverInstance();
		appLibrary.launchApp();
	}
	
	@Test(priority=1)
	public void sendDomainName() throws Exception {
		launchApp();
		up.searchFreeLancers(domainName);
		up.storeData(domainName);
	}
}
