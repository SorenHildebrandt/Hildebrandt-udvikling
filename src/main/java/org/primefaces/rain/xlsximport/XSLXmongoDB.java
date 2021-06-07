package org.primefaces.rain.xlsximport;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.bson.Document;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class XSLXmongoDB {

    public static void main(String args[]) throws IOException {
        try {
            MongoClient mongo = new MongoClient( "localhost" , 27017 );
            System.out.println("Connected to the database successfully");
            //Accessing the database
            MongoDatabase database = mongo.getDatabase("hildebra_db1");
            MongoCollection dbcoll = database.getCollection("excelTest");

            List empRecords = new ArrayList<>();
            File myFile = new File("/mongoImport/aktieafkast/aktieAfkast.xlsx");
            FileInputStream fis = new FileInputStream(myFile);
            // Finds the workbook instance for XLSX file
            Workbook myWorkBook = new XSSFWorkbook(fis);
            // Return first sheet from the XLSX workbook
            Sheet mySheet = myWorkBook.getSheetAt(0);
            System.out.println(mySheet.getSheetName());
            String headerArr[] = new String[10];

            // Get iterator to all the rows in current sheet

            Iterator rowIterator = mySheet.iterator();
            System.out.println("rowiterator " + rowIterator);
            Row headerRow = (Row) rowIterator.next();
            Iterator headerCellIterator = headerRow.cellIterator();
            System.out.println("headercelliterator " + headerCellIterator);
            int i= 0;
            while (headerCellIterator.hasNext()){
                Cell headerCell = (Cell) headerCellIterator.next();
                headerArr[i] = headerCell.toString();
                System.out.println("headerArr + i " + headerArr + "Integer " + i);
                i++;
            }
            while (rowIterator.hasNext()) {
                Row row = (Row) rowIterator.next();
                System.out.println("row " + row);
                Document emp = new Document();
                i=0;
                // For each row, iterate through each columns
                Iterator cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    try {
                        Cell cell = (Cell) cellIterator.next();
                        System.out.println("cell " + cell);
                        System.out.println("cell type" + cell.getCellType());
                        if(cell.getCellType() ==0)

                            emp.put(headerArr[i],cell.getStringCellValue());
                        //System.out.println("emp put string value " + emp.put(headerArr[i], cell.getStringCellValue()));
                        else
                            emp.put(headerArr[i], cell.getStringCellValue());
                            emp.put(headerArr[i], (int)cell.getNumericCellValue());
                        emp.put(headerArr[i], (Date)cell.getDateCellValue());
                        System.out.println("emp put numeric " + emp.put(headerArr[i], (int)cell.getNumericCellValue()));
                        //System.out.println("emp put numeric Date " + emp.put(headerArr[i], (Date)cell.getDateCellValue()));

                        i++;
                    }catch(Exception e){
                    }
                }
                System.out.println("empRecords " + empRecords.add(emp));
                empRecords.add(emp);
            }
            System.out.println("Gemmes til Mongo");
            dbcoll.insertMany(empRecords);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
