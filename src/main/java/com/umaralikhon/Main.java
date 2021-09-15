package com.umaralikhon;

import java.io.IOException;

public class Main {
    public static void main(String ... args) throws IOException {
        ExcelCreator excelCreator = new ExcelCreator();
        excelCreator.fillRows();

        ExcelReader.readFromExcel("File.xls");
    }
}
