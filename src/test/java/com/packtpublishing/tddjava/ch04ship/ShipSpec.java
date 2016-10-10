package com.packtpublishing.tddjava.ch04ship;

import org.testng.Assert;
import org.testng.annotations.*;

import static org.testng.Assert.*;

@Test
public class ShipSpec {
    private Ship ship = null;
    private Location location = null;

    @BeforeMethod
    public void beforeTest(){
        location= new Location(new Point(1, 1), Direction.NORTH);
        ship = new Ship(location);
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
        Ship expectedShip = new Ship(location.copy());
        expectedShip.forward();
        ship.executeCommand("f");
        Assert.assertEquals(expectedShip.getCurrentLocation(), ship.getCurrentLocation());
    }

    public void whenExecuteCommandBThenBackward(){
        Ship expectedShip = new Ship(location.copy());
        expectedShip.backward();
        ship.executeCommand("b");
        Assert.assertEquals(expectedShip.getCurrentLocation(), ship.getCurrentLocation());
    }

    public void whenExecuteCommandLThenTurnLeft(){
        Ship expectShip = new Ship(location.copy());
        expectShip.turnLeft();
        ship.executeCommand("l");
        Assert.assertEquals(expectShip.getCurrentLocation(), ship.getCurrentLocation());
    }

    public void whenExecuteCommandRThenTurnRight(){
        Ship expectedShip = new Ship(location.copy());
        expectedShip.turnRight();
        ship.executeCommand("r");
        Assert.assertEquals(expectedShip.getCurrentLocation(), ship.getCurrentLocation());
    }

    public void whenExecuteCommandFLThenForwardLeft(){
        Ship expectedShip = new Ship(location.copy());
        expectedShip.forward();
        expectedShip.turnLeft();
        ship.executeCommand("fl");
        Assert.assertEquals(expectedShip.getCurrentLocation(), ship.getCurrentLocation());
    }
}
