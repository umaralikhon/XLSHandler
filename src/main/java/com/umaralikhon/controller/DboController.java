package com.umaralikhon.controller;

import com.umaralikhon.model.DboRepository;
import com.umaralikhon.model.DboUsers;
import com.umaralikhon.service.XLSXHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    private static String SERVER_LOCATION = "files/dbo/dbo.xlsx";

    @GetMapping("/download")
    public ResponseEntity<?> download() throws IOException, ParseException {
        xlsxHandler.writeIntoExcel();
        File file = new File(SERVER_LOCATION + File.separator);

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + "dbo.xlsx");
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");

        Path path = Paths.get(file.getAbsolutePath());

        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

        return ResponseEntity.ok()
                .headers(header)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(resource);
    }
}
