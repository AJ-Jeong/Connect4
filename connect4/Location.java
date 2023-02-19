package connect4;
public class Location
{
    private int x, y;

    // Constructor
    public Location( int r, int c )
    {
        x = r;
        y = c;
    }

    // Returns this location's row
    public int getRow()
    {
        return x;
    }

    // Returns this location's column
    public int getCol()
    {
        return y;
    }
}
