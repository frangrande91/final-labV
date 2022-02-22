package com.utn.Grande.UtilTests;

import com.utn.Grande.model.ApiResponse;

import java.util.List;

public class ApiUtilTest {

    public static ApiResponse getApiResponse() {
        return ApiResponse.builder()
                .continent("North-America")
                .time("2020-06-02T22:15:06+00:00")
                .total("12345")
                .build();
    }

    public static List<ApiResponse> getListApiResponse() {
        return List.of(getApiResponse());
    }
}
