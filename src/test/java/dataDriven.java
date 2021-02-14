import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class dataDriven {
	
	//Identify the testcase column by scanning the entire first row
	
	//After u grab the particular testcase row, pull all the data from that row and feed it into text

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		
		FileInputStream fis = new FileInputStream("C:\\Users\\TED GERRAD ARIAGA\\Documents\\Testing Classes\\testdata.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		
		int sheets = workbook.getNumberOfSheets();
		for(int i=0;i<sheets;i++) 
		{
			if(workbook.getSheetName(i).equalsIgnoreCase("demodata"))
				{
			XSSFSheet sheet = workbook.getSheetAt(i);
			//Identify the testcase column by scanning the entire first row
			
			Iterator<Row> rows = sheet.iterator(); //sheet is a collection of rows
			Row firstrow = rows.next();
			Iterator<Cell> ce = firstrow.cellIterator(); //row is a colletion of cells
			
			int k=0;
			int column = 0;
			while(ce.hasNext()) 
			{
				Cell value = ce.next();
				if(value.getStringCellValue().equalsIgnoreCase("Testcases")) 
				{
					//desired condition
					column = k;
				}
				
				k++;
			}
			System.out.println(column);
			
			//once column is identified, then scan the entire testcase column to identify "invalid username/valid password"
				while(rows.hasNext()) 
				{
					Row r = rows.next();
					if(r.getCell(column).getStringCellValue().equalsIgnoreCase("Invalid username/valid password")) 
					{
						
						//pull all the data from that row and feed it into text. This is done using the cell iterator
						
						Iterator<Cell> cv = r.cellIterator();
						while(cv.hasNext()) 
						{
						System.out.println(cv.next().getStringCellValue());
						}
					}
				}
				
				
				while(rows.hasNext()) 
				{
					Row r2 = rows.next();
					if(r2.getCell(column).getStringCellValue().equalsIgnoreCase("Valid Username/invalid password")) 
					{
						
						//pull all the data from that row and feed it into text. This is done using the cell iterator
						
						Iterator<Cell> cv = r2.cellIterator();
						while(cv.hasNext()) 
						{
						System.out.println(cv.next().getStringCellValue());
						}
					}
				}
				}
		}
	}

}
