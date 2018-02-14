package modifymyorder; 
//Created By Tagore 1/12/18
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import jxl.Cell;
import jxl.CellView;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import java.io.ByteArrayInputStream;

import javax.imageio.ImageIO;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class modifyorder 
{
	//variables declaration goes here
	public WebDriver driver;
	public String timeinsec;
	public Calendar currentDate = Calendar.getInstance();
	public SimpleDateFormat formatter=  new SimpleDateFormat("yyyy_MMM_dd HH_mm_ss");
	public String[] DatatoWrite = new String[30];
	ArrayList<String> ReadCsv = new ArrayList<String>();
	String FilePath = "C:/Selenium Testing 2/eclipse/myorder-selenium/";
	String ResultPath;
	
	
	@Before
	public void setUp() throws Exception 
		{
			setFramework();
			ReadCsv = readCsv(FilePath+"ModifyMyOrder.csv");
		
		}

	//Start the script 		
	@Test
	public void FPT_ModifyOrder() throws Exception 
		{           
		   for(int i=1;i<=1;i++)
		   {
			   
				try 
				{	
					setRowData(ReadCsv.get(i).split(","));
				
					if(getRowData()[2].equals("Done"))	{System.out.println("Test"+i +" Done, skipping");continue;}
					datatowrite(i);
	        		resetValues();
					//Calling browser and accessing 18F URL
	        	
					callBrowser();
					deleteCookies();
					driver.get("https://akamai.1800flowers-uat.net");Thread.sleep(1000);
					
					///************Read Excel**************///
					String Brand = getRowData()[2];
					String UserType = getRowData()[3];
					String UserName = getRowData()[4];
					String Pwd = getRowData()[5];
					String OrderNumber = getRowData()[6];
					String CardMessage = getRowData()[7];
					String DeliveryDate = getRowData()[18];
					String Zip = getRowData()[17];
					///================End of excel code=====///
					
					
				    WebDriverWait wait = new WebDriverWait(driver, 60);
				    if (UserType == "RU"||UserType == "PU"){goto_Orderdetailspage_Registered(OrderNumber,UserName, Pwd);}
				    else{goto_TrackOrderPage_Guest(OrderNumber,Zip);}

					goto_OrderDetails(UserType, OrderNumber);
					//modifyOrder_False();
					//checkCardMessage();
					//checkDeliveryDate();
					//checkShipAddress();
					//updateMessage();
					updateAddress();
					driver.quit();
					
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
				
		   }
	
					
		
	
	//@Test
	public void modifyOrder_False() throws Exception 
	{           
	           try
				{
					String ErrorMessage = driver.findElement(By.className("orderNotModifiableErr")).getText();
					System.out.println("Error message:" + ErrorMessage);
					if(ErrorMessage.contains("This order may no longer be modified"))
					{
						System.out.println("Order not modifiable - Passed");
					    driver.quit();
					    driver = null;
					    System.exit(1);
					}
					else 
					{
						System.out.println("Should not Modify Past order - Failed");	
					}
			    }
		
				catch(Exception e)
				{
					//e.printStackTrace();
					System.out.println("View/Modify Order Dtails........................");
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
	//System.setProperty("webdriver.chrome.driver", "C:/Users/loadtest/Documents/GitHub/myorder-selenium/modifymyorder/lib/chromedriver.exe");
	System.setProperty("webdriver.chrome.driver", "C:/Selenium Testing 2/eclipse/myorder-selenium/modifymyorder/lib/chromedriver.exe");
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
				System.out.println("Car Message: "+ErrorMessage.split("\n")[1]);
				if(ErrorMessage.contains("test"))
				{
					System.out.println("Card Message Existing ---------------------- Passed");
				}
				else 
				{
					System.out.println("Card Message does not Existing");
					System.out.println("You could Update the Card Message ......");
				}
		    }
			catch(Exception e)
			{
				e.printStackTrace();
				//System.out.println("You could Update the Card Message ......");
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
				// Shipping Address:::::::::
        	   driver.findElement(By.id("modifyCardMessage")).click();
        	   WebDriverWait wait = new WebDriverWait(driver, 60);
        	   wait.until(ExpectedConditions.visibilityOfElementLocated((By.id("mod_window"))));
        	   WebElement iframeSwitch = driver.findElement(By.xpath("//iframe[contains(@id, 'mod_window')and contains (@class, 'mod-modal')]"));
				driver.switchTo().frame(iframeSwitch);	
        	   driver.findElement(By.xpath("//div[contains(text(), 'Update Recipient Details')]")).click();
        	   
        	   
        	   driver.findElement(By.id("firstName")).click();
			    driver.findElement(By.id("firstName")).clear();
			    driver.findElement(By.id("firstName")).sendKeys("tagore");
			    driver.findElement(By.id("lastName")).click();
			    driver.findElement(By.id("lastName")).clear();
			    driver.findElement(By.id("lastName")).sendKeys("gupt");
			    driver.findElement(By.id("phoneNumber")).click();
			    driver.findElement(By.id("phoneNumber")).clear();
			    driver.findElement(By.id("phoneNumber")).sendKeys("3453453454"); 	
			    driver.findElement(By.id("locationType")).click();
			    new Select(driver.findElement(By.id("locationType"))).selectByVisibleText("Business");
			    driver.findElement(By.xpath("//option[@value='2']")).click();
			    driver.findElement(By.id("company")).click();
			    driver.findElement(By.id("company")).clear();
			    driver.findElement(By.id("company")).sendKeys("test");
			    
			    driver.findElement(By.id("addressLine1")).click();
			    driver.findElement(By.id("addressLine1")).clear();
			    driver.findElement(By.id("addressLine1")).sendKeys("1 old country rd");
			    driver.findElement(By.id("addressLine2")).click();
			    driver.findElement(By.id("addressLine2")).clear();
			    driver.findElement(By.id("addressLine2")).sendKeys("ste 500");
			    driver.findElement(By.id("cityName")).click();
			    driver.findElement(By.id("cityName")).clear();
			    driver.findElement(By.id("cityName")).sendKeys("Carle Place"); 
				
			    driver.findElement(By.id("state")).click();
			    new Select(driver.findElement(By.id("state"))).selectByValue("New York");
			    driver.findElement(By.id("zipCode")).click();
			    driver.findElement(By.id("zipCode")).clear();
			    driver.findElement(By.id("zipCode")).sendKeys("11514"); 
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
				Thread.sleep(15000);
				driver.findElement(By.cssSelector("button.btn-cancel.bg-black")).click();
				driver.findElement(By.id("modifyCardMessage")).click();
				Thread.sleep(10000);
				String message = driver.findElement(By.id("mod_character_count")).getText();
				System.out.println(message);
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
				String ErrorMessage = driver.findElement(By.xpath("//div[@id='Confirm-Wrap']/div[6]/div[2]/div[2]/div[2]/div[2]/div[2]/div[2]/div")).getText();
				System.out.println("Error message:" + ErrorMessage);
				if(ErrorMessage.contains("St.")){System.out.println("Shipping Address Existing - Passed");}
				else {System.out.println("Shipping Address does not existing - Failed");}
		    }
	
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("error occured, SHIPPING ADDRESS DOES NOT EXISTING - Failed");
				driver.quit();
			}
    }

    public void review() throws Exception 
    {           
           try
			{
				String ErrorMessage = driver.findElement(By.cssSelector("div.confirmship")).getText();
				System.out.println("Error message:" + ErrorMessage);
				if(ErrorMessage.contains("St.")){System.out.println("Shipping Address Existing - Passed");}
				else {System.out.println("Shipping Address does not existing - Failed");}
		    }
	
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("error occured, SHIPPING ADDRESS DOES NOT EXISTING - Failed");
				driver.quit();
			}
    }

    public void deleteCookies() throws InterruptedException
    {
    	driver.manage().deleteAllCookies();
    }

    public void setFramework() throws Exception
	{
		//get current date in proper format
		String datewithtime = formatter.format(currentDate.getTime());
		//String[] datetime = datewithtime.split(" ");
		String datenow = datewithtime.split(" ")[0].replace("/", "_");	
		new File(FilePath+datenow).mkdirs();
		ResultPath = FilePath+datenow;
		writecsvHeader(20);
	}
	
	public ArrayList<String> readCsv(String csvfile) throws Exception
	{
		ArrayList<String> csvRows = new ArrayList<String>();
		    BufferedReader br = new BufferedReader(new FileReader(csvfile));
		    String line = null;
	
		    while ((line = br.readLine()) != null) { csvRows.add(line.toString());  }
		    br.close();
		    return csvRows;
	}
	
	public void writeCsv(String csvfile, String[] Data) throws Exception
			{
				FileWriter fileWriter = new FileWriter(csvfile,true);
			
				for(int i=0;i<Data.length;i++)
				{
					fileWriter.append(Data[i]);fileWriter.append(",");
				}
				fileWriter.append("\n");	
				
				fileWriter.close();
			}	
	
	public void writecsvHeader(int size) throws Exception
	{//S.No	RunStatus	Brand	User type	UserName	pwd	
		//OrderNumber	CardMessage	FirstName	LastName	Location	Company	PhoneNumber	Add1	
		//Add2	city	state	zip	Deliverydate	Result	Comments

		String[] Header = new String[size];
		Header[0]="S.No";
		Header[1]="RunStatus";
		Header[2]="Brand";
		Header[3]="User type";
		Header[4]="OrderNumber";
		Header[5]="CardMessage";
		Header[6]="New FirstName";
		Header[7]="New LastName";
		Header[8]="Location";
		Header[9]="Company";
		Header[10]="PhoneNumber";
		Header[11]="Add1";
		Header[12]="Add2";
		Header[13]="city";
		Header[14]="state";
		Header[15]="zip";
		Header[16]="Deliverydate";
		Header[17]="Result";
		Header[18]="Comments";
		Header[19] = "Defect";
		writeCsv(ResultPath+"/Result.csv",Header);
		
	}
	
	public void resetValues()
	{
		for(int j=0;j<DatatoWrite.length;j++){DatatoWrite[j]="";}	
	}
	
	public void datatowrite(int iteration)
	{
		DatatoWrite[1] = Integer.toString(iteration);
		DatatoWrite[2] = getRowData()[1];  //TC no
		DatatoWrite[3] = getRowData()[2];  // Run Status
	    DatatoWrite[4] = getRowData()[3];  // Brad
	    DatatoWrite[5] = getRowData()[4];  // User Type
	    DatatoWrite[6] = getRowData()[5];  // User Name
	    DatatoWrite[7] = getRowData()[6];  //pwd
	    DatatoWrite[8] = getRowData()[7];  //OrderNumber
	    DatatoWrite[9] = getRowData()[8];  // CardMessage
	    DatatoWrite[10] = getRowData()[9];  //FirstName
	    DatatoWrite[11] = getRowData()[10];  //LastName
	    DatatoWrite[12] = getRowData()[11];  // Location
	    DatatoWrite[13] = getRowData()[12];  // Company
	    DatatoWrite[14] = getRowData()[13];  //PhoneNumber
	    DatatoWrite[15] = getRowData()[14];  //add 1
	    DatatoWrite[16] = getRowData()[15];  // add 2
	    DatatoWrite[17] = getRowData()[16];  //city
	    DatatoWrite[18] = getRowData()[17];  //state
	    DatatoWrite[19] = getRowData()[18];  // zip
	    
	    
	  
	    if(getRowData()[17].length()<5)
		{
			if(getRowData()[17].length() == 4) DatatoWrite[10] = "0"+getRowData()[19];
			if(getRowData()[17].length() == 3) DatatoWrite[10] = "00"+getRowData()[19];
		}
	    
}

	public void takeScreenshot(String iteration, String filename) throws Exception
	{
		System.out.println("capturing Screenshot");
		//highlightElement(driver.findElement(By.id("placeOrderBtnStatic")));
		driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);//capture the webpage
		FileUtils.copyFile(scrFile, new File(ResultPath+"/"+iteration+" "+filename+".png"));
	}
	private String[] Rowdata;
	public String[] getRowData(){return this.Rowdata; }
	public void setRowData(String[] arrValue){this.Rowdata = arrValue;}


}
	/*	@After
		public void tearDown() throws Exception 
		{
			String verificationErrorString = verificationErrors.toString();	
			if (!"".equals(verificationErrorString)){fail(verificationErrorString);}
		}*/

