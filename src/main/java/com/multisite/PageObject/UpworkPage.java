package com.multisite.PageObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebElement;

import com.multisite.Library.AppLibrary;
import com.sun.beans.introspect.PropertyInfo.Name;

public class UpworkPage {
	
	AppLibrary appLibrary;
	
public static String searchField="xpath://div[@class='navbar-collapse d-none d-lg-flex sticky-sublocation']//input[@class='form-control']";
public static String searchIcon = "xpath://div[@class='navbar-collapse d-none d-lg-flex sticky-sublocation']//span[@class='glyphicon air-icon-search m-sm-left m-0-right']";
public static String title = "xpath://div[@class='ellipsis freelancer-tile-title_compact d-none d-md-block']";
public static String titleDescription = "xpath://p[@data-qa='tile_description']";
public static String skills = "xpath://div[@class=' skills-section']";
public static String name = "xpath://a[@class='freelancer-tile-name']"; 
public static String randomName = "xpath://section[@class='up-card-section py-30']//h1[@itemprop='name']";
public static String randomTitle = "xpath://h2[@class='mb-0 font-weight-black']";
public static String randomDesc = "xpath://div[@class='up-line-clamp']//span[@class='text-pre-line break']";
public static String randomSkill = "xpath://a[@class='up-skill-badge']";
public static String firstPageRandomName = "xpath://a[@class='freelancer-tile-name']";
	
public UpworkPage(AppLibrary appLibrary) throws IOException {
	this.appLibrary = appLibrary;
}
	
	public void searchFreeLancers(String name) {
		appLibrary.findElement(searchField).sendKeys(name);
		appLibrary.findElement(searchIcon).click();
		
	}
	
	public void clickONRandomName() {
		List<WebElement> ListRandomNames = appLibrary.findElements(firstPageRandomName);
		 Random rn = new Random();
		 int ran= 0;
		 for(int i=0; i<ListRandomNames.size(); i++)
		 {
			ran  = rn.nextInt(10)+1;
			//System.out.println(ran);
		 }
		 ListRandomNames.get(ran).click();
	}
	
	public void searchKeyword(List<String> all_elements_Title ,List<String> all_elements_Description ,List<String> all_elements_Skill, List<String> all_elements_name ,String domName) 
	{
		System.out.println("List of freelancers in which keyword is found :");
		//Checking keyword in each attribute
		 for (int i =0 ; i<all_elements_Title.size(); i++)
		 {     
			 String t = all_elements_Title.get(i);
			 String d = all_elements_Description.get(i);
			 String s = all_elements_Skill.get(i);
			 String Name = all_elements_name.get(i);
			 System.out.println("\n"+Name);
			 int flag = 0;
			 //equalsIgnoreCase
			if(t.contains(domName))
		        {
				    System.out.println("Title");
				    flag = 1;
		        	//System.out.print("title" );
		        }
			if(d.contains(domName)){
					System.out.println("Description");
					flag = 1;
			}
			if(s.contains(domName)) {
				System.out.println("Skill");
				flag = 1;
			}
			if (flag == 0) {
				System.out.println("Keyword not found");
			}
		 }
	}
	
	public void verifyRandomDataWithStored(List<String> all_elements_Title ,List<String> all_elements_Description ,List<String> all_elements_Skill, List<String> all_elements_name , String domName)
	{
		 String rName = appLibrary.findElement(randomName).getText();
		 String rTitle = appLibrary.findElement(randomTitle).getText();
		 String rDes = appLibrary.findElement(randomDesc).getText();
		 String rDes1 = rDes.replace("\n","").replaceAll("\\s+","");
		 int flag = 0;
		 
		 List<WebElement> ListRandomSkills = appLibrary.findElements(randomSkill);
		 ArrayList<String> all_elements_rSkills = new ArrayList<String>();
		 for (WebElement ele : ListRandomSkills){
			 all_elements_rSkills.add(ele.getText());
			}
		 String RandomSkill = String.join(" ", all_elements_rSkills); 
		 
		 for(int j=0; j<all_elements_name.size(); j++ ) 
		 {
			 if(all_elements_name.get(j).equals(rName))
			 {   
				 System.out.println("Random Name " +rName+ " found at " +j+ " position");
				 		
				 if(all_elements_Title.get(j).equals(rTitle))
				 {
					 System.out.println("Title : " + rTitle);
				 }
				 
				 String  Description = all_elements_Description.get(j).replaceAll("\\s+","").replace("...","");
				 String  skills = all_elements_Skill.get(j).replaceAll("\\s+","").replace("more","").replaceAll("[0-9]","");
				 String  RS = RandomSkill.replaceAll("\\s+","");
				 
				if(rDes1.contains(Description))
				{
					System.out.println("Description : " + rDes);
				}
				appLibrary.sleep(2000);
				 if(RS.contains(skills))
				 {
					 System.out.println("Skills : " + all_elements_rSkills);
				 }
			 }
									 
	} 
		 System.out.println("Finding keyword in Attributes of Random Record");
			   		 
		if( rTitle.contains(domName))
		{
			System.out.println("Keyword found in Title: " +rTitle);
		    flag = 1;
		}
		if( rDes.contains(domName))
		{
			System.out.println("Keyword found in Description: " +rDes);
		    flag = 1;
		}
		 
		for (int i = 0; i < all_elements_rSkills.size() ; i++)
		{
			if( all_elements_rSkills.get(i).contains(domName))
			{
				System.out.println("Keyword found in Skills: " +all_elements_rSkills.get(i));
			    flag = 1;
			}
		}
		if (flag == 0) {
			System.out.println("Keyword not found in Random Data");
		}
		 
	}
	
	public void storeData(String domName) {
		
		 List<WebElement> ListTitles = appLibrary.findElements(title);
		 List<WebElement> ListDescription = appLibrary.findElements(titleDescription);
		 List<WebElement> ListSkills = appLibrary.findElements(skills);
		 List<WebElement> FreelancerName = appLibrary.findElements(name);
		 List<String> all_elements_Title=new ArrayList<>();
		 List<String> all_elements_Description=new ArrayList<>();
		 List<String> all_elements_Skill=new ArrayList<>();
		 List<String> all_elements_name=new ArrayList<>();
		 //Storing title, description and skills 		 
		 for (int i =0 ; i<ListTitles.size(); i++)
		 {      
			   	all_elements_Title.add(ListTitles.get(i).getText());
			   	all_elements_Description.add(ListDescription.get(i).getText());		   	
		    	all_elements_Skill.add(ListSkills.get(i).getText());
		    	all_elements_name.add(FreelancerName.get(i).getText());
		 }
		 
		 searchKeyword(all_elements_Title, all_elements_Description, all_elements_Skill, all_elements_name, domName);
		 clickONRandomName();
		 verifyRandomDataWithStored( all_elements_Title , all_elements_Description , all_elements_Skill, all_elements_name ,domName);
	}
			
}



