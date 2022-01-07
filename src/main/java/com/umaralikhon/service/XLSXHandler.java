package com.umaralikhon.service;

import com.umaralikhon.model.DboRepository;
import com.umaralikhon.model.DboUsers;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class XLSXHandler {

    private final DboRepository dboRepository;

    public XLSXHandler(DboRepository dboRepository) {
        this.dboRepository = dboRepository;
    }

    @SuppressWarnings("deprecation")
    public void writeIntoExcel() throws IOException, ParseException {

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        int rowIndex = 1;
        List<DboUsers> dboUsers = dboRepository.findAll();

        sheet.createRow(0);
        sheet.getRow(0).createCell(0).setCellValue("ID");
        sheet.getRow(0).createCell(1).setCellValue("Имя");
        sheet.getRow(0).createCell(2).setCellValue("Фамилия");
        sheet.getRow(0).createCell(3).setCellValue("Статус");
        sheet.getRow(0).createCell(4).setCellValue("Логин");
        sheet.getRow(0).createCell(5).setCellValue("Дата");

        for(DboUsers users : dboUsers) {

            if (users.getZStatus().name().equals("ACTIVE")) {
                sheet.createRow(rowIndex);

                sheet.getRow(rowIndex).createCell(0);
                sheet.getRow(rowIndex).getCell(0).setCellStyle(bordered(workbook));
                sheet.getRow(rowIndex).getCell(0).setCellValue(users.getCustomerId());

                sheet.getRow(rowIndex).createCell(1);
                sheet.getRow(rowIndex).getCell(1).setCellStyle(bordered(workbook));
                sheet.getRow(rowIndex).getCell(1).setCellValue(users.getFirstname());

                sheet.getRow(rowIndex).createCell(2);
                sheet.getRow(rowIndex).getCell(2).setCellStyle(bordered(workbook));
                sheet.getRow(rowIndex).getCell(2).setCellValue(users.getLastname());

                sheet.getRow(rowIndex).createCell(3);
                sheet.getRow(rowIndex).getCell(3).setCellStyle(bordered(workbook));
                sheet.getRow(rowIndex).getCell(3).setCellValue(users.getStatus().name());

                sheet.getRow(rowIndex).createCell(4);
                sheet.getRow(rowIndex).getCell(4).setCellStyle(bordered(workbook));
                sheet.getRow(rowIndex).getCell(4).setCellValue(users.getLogin());

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
                sheet.getRow(rowIndex).createCell(5);
                sheet.getRow(rowIndex).getCell(5).setCellStyle(bordered(workbook));
                sheet.getRow(rowIndex).getCell(5).setCellValue(dateFormat.format(users.getDate()));

                rowIndex++;
            }
        }

        workbook.write(new FileOutputStream("files/dbo/dbo.xlsx"));
        workbook.close();
    }

    public CellStyle bordered(Workbook workbook) {
        CellStyle border = workbook.createCellStyle();
        border.setBorderBottom(BorderStyle.THIN);
        border.setBorderTop(BorderStyle.THIN);
        border.setBorderLeft(BorderStyle.THIN);
        border.setBorderRight(BorderStyle.THIN);
        border.setAlignment(HorizontalAlignment.CENTER);
        return border;
    }
}
