package com.utn.Grande.model;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse {

    @SerializedName("continent")
    private String continent;

    @SerializedName("total")
    private String total;

    @SerializedName("time")
    private String time;
}
