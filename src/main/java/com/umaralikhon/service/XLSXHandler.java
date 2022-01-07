package com.umaralikhon.service;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

@Service
public class XLSXHandler {

    @SuppressWarnings("deprecation")
    public void writeIntoExcel(String file) throws IOException {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        Row row = sheet.createRow(0);
        Cell name = row.createCell(0);
        Cell birthday = row.createCell(1);

        DataFormat format = workbook.createDataFormat();
        CellStyle dateStyle = workbook.createCellStyle();

        dateStyle.setDataFormat(format.getFormat("dd.mm.yyyy"));
        birthday.setCellStyle(dateStyle);
        birthday.setCellValue(new Date(102, 4,13));
        name.setCellValue("Diana");

        sheet.autoSizeColumn(1);
        workbook.write(new FileOutputStream(file));
        workbook.close();
    }
}
