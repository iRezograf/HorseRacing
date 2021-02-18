package dao;

import entity.Jokey;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class JokeyDAOTest {

    @Test
    public void testSave() {
        Jokey jokey = new Jokey();
        jokey.setId(12);
        jokey.setName("Victor");

        /** Here must be check on inserting into table jokey */
        Assert.assertEquals(jokey.getName(), "Victor");
    }
}