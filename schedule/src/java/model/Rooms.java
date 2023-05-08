/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author nguyn
 */
public class Rooms {
    private int roomId;
    private String name;
    private int floor;
    private int buildingId;
    private String buildingName;
    public Rooms() {
    }

    public Rooms(int roomId, String name, int floor, int buildingId) {
        this.roomId = roomId;
        this.name = name;
        this.floor = floor;
        this.buildingId = buildingId;
    }

    public Rooms(int roomId, String name, int floor, int buildingId, String buildingName) {
        this.roomId = roomId;
        this.name = name;
        this.floor = floor;
        this.buildingId = buildingId;
        this.buildingName = buildingName;
    }
 
    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(int buildingId) {
        this.buildingId = buildingId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    
    
}
