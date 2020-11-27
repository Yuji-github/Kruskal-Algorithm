import java.util.ArrayList;
import static java.lang.Math.round;


//purpose
//store imported data only
public class hotspot
{
    //global variable
    private int ID;
    private double X, Y;
    private int index;

    public hotspot(int ID, double x, double y, int index)
    {
        this.ID =  ID;
        this.X = x;
        this.Y = y;
        this.index = index;
    }

    public hotspot(int ID, double x, double y)
    {
        this.ID = ID;
        this.X = x;
        this.Y = y;
    }

    //getter
    public int getID()
    {
        return this.ID;
    }

    public double getX()
    {
        return this.X;
    }

    public double getY()
    {
        return this.Y;
    }

    public int getIndex() { return index; }
}
