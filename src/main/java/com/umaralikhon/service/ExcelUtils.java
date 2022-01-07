package com.umaralikhon.service;


import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelUtils {

    private Workbook workbook;

    public ExcelUtils(Workbook workbook){
        this.workbook = workbook;
    }
    public CellStyle bordered() {
        CellStyle border = workbook.createCellStyle();
        border.setBorderBottom(BorderStyle.THIN);
        border.setBorderTop(BorderStyle.THIN);
        border.setBorderLeft(BorderStyle.THIN);
        border.setBorderRight(BorderStyle.THIN);
        border.setAlignment(HorizontalAlignment.CENTER);
        return border;
    }
}
