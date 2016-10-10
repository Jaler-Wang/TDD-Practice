package com.packtpublishing.tddjava.ch04ship;

public class Ship {

    Location currentLocation = null;

    public Ship(Location location) {
        currentLocation = location;
    }

    public Ship() {

    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public void forward() {
        currentLocation.forward();
    }


    public void backward() {
        currentLocation.backward();
    }
}
