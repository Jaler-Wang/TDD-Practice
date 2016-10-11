package com.packtpublishing.tddjava.ch04ship;

import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

@Test
public class ShipSpec {
    private Ship ship = null;
    private Location location = null;
    private Planet planet = null;
    private Ship expectedShip = null;
    private List<Point>  obstacles = null;
    @BeforeMethod
    public void beforeTest(){
        Point max = new Point(50, 50);
        obstacles = new ArrayList<>();
        obstacles.add(new Point(12, 12));
        planet = new Planet(max, obstacles);

        location= new Location(new Point(20, 20), Direction.NORTH);
        ship = new Ship(location, planet);
        expectedShip = new Ship(location.copy(), planet);
    }

    public void WhenInstantiatedThenLocationIsSet(){
        Assert.assertEquals(location, ship.getCurrentLocation());
    }

    public void whenMoveForwardThenForward(){
        Location expectedLocation = location.copy();
        expectedLocation.forward();
        ship.forward();

        Assert.assertEquals(expectedLocation, ship.getCurrentLocation());
    }

    public void whenMoveBackwardThenBackWard(){
        Location expectedLocation = location.copy();
        expectedLocation.backward();
        ship.backward();
        Assert.assertEquals(expectedLocation, ship.getCurrentLocation());
    }

    public void whenTurnLeftThenTurnLeft(){
        Location expectedLocation = location.copy();
        expectedLocation.turnLeft();
        ship.turnLeft();
        Assert.assertEquals(expectedLocation, ship.getCurrentLocation());
    }

    public void whenTurnRightThenTurnRight(){
        Location expectedLocation = location.copy();
        expectedLocation.turnRight();
        ship.turnRight();
        Assert.assertEquals(expectedLocation, ship.getCurrentLocation());
    }

    public void whenExecuteCommandFThenForward(){
        expectedShip.forward();
        ship.executeCommand("f");
        Assert.assertEquals(expectedShip.getCurrentLocation(), ship.getCurrentLocation());
    }

    public void whenExecuteCommandBThenBackward(){
        expectedShip.backward();
        ship.executeCommand("b");
        Assert.assertEquals(expectedShip.getCurrentLocation(), ship.getCurrentLocation());
    }

    public void whenExecuteCommandLThenTurnLeft(){
        expectedShip.turnLeft();
        ship.executeCommand("l");
        Assert.assertEquals(expectedShip.getCurrentLocation(), ship.getCurrentLocation());
    }

    public void whenExecuteCommandRThenTurnRight(){
        expectedShip.turnRight();
        ship.executeCommand("r");
        Assert.assertEquals(expectedShip.getCurrentLocation(), ship.getCurrentLocation());
    }

    public void whenExecuteCommandFLThenForwardLeft(){
        expectedShip.forward();
        expectedShip.turnLeft();
        ship.executeCommand("fl");
        Assert.assertEquals(expectedShip.getCurrentLocation(), ship.getCurrentLocation());
    }

    public void whenInstantiatedThenPlanetIsStored(){

        Assert.assertEquals(planet, ship.getPlanet());
    }

    public void givenLocationOnEastBoundaryWhenMoveEastThenPassBoundary(){
        location.setDirection(Direction.EAST);
        location.getPoint().setX(planet.getMax().getX());
        ship.executeCommand("f");
        Assert.assertEquals(location.getX(), 1);
    }

    public void givenLocationOnWestBoundaryWhenMoveWestThenPassBoundary(){
        location.setDirection(Direction.EAST);
        location.getPoint().setX(1);
        ship.executeCommand("b");
        Assert.assertEquals(location.getX(), planet.getMax().getX());
    }

    public void whenInstantiatedThenObstacleIsStored(){
        Assert.assertEquals(ship.getPlanet().getObstacles(), obstacles);
    }

    public void givenLocationOnWestOfObstacleWhenMoveForwardThenMeetObstacle(){
        location.setDirection(Direction.EAST);
        location.getPoint().setX(11);
        location.getPoint().setY(12);
        Assert.assertEquals("X", ship.executeCommand("f"));
    }

    public void givenLocationOnWestOfObstacleWhenMoveBackwardThenMeetObstacle(){
        location.setDirection(Direction.EAST);
        location.getPoint().setX(13);
        location.getPoint().setY(12);
        Assert.assertEquals("X", ship.executeCommand("b"));
    }

    public void givenLocationFarAwayObstacleWhenForwardThenNotMeetObstacle(){
        Assert.assertEquals("O", ship.executeCommand("f"));
    }

    public void givenLocationFarAwayObstacleWhenBackwardThenNotMeetObstacle(){
        Assert.assertEquals("O", ship.executeCommand("b"));
    }
}
