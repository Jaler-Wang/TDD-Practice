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

    public void whenTurnRigthThenTurnRigth(){
        Location expectedLocation = location.copy();
        expectedLocation.turnRight();
        ship.turnRight();
        Assert.assertEquals(expectedLocation, ship.getCurrentLocation());
    }
}
