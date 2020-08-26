package com.scrapping.PageObject;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.scrapping.Library.AppLibrary;

public class TrivagoPage {

	AppLibrary appLibrary;
	boolean present;
	String filePath = "TestData" + File.separator + "test3.xlsx";
	String imagePath = "TestData" + File.separator + "Image";
	File src = new File (filePath);
	XSSFWorkbook wb = new XSSFWorkbook();
	List<String> allNames=new ArrayList<>();
	List<String> allRatings=new ArrayList<>();
	List<String> allPrice=new ArrayList<>();
	
	public static String enterCityName = "xpath://input[@id='querytext']";
	public static String placeholder = "xpath://input[@placeholder='Enter a hotel name or destination']";
	public static String clickONCalender = "xpath://button[@data-qa='calendar-checkin']";
	public static String selectDate = "xpath://tbody//tr//td";
	public static String cityNamesList = "xpath://div[@class='ssg-suggestion__info']";
	public static String okButton = "xpath://button[@id='onetrust-accept-btn-handler']";
	public static String applyButton = "xpath://button[@class='btn btn--primary btn--small btn--apply-config']";
	public static String slider = "xpath://div[@role='slider']";
	public static String hotelNames = "xpath://div[@class='item__flex-column']//span[@class='item-link name__copytext name__copytext--small']";
	//public static String ratings = "xpath://span[@class='item-components__pillValue--d6d0b item-components__value-sm--d2127 item-components__pillValue--d6d0b']";
	public static String ratings = "xpath://span[@class='review']";
	public static String USD = "xpath://select[@id='currency']";
	public static String currencies = "xpath://option[@class='option']";
	//public static String lowestPrice = "xpath://article[@class='accommodation-list__cheapest--e6ecb accommodation-list__article--5f8ea']//span[@class='accommodation-list__price--dd5cb']";
	public static String lowestPrice = "xpath://button[@data-qa='cheapest-deal']|//button[@data-qa='airbnb-cheapest-deal']";
	public static String searchedHotel = "xpath://div[@class='item__flex-column']//span[@title='JW Marriott Pune']";
	public static String image = "xpath://div[@data-qa='gallery-slideout']";
	public static String nextButton = "xpath://span[@class='icon-ic icon-rtl pagination__icon icon-contain']";
	
	public TrivagoPage(AppLibrary appLibrary) throws IOException {
		this.appLibrary = appLibrary;
	}
	
	public void enterName(String name) {
		String option = name;
		appLibrary.findElement(enterCityName).clear();
		appLibrary.sleep(2000);
		appLibrary.findElement(placeholder).sendKeys(name);
		appLibrary.sleep(2000);
		List<WebElement> options = appLibrary.findElements(cityNamesList);
		for (WebElement opt : options) {
            if (opt.getText().contains(option)) {
            	appLibrary.sleep(2000);
                opt.click();
                return;
            }
        }
        throw new NoSuchElementException("Can't find " + option + " in dropdown");
	}
	
	public void checkIN(String day) {
		appLibrary.findElement(okButton).click();
		List<WebElement> dateWidget = appLibrary.findElements(selectDate);
		//List<WebElement> columns=dateWidget.findElements(By.tagName("td"));
		for (WebElement ele : dateWidget){
			if (ele.getText().equals(day)){
				appLibrary.sleep(2000);
			    ele.click();
			    break;
		}
		}
		
}
	public void checkOut(String day) {
		List<WebElement> dateWidget = appLibrary.findElements(selectDate);
		//List<WebElement> columns=dateWidget.findElements(By.tagName("td"));
		for (WebElement ele : dateWidget){
			if (ele.getText().equals(day)){
				appLibrary.sleep(2000);
			    ele.click();
			    appLibrary.sleep(1000);
			    appLibrary.findElement(applyButton).click();
			    break;
		 }
	   }		
	}
	
	public void createExcelFile() throws Exception {
		try {
		XSSFWorkbook wb = new XSSFWorkbook();			
		FileOutputStream out = new FileOutputStream(new File(filePath));  
		wb.write(out);
		out.close();
		}
		catch(Exception e){

			System.out.println(e);
	}
		System.out.println("Excel file created");
		fetchListOfNames(allNames, allRatings, allPrice);

}
	
	public void storeData(int flag) throws Exception {
		 List<WebElement> ListNames = appLibrary.findElements(hotelNames);		 
		 List<WebElement> ListRatings = appLibrary.findElements(ratings);
		 appLibrary.sleep(2000);
		 List<WebElement> ListPrice = appLibrary.findElements(lowestPrice);
		 
	/*	 List<String> allNames1=new ArrayList<>();
		 List<String> allRatings=new ArrayList<>();
		 List<String> allPrice=new ArrayList<>();*/
		 
		 for (int i =0 ; i<ListNames.size(); i++)
		 {
			 
			 allNames.add(ListNames.get(i).getText());
			 System.out.println(allNames.get(flag));
			 String rate = ListRatings.get(i).getText();
			 rate = rate.substring(0, 3);
			 if (rate.contains("[a-z]+"))
			 		{ allRatings.add("NA"); }
			else { allRatings.add(rate);}
			 System.out.println(allRatings.get(flag));
			 allPrice.add(ListPrice.get(i).getText());
			 String p = allPrice.get(flag);
			p = p.replaceAll("[^0-9$]+", " ").replace(" ", "");
			 System.out.println(p);
			 System.out.println("===========================================");
			 flag= flag+1;
		 }
		 try {
		    WebElement button = appLibrary.findElement(nextButton);
		    present = true;
		    button.click();
		    appLibrary.sleep(3000);
		    storeData(flag);
		 } catch (Exception e) {
		    present = false;
		    System.out.println(e);		       
		 }
	}
	
	public void fetchListOfNames(List<String> allNames, List<String> allRatings, List<String> allPrice) throws Exception {
		 //createExcelFile();
		 FileOutputStream out = new FileOutputStream(src);
		 XSSFSheet sh = wb.createSheet("HotelList");
		 appLibrary.sleep(2000);
		 
		 int rowCount = allNames.size();
	
	     sh.createRow(0).createCell(0).setCellValue("HotelNames");
		 sh.getRow(0).createCell(1).setCellValue("Ratings");
		 sh.getRow(0).createCell(2).setCellValue("LowestPrice");
		 
		 for(int i=0; i<rowCount; i++) 
		 {
			 appLibrary.sleep(2000);
			 String hName = allNames.get(i);
			 sh.createRow(i+1).createCell(0).setCellValue(hName);
			 String ratings = allRatings.get(i);
			 sh.getRow(i+1).createCell(1).setCellValue(ratings);
			 String Lprice = allPrice.get(i);
			 Lprice = Lprice.replaceAll("[^0-9$]+", " ").replace(" ", "");
			 sh.getRow(i+1).createCell(2).setCellValue(Lprice);
		 }
		 wb.write(out);
	}
	
	public void compareExcel() throws Exception 
	{
		 
		 Sheet sheet1= wb.getSheet("HotelList");
		 int rowCounts = sheet1.getLastRowNum()-sheet1.getFirstRowNum();
		 for (int i = 1; i < rowCounts; i++)
		 {
		         Row row1 = sheet1.getRow(i);
		         String s1 = row1.getCell(0).getStringCellValue();
		         String s2 = appLibrary.findElement(searchedHotel).getText();
		             if (s1.equals(s2))
		                { 
		                    System.out.println("found at position " +(i+1));                
		                 }           
		 }  
		 //sendMail(commonData);
	 }
	
	public void rotateSlider(WebDriver driver) {
		int x=-900;
	    WebElement slider = appLibrary.findElement("xpath://div[@role='slider']");
	    int width=slider.getSize().getWidth();
	    Actions move = new Actions(driver);
	    move.moveToElement(slider, ((width*x)/100), 0).click();
	    move.build().perform();
	    System.out.println("Slider moved");
	    }
		 
	public void saveImage() {
		BufferedImage image = null;
		try {
			String name = appLibrary.findElement(searchedHotel).getText();
			URL url = new URL ("http://imgcy.trivago.com/c_lfill,d_dummy.jpeg,e_sharpen:60,f_auto,h_450,q_auto,w_450/itemimages/16/39/1639371_v4.jpeg");
			image = ImageIO.read(url);
			ImageIO.write(image, "png", new File(imagePath, name + ".png"));
		}catch(IOException e) {
			
		}
	}
}

