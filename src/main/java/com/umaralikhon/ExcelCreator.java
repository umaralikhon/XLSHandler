package com.umaralikhon;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelCreator {
    private HSSFWorkbook workbook = new HSSFWorkbook(); //Creating excel file
    private HSSFSheet sheet = workbook.createSheet("Person data"); //Excel page with name
    private List<Person> personList = fillData();
    private int rowNum = 0; //Counter for rows

    public void fillRows() {
        Row row = sheet.createRow(rowNum);
        row.createCell(0).setCellValue("Name");
        row.createCell(1).setCellValue("Age");
        row.createCell(2).setCellValue("City");

        for(Person p : personList){
            createSheetHeader(sheet, rowNum++, p);
        }

        try( FileOutputStream fout = new FileOutputStream(new File("File.xls"))){
            workbook.write(fout);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createSheetHeader(HSSFSheet sheet, int rowNum, Person person){
        Row row = sheet.createRow(rowNum);

        row.createCell(0).setCellValue(person.getName());
        row.createCell(1).setCellValue(person.getAge());
        row.createCell(2).setCellValue(person.getCity());
    }

    private  List<Person> fillData(){
        List<Person> personList = new ArrayList<>();

        personList.add(new Person("Alisa", 20, "Moscow"));
        personList.add(new Person("Alexa", 20, "California"));
        personList.add(new Person("Siri", 20, "California"));

        return personList;
    }
}
