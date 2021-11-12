package com.hotel.model;

import java.sql.Blob;

public class RoomImage {
    int roomImageID;
    int roomID;
    String caption;
    String mime;
    Blob image;

    public int getRoomImageID() {
        return roomImageID;
    }

    public void setRoomImageID(int roomImageID) {
        this.roomImageID = roomImageID;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }
}
