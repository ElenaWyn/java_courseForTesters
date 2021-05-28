package myFirstPakage;

public class Point {
    public int x;
    public int y;

    public Point (int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int len(Point b)
    {
        int a = Math.abs(this.x - b.x);
        int c = Math.abs(this.y - b.y);

        return (int) Math.sqrt(a*a + c*c);

    }
}
