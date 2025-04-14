package com.college.bus.dto;

public class BusLocationDto {
    private Long busId;
    private Double latitude;
    private Double longitude;

    // Constructors
    public BusLocationDto() {}

    public BusLocationDto(Long busId, Double latitude, Double longitude) {
        this.busId = busId;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Getters & Setters
    public Long getBusId() {
        return busId;
    }

    public void setBusId(Long busId) {
        this.busId = busId;
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
}
