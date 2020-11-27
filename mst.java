//purpose
//store mst data

public class mst implements Comparable <mst>
{
    private int start, end;
    private int parent;
    private double edge;
    private int children; //todo ??? need???


    public mst(int start, double edge, int end)
    {
        this.parent = -1; //set as -1 is no parent atm
        this.start = start;
        this.edge = edge;
        this.end = end;
    }

    public int getStart()
    {
        return start;
    }

    public double getEdge()
    {
        return edge;
    }

    public int getEnd()
    {
        return end;
    }

    public int getParent() { return parent; }

    public void setEnd(int end)
    {
        this.end = end;
    }

    public void setParent(int parent) { this.parent= parent; }


    @Override
    public int compareTo(mst compareMST)
    {
        if(this.getEnd() > compareMST.getEnd())
        {
            return 1;
        }
        else if(this.getEnd() < compareMST.getEnd())
        {
            return -1;
        }
        return 0;
    }
}
