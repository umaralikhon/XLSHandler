package com.umaralikhon.controller;

import com.umaralikhon.model.DboRepository;
import com.umaralikhon.model.DboUsers;
import com.umaralikhon.service.XLSXHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DboController {
    private final DboRepository dboRepository;
    private final XLSXHandler xlsxHandler;

    @Autowired
    public DboController(DboRepository dboRepository, XLSXHandler xlsxHandler){
        this.dboRepository = dboRepository;
        this.xlsxHandler = xlsxHandler;
    }

    @PostMapping
    public DboUsers saveUser(@RequestBody DboUsers dboUsers){
        return dboRepository.save(dboUsers);
    }

    @GetMapping
    public List<DboUsers> getAllUsers(){
        return dboRepository.findAll();
    }

    @GetMapping("/excel")
    public void createExcel(){
        try {
            xlsxHandler.writeIntoExcel("Test.xls");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
