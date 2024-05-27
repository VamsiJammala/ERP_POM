package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileUtils 
{
	XSSFWorkbook wb;
	
	public ExcelFileUtils(String ExcelPath) throws Throwable
	{
		FileInputStream fi = new FileInputStream(ExcelPath);
		wb = new XSSFWorkbook(fi);
	}
	
	public int rowCount(String SheetName)
	{
		return wb.getSheet(SheetName).getLastRowNum();
	}
	
	public String getCellData(String SheetName,int Row,int Cloumn)
	{
		String data="";
		if(wb.getSheet(SheetName).getRow(Row).getCell(Cloumn).getCellType()==CellType.NUMERIC)
		{
			int value = (int)wb.getSheet(SheetName).getRow(Row).getCell(Cloumn).getNumericCellValue();
			data = String.valueOf(value);
		}
		else
		{
			data = wb.getSheet(SheetName).getRow(Row).getCell(Cloumn).getStringCellValue();
		}
		return data;
	}
	
	public void setCelldata(String SheetName,int Row,int Column,String Status,String WriteExcel) throws Throwable
	{
		XSSFSheet ws = wb.getSheet(SheetName);
		XSSFRow rownum = ws.getRow(Row);
		XSSFCell cell = rownum.createCell(Column);
		cell.setCellValue(Status);
		
		if(Status.equalsIgnoreCase("Pass"))
		{
			XSSFCellStyle style = wb.createCellStyle();
			XSSFFont font = wb.createFont();
			font.setColor(IndexedColors.GREEN.getIndex());
			font.setBold(true);
			style.setFont(font);
			rownum.getCell(Column).setCellStyle(style);
		}
		else if(Status.equalsIgnoreCase("False"))
		{
			XSSFCellStyle style = wb.createCellStyle();
			XSSFFont font = wb.createFont();
			font.setColor(IndexedColors.RED.getIndex());
			font.setBold(true);
			style.setFont(font);
			rownum.getCell(Column).setCellStyle(style);
		}
		else if(Status.equalsIgnoreCase("Blocked"))
		{
			XSSFCellStyle style = wb.createCellStyle();
			XSSFFont font = wb.createFont();
			font.setColor(IndexedColors.BLUE.getIndex());
			font.setBold(true);
			style.setFont(font);
			rownum.getCell(Column).setCellStyle(style);
		}
		
		FileOutputStream fo = new FileOutputStream(WriteExcel);
		wb.write(fo);
		
	}
}
