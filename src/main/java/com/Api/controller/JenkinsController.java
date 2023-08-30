package com.Api.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Api.service.JenkinsService;

@RestController
@RequestMapping("/jenkins")
public class JenkinsController {

    @Autowired
    private JenkinsService jenkinsService;

    @GetMapping("/landing-page")
    public ResponseEntity<String> getJenkinsLandingPage() {
        try {
            String landingPage = jenkinsService.getJenkinsLandingPage();
            return new ResponseEntity<>(landingPage, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error calling Jenkins API.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/create-item")
    public ResponseEntity<String> createJenkinsItem(
        @RequestParam String itemName,
        @RequestBody String itemConfigXml
    ) {
        jenkinsService.createJenkinsItem(itemName, itemConfigXml);
        return ResponseEntity.ok("Request to create a new item sent to Jenkins.");
    }
}
    

