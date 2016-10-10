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

    public void turnLeft() {
        currentLocation.turnLeft();
    }

    public void turnRight() {
        currentLocation.turnRight();
    }

    public void executeCommand(String command) {
        char[] commands = command.toLowerCase().toCharArray();
        for(int i = 0; i < commands.length; i++){
            executeCommand(commands[i]);
        }

    }

    private void executeCommand(char command) {
        switch(command){
            case 'f':
                forward();
                break;
            case 'b':
                backward();
                break;
            case 'l':
                turnLeft();
                break;
            case 'r':
                turnRight();
        }
    }
}
