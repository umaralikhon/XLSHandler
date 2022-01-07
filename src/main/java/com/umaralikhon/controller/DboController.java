package com.umaralikhon.controller;

import com.umaralikhon.model.DboRepository;
import com.umaralikhon.model.DboUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DboController {
    private final DboRepository dboRepository;

    @Autowired
    public DboController(DboRepository dboRepository){
        this.dboRepository = dboRepository;
    }

    @PostMapping
    public DboUsers saveUser(@RequestBody DboUsers dboUsers){
        return dboRepository.save(dboUsers);
    }

    @GetMapping
    public List<DboUsers> getAllUsers(){
        return dboRepository.findAll();
    }
}
