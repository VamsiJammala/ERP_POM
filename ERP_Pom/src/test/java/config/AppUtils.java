package config;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import commonFunctions.AdminLoginPage;
import commonFunctions.LogoutPage;

public class AppUtils 
{
	public static WebDriver driver;
	public static Properties conpro;
	
	@BeforeTest
	public static void launchapp() throws Throwable
	{
		conpro = new Properties();
		conpro.load(new FileInputStream("./PropertyFiles/Environment.properties"));
		if(conpro.getProperty("Browser").equalsIgnoreCase("Chrome"))
		{
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get(conpro.getProperty("Url"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			AdminLoginPage li = PageFactory.initElements(driver, AdminLoginPage.class);
			li.loginmodule("admin", "master");
		}
		else if(conpro.getProperty("Browser").equalsIgnoreCase("Firefox"))
		{
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.get(conpro.getProperty("Url"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			AdminLoginPage li = PageFactory.initElements(driver, AdminLoginPage.class);
			li.loginmodule("admin", "master");
		}
		else
		{
			driver = new EdgeDriver();
			driver.manage().window().maximize();
			driver.get(conpro.getProperty("Url"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			AdminLoginPage li = PageFactory.initElements(driver, AdminLoginPage.class);
			li.loginmodule("admin", "master");
		}
	}
	
	@AfterTest
	public static void teardown()
	{
		LogoutPage lo = PageFactory.initElements(driver, LogoutPage.class);
		lo.logout();
		driver.quit();
	}
}
