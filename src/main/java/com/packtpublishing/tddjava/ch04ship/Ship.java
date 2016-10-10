package com.packtpublishing.tddjava.ch04ship;

public class Ship {

    Location currentLocation = null;
    private Planet planet;

    public Ship(Location location) {
        currentLocation = location;
    }

    public Ship(Location location, Planet planet) {
        this.planet = planet;
        currentLocation = location;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public void forward() {
        currentLocation.forward(planet.getMax());
    }


    public void backward() {
        currentLocation.backward(planet.getMax());
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

    public Planet getPlanet() {
        return planet;
    }
}
