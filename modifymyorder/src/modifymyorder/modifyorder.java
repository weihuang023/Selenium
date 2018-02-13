<<<<<<< HEAD
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
	//@Test
	public void FPT_ModifyOrder() throws Exception 
		{           
		           try
					{
						//Calling browser and accessing 18F URL
		        	    WebDriverWait wait = new WebDriverWait(driver, 60);
						callBrowser();
						deleteCookies();
						goto_Orderdetailspage_Registered("W00995422667594","mmotest@tag.com","tag@123");
						
						driver.findElement(By.id("modifyCardMessage")).click();
						
						wait.until(ExpectedConditions.visibilityOfElementLocated((By.id("mod_window"))));
						
						
						WebElement iframeSwitch = driver.findElement(By.xpath("//iframe[contains(@id, 'mod_window')and contains (@class, 'mod-modal')]"));
						driver.switchTo().frame(iframeSwitch);						
						WebElement MessageArea = wait.until(ExpectedConditions.visibilityOfElementLocated((By.id("editgiftmessage"))));
						//WebElement MessageArea = driver.findElement(By.cssSelector("tesxtarea[id=\"editgiftmessage\"]"));
						
						MessageArea.sendKeys(Keys.TAB);
						MessageArea.clear();
						MessageArea.sendKeys("Testing Automation");
						
						//driver.switchTo().defaultContent();
						driver.findElement(By.xpath("//div/div[2]/div/div/span")).click();
						
	
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
					    
					    driver.findElement(By.xpath("//div[@id='mod_ajaxContent']/div[2]/div/div/button[2]")).click();
					    driver.findElement(By.xpath("//div[@id='mod_ajaxContent']/div[2]/div[2]/button[2]")).click();
					    
					    wait.until(ExpectedConditions.visibilityOfElementLocated((By.linkText("Close"))));
					    driver.findElement(By.linkText("Close")).click();
						driver.switchTo().defaultContent();
						driver.quit();driver = null;
				    }
			
					catch(Exception e)
					{
						e.printStackTrace();
						System.out.println("error occured");
						driver.quit();
					}
		}
	
	@Test
	public void ModifyOrder_False() throws Exception 
	{           
	           try
				{
					//Calling browser and accessing 18F URL
					callBrowser();
					deleteCookies();
					driver.get("https://akamai.1800flowers-uat.net");Thread.sleep(1000);
					WebDriverWait wait = new WebDriverWait(driver, 60);
					
					goto_Orderdetailspage_Registered("W00995422667510","mmotest@tag.com","tag@123");
					
					String ErrorMessage = driver.findElement(By.className("orderNotModifiableErr")).getText();
					System.out.println("Error message:" + ErrorMessage);
					if(ErrorMessage.contains("This order may no longer be modified")){System.out.println("Order not modifiable - Passed");}
					else {System.out.println("Past order modifiable - Failed");}
					driver.quit();driver = null;
			    }
		
				catch(Exception e)
				{
					e.printStackTrace();
					System.out.println("error occured");
					driver.quit();
				}
	}

	public void ModifyOrder_Logo() throws Exception 
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
	
					/*ProfilesIni profile = new ProfilesIni();
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
	System.setProperty("webdriver.chrome.driver", "C:/Selenium Testing 2/chromedriver.exe");
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

public void goto_OrderDetails(String user_type, String OrderNumber)
{
	switch (user_type){
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
}}

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


public void deleteCookies() throws InterruptedException
{driver.manage().deleteAllCookies();}

}
	/*	@After
		public void tearDown() throws Exception 
		{
			String verificationErrorString = verificationErrors.toString();	
			if (!"".equals(verificationErrorString)){fail(verificationErrorString);}
		}*/

=======
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
	//@Test
	public void FPT_ModifyOrder() throws Exception 
		{           
		           try
					{
						//Calling browser and accessing 18F URL
						callBrowser();
						deleteCookies();
						driver.get("https://akamai.1800flowers-uat.net");Thread.sleep(1000);
						WebDriverWait wait = new WebDriverWait(driver, 60);
						
						//Click on My Orders, enter order details and click submit
						driver.findElement(By.linkText("Track Your Order")).click();
						
						driver.findElement(By.id("label")).sendKeys("W00995422630853");
						driver.findElement(By.id("label2")).sendKeys("11514");
						driver.findElement(By.cssSelector("input[alt=\"Submit\"]")).click();
						
						driver.findElement(By.cssSelector("img[alt=\"Order Details\"]")).click();
						
						driver.findElement(By.id("modifyCardMessage")).click();
						
						wait.until(ExpectedConditions.visibilityOfElementLocated((By.id("mod_window"))));
						
						
						WebElement iframeSwitch = driver.findElement(By.xpath("//iframe[contains(@id, 'mod_window')and contains (@class, 'mod-modal')]"));
						driver.switchTo().frame(iframeSwitch);						
						WebElement MessageArea = wait.until(ExpectedConditions.visibilityOfElementLocated((By.id("editgiftmessage"))));
						//WebElement MessageArea = driver.findElement(By.cssSelector("tesxtarea[id=\"editgiftmessage\"]"));
						
						MessageArea.sendKeys(Keys.TAB);
						MessageArea.clear();
						MessageArea.sendKeys("Testing Automation");
						
						//driver.switchTo().defaultContent();
						driver.findElement(By.xpath("//div/div[2]/div/div/span")).click();
						
	
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
					    
					    driver.findElement(By.xpath("//div[@id='mod_ajaxContent']/div[2]/div/div/button[2]")).click();
					    driver.findElement(By.xpath("//div[@id='mod_ajaxContent']/div[2]/div[2]/button[2]")).click();
					    
					    wait.until(ExpectedConditions.visibilityOfElementLocated((By.linkText("Close"))));
					    driver.findElement(By.linkText("Close")).click();
						driver.switchTo().defaultContent();
						driver.quit();driver = null;
				    }
			
					catch(Exception e)
					{
						e.printStackTrace();
						System.out.println("error occured");
						driver.quit();
					}
		}
	
	@Test
	public void ModifyOrder_False() throws Exception 
	{           
	           try
				{
					//Calling browser and accessing 18F URL
					callBrowser();
					deleteCookies();
					driver.get("https://akamai.1800flowers-uat.net");Thread.sleep(1000);
					WebDriverWait wait = new WebDriverWait(driver, 60);
					
					//Click on My Orders, enter order details and click submit
					driver.findElement(By.linkText("Track Your Order")).click();
					
					driver.findElement(By.id("label")).sendKeys("W00995422630853");
					driver.findElement(By.id("label2")).sendKeys("11514");
					driver.findElement(By.cssSelector("input[alt=\"Submit\"]")).click();
					
					driver.findElement(By.cssSelector("img[alt=\"Order Details\"]")).click();
					
					String ErrorMessage = driver.findElement(By.className("orderNotModifiableErr")).getText();
					System.out.println("Error message:" + ErrorMessage);
					if(ErrorMessage.contains("This order may no longer be modified")){System.out.println("Order not modifiable - Passed");}
					else {System.out.println("Past order modifiable - Failed");}
					driver.quit();driver = null;
			    }
		
				catch(Exception e)
				{
					e.printStackTrace();
					System.out.println("error occured");
					driver.quit();
				}
	}

	public void ModifyOrder_Logo() throws Exception 
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
	
					/*ProfilesIni profile = new ProfilesIni();
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
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\loadtest\\Documents\\GitHub\\myorder-selenium\\modifymyorder\\lib\\chromedriver.exe");
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

public void deleteCookies() throws InterruptedException
{driver.manage().deleteAllCookies();}

}
	/*	@After
		public void tearDown() throws Exception 
		{
			String verificationErrorString = verificationErrors.toString();	
			if (!"".equals(verificationErrorString)){fail(verificationErrorString);}
		}*/

>>>>>>> 3c8ac4461b67bc119b1173f265b1113a5b3cbf79
