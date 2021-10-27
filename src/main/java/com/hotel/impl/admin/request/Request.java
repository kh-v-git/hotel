package com.hotel.impl.admin.request;

import java.time.LocalDateTime;
import java.util.Objects;

public class Request {
    private int userRequestID;
    private int userID;
    private String status;
    private String bedSize;
    private int adultCapacity;
    private int childCapacity;
    private LocalDateTime arrivalDay;
    private LocalDateTime departureDay;

    public int getUserRequestID() {
        return userRequestID;
    }

    public void setUserRequestID(int userRequestID) {
        this.userRequestID = userRequestID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
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

    public LocalDateTime getArrivalDay() {
        return arrivalDay;
    }

    public void setArrivalDay(LocalDateTime arrivalDay) {
        this.arrivalDay = arrivalDay;
    }

    public LocalDateTime getDepartureDay() {
        return departureDay;
    }

    public void setDepartureDay(LocalDateTime departureDay) {
        this.departureDay = departureDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return getUserRequestID() == request.getUserRequestID() && getUserID() == request.getUserID() && getAdultCapacity() == request.getAdultCapacity() && getChildCapacity() == request.getChildCapacity() && getStatus().equals(request.getStatus()) && getBedSize().equals(request.getBedSize()) && getArrivalDay().equals(request.getArrivalDay()) && getDepartureDay().equals(request.getDepartureDay());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserRequestID(), getUserID(), getStatus(), getBedSize(), getAdultCapacity(), getChildCapacity(), getArrivalDay(), getDepartureDay());
    }

    @Override
    public String toString() {
        return "Request{" +
                "status='" + status + '\'' +
                ", bedSize='" + bedSize + '\'' +
                ", adultCapacity=" + adultCapacity +
                ", childCapacity=" + childCapacity +
                ", arrivalDay=" + arrivalDay +
                ", departureDay=" + departureDay +
                '}';
    }
}
