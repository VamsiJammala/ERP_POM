package driverFactory;

import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonFunctions.CustomerPage;
import config.AppUtils;
import utilities.ExcelFileUtils;

public class AppTest extends AppUtils
{
	String inputPath = "./FileInput/DataEngine.xlsx";
	String outputPath = "./FileOutput/Results.xlsx";
	String TcSheet = "CustomerData";
	ExtentReports report;
	ExtentTest logger;
	
	@Test
	public void kickStart() throws Throwable
	{
		report = new ExtentReports("./target/Extentreports/customerReport.html");
		ExcelFileUtils xl = new ExcelFileUtils(inputPath);
		int rc = xl.rowCount(TcSheet);
		Reporter.log("No of Rows :: "+rc,true);
		for(int i=1;i<=rc;i++)
		{
			logger = report.startTest("Customer Module");
			String cusName = xl.getCellData(TcSheet, i, 0);
			String address = xl.getCellData(TcSheet, i, 1);
			String city = xl.getCellData(TcSheet, i, 2);
			String country = xl.getCellData(TcSheet, i, 3);
			String cPerson = xl.getCellData(TcSheet, i, 4);
			String pNumber = xl.getCellData(TcSheet, i, 5);
			String email = xl.getCellData(TcSheet, i, 5);
			String mNum = xl.getCellData(TcSheet, i, 7);
			String notes = xl.getCellData(TcSheet, i, 8);
			
			CustomerPage cp = PageFactory.initElements(driver,CustomerPage.class);
			logger.log(LogStatus.INFO, cusName+" "+address+" "+city+" "+country+" "+cPerson+" "+pNumber+" "+email+" "+mNum+" "+notes);
			boolean res = cp.addCustomer(cusName, address, city, country, cPerson, pNumber, email, mNum, notes);
			if(res)
			{
				xl.setCelldata(TcSheet, i, 9, "Pass", outputPath);
				logger.log(LogStatus.PASS, "New Customer Added Successfully");
			}
			else
			{
				xl.setCelldata(TcSheet, i, 9, "Fail", outputPath);
				logger.log(LogStatus.FAIL,"Failed to Add Customer");
			}
			report.endTest(logger);
			report.flush();
		}
	}
}
