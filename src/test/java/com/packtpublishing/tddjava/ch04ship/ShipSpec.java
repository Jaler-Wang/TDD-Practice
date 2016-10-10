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

    public void Given11NWhenForwardThen01N(){
        ship.forward();
        location = ship.getCurrentLocation();

        Assert.assertTrue(new Location(new Point(1, 100), Direction.NORTH).equals(location));
    }

    public void Given11SWhenForwardThen10S(){
        location.setDirection(Direction.SOUTH);
        ship.setCurrentLocation(location);
        ship.forward();
        Assert.assertTrue(new Location(new Point(1, 2), Direction.SOUTH).equals(location));

    }
    public void Given11NWhenBackwardThen12N(){
        ship.backward();
        Assert.assertEquals(new Location(new Point(1, 2), Direction.NORTH), ship.getCurrentLocation());
    }
}
