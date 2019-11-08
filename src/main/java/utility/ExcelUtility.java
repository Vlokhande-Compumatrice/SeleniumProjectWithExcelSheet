package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	static XSSFWorkbook wb;
	public static XSSFSheet sheet1;
	static String file;

	public ExcelUtility(String filepath) throws Exception {
		file = filepath;
		File src = new File(filepath);
		FileInputStream fis = new FileInputStream(src);
		wb = new XSSFWorkbook(fis);
	}

	public String getTestData(String sheetname, int row, int column) throws Exception {
		DataFormatter formatter = new DataFormatter();

		sheet1 = wb.getSheet(sheetname);
		// String data = sheet1.getRow(row).getCell(column).getStringCellValue();
		Cell cell = sheet1.getRow(row).getCell(column);
		String data = formatter.formatCellValue(cell);
		System.out.println("Data from excel is" + data);
		return data;
	}

	public void writeExcelData(String sheetname, int rowNo, String[] data) throws IOException {

		sheet1 = wb.getSheet(sheetname);
		Row row = sheet1.createRow(rowNo);
		for (int i = 0; i < data.length; i++) {
			row.createCell(i).setCellValue(data[i]);
		}
		FileOutputStream fout = new FileOutputStream(file);
		wb.write(fout);
		//wb.close();
	}
	
	public void writeData(String sheetname, int rowNo, int column, String data) throws IOException {

		sheet1 = wb.getSheet(sheetname);
		Row row = sheet1.getRow(rowNo);		
		row.createCell(column).setCellValue(data);
	
		FileOutputStream fout = new FileOutputStream(file);
		wb.write(fout);
		//wb.close();
	}
}
