package com.utn.Grande.controller;

import com.utn.Grande.model.ApiResponse;
import com.utn.Grande.service.ApiService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

import static com.utn.Grande.UtilTests.ApiUtilTest.getListApiResponse;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ApiControllerTest {

    private ApiService apiService;
    private ApiController apiController;

    @Before
    public void setUp() {
        apiService = mock(ApiService.class);
        apiController = new ApiController(apiService);
    }

    @Test
    public void getHistoryOkTest() throws IOException, InterruptedException {
        when(apiService.apiCall()).thenReturn(getListApiResponse());

        ResponseEntity<List<ApiResponse>> response = apiController.getHistory();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(getListApiResponse(), response.getBody());
        verify(apiService, times(1)).apiCall();
    }
}
