package datadriven;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadDataFromExcel {

	@SuppressWarnings("deprecation")
	public ArrayList<String> getData(String testCaseName,String sheetName) throws IOException {
		
		FileInputStream fis=new FileInputStream("C://Users//ssrivastava6937//Documents//Shubhi//Book1.xlsx");
		XSSFWorkbook wb=new XSSFWorkbook(fis);
		ArrayList<String> al=new ArrayList<String>();
		int sheets=wb.getNumberOfSheets();
		for(int i=0;i<sheets;i++)
		{
			if(wb.getSheetName(i).equalsIgnoreCase(sheetName))
			{
			       XSSFSheet sh=wb.getSheetAt(i);	//Got the sheet
			       Iterator<Row> rows = sh.iterator();   //it will now have access to all the rows:Sheet is a collection of rows
			       Row firstRow=rows.next();   //it will get the first row and contains all the columns
			       Iterator<Cell> cell=firstRow.cellIterator();//Row is a collection of cells
			       int k=0;
			       int column = 0;
			       //get the column index
			       while(cell.hasNext())
			       {
			    	   Cell value=cell.next();
			    	   if(value.getStringCellValue().equalsIgnoreCase("Testcases"))
			    	   {
			    		   column=k;
			    	   }
			    	   
			    	   k++;
			       }
			       
			       while(rows.hasNext())
			       {
			    	  Row r= rows.next();
			    	  String cellValue= r.getCell(column).getStringCellValue();
			    	  if(cellValue.equalsIgnoreCase(testCaseName))
			    	  {
			    		  Iterator<Cell> c =r.cellIterator();
			    		  while (c.hasNext()) {
			    			  
			    			  Cell cellData=c.next();
			    			  if(cellData.getCellTypeEnum()==CellType.STRING)
			    			  {
							al.add(cellData.getStringCellValue());
			    			  }
			    			  
			    			  else {
			    				al.add(NumberToTextConverter.toText(cellData.getNumericCellValue())); //convert int to text
			    				 
			    			  }
							
						}
			    	  }
			    	 
			       }
			        
			}
		}
		return al;
		

	}	

}
