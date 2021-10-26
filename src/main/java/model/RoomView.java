package model;

import com.hotel.impl.admin.room.Room;

public class RoomView {
    private int adultCapacity;
    private int childCapacity;
    private double price;
    private String level;
    private String badSize;
    private double minPrice;

    public static RoomView toModel(Room room) {
        RoomView roomView = new RoomView();
        roomView.setAdultCapacity(room.getAdultCapacity());
        roomView.setChildCapacity(room.getChildCapacity());
        roomView.setPrice(roomView.getPrice());
        roomView.setLevel(roomView.getLevel());
        roomView.setBadSize(room.getBadSize());
        roomView.setMinPrice(room.getPrice());
        return roomView;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getBadSize() {
        return badSize;
    }

    public void setBadSize(String badSize) {
        this.badSize = badSize;
    }
}
