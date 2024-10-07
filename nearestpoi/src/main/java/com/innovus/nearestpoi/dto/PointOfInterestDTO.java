
package com.innovus.nearestpoi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PointOfInterestDTO {
    
    @NotBlank(message = "Oops! Looks like you forgot to name this place. Please provide a name.")
    private String name;
    
    @NotNull(message = "Oops! Looks like you forgot the latitude. Please provide it.")
    private Double latitude;

    @NotNull(message = "Oops! Looks like you forgot the longitude. Please provide it.")
    private Double longitude;
    
    private int requestCount;
    
    public PointOfInterestDTO() {
    }

    public PointOfInterestDTO(String name, Double latitude, Double longitude, int requestCount) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.requestCount = requestCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public int getRequestCount() {
        return requestCount;
    }

    public void setRequestCount(int requestCount) {
        this.requestCount = requestCount;
    }
}

