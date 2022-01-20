package com.umaralikhon.controller;

import com.umaralikhon.model.DboRepository;
import com.umaralikhon.model.DboUsers;
import com.umaralikhon.service.XLSXHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DboController {
    private final DboRepository dboRepository;
    private final XLSXHandler xlsxHandler;

    @Autowired
    public DboController(DboRepository dboRepository, XLSXHandler xlsxHandler) {
        this.dboRepository = dboRepository;
        this.xlsxHandler = xlsxHandler;
    }

    @PostMapping
    public DboUsers saveUser(@RequestBody DboUsers dboUsers) {
        return dboRepository.save(dboUsers);
    }

    @GetMapping
    public List<DboUsers> getAllUsers() {
        return dboRepository.findAll();
    }

    @GetMapping("/download")
    public ResponseEntity<Object> download() throws IOException, ParseException {

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=dbo.xlsx");
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");

        String encodeByte = new String(xlsxHandler.writeIntoExcel());

        return ResponseEntity.ok()
                .headers(header)
                .contentLength(encodeByte.length())
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(encodeByte);
    }
}
