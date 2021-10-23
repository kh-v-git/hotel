package com.hotel.impl.admin.request;

import java.time.LocalDateTime;
import java.util.Objects;

public class Request {
    private int userRequestID;
    private int userID;
    private String status;
    private String badSize;
    private String level;
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

    public String getBadSize() {
        return badSize;
    }

    public void setBadSize(String badSize) {
        this.badSize = badSize;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
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
        return getUserRequestID() == request.getUserRequestID() && getUserID() == request.getUserID() && getAdultCapacity() == request.getAdultCapacity() && getChildCapacity() == request.getChildCapacity() && getStatus().equals(request.getStatus()) && getBadSize().equals(request.getBadSize()) && getLevel().equals(request.getLevel()) && getArrivalDay().equals(request.getArrivalDay()) && getDepartureDay().equals(request.getDepartureDay());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserRequestID(), getUserID(), getStatus(), getBadSize(), getLevel(), getAdultCapacity(), getChildCapacity(), getArrivalDay(), getDepartureDay());
    }

    @Override
    public String toString() {
        return "Request{" +
                "status='" + status + '\'' +
                ", badSize='" + badSize + '\'' +
                ", level='" + level + '\'' +
                ", adultCapacity=" + adultCapacity +
                ", childCapacity=" + childCapacity +
                ", arrivalDay=" + arrivalDay +
                ", departureDay=" + departureDay +
                '}';
    }
}
