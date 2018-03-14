package modifyMyOrder; 

import java.io.FileWriter;
//import java.io.IOException;
//import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import java.util.Arrays;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ModifyOrder 
{
	//variables declaration goes here
	public WebDriver driver;
	public String timeinsec;
	public Calendar currentDate = Calendar.getInstance();
	public SimpleDateFormat formatter=  new SimpleDateFormat("yyyy_MMM_dd HH_mm_ss");
	public String[] DatatoWrite = new String[35];
	ArrayList<String> ReadCsv = new ArrayList<String>();
	//String FilePath = "C:/Users/loadtest/Documents/GitHub/myorder-selenium/";
	String FilePath = "../";
	String ResultPath;
	String browser = "CH";
	
	@Before
	public void setUp() throws Exception 
		{
			setFramework();
			ReadCsv = readCsv(FilePath+"ModifyMyOrder.csv");
			
		}
	//Start the script 		
	//@Test
	public void modifyMyOrder_Tests() throws Exception {
	
		   for(int i=28;i<=28;i++) {   
			   resetValues();
				try {		
    			
						setRowData(ReadCsv.get(i).split(","));
				
						if(getRowData()[2].equals("Done"))	{System.out.println("Test"+i +" Done, skipping");continue;}
						datatowrite(i);

	        			///************Read Excel**************///
	        			String TestCase = getRowData()[1];
	        			String RunStatus = getRowData()[2];
	        			String brand = getRowData()[3];
	        			String userType = getRowData()[4]; 
	        			String userName = getRowData()[5];
	        			String pwd = getRowData()[6];
	        			String ExpectedMMOStatus = getRowData()[7];
	        			String orderNumber = getRowData()[8];
	        			//**********************************//
	        			
	        			//**Orderdetails before modification***//
	        			String cardMessage = getRowData()[9];
	        			String zipcode =  getRowData()[19];
	            	    String deliveryDate = getRowData()[20];
	            	    //**********************************//
	            	    
	            	    //** New Orderdetails*****//
	        			String NewCardMessage = getRowData()[21];
	            	    String NewDelDate = getRowData()[32];
	        			///================End of excel code=====///
						String Comment ="";
	            	    
	            	    System.out.println("---------------------------------- Test Start --------------------------------");
					    //WebDriverWait wait = new WebDriverWait(driver, 60);
						//Calling browser and accessing 18F URL
	            	    callBrowser(browser);
						deleteCookies();
						
						System.out.println("Step 1: Launching Flowers UAT Website ");
						driver.get("https://akamai.1800flowers-uat.net");
						Thread.sleep(1000);
						
					    if (userType.contains("RU")||userType.contains("PU")) {
					    	System.out.println("Step 2: Track Order By User Type: " +userType);
							goto_OrderDetailsPage_Registered(orderNumber,userName, pwd);
					    }
					    else {
							System.out.println("Step 2: Track Order by User Type: " +userType);
							goto_TrackOrderPage_Guest(orderNumber,zipcode);	    
					    }

						System.out.println("Step 4: View/Modify Order Details ");
						goto_OrderDetails(userType,orderNumber);
						
						if (ExpectedMMOStatus.contains("FALSE")) { 
							System.out.println("Validating Error Message ");
							modifyOrder_False();
							//continue;
							driver.quit();
						}
						

						System.out.println("Step 5: Validate & Add/Update Message ");
						updateMessage(NewCardMessage, Comment);
						
						System.out.println("Step 6: Validate & Update Recipient Information ");
						updateAddress(Comment);
						
						System.out.println("Step 7: Validate & Update Delivery Date");
						updateDate(Comment);
						
						System.out.println("Step 8: Review Changes");
						reviewChanges();
						
						System.out.println("Step 9: Submit Changes");
						//submitChanges();
						
						System.out.println("Step 10: Verify Inflight Message window");
						//validateInflightChanges();
						
						System.out.println("Step 11: Writing results to Sheet");
						
						DatatoWrite[2]="Done";
						if(Comment.endsWith(""))DatatoWrite[23]="Passed"; else DatatoWrite[23]="Failed";
						DatatoWrite[24]=Comment;
						writeCsv(ResultPath+"/"+"Result.csv",DatatoWrite);
						
						driver.quit();driver = null;
						System.out.println("---------------------------------- Test End ----------------------------------");
					
				}
				catch(Exception e){
					e.printStackTrace();
					System.out.println("error occured");
					DatatoWrite[2]="Done";		
					DatatoWrite[22]="Failed";
					DatatoWrite[23]= findexception(e.toString());
					//DatatoWrite[18]= formatter.format(currentDate.getTime());
					writeCsv(ResultPath+"/"+"Result.csv",DatatoWrite);
					driver.quit();driver = null;
				}
		   }
	}

	@Test
	public void modifyOrderValidateFieldErrors_Tests() throws Exception {
	   for(int i=28;i<=28;i++) {   
		   resetValues();
			try {		
			
					setRowData(ReadCsv.get(i).split(","));
			
					if(getRowData()[2].equals("Done"))	{System.out.println("Test"+i +" Done, skipping");continue;}
					datatowrite(i);
        			
        			
        			///************Read Excel**************///
        			String userType = getRowData()[4]; 
        			String userName = getRowData()[5];
        			String pwd = getRowData()[6];
          			String ExpectedMMOStatus = getRowData()[7];
        			String orderNumber = getRowData()[8];
  
            	    String zipcode =  getRowData()[19];
            	    
        			///================End of excel code=====///
					
            	    System.out.println("---------------------------------- Test: Modify Order Valiate Failed Error --------------------------------");
				    //WebDriverWait wait = new WebDriverWait(driver, 60);
					//Calling browser and accessing 18F URL
            	    callBrowser(browser);
					deleteCookies();
					
					System.out.println("Step 1: Go To the Home Page ");
					driver.get("https://akamai.1800flowers-uat.net");
					Thread.sleep(1000);
					
				    if (userType.contains("RU")||userType.contains("PU")){
				    	System.out.println("Step 2: Track Order By User Type: " +userType);
						goto_OrderDetailsPage_Registered(orderNumber,userName, pwd);
				    }
				    else {
						System.out.println("Step 2: Track Order by User Type: " +userType);
						goto_TrackOrderPage_Guest(orderNumber,zipcode);	    
				    }

					System.out.println("Step 4: View/Modify Order Details ");
					goto_OrderDetails(userType,orderNumber);
								
					
					if (ExpectedMMOStatus.contains("FALSE")) { 
						System.out.println("Validating Error Message ");
						modifyOrder_False();
						continue;
					}
					
					
					System.out.println("Step 5: Validate Message ");
					checkCardMessageWithoutInput("", "Validate Failed Error on Card Message");
					
					System.out.println("Step 6: Validate Recipient Information ");
					checkRecipientWithoutInput("Validate Failed Error on Recipient Info");
					
					System.out.println("Step 7: Validate Delivery Date");
					checkDeliveryDateWithoutInput("Validate Failed Error on Delivery Date");
					
//					System.out.println("Step 8: Review Changes");
//					reviewChanges();
					
					//review();
					DatatoWrite[2]="Done";					
					//DatatoWrite[18]= formatter.format(currentDate.getTime());
					DatatoWrite[23]="Passed";
					writeCsv(ResultPath+"/"+"Result.csv",DatatoWrite);
					
					driver.quit();
					driver = null;
					System.out.println("---------------------------------- Test End ----------------------------------");
				
			}
				catch(Exception e) {
					e.printStackTrace();
					System.out.println("error occured");
					DatatoWrite[2]="Done";		
					DatatoWrite[22]="Failed";
					DatatoWrite[23]= findexception(e.toString());;
					//DatatoWrite[18]= formatter.format(currentDate.getTime());
					writeCsv(ResultPath+"/"+"Result.csv",DatatoWrite);
					driver.quit();
					driver = null;
				}
	   }
}

	//************************************************************//
	//*****// Methods Below Will be called from Test above // ****//
	//************************************************************//
	
	public void modifyOrder_False() throws Exception {
	           	   
		WebDriverWait wait = new WebDriverWait(driver,60);
		wait.until(ExpectedConditions.visibilityOfElementLocated((By.className("orderNotModifiableErr"))));
		String ErrorMessage = driver.findElement(By.className("orderNotModifiableErr")).getText();
		
		System.out.println("Step 6: Writing results to Sheet");
		if(ErrorMessage.contains("This order may no longer be modified")){
			System.out.println("Error:" + ErrorMessage+" - Passed");
			
			DatatoWrite[2]="Done";
			DatatoWrite[23]="Passed";
			writeCsv(ResultPath+"/"+"Result.csv",DatatoWrite);
		    System.out.println("---------------------------------- Test End    --------------------------------");
		    //System.exit(1);
		}
		else{   				
			//DatatoWrite[18]= formatter.format(currentDate.getTime());
			driver.quit();
			driver = null;
			DatatoWrite[2]="Done";		
			DatatoWrite[22]="Failed";
			DatatoWrite[23]= "Error message not displayed";
			writeCsv(ResultPath+"/"+"Result.csv",DatatoWrite);
		}
	}

	public String findexception(String Error) {
		String SplitError[] = Error.split(":");
		if(Error.contains(" no such element")||Error.contains("NoSuchElementException")) Error = (SplitError[4].replace(',', '-')+(SplitError[5].split("}"))[0]);
		
		if(Error.contains("chrome not reachable")||Error.contains("no such window")) Error = "Chrome window closed";
		
		return Error;
	}
    
    public void callBrowser(String BrsrName) {
    try{
    	switch(BrsrName){
    		case "FF":
    			driver = new FirefoxDriver();					
    			break;
    		
    		case "CH":
    			System.setProperty("webdriver.chrome.driver","../modifymyorder/lib/chromedriver.exe");
    			ChromeOptions  options = new ChromeOptions();
    			options.addArguments("start-maximized");
    			driver = new ChromeDriver(options);
    			break;	
    		
    		case "IE":
    			System.setProperty("webdriver.ie.driver", "C:/Selenium Testing/Selenium component/IEDriverServer_Win32_2.45.0/IEDriverServer_Win32_2.45.0.exe");
    			driver=new InternetExplorerDriver();
    			break;
    				
    		case "default":
    			System.out.println("Browser name is incorrect");
    			break;
    		}
    	}
    	catch(Exception e){	System.out.println("error occured, exiting");System.out.println(e.toString());}
    }

    public void goto_TrackOrderPage_Guest(String ordernumber, String zipcode) {
    	//Click on My Orders, enter order details and click submit
    	driver.findElement(By.linkText("Track Your Order")).click();
		String verifyTitleTrackByNumSignIn = driver.findElement(By.xpath("//div[2]/h1")).getText();
		System.out.println("Title: "+verifyTitleTrackByNumSignIn);
		System.out.println("Step 3: Order#: "+ordernumber+ " Zip: "+zipcode);
    	driver.findElement(By.id("label")).sendKeys(ordernumber);
    	driver.findElement(By.id("label2")).sendKeys(zipcode);
    	driver.findElement(By.cssSelector("input[alt=\"Submit\"]")).click();
    }

    public void goto_OrderDetailsPage_Registered(String orderNumber, String userName, String pwd) {
    	//Click on My Orders, enter order details and click submit
    	driver.findElement(By.linkText("Track Your Order")).click();
		String verifyTitleReturningSignIn = driver.findElement(By.cssSelector("h1")).getText();
		System.out.println("Title: "+verifyTitleReturningSignIn);
		System.out.println("Step 3: Order#: "+orderNumber+ " Email: "+userName+ " Password: " +pwd);
    	driver.findElement(By.id("logonId")).sendKeys(userName);
    	driver.findElement(By.id("logonPassword")).sendKeys("tag@123");
    	driver.findElement(By.cssSelector("input[alt=\"Sign In\"]")).click();
    	//driver.findElement(By.cssSelector("img[alt=\"Order Details\"]")).click();	
    }

    public void goto_OrderDetails(String userType, String orderNumber) {
    		switch (userType){
				case "GU": 
					
						driver.findElement(By.cssSelector("img[alt=\"Order Details\"]")).click();break;
				case "PU":
				case "RU":
					{
						WebDriverWait wait = new WebDriverWait(driver,60);
						String ShortOrderNumber = orderNumber.substring(5);
			        	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), '"+ShortOrderNumber+"')]/following-sibling::div[2]/form/a/img")));
			 
						driver.findElement(By.xpath("//div[contains(text(), '"+ShortOrderNumber+"')]/following-sibling::div[2]/form/a/img")).click();
						
					}
					break;
    		}	
    }
    
    public void checkCardMessage() throws Exception {
           try{
				String cardMessage = driver.findElement(By.cssSelector("div.confirmmessage")).getText();
				System.out.println("Card Message: "+cardMessage.split("\n")[1]);
				if(cardMessage.contains("No Card Message")){
					System.out.println("Card Message does not Existing");
					System.out.println("You could Update the Card Message ......");
				}
				else {
					System.out.println("Card Message Existing ---------------------- Passed");
				}
		    }
			catch(Exception e){
				e.printStackTrace();
			}
    }

    public void checkShipAddress() throws Exception {
           try{
				String address = driver.findElement(By.cssSelector("div.confirmship")).getText();
				String[] shippingAddress = address.split("\n");
				System.out.println("Name:         "+shippingAddress[1]);
				System.out.println("Address:      "+shippingAddress[2]+" "+shippingAddress[3]);
				System.out.println("Phone Number: "+shippingAddress[4]);
				if(address.contains("St."))
				{
					System.out.println("Recipient Info Existing -------------------- Passed");
				}
				else 
				{
					System.out.println("Recipient Info Not Matched - Failed");
				}
		    }
	
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("error occured, Recipient Info DOES NOT EXISTING - Failed");
				driver.quit();
			}
    }

    public void checkDeliveryDate() throws Exception {
           try{
				String deliveryDate = driver.findElement(By.xpath("//div[@id='Confirm-Wrap']/div[5]/div[2]/div[2]/div[2]/div[2]/div[2]/div[2]/div")).getText();
				System.out.println("Delivery Date: "+deliveryDate.replace("\n", "").substring(63,73));
				if(deliveryDate.contains("2018")){System.out.println("Delivery Date Existing  -------------------- Passed");}
				else {System.out.println("Delivery Date does not existing - Failed");}
		    }
			catch(Exception e){
				e.printStackTrace();
				System.out.println("error occured, DELIVERY DATE DOES NOT EXISTING - Failed");
				driver.quit();
			}
    }
   
    public String checkCardMessageWithoutInput(String newCardMessage, String Comment) throws Exception {
    	try{
			System.out.println("Continue Modify My Order.......");
			driver.findElement(By.id("modifyCardMessage")).click();
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.visibilityOfElementLocated((By.id("mod_window"))));
			
			WebElement iframeSwitch = driver.findElement(By.xpath("//iframe[contains(@id, 'mod_window')and contains (@class, 'mod-modal')]"));
			driver.switchTo().frame(iframeSwitch);	
			
			WebElement MessageArea = wait.until(ExpectedConditions.visibilityOfElementLocated((By.id("editgiftmessage"))));
			
			if (Comment.contains("Validate Failed Error"))
			{
				driver.findElement(By.cssSelector("button.btn-continue.bg-brandColor-primary")).click();
				String cardMessageWarning = driver.findElement(By.className("warning-dialogue ")).getText();
				System.out.println("Warning: "+cardMessageWarning);
				Thread.sleep(3000);
				System.out.println("++++++++++ Update Card Message +++++++++++++++++");
				MessageArea.sendKeys(Keys.TAB);
				MessageArea.clear();
				MessageArea.sendKeys(newCardMessage);
				String message1 = MessageArea.getText();
				String characterCount1 =  driver.findElement(By.id("mod_character_count")).getText();
				System.out.println("New Mess  on Modify My Order: "+message1);
				System.out.println("New Count on Modify My Order: "+characterCount1);
				System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");
			}
    	}
    	catch(Exception e){
			e.printStackTrace();
			System.out.println("error occured, on updateMessage section");
			if(Comment =="") Comment = e.toString(); else Comment = Comment +"; "+e.toString();
			//driver.quit();
		}
    	return Comment;
    }
	
    public String checkRecipientWithoutInput(String Comment) throws Exception {
    	
    	try{	    
		    String NewFirstName = " ";
			String NewLastName = " ";
			String NewPhoneNum = "";
			String NewLocation = getRowData()[24];
			String NewCompany = " ";
			String NewAdd1 = " ";
			String NewAdd2 = " ";
		    String NewCity =  " ";
		    String NewState = getRowData()[30];
		    String NewZip = "";
	
		
		    driver.findElement(By.xpath("//div[contains(text(), 'Update Recipient Details')]")).click();
		    Thread.sleep(3000);

		  
		    
		    System.out.println("++++++++++ Update New Recipient Info +++++++++++");
		    WebElement FirstNameField = driver.findElement(By.id("firstName")); 
	        FirstNameField.click();
	        FirstNameField.clear();
	        FirstNameField.sendKeys(NewFirstName);
		    if (NewFirstName.contains("")) {NewFirstName = "Empty";}

	        WebElement LastNameField = driver.findElement(By.id("lastName"));
	        LastNameField.click();
	        LastNameField.clear();
	        LastNameField.sendKeys(NewLastName);
		    if (NewLastName.contains("")) {NewLastName = "Empty";}

	        WebElement PhoneNumberField = driver.findElement(By.id("phoneNumber"));
	        PhoneNumberField.click();
	        PhoneNumberField.clear();
	        PhoneNumberField.sendKeys(NewPhoneNum);
		    if (NewPhoneNum.contains("")) {NewPhoneNum = "Empty";}

	        System.out.println("Select New Location Type: " +NewLocation);
	        driver.findElement(By.id("locationType")).click();
	        new Select(driver.findElement(By.id("locationType"))).selectByVisibleText(NewLocation);
	        
	        WebElement CompanyField = driver.findElement(By.id("company"));
	        CompanyField.click();
	        CompanyField.clear();
	        CompanyField.sendKeys(NewCompany);
		    if (NewCompany.contains("")) {NewCompany = "Empty";}

	        WebElement AddressLine1Field = driver.findElement(By.id("addressLine1"));
		    AddressLine1Field.click();
		    AddressLine1Field.clear();
		    AddressLine1Field.sendKeys(NewAdd1);
		    if (NewAdd1.contains("")) {NewAdd1 = "Empty";}

	        WebElement AddressLine2Field = driver.findElement(By.id("addressLine2"));
		    AddressLine2Field.click();
		    AddressLine2Field.clear();
		    AddressLine2Field.sendKeys(NewAdd2);
		    if (NewAdd2.contains("")) {NewAdd2 = "Empty";}

	        WebElement CityField = driver.findElement(By.id("cityName"));
		    CityField.click();
		    CityField.clear();
		    CityField.sendKeys(NewCity);
		    if (NewCity.contains("")) {NewCity = "Empty";}
	     
	        System.out.println("Select New State:         " +NewState);
	        driver.findElement(By.id("state")).click();
	        new Select(driver.findElement(By.id("state"))).selectByValue(NewState);
	        
	        WebElement ZipCodeField = driver.findElement(By.id("zipCode"));
		    ZipCodeField.click();
		    ZipCodeField.clear();
		    ZipCodeField.sendKeys(NewZip);
		    if (NewZip.contains("")) {NewZip = "Empty";}
	        Thread.sleep(5000);

	        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");
	        if (Comment.contains("Validate Failed Error"))
	        {
	     	    System.out.println("Entered New First Name:  " +NewFirstName);
	     	    System.out.println("Entered New Last Name:   " +NewLastName);
	     	   	System.out.println("Entered New PhoneNumber: " +NewPhoneNum);
		        System.out.println("Entered New Company name:" +NewCompany);
		        System.out.println("Entered New Address 1:   " +NewAdd1);
		        System.out.println("Entered New address 2:   " +NewAdd2);
		        System.out.println("Entered New City:        " +NewCity);
		        System.out.println("Entered New zip:         " +NewZip);
		        
				driver.findElement(By.cssSelector("button.btn-continue.bg-brandColor-primary")).click();
		        Thread.sleep(10000);
		    	String warnMessage = driver.findElement(By.cssSelector("div.warning-dialogue")).getText();
		    	System.out.println("Error: " +warnMessage);
	
		        Thread.sleep(5000);
		        
	        }
    	}	
    	catch(Exception e){
			e.printStackTrace();
			System.out.println("error occured, on updateAddress section");
			if(Comment =="") Comment = e.toString(); else Comment = Comment +"; "+e.toString();
			//driver.quit();
		}
    	return Comment;   
    }

    public String checkDeliveryDateWithoutInput(String Comment) throws Exception {
    	try{       
	        String deliveryDate =  getRowData()[20];
	        String NewdeliveryDate[] =  getRowData()[32].split("/");
	        
	        String DeliveryDate[] = deliveryDate.split("/");
	        if(DeliveryDate[0].length() == 1) DeliveryDate[0] = "0"+ DeliveryDate[0];
	        if(DeliveryDate[1].length() == 1) DeliveryDate[1] = "0"+ DeliveryDate[1];
	        
	        String FormatDeliveryDate = DeliveryDate[0]+"-" +DeliveryDate[1]+"-2018";
		   //Delivery date update
		    driver.findElement(By.xpath("//div[contains(text(), 'Change Delivery Date')]")).click();
		    Thread.sleep(3000);
		    
		    String SelectedDate = driver.findElement(By.id("mod_selected_date")).getText();
		    System.out.println("Selected Delivery Date: " + SelectedDate);
		    
		    if(SelectedDate.equals(FormatDeliveryDate)) 
		    	{
		    		System.out.println("Original Delivery Date: "+FormatDeliveryDate);
		    	}
		    else 
		    	{
		    		System.out.println("Selected Delivery Date is NOT Matching " +FormatDeliveryDate);
		    	}
		    //Select new date
		    WebElement deldate = driver.findElement(By.xpath("//div[contains(text(), "+NewdeliveryDate[1]+")]"));
		    deldate.click();
		    
	    }
	    catch(Exception e){
			e.printStackTrace();
			System.out.println("error occured on updateDate section");
			if(Comment =="") Comment = e.toString(); else Comment = Comment +"; "+e.toString();
			//driver.quit();
		}
    	return Comment;      
    }
    
    
    public String updateMessage(String newCardMessage, String Comment) throws Exception {
    	try{
			driver.findElement(By.id("modifyCardMessage")).click();
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.visibilityOfElementLocated((By.id("mod_window"))));
			
			WebElement iframeSwitch = driver.findElement(By.xpath("//iframe[contains(@id, 'mod_window')and contains (@class, 'mod-modal')]"));
			driver.switchTo().frame(iframeSwitch);	
			
			WebElement MessageArea = wait.until(ExpectedConditions.visibilityOfElementLocated((By.id("editgiftmessage"))));
			String message = MessageArea.getText();
			String cardMessage = getRowData()[9];
			
			//if(cardMessage == "No") cardMessage = "";
			String characterCount =  driver.findElement(By.id("mod_character_count")).getText();
			System.out.println("Init Mess  on Modify My Order: "+message);
			System.out.println("Init Count on Modify My Order: "+characterCount);
			try
			{Assert.assertEquals(message, cardMessage);
			System.out.println("Card Message is matching with the initial card mesage entere while placing order");} 
			catch(Throwable t)
			{System.out.println("Gift message not matching with the initial message enetered during order creation");}
			
			System.out.println("Continue Modify My Order............");
			Thread.sleep(3000);
	//		driver.findElement(By.cssSelector("button.btn-continue.bg-brandColor-primary")).click();
	//		String warnMessage = driver.findElement(By.cssSelector("div.warning-dialogue")).getText();
	//		System.out.println("Warning: "+warnMessage);
			System.out.println("++++++++++ Update Card Message +++++++++++++++++");
			MessageArea.sendKeys(Keys.TAB);
			MessageArea.clear();
			MessageArea.sendKeys(newCardMessage);
			String message1 = MessageArea.getText();
			String characterCount1 =  driver.findElement(By.id("mod_character_count")).getText();
			System.out.println("New Mess  on Modify My Order: "+message1);
			System.out.println("New Count on Modify My Order: "+characterCount1);
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");

    	}	
    	catch(Exception e){
			e.printStackTrace();
			System.out.println("error occured, on updateMessage section");
			if(Comment =="") Comment = e.toString(); else Comment = Comment +"; "+e.toString();
			//driver.quit();
		}
    	return Comment;
    }
    
    
    public String updateAddress(String Comment) throws Exception {
    	try{
	    String firstName = getRowData()[10];
	    String lastName =  getRowData()[11];
//	    String locationType = getRowData()[12];
	    String company = getRowData()[13];
	    String phoneNumber = getRowData()[14];
	    String address1 = getRowData()[15];
	    String address2 = getRowData()[16];
	    String city =  getRowData()[17];
//	    String state =  getRowData()[18];
	    String zipcode =  getRowData()[19];
	    
	    String NewFirstName = getRowData()[22];
		String NewLastName = getRowData()[23];
		String NewPhoneNum = getRowData()[26];
		String NewLocation = getRowData()[24];
		String NewCompany = getRowData()[25];
		String NewAdd1 = getRowData()[27];
		String NewAdd2 = getRowData()[28];
	    String NewCity =  getRowData()[29];
	    String NewState = getRowData()[30];
	    String NewZip = getRowData()[31];
	   
	    
	    driver.findElement(By.xpath("//div[contains(text(), 'Update Recipient Details')]")).click();
	    Thread.sleep(3000);
	   
	    System.out.println("++++++++++ Update New Recipient Info +++++++++++++++++");
	    
	    WebElement FirstNameField = driver.findElement(By.id("firstName"));
	    String initFirst = FirstNameField.getAttribute("value"); 
	    if(firstName.equals(initFirst)) System.out.println("First Name Matched"); else System.out.println("First Name NOT Matched"); 
        System.out.println("Entered New First Name :" +NewFirstName);
        FirstNameField.click();
        FirstNameField.clear();
        FirstNameField.sendKeys(NewFirstName);
        
        WebElement LastNameField = driver.findElement(By.id("lastName"));
	    String initLast = LastNameField.getAttribute("value"); 
	    if(lastName.equals(initLast)) System.out.println("Last Name Matched"); else System.out.println("Last Name NOT Matched");
        System.out.println("Entered New Last Name :" +NewLastName);
        LastNameField.click();
        LastNameField.clear();
        LastNameField.sendKeys(NewLastName);
        
        
        WebElement PhoneNumberField = driver.findElement(By.id("phoneNumber"));
	    String initPhone = PhoneNumberField.getAttribute("value"); 
	    if(phoneNumber.equals(initPhone)) System.out.println("Phone nummber Matched"); else System.out.println("Phone nummber NOT Matched");
        System.out.println("Entered New PhoneNumber :" +NewPhoneNum);
        PhoneNumberField.click();
        PhoneNumberField.clear();
        PhoneNumberField.sendKeys(NewPhoneNum);
        
        System.out.println("Select New Location Type :" +NewLocation);
        driver.findElement(By.id("locationType")).click();
        new Select(driver.findElement(By.id("locationType"))).selectByVisibleText(NewLocation);
        
        WebElement CompanyField = driver.findElement(By.id("company"));
	    String initCompany = CompanyField.getAttribute("value"); 
	    if(company.equals(initCompany)) System.out.println("Company Name Matched");else System.out.println("Company NOT Matched");
        CompanyField.click();
        CompanyField.clear();
        CompanyField.sendKeys(NewCompany);
        System.out.println("Entered New Company name:" +NewCompany);
        
        WebElement AddressLine1Field = driver.findElement(By.id("addressLine1"));
	    String initAdd1 = AddressLine1Field.getAttribute("value"); 
	    if(address1.equals(initAdd1)) System.out.println("Address 1 Matched");else System.out.println("Address 1 NOT Matched");
	    AddressLine1Field.click();
	    AddressLine1Field.clear();
	    AddressLine1Field.sendKeys(NewAdd1);
        System.out.println("Entered New Address 1:" +NewAdd1);
        
        WebElement AddressLine2Field = driver.findElement(By.id("addressLine2"));
	    String initAdd2 = AddressLine2Field.getAttribute("value").trim(); System.out.println("initial add2:"+ initAdd2);
	    if(address2.equals(initAdd2)) System.out.println("Address 2 Matched");else System.out.println("Address 2 NOT Matched");
	    AddressLine2Field.click();
	    AddressLine2Field.clear();
	    AddressLine2Field.sendKeys(NewAdd2);
        System.out.println("Entered New address 2:" +NewAdd2);
        
        WebElement CityField = driver.findElement(By.id("cityName"));
	    String initCity = CityField.getAttribute("value"); 
	    if(city.equals(initCity)) System.out.println("City field Matched");else System.out.println("city NOT Matched");
	    CityField.click();
	    CityField.clear();
	    CityField.sendKeys(NewCity);
        System.out.println("Entered New City:" +NewCity);
        
        System.out.println("Select New State         :" +NewState);
        driver.findElement(By.id("state")).click();
        new Select(driver.findElement(By.id("state"))).selectByValue(NewState);
        
        WebElement ZipCodeField = driver.findElement(By.id("zipCode"));
	    String initZipCode = ZipCodeField.getAttribute("value"); 
	    if(zipcode.equals(initZipCode)) System.out.println("Zipcode field Matched");else System.out.println("zipcode NOT Matched");
	    ZipCodeField.click();
	    ZipCodeField.clear();
	    ZipCodeField.sendKeys(NewZip);
        System.out.println("Entered New zip:" +NewZip);
        
        System.out.println("------------------------------------------------------------------------------");
    	}	
    	catch(Exception e){
			e.printStackTrace();
			System.out.println("error occured, on updateAddress section");
			if(Comment =="") Comment = e.toString(); else Comment = Comment +"; "+e.toString();
			//driver.quit();
		}
    	return Comment;   
    }

    
    public String updateDate(String Comment) throws Exception {
    	try{       
	        String deliveryDate =  getRowData()[20];
	        String NewdeliveryDate[] =  getRowData()[32].split("/");
	        
	        String DeliveryDate[] = deliveryDate.split("/");
	        if(DeliveryDate[0].length() == 1) DeliveryDate[0] = "0"+ DeliveryDate[0];
	        if(DeliveryDate[1].length() == 1) DeliveryDate[1] = "0"+ DeliveryDate[1];
	        
	        String FormatDeliveryDate = DeliveryDate[0]+"-" +DeliveryDate[1]+"-2018";
		   //Delivery date update
		    driver.findElement(By.xpath("//div[contains(text(), 'Change Delivery Date')]")).click();
		    Thread.sleep(3000);
		    
		    String SelectedDate = driver.findElement(By.id("mod_selected_date")).getText();
		    System.out.println("Selected Delivery Date is - " + SelectedDate);
		    
		    if(SelectedDate.equals(FormatDeliveryDate)) System.out.println("Selected Delivery Date is Matching");
		    else System.out.println("Selected Delivery Date is NOT Matching");System.out.println(FormatDeliveryDate);
		    
		    //Select new date
		    WebElement deldate = driver.findElement(By.xpath("//div[contains(text(), "+NewdeliveryDate[1]+")]"));
		    deldate.click();
		    
		    
	    }
	    catch(Exception e){
			e.printStackTrace();
			System.out.println("error occured on updateDate section");
			if(Comment =="") Comment = e.toString(); else Comment = Comment +"; "+e.toString();
			//driver.quit();
		}
    	return Comment;      
    }

	
    public void reviewChanges() throws Exception {
           try{
        	   	String NewGiftMessage= getRowData()[21];
        	   	String NewFirstName = getRowData()[22];
	       		String NewLastName = getRowData()[23];
	       		String NewPhoneNum = getRowData()[26];
	       		String NewLocation = getRowData()[24];
	       		String NewCompany = getRowData()[25];
	       		String NewAdd1 = getRowData()[27];
//	       		String NewAdd2 = getRowData()[28];
	       	    String NewCity =  getRowData()[29];
	       	    String NewState = getRowData()[30];
	       	    String NewZip = getRowData()[31];
	       	    String NewdeliveryDate =  getRowData()[32];
	       	    
       	    
       		    //WebDriverWait wait = new WebDriverWait(driver, 60);
				//System.out.println("Step 9: Continue Modify My Order Again");
				driver.findElement(By.cssSelector("button.btn-continue.bg-brandColor-primary")).click();
				Thread.sleep(3000);
				//System.out.println("Step 10: Verify Updated Card Message/Addresses ");
				String updateMessage = driver.findElement(By.xpath("//div[2]/div[2]/div[2]/div/p")).getText();
				if(updateMessage.equals(NewGiftMessage)) System.out.println("Card Message on Reviw page: Matched with the entered Gift Message");
				else System.out.println("Card Message on Reviw page: NOT Matched with the entered Gift Message");
				
				String updateName = driver.findElement(By.xpath("//div[3]/div/p")).getText(); System.out.println("Recipient Name :"+updateName);
				if(updateName.equals(NewFirstName +" "+NewLastName)) System.out.println("Name on Reviw page: Matched with Name entered");
				else System.out.println("Name on Reviw page: NOT Matched with Name entered");
				
				String updatePhone = driver.findElement(By.xpath("//div[3]/div/p[2]")).getText();
				if(updatePhone.equals(NewPhoneNum)) System.out.println("Phone Number on Reviw page: Matched with Phone Number entered");
				else System.out.println("Phone Number on Reviw page: NOT Matched with Phone Number entered");
				
				String updateLocationType = driver.findElement(By.xpath("//p[3]")).getText();
				if(updateLocationType.equals(NewLocation)) System.out.println("Location on Reviw page: Matched with Location Type entered");
				else System.out.println("Location on Reviw page: NOT Matched with Location Type entered");
				
				String UpdatedCompany = driver.findElement(By.xpath("//p[4]")).getText();
				if(UpdatedCompany.equals(NewCompany)) System.out.println("Company on Reviw page: Matched with Company entered");
				else System.out.println("Company on Reviw page: NOT Matched with Company entered");
				 
				String updateAddressline1 = driver.findElement(By.xpath("//p[5]")).getText();
				if(updateAddressline1.equals(NewAdd1)) System.out.println("Address 1 on Reviw page: Matched with Address 1 entered");
				else System.out.println("Address 1 on Reviw page: NOT Matched with Address 1 entered");
				
				String updateAddressline2 = driver.findElement(By.xpath("//p[6]")).getText();
				
				String updateCity = driver.findElement(By.xpath("//p[7]")).getText();
				if(updateCity.equals(NewCity)) System.out.println("City on Reviw page: Matched with new city entered");
				else System.out.println("City on Reviw page: NOT Matched with new city entered");
				
				String updateState = driver.findElement(By.xpath("//p[8]")).getText();
				if(updateState.equals(NewState)) System.out.println("State on Reviw page: Matched with State entered");
				else System.out.println("State on Reviw page: NOT Matched with State entered");
				
				String updateZipcode = driver.findElement(By.xpath("//p[9]")).getText();
				if(updateZipcode.equals(NewZip)) System.out.println("Zipcode on Reviw page: Matched with Zipcode entered");
				else System.out.println("Zipcode on Reviw page: NOT matched with Zipcode entered");
				
				String updateDeliveryDate = driver.findElement(By.xpath("//div[4]/div/p")).getText();
				if(updateDeliveryDate.equals(NewdeliveryDate)) System.out.println("Deliverydate on Reviw page: Matched with new Deliverydate entered");
				else System.out.println("Deliverydate on Reviw page: NOT Matched with new Deliverydate entered");
				//----------------------------//
				System.out.println("-------------- Review Your Change --------------- ");
				System.out.println("Card Message   :"+updateMessage);
				System.out.println("Phone Number   :"+updatePhone);
				System.out.println("Location Type  :"+updateLocationType);
				System.out.println("Delivery Date  :"+updateDeliveryDate);
				System.out.println("Address 1	   :"+updateAddressline1);
				System.out.println("Address 2	   :"+updateAddressline2);
				System.out.println("City		   :"+updateCity);
				System.out.println("State		   :"+updateState);
				System.out.println("Zipcode		   :"+updateZipcode);
				System.out.println("---------------------------------------------------");
		    }
			catch(Exception e){
				e.printStackTrace();
				System.out.println("error occured, REVIEW UPDATED - Failed");
				driver.quit();
			}
    }

	public void submitChanges() throws Exception {
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, 60);
			driver.findElement(By.cssSelector("button.btn-submit.bg-brandColor-primary")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated((By.cssSelector("h1.txt_hdr-xl.txt_align-center"))));
			String thankYouMessage = driver.findElement(By.cssSelector("h1.txt_hdr-xl.txt_align-center")).getText();
			String thankYouMessage1 = driver.findElement(By.cssSelector("p.txt_base.txt_align-center")).getText();
			System.out.println(thankYouMessage);
			System.out.println(thankYouMessage1);
			wait.until(ExpectedConditions.visibilityOfElementLocated((By.linkText("Close"))));
			driver.findElement(By.linkText("Close")).click();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("error occured, Submit Changes Failed");
			driver.quit();
		}
	}
	
	
	public void validateInflightChanges() throws Exception {
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, 60);
			System.out.println("Step 12: Verify Pending Queue Message After Submit Change ");
			driver.switchTo().defaultContent();
			Thread.sleep(15000);
			driver.findElement(By.id("modifyCardMessage")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated((By.id("mod_window"))));
			WebElement iframeSwitch = driver.findElement(By.xpath("//iframe[contains(@id, 'mod_window')and contains (@class, 'mod-modal')]"));
			driver.switchTo().frame(iframeSwitch);						
			wait.until(ExpectedConditions.visibilityOfElementLocated((By.cssSelector("p.txt_base.txt_align-center"))));
			String pendingQueueMessage = driver.findElement(By.cssSelector("h1.txt_hdr-xl.txt_align-center")).getText();
			String pendingQueueMessage1 = driver.findElement(By.cssSelector("p.txt_base.txt_align-center")).getText();
			System.out.println(pendingQueueMessage);
			System.out.println(pendingQueueMessage1);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("error occured, InflightChanges Failed");
			driver.quit();
		}
	}
	
    
	public void deleteCookies() throws InterruptedException {
    	driver.manage().deleteAllCookies();
    }

    
	public void setFramework() throws Exception {
		//get current date in proper format
		String datewithtime = formatter.format(currentDate.getTime());
		//String[] datetime = datewithtime.split(" ");
		String datenow = datewithtime.split(" ")[0].replace("/", "_");	
		//String TimeNow = datewithtime.split(" ")[1].replace("/", "_");	
		new File(FilePath+datenow).mkdirs();
		ResultPath = FilePath+datenow;
		writecsvHeader(35);
	}
	
	
	public ArrayList<String> readCsv(String csvfile) throws Exception {
		ArrayList<String> csvRows = new ArrayList<String>();
	    BufferedReader br = new BufferedReader(new FileReader(csvfile));
	    String line = null;

	    while ((line = br.readLine()) != null) { csvRows.add(line.toString());  }
	    br.close();
	    return csvRows;
	}
	
	
	public void writeCsv(String csvfile, String[] Data) throws Exception {
		FileWriter fileWriter = new FileWriter(csvfile,true);
	
		for(int i=0;i<Data.length;i++)
		{
			fileWriter.append(Data[i]);fileWriter.append(",");
		}
		fileWriter.append("\n");	
		
		fileWriter.close();
	}	
	
	public void writecsvHeader(int size) throws Exception {

		String[] Header = new String[size];
		Header[0]="TestCase No";
		Header[1]="Test Case";
		Header[2]="RunStatus";
		Header[3]="Brand";
		Header[4]="Usertype";
		Header[5]="User Id";
		Header[6]="Pwd";
		Header[7]="Expected MMO Status";
		Header[8]="OrderNumber";
		
		Header[9]="New CardMessage";
		Header[10]="New FirstName";
		Header[11]="New LastName";
		Header[12]="New Location";
		Header[13]="New Company";
		Header[14]="New PhoneNumber";
		Header[15]="New Add1";
		Header[16]="New Add2";
		Header[17]="New City";
		Header[18]="New State";
		Header[19]="New Zip";
		Header[20]="New Deliverydate";
		Header[21]="Result";
		Header[22]="Comments";
		Header[23] = "Defect";
		writeCsv(ResultPath+"/Result.csv",Header);
		
	}
	
	
	public void resetValues() {
		for(int j=0;j<DatatoWrite.length;j++){DatatoWrite[j]="";}	
	}
	
	
	public void datatowrite(int iteration) {
		DatatoWrite[0] = Integer.toString(iteration);
		DatatoWrite[1] = getRowData()[1];   // TC no
		DatatoWrite[2] = getRowData()[2];   //Test Case
	    DatatoWrite[3] = getRowData()[3];   // Run Status
	    DatatoWrite[4] = getRowData()[4];   // Brand
	    DatatoWrite[5] = getRowData()[5];   // User Type
	    DatatoWrite[6] = getRowData()[6];   // User Name
	    DatatoWrite[7] = getRowData()[7];   // Pwd
	    DatatoWrite[8] = getRowData()[8];   // OrderNumber
	    DatatoWrite[9] = getRowData()[21];  // CardMessage
	    DatatoWrite[10] = getRowData()[22]; // FirstName
	    DatatoWrite[11] = getRowData()[23]; // LastName
	    DatatoWrite[12] = getRowData()[24]; // Location
	    DatatoWrite[13] = getRowData()[25]; // Company
	    DatatoWrite[14] = getRowData()[26]; // PhoneNumber
	    DatatoWrite[15] = getRowData()[27]; // Add 1
	    DatatoWrite[16] = getRowData()[28]; // Add 2
	    DatatoWrite[17] = getRowData()[29]; // City
	    DatatoWrite[18] = getRowData()[30];   // State 
	    DatatoWrite[19] = getRowData()[31]; // Zip
	    DatatoWrite[20] = getRowData()[32]; //New Delivery date
	    if(getRowData()[31].length()<5)
		{
			if(getRowData()[31].length() == 4) DatatoWrite[10] = "0"+getRowData()[31];
			if(getRowData()[31].length() == 3) DatatoWrite[10] = "00"+getRowData()[31];
		}
	    
}

	
	public void takeScreenshot(String iteration, String filename) throws Exception {
		System.out.println("capturing Screenshot");
		//highlightElement(driver.findElement(By.id("placeOrderBtnStatic")));
		driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);//capture the webpage
		FileUtils.copyFile(scrFile, new File(ResultPath+"/"+iteration+" "+filename+".png"));
	}
	
	
	private String[] Rowdata;
	
	public String[] getRowData() {
		return this.Rowdata; 
	}
	
	
	public void setRowData(String[] arrValue) {
		this.Rowdata = arrValue;
	}
	
}
	/*	@After
		public void tearDown() throws Exception 
		{
			String verificationErrorString = verificationErrors.toString();	
			if (!"".equals(verificationErrorString)){fail(verificationErrorString);}
		}*/

