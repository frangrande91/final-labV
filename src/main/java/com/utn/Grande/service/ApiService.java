package com.utn.Grande.service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.utn.Grande.model.ApiResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
public class ApiService {

    @CircuitBreaker(name = "apiCovid", fallbackMethod = "fallback")
    public List<ApiResponse> apiCall() throws IOException, InterruptedException {

        Random ran = new Random();

        if(ran.nextBoolean()){
            throw new RuntimeException();
        }

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://covid-193.p.rapidapi.com/history?country=usa&day=2020-06-02"))
                .header("x-rapidapi-host", "covid-193.p.rapidapi.com")
                .header("x-rapidapi-key", "549a7bb64cmsh22a289e69814bbcp1e7974jsnc74d4dcbd570")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        JsonArray jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject().get("response").getAsJsonArray();
        List<ApiResponse> responses = new ArrayList<>();
        jsonResponse.forEach(p -> responses.add(ApiResponse.builder()
                                        .continent(p.getAsJsonObject().get("continent").getAsString())
                                        .time(p.getAsJsonObject().get("time").getAsString())
                                        .total(p.getAsJsonObject().get("cases").getAsJsonObject().get("total").getAsString())
                                        .build()));
        return responses;
    }

    public ApiResponse fallback(Throwable t) {
        log.error("fallback cause {}", t.toString());
        return ApiResponse.builder().build();
    }
}
