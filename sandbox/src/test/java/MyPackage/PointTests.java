package MyPackage;

import myFirstPakage.Point;
import org.testng.Assert;
import org.testng.annotations.Test;



public class PointTests {

    @Test
    public void Test1(){
        Point a = new Point(4, 1);
        Point b = new Point(5, -3);
        Assert.assertEquals(a.len(b),4);
    }

    @Test
    public void Test2(){
        Point a = new Point(4, 1);
        Point b = new Point(5, -3);
        Assert.assertEquals(b.len(a),4);
    }
}
