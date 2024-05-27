package commonFunctions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminLoginPage
{
	@FindBy(id = "btnreset")
	WebElement objResetBtn;
	
	@FindBy(xpath = "//input[@id='username']")
	WebElement objUname;
	
	@FindBy(xpath = "//input[@id='password']")
	WebElement objPwd;
	
	@FindBy(id = "btnsubmit")
	WebElement objLoginBtn;
	
	public void loginmodule(String Uname,String Pwd)
	{
		objResetBtn.click();
		objUname.sendKeys(Uname);
		objPwd.sendKeys(Pwd);
		objLoginBtn.click();
	}
}
