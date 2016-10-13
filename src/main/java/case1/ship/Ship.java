package case1.ship;

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

    public boolean forward() {
       return currentLocation.forward(planet.getMax(), planet.getObstacles());
    }


    public boolean backward() {
        return currentLocation.backward(planet.getMax(), planet.getObstacles());
    }

    public void turnLeft() {
        currentLocation.turnLeft();
    }

    public void turnRight() {
        currentLocation.turnRight();
    }

    public String executeCommand(String command) {
        char[] commands = command.toLowerCase().toCharArray();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < commands.length; i++){
            sb.append(executeCommand(commands[i]));
        }
        return sb.toString();
    }

    private String executeCommand(char command) {
        boolean canMoving = true;
        switch(command){
            case 'f':
                canMoving = forward();
                break;
            case 'b':
                canMoving = backward();
                break;
            case 'l':
                turnLeft();
                break;
            case 'r':
                turnRight();
        }
        if(canMoving){
            return "O";
        }
        else{
            return "X";
        }
    }

    public Planet getPlanet() {
        return planet;
    }
}
