package commonFunctions;

import java.time.Duration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SupplierPage 
{
	WebDriver driver;
	
	public SupplierPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	@FindBy(xpath = "(//a[text()='Suppliers'])[2]")
	WebElement objSupLink;
	
	@FindBy(xpath = "(//span[@data-caption='Add'])[1]")
	WebElement objAddIcon;
	
	@FindBy(xpath = "//input[@id='x_Supplier_Number']")
	WebElement objSupNum;
	
	@FindBy(xpath = "//input[@id='x_Supplier_Name']")
	WebElement objSupName;
	
	@FindBy(xpath = "//textarea[@id='x_Address']")
	WebElement objAddress;
	
	@FindBy(xpath = "//input[@id='x_City']")
	WebElement objCity;
	
	@FindBy(xpath = "//input[@id='x_Country']")
	WebElement objCountry;
	
	@FindBy(xpath = "//input[@id='x_Contact_Person']")
	WebElement objConPerson;
	
	@FindBy(xpath = "//input[@id='x_Phone_Number']")
	WebElement objPhnNum;
	
	@FindBy(xpath = "//input[@id='x__Email']")
	WebElement objEmail;
	
	@FindBy(xpath = "//input[@id='x_Mobile_Number']")
	WebElement objMobileNum;
	
	@FindBy(xpath = "//textarea[@id='x_Notes']")
	WebElement objNotes;
	
	@FindBy(id = "btnAction")
	WebElement objAddBtn;
	
	@FindBy(xpath = "//button[text()='OK!']")
	WebElement objCnfmOk;
	
	@FindBy(xpath = "(//button[text()='OK'])[6]")
	WebElement objAlertOk;
	
	@FindBy(xpath = "//span[@data-phrase='SearchBtn']")
	WebElement objSearchPanel;
	
	@FindBy(xpath = "//input[@id='psearch']")
	WebElement objSearchTxtBx;
	
	@FindBy(xpath = "//button[@id='btnsubmit']")
	WebElement objSearchBtn;
	
	@FindBy(xpath = "//table[@id='tbl_a_supplierslist']/tbody/tr/td[6]/div/span/span")
	WebElement objSupTable;
	
	public boolean addSupplier(String Name,String Address,String City,String Country,String ConPerson,String PhnNum,String Email,String MblNum,String Notes)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		objSupLink.click();
		objAddIcon.click();
		String Exp_Data = objSupNum.getAttribute("value");
		objSupName.sendKeys(Name);
		objAddress.sendKeys(Address);
		objCity.sendKeys(City);
		objCountry.sendKeys(Country);
		objConPerson.sendKeys(ConPerson);
		objPhnNum.sendKeys(PhnNum);
		objEmail.sendKeys(Email);
		objMobileNum.sendKeys(MblNum);
		objNotes.sendKeys(Notes);
		objAddBtn.sendKeys(Keys.ENTER);;
		wait.until(ExpectedConditions.visibilityOf(objCnfmOk));
		objCnfmOk.click();
		wait.until(ExpectedConditions.visibilityOf(objAlertOk));
		objAlertOk.click();
		if(!objSearchTxtBx.isDisplayed())
		{
			objSearchPanel.click();
		}
		objSearchTxtBx.clear();
		objSearchTxtBx.sendKeys(Exp_Data);
		objSearchBtn.click();
		String Act_Data = objSupTable.getText();
		if(Exp_Data.equalsIgnoreCase(Act_Data))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
