package modifymyorder; 
//Created By Tagore 1/12/18
import java.io.FileWriter;
//import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import jxl.write.DateTime;
import java.io.ByteArrayInputStream;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;

public class modifyorder 
{
	//variables declaration goes here
	public WebDriver driver;
	
	@Before
	public void setUp() throws Exception 
		{
		
		}

	//Start the script 		
	@Test
	public void FPT_ModifyOrder() throws Exception 
		{           
		           try
					{
		        	   

						String[][] order = {{"W00995422678505","11355"},{"W00995422675557","11355"},{"W00995422630853","11514"}};
						//Calling browser and accessing 18F URL
		        	
						callBrowser();
						deleteCookies();
						System.out.println("---------------------------------- Test Start --------------------------------");
						System.out.println("Step 1: Go To the Home Page ");
						driver.get("https://akamai.1800flowers-uat.net");
						Thread.sleep(1000);
					    //WebDriverWait wait = new WebDriverWait(driver, 60);
						//goto_Orderdetailspage_Registered("W00995422667594","mmotest@tag.com","tag@123");
						
						String userType = "GU";
						//String userType = "RU";
						if (userType == "GU")
						{
							System.out.println("Step 2: Track Order by User Type " +userType);
							System.out.println("Step 3: Enter Order#:  "+order[0][0]+ " Zip: "+order[0][1]);
							goto_TrackOrderPage_Guest(order[0][0],order[0][1]);
						}
						else
						{
							System.out.println("Step 2: Track Order By User Type " +userType);
							System.out.println("Step 3: Order "+order[0][0]+ " Email "+order[0][1]+ " Password ");
						}
						
						System.out.println("Step 4: View/Modify Order Details ");
						goto_OrderDetails(userType,order[0][0]);
						
						modifyOrder_False();
//						checkCardMessage();
//						checkDeliveryDate();
//						checkShipAddress();
					
						System.out.println("Step 5: Add or Update Message ");
						updateMessage();
						System.out.println("Step 8: Update Recipient Information ");
						updateAddress();
						
						review();
						driver.quit();
						System.out.println("---------------------------------- Test End ----------------------------------");
						
//						driver.findElement(By.id("modifyCardMessage")).click();
//						
//						wait.until(ExpectedConditions.visibilityOfElementLocated((By.id("mod_window"))));
//						
//						
//						WebElement iframeSwitch = driver.findElement(By.xpath("//iframe[contains(@id, 'mod_window')and contains (@class, 'mod-modal')]"));
//						driver.switchTo().frame(iframeSwitch);						
//						WebElement MessageArea = wait.until(ExpectedConditions.visibilityOfElementLocated((By.id("editgiftmessage"))));
//						//WebElement MessageArea = driver.findElement(By.cssSelector("tesxtarea[id=\"editgiftmessage\"]"));
//						
//						MessageArea.sendKeys(Keys.TAB);
//						MessageArea.clear();
//						MessageArea.sendKeys("Testing Automation");
//						
//						//driver.switchTo().defaultContent();
//						driver.findElement(By.xpath("//div/div[2]/div/div/span")).click();
//						
//	
//					    driver.findElement(By.id("firstName")).click();
//					    driver.findElement(By.id("firstName")).clear();
//					    driver.findElement(By.id("firstName")).sendKeys("tagore");
//					    driver.findElement(By.id("lastName")).click();
//					    driver.findElement(By.id("lastName")).clear();
//					    driver.findElement(By.id("lastName")).sendKeys("gupt");
//					    driver.findElement(By.id("phoneNumber")).click();
//					    driver.findElement(By.id("phoneNumber")).clear();
//					    driver.findElement(By.id("phoneNumber")).sendKeys("3453453454"); 	
//					    driver.findElement(By.id("locationType")).click();
//					    new Select(driver.findElement(By.id("locationType"))).selectByVisibleText("Business");
//					    driver.findElement(By.xpath("//option[@value='2']")).click();
//					    driver.findElement(By.id("company")).click();
//					    driver.findElement(By.id("company")).clear();
//					    driver.findElement(By.id("company")).sendKeys("test");
//					    
//					    driver.findElement(By.xpath("//div[@id='mod_ajaxContent']/div[2]/div/div/button[2]")).click();
//					    driver.findElement(By.xpath("//div[@id='mod_ajaxContent']/div[2]/div[2]/button[2]")).click();
//					    
//					    wait.until(ExpectedConditions.visibilityOfElementLocated((By.linkText("Close"))));
//					    driver.findElement(By.linkText("Close")).click();
//						driver.switchTo().defaultContent();
//						driver.quit();
//						driver = null;
				    }
			
					catch(Exception e)
					{
						e.printStackTrace();
						System.out.println("error occured");
						driver.quit();
					}
		}
	
	//@Test
	public void modifyOrder_False() throws Exception 
	{           
	           try
				{
					String ErrorMessage = driver.findElement(By.className("orderNotModifiableErr")).getText();
					System.out.println("Error:" + ErrorMessage);
					if(ErrorMessage.contains("This order may no longer be modified"))
					{
						System.out.println("No Orders can be modified - Passed");
					    driver.quit();
					    driver = null;
					    System.out.println("---------------------------------- Test End    --------------------------------");
					    System.exit(1);
					}
			    }
		
				catch(Exception e)
				{
					//e.printStackTrace();
					System.out.println("Get Order Details........................");
					//driver.quit();
				}
	}

	public void modifyOrder_Logo() throws Exception 
	{           
	           try
				{
					//Calling browser and accessing 18F URL


			    }
		
				catch(Exception e)
				{
					e.printStackTrace();
					System.out.println("error occured, While executing ModifyOrder_Logo");
					driver.quit();
				}
	}
	
    public void callBrowser()
    {

	//########################################################################################################################
	
	//#########################################################################################################################
	
	/*  ProfilesIni profile = new ProfilesIni();
		FirefoxProfile ffprofile = profile.getProfile("default");*/
	//	ffprofile.addExtension(new File("path/to/my/firebug.xpi"));							
	//	FirefoxProfile profile = new FirefoxProfile();
	//	ffprofile.setPreference("network.proxy.type", 1); //1 for manual proxy, 2 for auto config url
	//	ffprofile.setPreference("network.proxy.http", "cptmg");
		//ffprofile.setPreference("network.proxy.http_port", 4444);
	//System.setProperty("webdriver.gecko.driver","C:\\Selenium Testing 2\\Selenium component\\geckodriver.exe");
	//driver = new FirefoxDriver();
	
	//ProfilesIni profile = new ProfilesIni();
	//FirefoxProfile myprofile = profile.getProfile("default");
	//myprofile.setAcceptUntrustedCertificates(true);
	//myprofile.setAssumeUntrustedCertificateIssuer(false);
	//driver = new FirefoxDriver(myprofile);
	
	//=====================================================================
	// Fire Fox - MJS
	//=====================================================================
	/*
	// This is the current Firefox Driver - 7-10-17 //
	System.setProperty("webdriver.gecko.driver","C:\\Selenium Testing/Selenium component/geckodriver.exe");
	DesiredCapabilities capabilities = new DesiredCapabilities();
	capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
	driver = new FirefoxDriver(capabilities);
	*/
	//---------------------------------------------------------------------
	//=====================================================================
	// Chrome - MJS
	//=====================================================================
	//System.setProperty("webdriver.chrome.driver", "C:/Selenium Testing/Selenium component/chromedriver.exe");
	//driver = new ChromeDriver();
	// Fixes Disable Developer Extensions Bug
	/*ChromeOptions options = new ChromeOptions();
	options.addArguments("--disable-extensions");
	System.setProperty("webdriver.chrome.driver", "C:/Selenium Testing 2/chromedriver.exe");
	driver = new ChromeDriver(options);
	*/
	// */
	//=====================================================================
	//=====================================================================
					/*System.setProperty("webdriver.firefox.marionette","C:\\Selenium Testing 2\\geckodriver.exe");
				File pathToBinary = new File("C:\\Program Files\\Mozilla Firefox43\\firefox.exe");
				FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
				driver = new FirefoxDriver(ffBinary,ffprofile);	*/		
	
	//	
	//	driver = new FirefoxDriver(ffprofile);
	//########################################################################################################################
	System.setProperty("webdriver.chrome.driver", "C:/Users/loadtest/Documents/GitHub/myorder-selenium/modifymyorder/lib/chromedriver.exe");
	//System.setProperty("webdriver.chrome.driver", "C:/Selenium Testing 2/eclipse/myorder-selenium/modifymyorder/lib/chromedriver.exe");
	ChromeOptions  options = new ChromeOptions();
	options.addArguments("start-maximized");
	driver = new ChromeDriver(options);
	//#########################################################################################################################
	//DesiredCapabilities cap=DesiredCapabilities.safari();
	//cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
	//System.setProperty("webdriver.safari.driver","Safari driver path");							
	//driver = new SafariDriver();
	//##########################################################################################################################
	//	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);//set the default time out for browser
	//	driver.manage().window().maximize();
	
	//handle security popups		
	//		driver.get("www.google.com");
	//		WebDriverWait wait = new WebDriverWait(driver, 10);      
	//		Alert alert = wait.until(ExpectedConditions.alertIsPresent());     
	//		alert.authenticateUsing(new UserAndPassword("cnishant", ""));
	
    }

    public void goto_TrackOrderPage_Guest(String OrderNumber, String Zip_code)
    {
    	//Click on My Orders, enter order details and click submit
    	driver.findElement(By.linkText("Track Your Order")).click();
    	driver.findElement(By.id("label")).sendKeys(OrderNumber);
    	driver.findElement(By.id("label2")).sendKeys(Zip_code);
    	driver.findElement(By.cssSelector("input[alt=\"Submit\"]")).click();
    }

    public void goto_Orderdetailspage_Registered(String OrderNumber, String UserName, String Pwd)
    {
	 //Click on My Orders, enter order details and click submit
	 driver.findElement(By.linkText("Track Your Order")).click();
	 driver.findElement(By.id("logonId")).sendKeys(UserName);
	 driver.findElement(By.id("logonPassword")).sendKeys(Pwd);
	 driver.findElement(By.cssSelector("input[alt=\"Sign In\"]")).click();
	 //driver.findElement(By.cssSelector("img[alt=\"Order Details\"]")).click();	
    }

    public void goto_OrderDetails(String user_type, String OrderNumber)
    {
    		switch (user_type)
    		{
				case "GU": driver.findElement(By.cssSelector("img[alt=\"Order Details\"]")).click(); break;
				case "PU":
				case "RU":
					{
						WebElement OrderParent = driver.findElement(By.xpath("//div[contains(text(), '"+OrderNumber+"')]/following-sibling::div[2]/form/a/img"));
						///html/body/div[6]/div[4]/div[1]/div[3]/form/a/img
					    //WebElement NextSibling = OrderParent.findElement(By.cssSelector("alt=\"Order Details\"]"));
					    OrderParent.click();
					    break;
					}
    		}	
    }
    
    public void checkCardMessage() throws Exception 
    {           
           try
			{
				String ErrorMessage = driver.findElement(By.cssSelector("div.confirmmessage")).getText();
				System.out.println("Card Message: "+ErrorMessage.split("\n")[1]);
				if(ErrorMessage.contains("No Card Message"))
				{
					System.out.println("Card Message does not Existing");
					System.out.println("You could Update the Card Message ......");
				}
				else 
				{
					System.out.println("Card Message Existing ---------------------- Passed");
				}
		    }
			catch(Exception e)
			{
				e.printStackTrace();
			}
    }

    public void checkShipAddress() throws Exception 
    {           
           try
			{
				String ErrorMessage = driver.findElement(By.cssSelector("div.confirmship")).getText();
				String[] shippingAddress = ErrorMessage.split("\n");
				System.out.println("Name:             "+shippingAddress[1]);
				System.out.println("Shipping Address: "+shippingAddress[2]+" "+shippingAddress[3]);
				System.out.println("Phone Number:     "+shippingAddress[4]);
				if(ErrorMessage.contains("St.")){System.out.println("Recipient Info Existing -------------------- Passed");}
				else {System.out.println("Recipient Info Existing does not existing - Failed");}
		    }
	
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("error occured, Recipient Info DOES NOT EXISTING - Failed");
				driver.quit();
			}
    }

    public void checkDeliveryDate() throws Exception 
    {           
           try
			{
				String ErrorMessage = driver.findElement(By.xpath("//div[@id='Confirm-Wrap']/div[5]/div[2]/div[2]/div[2]/div[2]/div[2]/div[2]/div")).getText();
				System.out.println("Delivery Date: "+ErrorMessage.replace("\n", "").substring(63,73));
				if(ErrorMessage.contains("2018")){System.out.println("Delivery Date Existing  -------------------- Passed");}
				else {System.out.println("Delivery Date does not existing - Failed");}
		    }
	
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("error occured, DELIVERY DATE DOES NOT EXISTING - Failed");
				driver.quit();
			}
    }

    public void updateAddress() throws Exception 
    {           
           try
			{
        	    String firstName = "tangore";
        	    String lastName = "gupt";
        	    String phoneNumber = "3453453454";
        	    String locationType = "Business";
        	    String address1 = "1 old country rd";
        	    String company = "test";
        	    String address2 = "ste 500";
        	    String city = "Carle Place";
        	    String state = "NY";
        	    String zipcode = "11514";
        	    
        	    driver.findElement(By.xpath("//div[contains(text(), 'Update Recipient Details')]")).click();
        	    Thread.sleep(3000);
        	    String initFirst = driver.findElement(By.id("firstName")).getAttribute("value");
        	    String initLast = driver.findElement(By.id("lastName")).getAttribute("value");
        	    String initPhoneNumber = driver.findElement(By.id("phoneNumber")).getAttribute("value");
        	    String initAddress1 = driver.findElement(By.id("addressLine1")).getAttribute("value");
        	    String initAddress2 = driver.findElement(By.id("addressLine2")).getAttribute("value");
        	    String initCity = driver.findElement(By.id("cityName")).getAttribute("value");
        	    String initState = driver.findElement(By.id("state")).getAttribute("value");
        	    String initZipcode = driver.findElement(By.id("zipCode")).getAttribute("value");
        	    
         	    System.out.println("++++++++++ Verify Old Recipient Info +++++++++++++++++");
        	    System.out.println("Init First Name:    "+initFirst);
        	    System.out.println("Init Last  Name:    "+initLast);
        	    System.out.println("Init Phone Number:  "+initPhoneNumber);
        	    System.out.println("Init Address1:      "+initAddress1);
        	    System.out.println("Init Address2:      "+initAddress2);
        	    System.out.println("Init City:          "+initCity);
        	    System.out.println("Init State:         "+initState);
        	    System.out.println("Init Zipcode:       "+initZipcode);
        	    
        	    System.out.println("++++++++++ Update New Recipient Info +++++++++++++++++");
                System.out.println("Enter New First Name     :" +firstName);
                driver.findElement(By.id("firstName")).click();
                driver.findElement(By.id("firstName")).clear();
                driver.findElement(By.id("firstName")).sendKeys(firstName);
                
                System.out.println( "Enter New Last  Name     :" +lastName);
                driver.findElement(By.id("lastName")).click();
                driver.findElement(By.id("lastName")).clear();
                driver.findElement(By.id("lastName")).sendKeys(lastName);
                
                System.out.println( "Enter New Phone Number   :"  +phoneNumber);
                driver.findElement(By.id("phoneNumber")).click();
                driver.findElement(By.id("phoneNumber")).clear();
                driver.findElement(By.id("phoneNumber")).sendKeys(phoneNumber);
                
                System.out.println("Select New Location Type :" +locationType);
                driver.findElement(By.id("locationType")).click();
                new Select(driver.findElement(By.id("locationType"))).selectByVisibleText(locationType);
                
                System.out.println("Enter New Company        :" +company);
                driver.findElement(By.xpath("//option[@value='2']")).click();
                driver.findElement(By.id("company")).click();
                driver.findElement(By.id("company")).clear();
                driver.findElement(By.id("company")).sendKeys(company);
        
                System.out.println("Enter New Adreess1       :"+address1);
                driver.findElement(By.id("addressLine1")).click();
                driver.findElement(By.id("addressLine1")).clear();
                driver.findElement(By.id("addressLine1")).sendKeys(address1);
                
                System.out.println("Enter New Adreess2       :" +address2);
                driver.findElement(By.id("addressLine2")).click();
                driver.findElement(By.id("addressLine2")).clear();
                driver.findElement(By.id("addressLine2")).sendKeys(address2);
                
                System.out.println("Enter New City           :" +city);
                driver.findElement(By.id("cityName")).click();
                driver.findElement(By.id("cityName")).clear();
                driver.findElement(By.id("cityName")).sendKeys(city); 
                
                System.out.println("Select New State         :" +state);
                driver.findElement(By.id("state")).click();
                new Select(driver.findElement(By.id("state"))).selectByValue(state);
                
                System.out.println("Enter New Zipcode        :"  +zipcode);
                driver.findElement(By.id("zipCode")).click();
                driver.findElement(By.id("zipCode")).clear();
                driver.findElement(By.id("zipCode")).sendKeys(zipcode); 
		    }
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("error occured, Update Addresss - Failed");
				driver.quit();
			}
    }

    public void updateMessage() throws Exception 
    {           
           try
			{
				driver.findElement(By.id("modifyCardMessage")).click();
				WebDriverWait wait = new WebDriverWait(driver, 60);
				wait.until(ExpectedConditions.visibilityOfElementLocated((By.id("mod_window"))));
				
				WebElement iframeSwitch = driver.findElement(By.xpath("//iframe[contains(@id, 'mod_window')and contains (@class, 'mod-modal')]"));
				driver.switchTo().frame(iframeSwitch);						
				WebElement MessageArea = wait.until(ExpectedConditions.visibilityOfElementLocated((By.id("editgiftmessage"))));
				String message = MessageArea.getText();
				String characterCount =  driver.findElement(By.id("mod_character_count")).getText();
				System.out.println("Init Mess  on Modify My Order: "+message);
				System.out.println("Init Count on Modify My Order: "+characterCount);
				
				System.out.println("Step 6: Continue Modify My Order");
				Thread.sleep(3000);
				driver.findElement(By.cssSelector("button.btn-continue.bg-brandColor-primary")).click();
				String warnMessage = driver.findElement(By.cssSelector("div.warning-dialogue")).getText();
				System.out.println("Warning: "+warnMessage);

				System.out.println("Step 7: Update New Card Message");
				MessageArea.sendKeys(Keys.TAB);
				MessageArea.clear();
				String newCardMessage = "Test Automation 2000000000000000000000";
				MessageArea.sendKeys(newCardMessage);
				String message1 = MessageArea.getText();
				String characterCount1 =  driver.findElement(By.id("mod_character_count")).getText();
				System.out.println("New Mess  on Modify My Order: "+message1);
				System.out.println("New Count on Modify My Order: "+characterCount1);
				
		    }
	
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("error occured, Update Card Message - Failed");
				driver.quit();
			}
    }
    
    
    public void updateDate() throws Exception 
    {           
           try
			{
        	   //Delivery date update
		    }
	
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("error occured, Update Delivery Date - Failed");
				driver.quit();
			}
    }

    public void review() throws Exception 
    {           
           try
			{
       		    WebDriverWait wait = new WebDriverWait(driver, 60);
				System.out.println("Step 8: Continue Modify My Order Again");
				driver.findElement(By.cssSelector("button.btn-continue.bg-brandColor-primary")).click();
				Thread.sleep(3000);
				System.out.println("Step 9: Verify Updated Card Message/Addresses ");
				String updateMessage = driver.findElement(By.xpath("//div[2]/div[2]/div[2]/div/p")).getText();
				String updateName = driver.findElement(By.xpath("//div[3]/div/p")).getText();
				String updatePhone = driver.findElement(By.xpath("//p[2]")).getText();
				String updateLocationType = driver.findElement(By.xpath("//p[3]")).getText();
				String updateAddressline1 = driver.findElement(By.xpath("//p[5]")).getText();
				String updateAddressline2 = driver.findElement(By.xpath("//p[6]")).getText();
				String updateCity = driver.findElement(By.xpath("//p[7]")).getText();
				String updateState = driver.findElement(By.xpath("//p[8]")).getText();
				String updateZipcode = driver.findElement(By.xpath("//p[9]")).getText();
				String updateDeliveryDate = driver.findElement(By.xpath("//div[4]/div/p")).getText();
				System.out.println("-------------- Review Your Change --------------- ");
				System.out.println("Card Message   :"+updateMessage);
				System.out.println("Recipient Name :"+updateName);
				System.out.println("Phone Number   :"+updatePhone);
				System.out.println("Location Type  :"+updateLocationType);
				System.out.println("Delivery Date  :"+updateDeliveryDate);
				System.out.println("Address 1	   :"+updateAddressline1);
				System.out.println("Address 2	   :"+updateAddressline2);
				System.out.println("City		   :"+updateCity);
				System.out.println("State		   :"+updateState);
				System.out.println("Zipcode		   :"+updateZipcode);
				System.out.println("Step 10: Submit Change ");
				driver.findElement(By.cssSelector("button.btn-submit.bg-brandColor-primary")).click();
				wait.until(ExpectedConditions.visibilityOfElementLocated((By.cssSelector("h1.txt_hdr-xl.txt_align-center"))));
				String thankYouMessage = driver.findElement(By.cssSelector("h1.txt_hdr-xl.txt_align-center")).getText();
				String thankYouMessage1 = driver.findElement(By.cssSelector("p.txt_base.txt_align-center")).getText();
				System.out.println(thankYouMessage);
				System.out.println(thankYouMessage1);
				wait.until(ExpectedConditions.visibilityOfElementLocated((By.linkText("Close"))));
				driver.findElement(By.linkText("Close")).click();
				System.out.println("Step 11: Verify Pending Queue Message After Submit Change ");
				driver.switchTo().defaultContent();
				Thread.sleep(15000);
				driver.findElement(By.id("modifyCardMessage")).click();
				wait.until(ExpectedConditions.visibilityOfElementLocated((By.id("mod_window"))));
				WebElement iframeSwitch = driver.findElement(By.xpath("//iframe[contains(@id, 'mod_window')and contains (@class, 'mod-modal')]"));
				driver.switchTo().frame(iframeSwitch);						
				wait.until(ExpectedConditions.visibilityOfElementLocated((By.cssSelector("p.txt_base.txt_align-center"))));
				String pendingQueueMessage = driver.findElement(By.cssSelector("p.txt_base.txt_align-center")).getText();
				System.out.println(pendingQueueMessage);
		    }
	
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("error occured, REVIEW UPDATED - Failed");
				driver.quit();
			}
    }
    

    public void deleteCookies() throws InterruptedException
    {
    	driver.manage().deleteAllCookies();
    }

}
	/*	@After
		public void tearDown() throws Exception 
		{
			String verificationErrorString = verificationErrors.toString();	
			if (!"".equals(verificationErrorString)){fail(verificationErrorString);}
		}*/

