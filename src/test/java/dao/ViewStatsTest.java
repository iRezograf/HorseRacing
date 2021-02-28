package dao;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ViewStatsTest {
    ViewStats viewStats;
    @BeforeMethod
    public void setUp() {
       viewStats = new ViewStats();
    }

    @AfterMethod
    public void tearDown() {
    }

    @Test
    public void testViewJokeyWinner() {
        viewStats.viewJokeyWinner();
    }
}