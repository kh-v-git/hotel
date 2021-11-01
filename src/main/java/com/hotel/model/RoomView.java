package com.hotel.model;

public class RoomView {
    private String bedSize;
    private double minPrice;
    private double maxPrice;
    private int maxAdults;
    private int minAdults;
    private int maxChildren;
    private int minChildren;

    public RoomView(String bedSize, double minPrice, double maxPrice, int maxAdults, int minAdults, int maxChildren, int minChildren) {
        this.bedSize = bedSize;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.maxAdults = maxAdults;
        this.minAdults = minAdults;
        this.maxChildren = maxChildren;
        this.minChildren = minChildren;

    }
    public int getMaxAdults() {
        return maxAdults;
    }

    public void setMaxAdults(int maxAdults) {
        this.maxAdults = maxAdults;
    }

    public int getMinAdults() {
        return minAdults;
    }

    public void setMinAdults(int minAdults) {
        this.minAdults = minAdults;
    }

    public int getMaxChildren() {
        return maxChildren;
    }

    public void setMaxChildren(int maxChildren) {
        this.maxChildren = maxChildren;
    }

    public int getMinChildren() {
        return minChildren;
    }

    public void setMinChildren(int minChildren) {
        this.minChildren = minChildren;
    }


    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public String getBedSize() {
        return bedSize;
    }

    public void setBedSize(String bedSize) {
        this.bedSize = bedSize;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

}
