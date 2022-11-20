package com.team1.incidentticketsystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team1.incidentticketsystem.services.TestDataService;

@RestController
@RequestMapping("/test-data")
public class TestDataController
{
    @Autowired
    TestDataService testDataService;

    @GetMapping("/make-employees")
    public ResponseEntity<String> makeEmployees()
    {
        this.testDataService.addAdmin();
        this.testDataService.createEmployees(10);

        return ResponseEntity.ok("made employees");
    }
}
