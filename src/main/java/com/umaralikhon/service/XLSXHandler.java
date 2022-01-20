package com.umaralikhon.service;

import com.umaralikhon.model.DboRepository;
import com.umaralikhon.model.DboUsers;
import org.apache.commons.codec.binary.Base64;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
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
    public byte[] writeIntoExcel() throws IOException, ParseException {

        String fileName = "files/receipt/dbo.xlsx";
        Workbook workbook = new XSSFWorkbook();
        ExcelUtils excelUtils = new ExcelUtils(workbook);
        Sheet sheet = workbook.createSheet();
        int rowIndex = 1;
        List<DboUsers> dboUsers = dboRepository.findAll();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try{
            sheet.createRow(0);
            sheet.getRow(0).createCell(0).setCellValue("ID");
            sheet.getRow(0).createCell(1).setCellValue("ФИО");
            sheet.getRow(0).createCell(2).setCellValue("Логин");
            sheet.getRow(0).createCell(3).setCellValue("Статус");
            sheet.getRow(0).createCell(4).setCellValue("Резидент");
            sheet.getRow(0).createCell(5).setCellValue("Паспорт");
            sheet.getRow(0).createCell(6).setCellValue("ИНН");
            sheet.getRow(0).createCell(7).setCellValue("ПИНФЛ");
            sheet.getRow(0).createCell(8).setCellValue("Дата рег.");

            for (DboUsers users : dboUsers) {
                if (users.getZStatus().name().equals("ACTIVE")) {
                    sheet.createRow(rowIndex);

                    sheet.getRow(rowIndex).createCell(0);
                    sheet.getRow(rowIndex).getCell(0).setCellStyle(excelUtils.bordered());
                    sheet.getRow(rowIndex).getCell(0).setCellValue(users.getAbsId());

                    sheet.getRow(rowIndex).createCell(1);
                    sheet.getRow(rowIndex).getCell(1).setCellStyle(excelUtils.bordered());
                    sheet.getRow(rowIndex).getCell(1).setCellValue(users.getFullName());

                    sheet.getRow(rowIndex).createCell(2);
                    sheet.getRow(rowIndex).getCell(2).setCellStyle(excelUtils.bordered());
                    sheet.getRow(rowIndex).getCell(2).setCellValue(users.getUsername());

                    sheet.getRow(rowIndex).createCell(3);
                    sheet.getRow(rowIndex).getCell(3).setCellStyle(excelUtils.bordered());
                    sheet.getRow(rowIndex).getCell(3).setCellValue(users.getStatus().name());

                    sheet.getRow(rowIndex).createCell(4);
                    sheet.getRow(rowIndex).getCell(4).setCellStyle(excelUtils.bordered());

                    if (users.getIsResident()) {
                        sheet.getRow(rowIndex).getCell(4).setCellValue("Да");
                    } else {
                        sheet.getRow(rowIndex).getCell(4).setCellValue("Нет");
                    }

                    sheet.getRow(rowIndex).createCell(5);
                    sheet.getRow(rowIndex).getCell(5).setCellStyle(excelUtils.bordered());
                    sheet.getRow(rowIndex).getCell(5).setCellValue(users.getPassSerial() + " " + users.getPassNumber());

                    sheet.getRow(rowIndex).createCell(6);
                    sheet.getRow(rowIndex).getCell(6).setCellStyle(excelUtils.bordered());
                    sheet.getRow(rowIndex).getCell(6).setCellValue(users.getTin());

                    sheet.getRow(rowIndex).createCell(7);
                    sheet.getRow(rowIndex).getCell(7).setCellStyle(excelUtils.bordered());
                    sheet.getRow(rowIndex).getCell(7).setCellValue(users.getPinfl());

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
                    sheet.getRow(rowIndex).createCell(8);
                    sheet.getRow(rowIndex).getCell(8).setCellStyle(excelUtils.bordered());
                    sheet.getRow(rowIndex).getCell(8).setCellValue(dateFormat.format(users.getRegisteredDate()));

                    rowIndex++;
                }
            }

            workbook.write(new FileOutputStream(fileName));

            byte[] data = Files.readAllBytes(Path.of(fileName));
            byte[] base64 = Base64.encodeBase64(data);
            return base64;

        }catch (Exception ex){
            return null;
        }finally {
            workbook.close();
            outputStream.close();
        }
    }
}
