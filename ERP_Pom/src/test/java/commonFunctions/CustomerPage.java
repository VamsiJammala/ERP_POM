package commonFunctions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class CustomerPage
{
	WebDriver driver;
	
	public CustomerPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	@FindBy(xpath = "(//a[text()='Customers'])[2]")
	WebElement objCuslink;
	
	@FindBy(xpath = "//span[@data-phrase='AddLink']")
	WebElement objAddIcon;
	
	@FindBy(xpath = "//input[@id='x_Customer_Number']")
	WebElement objCusNum;
	
	@FindBy(xpath = "//input[@id='x_Customer_Name']")
	WebElement objCusName;
	
	@FindBy(xpath = "//textarea[@id='x_Address']")
	WebElement objAddress;
	
	@FindBy(xpath = "//input[@id='x_City']")
	WebElement objCity;
	
	@FindBy(xpath = "//input[@id='x_Country']")
	WebElement objCountry;
	
	@FindBy(xpath = "//input[@id='x_Contact_Person']")
	WebElement objCperson;
	
	@FindBy(xpath = "//input[@id='x_Phone_Number']")
	WebElement objPnum;
	
	@FindBy(xpath = "//input[@id='x__Email']")
	WebElement objEmail;
	
	@FindBy(xpath = "//input[@id='x_Mobile_Number']")
	WebElement objMnum;
	
	@FindBy(xpath = "//input[@id='x_Notes']")
	WebElement objNotes;
	
	@FindBy(id = "btnAction")
	WebElement objAddBtn;
	
	@FindBy(xpath = "//button[text()='OK!']")
	WebElement objCnfmBtn;
	
	@FindBy(xpath = "(//button[text()='OK'])[6]")
	WebElement objAlertOk;
	
	@FindBy(xpath = "//span[@data-phrase='SearchBtn']")
	WebElement objSearchPanel;
	
	@FindBy(xpath = "//button[@id='btnsubmit']")
	WebElement objSearchBtn;
	
	@FindBy(xpath = "//input[@id='psearch']")
	WebElement objSearchTxtBox;
	
	@FindBy(xpath = "//table[@id='tbl_a_customerslist']/tbody/tr/td[5]/div/span/span")
	WebElement objCusTable;

	public boolean addCustomer(String Name,String Address,String City,String Country,String Cperson,String Pnum,String Email,String Mnum,String Notes) throws Throwable
	{
		Actions ac = new Actions(driver);
		ac.moveToElement(objCuslink).click().perform();
		ac.moveToElement(objAddIcon).click().perform();
		String Exp_Data = objCusNum.getAttribute("value");
		objCusName.sendKeys(Name);
		objAddress.sendKeys(Address);
		objCity.sendKeys(City);
		objCountry.sendKeys(Country);
		objCperson.sendKeys(Cperson);
		objPnum.sendKeys(Pnum);
		objEmail.sendKeys(Email);
		objMnum.sendKeys(Mnum);
		objNotes.sendKeys(Notes);
		objAddBtn.click();
		objCnfmBtn.click();
		Thread.sleep(3000);
		objAlertOk.click();
		if(!objSearchTxtBox.isDisplayed())
		{
			objSearchPanel.click();
		}
		objSearchTxtBox.clear();
		objSearchTxtBox.sendKeys(Exp_Data);
		objSearchBtn.click();
		String Act_Data = objCusTable.getText();
		if(Exp_Data.equals(Act_Data))
		{
			Reporter.log("Customer Details Matching ||"+Exp_Data+" || "+Act_Data,true);
			return true;
		}
		else 
		{
			Reporter.log("Customer Details Matching ||"+Exp_Data+" || "+Act_Data,true);
			return false;
		}
	}
}
