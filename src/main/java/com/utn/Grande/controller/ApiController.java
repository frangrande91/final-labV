package com.utn.Grande.controller;

import com.utn.Grande.model.ApiResponse;
import com.utn.Grande.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/covid")
public class ApiController {

    private final ApiService apiService;

    @Autowired
    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping
    public ResponseEntity<List<ApiResponse>> getHistory() throws IOException, InterruptedException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(apiService.apiCall());
    }


}
