package com.hotel.impl.request;

import java.time.LocalDate;

public class Request {
    private Integer userRequestID;
    private Integer userID;
    private Integer roomID;
    private String status;
    private String bedSize;
    private int adultCapacity;
    private int childCapacity;
    private LocalDate arrivalDay;
    private LocalDate departureDay;

    public int getUserRequestID() {
        return userRequestID;
    }

    public void setUserRequestID(Integer userRequestID) {
        this.userRequestID = userRequestID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(Integer roomID) {
        this.roomID = roomID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBedSize() {
        return bedSize;
    }

    public void setBedSize(String bedSize) {
        this.bedSize = bedSize;
    }

    public int getAdultCapacity() {
        return adultCapacity;
    }

    public void setAdultCapacity(int adultCapacity) {
        this.adultCapacity = adultCapacity;
    }

    public int getChildCapacity() {
        return childCapacity;
    }

    public void setChildCapacity(int childCapacity) {
        this.childCapacity = childCapacity;
    }

    public LocalDate getArrivalDay() {
        return arrivalDay;
    }

    public void setArrivalDay(LocalDate arrivalDay) {
        this.arrivalDay = arrivalDay;
    }

    public LocalDate getDepartureDay() {
        return departureDay;
    }

    public void setDepartureDay(LocalDate departureDay) {
        this.departureDay = departureDay;
    }

}
