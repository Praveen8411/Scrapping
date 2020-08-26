package com.multisite.MiniRegression;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.multisite.Library.AppLibrary;
import com.multisite.Library.TestBase;
import com.multisite.PageObject.UpworkPage;

public class UpworkTest extends TestBase {
	
	private UpworkPage up;
	private String domainName;
	
	@BeforeClass
	public void setUp() throws Exception {
		// Get the AppLibrary Object which is used to call the methods
		appLibrary = new AppLibrary("UpworkTest");
		up = new UpworkPage(appLibrary);
		//mePage = new MyEventsPage(appLibrary);
		// = "https://eclipse.qa6.pqe.io/dashboard";
		//account = "QA Test Account";
		//eventName = "Test Event" + AppLibrary.getRandomNumberString("");
		//editEventName = "Edit " + eventName;
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
