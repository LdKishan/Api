package com.Api.service;


import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class JenkinsService {
	@Autowired
    private RestTemplate restTemplate;
	
		private final String jenkinsUrl = "http://54.87.215.83:8080/";
	    private final String apiToken = "113dde0050bd78a355012c24da9897f29e";
	    private final String username = "Kishan8917";


	    public JenkinsService(RestTemplate restTemplate) {
	        this.restTemplate = restTemplate;
	    }

	    public String getJenkinsLandingPage() {
	        HttpHeaders headers = new HttpHeaders();
	        headers.set("Authorization", "Basic " + Base64.getEncoder().encodeToString((username + ":" + apiToken).getBytes()));
	        HttpEntity<String> entity = new HttpEntity<>(headers);
	        ResponseEntity<String> response = restTemplate.exchange(jenkinsUrl, HttpMethod.GET, entity, String.class);

	        if (response.getStatusCode() == HttpStatus.OK) {
	            return response.getBody();
	        } else {
	            throw new RuntimeException("Failed to access Jenkins landing page. Status Code: " + response.getStatusCode());
	        }
	    }
	    
	    public void createJenkinsItem(String itemName, String itemConfigXml) {
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_XML);

	        HttpEntity<String> requestEntity = new HttpEntity<>(itemConfigXml, headers);

	        ResponseEntity<String> responseEntity = restTemplate.exchange(
	            jenkinsUrl + "createItem?name=" + itemName,
	            HttpMethod.POST,
	            requestEntity,
	            String.class
	        );

	        if (responseEntity.getStatusCode() == HttpStatus.CREATED) {
	            System.out.println("New item created successfully.");
	        } else {
	            System.err.println("Failed to create a new item. Status code: " + responseEntity.getStatusCode());
	        }
	    }
}