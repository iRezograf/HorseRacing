package entity;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class JokeyTest {

    @Test
    public void testTestToString() {
        Jokey jokey = new Jokey();
        jokey.setId(12);
        jokey.setName("Victor");
        Assert.assertEquals(jokey.toString(),"Jokey: {" +
                "id=" + jokey.id +
                ", name='" + jokey.name + '\'' +
                "}");
    }

    @Test
    public void testSetId() {
        Jokey jokey = new Jokey();
        jokey.setId(42);
        Assert.assertEquals(jokey.getId(), 42);
    }

    @Test
    public void testSetName() {
        Jokey jokey = new Jokey();
        jokey.setId(12);
        jokey.setName("Victor");
        Assert.assertEquals(jokey.getName(), "Victor");
    }
}