package commonFunctions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogoutPage 
{
	@FindBy(xpath = "(//a[text()=' Logout'])[2]")
	WebElement objLogoutBtn;
	
	public void logout()
	{
		objLogoutBtn.click();
	}
}
