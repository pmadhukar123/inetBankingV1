package com.inetbanking.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	
	public static List<Map<String,String>> getTestDataInMap(String sheetname) throws IOException { //here we pass parameter as String sheetname so that if we give any sheetname from excel that will be picked
		List<Map<String,String>> testDataAllRows=null;
		
		Map<String,String> testData=null;
		
		try{
			FileInputStream fileInputStream=new FileInputStream(System.getProperty("user.dir")+"/src/test/java/com/inetbanking/testData/TestData.xlsx");
			Workbook workbook=new XSSFWorkbook(fileInputStream);
			Sheet sheet=workbook.getSheet(sheetname);
			int lastRowNumber=sheet.getLastRowNum(); //Gives the total no of rows in sheet 0
			int lastColNumber=sheet.getRow(0).getLastCellNum();  //Gives the total no of cols in sheet 0
			
			List list=new ArrayList();  //here we create list object to get all the column values in the 1st row i.e. header
			for (int i=0; i<lastColNumber; i++){
				Row row=sheet.getRow(0); //here we only want to read the header row so we keep value as 0
				Cell cell=row.getCell(i);
				String rowHeader=cell.getStringCellValue().trim();
				list.add(rowHeader); //once we get the values, we add those to the array list
			}
			
			testDataAllRows=new ArrayList<Map<String,String>>();
			
			for (int j=1; j<=lastRowNumber; j++){ //here we run loop to get all the row values from 2nd row to last skipping the header row
				Row row=sheet.getRow(j); //here we want to increment and read through all rows except header so we keep value as j
			
				testData=new TreeMap<String,String>(String.CASE_INSENSITIVE_ORDER);
				DataFormatter df = new DataFormatter();  //here we use DataFormatter so that we dont have issue in reading any cell value from excel
				
				for (int k=0; k<lastColNumber; k++){ //here we have another loop inside so that row and column both increments and we get all column values also
					Cell cell=row.getCell(k);
					String colValue=df.formatCellValue(cell).trim();
					testData.put((String) list.get(k), colValue);
				}
				
				testDataAllRows.add(testData);
			
			}
			
			
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}
		
		return testDataAllRows;
		}
	
	
}


