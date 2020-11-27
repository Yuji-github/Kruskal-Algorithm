//purpose
//calculating Kruskal’s Algo

import java.util.ArrayList;

import static java.lang.Math.round;
import static java.util.Collections.sort;

public class KruskalAlgo
{
    private ArrayList<hotspot> myHotSpot = new ArrayList<>(); //these values go to the kruskal
    private ArrayList<edge> myEdge = new ArrayList<>(); //provide min edge for kruskal
    private ArrayList<hotspot>[] kruskal; //store find set values

    private int[] parent; //store parent of edge
    private int cluster;
    private int edge; //if need to handle multiple kruskal's change to private static int edge for sharing

    private double interDistance; //maximum min inter distance between connected vertex

    public KruskalAlgo() { } //nothing to create atm

    public void setMyEdge(ArrayList<edge> myEdge) { this.myEdge = myEdge; }

    public void setMyHotSpot(ArrayList<hotspot> myHotSpot) { this.myHotSpot = myHotSpot; }

    //disjoint set = make set + find set + merge

    public void disjoint(int new_vertex, int stop)
    {
        double minEdge;
        int start, end;

        cluster = myHotSpot.size();

        int stoper = stop; //in the main called cluster

        //this is for printing out the results
        kruskal = new ArrayList[new_vertex];
        for(int i=0; i < new_vertex; i++)
        {
            kruskal[i] = new ArrayList<>();
        }

        //make set itself
        parent = new int[new_vertex];
        for(int i=0; i < new_vertex; i++)
        {
            parent[i] = i;
        }

        //sorted by weight

        sort(myEdge); //sort by weight

        if(stop ==1) //special case
        {
            for(int i=0; i < new_vertex; i++)
            {
                parent[i] = 0;
            }

            for(int i=0; i < new_vertex; i++)
            {
                for(int j=0; j < new_vertex; j++)
                {
                    if(i == parent[j])
                    {
                        kruskal[i].add(new hotspot(myHotSpot.get(j).getID(), myHotSpot.get(j).getX(), myHotSpot.get(j).getY()));
                    }
                }
            }

            double x =0;
            double y =0;
            StringBuffer name;
            int number = 1;
            for(int i=0; i < new_vertex; i++)
            {
                name = new StringBuffer();
                x =0;
                y =0;
                if(!kruskal[i].isEmpty())
                {
                    for(int j=0; j < kruskal[i].size(); j++)
                    {
                        name.append(" "+ kruskal[i].get(j).getID() +",");
                        x += kruskal[i].get(j).getX();
                        y += kruskal[i].get(j).getY();
                    }

                    x = x/kruskal[i].size();
                    y = y/kruskal[i].size();

                    System.out.println("Station " +  number +":");
                    System.out.println(String.format("Coordinates { %-1.2f, %-1.2f }", x, y));
                    System.out.println("HotSpots {"+ name +" }");
                    number++;
                }
            }

            System.out.println("\nInter-Clustering Distance: " + 0.00); //end of the kruskal's Algo

        }
        else
        {
            while(cluster > stoper) // clusters are stopper of the this methods
            {
                // ex)      1.41 <--- min edge value
                // start --------- end point

                minEdge = myEdge.get(edge).getWeight(); //always come first values
                start =  myEdge.get(edge).getStart(); //start value
                end = myEdge.get(edge).getEnd(); //get end value

                merge(findSet(start), findSet(end)); //find set always return true parent values

                edge++;
            }

            interDistance = myEdge.get(edge+1).getWeight(); //find the inter cluster distance

            //adding values to kruskal arraylist array base on parent index
            //following the sample format and 2 clusters
            //get parent will be like
            //parent[] = 0, 0, 0, 3, 3


            //store the data based on the parent into the arraylist of array
            for(int i=0; i < new_vertex; i++)
            {
                for(int j=0; j < new_vertex; j++)
                {
                    if(i == parent[j])
                    {
                        kruskal[i].add(new hotspot(myHotSpot.get(j).getID(), myHotSpot.get(j).getX(), myHotSpot.get(j).getY(), parent[j]));
                    }
                }
            }

            int check =0;

            while(cluster != check)
            {
                for(int i=0; i <new_vertex; i++)
                {
                    if(!kruskal[i].isEmpty())
                    {
                        check++;
                    }
                }

                for(int i=0; i <new_vertex; i++) {
                    for (int j = 0; j < kruskal[i].size(); j++)
                    {
                        if (i != kruskal[i].get(j).getIndex())
                        {
                            parent[j] = i;
                            kruskal[i].add(new hotspot(myHotSpot.get(j).getID(), myHotSpot.get(j).getX(), myHotSpot.get(j).getY(), parent[j]));
                        }
                    }
                }
            }

            //section for printing out

            double x =0;
            double y =0;
            StringBuffer name;
            int number = 1;
            for(int i=0; i < new_vertex; i++)
            {
                name = new StringBuffer();
                x =0;
                y =0;
                if(!kruskal[i].isEmpty())
                {
                    for(int j=0; j < kruskal[i].size(); j++)
                    {
                        name.append(" "+ kruskal[i].get(j).getID() +",");
                        x += kruskal[i].get(j).getX();
                        y += kruskal[i].get(j).getY();
                    }

                    x = x/kruskal[i].size();
                    y = y/kruskal[i].size();

                    System.out.println("Station " +  number +":");
                    System.out.println(String.format("Coordinates { %-1.2f, %-1.2f }", x, y));
                    System.out.println("HotSpots {"+ name +" }");
                    number++;
                }
            }
            System.out.println("\nInter-Clustering Distance: " + interDistance); //end of the kruskal's Algo
        }
    }


    public int findSet(int i) //return original make set value
    {
        while(i != parent[i])
        {
            i = parent[i];
        }
        return i;
    }

    public void merge(int start, int end) //merge the parent array
    {
        parent[end] = start;
        cluster--;
    }

}
