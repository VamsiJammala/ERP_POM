package driverFactory1;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import commonFunctions.SupplierPage;
import config.AppUtils;
import utilities.ExcelFileUtils;

public class AppTest extends AppUtils
{
	String inputPath = "./FileInput/DataEngine.xlsx";
	String outputPath = "./FileOutput/supResults.xlsx";
	String TcSheet = "SupplierData";
	@Test
	public void kickStart() throws Throwable
	{
		ExcelFileUtils xl = new ExcelFileUtils(inputPath);
		int rc = xl.rowCount(TcSheet);
		for(int i=1;i<=rc;i++)
		{
			String supname = xl.getCellData(TcSheet, i, 0);
			String address = xl.getCellData(TcSheet, i, 1);
			String city = xl.getCellData(TcSheet, i, 2);
			String country = xl.getCellData(TcSheet, i, 3);
			String cperson = xl.getCellData(TcSheet, i, 4);
			String phnNum = xl.getCellData(TcSheet, i, 5);
			String email = xl.getCellData(TcSheet, i, 6);
			String mblNum = xl.getCellData(TcSheet, i, 7);
			String notes = xl.getCellData(TcSheet, i, 8);
			
			SupplierPage sp = PageFactory.initElements(driver, SupplierPage.class);
			boolean res = sp.addSupplier(supname, address, city, country, cperson, phnNum, email, mblNum, notes);
			if(res)	
			{
				xl.setCelldata(TcSheet, i, 9, "Pass", outputPath);
			}
			else
			{
				xl.setCelldata(TcSheet, i, 9, "Fail", outputPath);
			}
		}
	}
}
