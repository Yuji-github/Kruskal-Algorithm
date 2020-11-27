/*
* Yuji Ishikawa
* c3338047
* */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import static java.util.Collections.sort;

public class kcluster
{
    //global variable
    private KruskalAlgo kruskal = new KruskalAlgo();
    private weight matrix = new weight();
    private ArrayList <hotspot> myHotSpot = new ArrayList<>();
    private ArrayList<edge> myEdge = new ArrayList<>();
    private int size, cluster =2;

    private void importFile() //given file name comes here
    {
        String importName = null;
        Scanner importStream = null;
        int ID = -999;
        double X = -999;
        double Y = -999;

        try //this try is try to access the file
        {
            System.out.println("...Importing...");
            //importName = fileName;
            importName = "/Users/YIshi/IdeaProjects/kcluster/src/test.txt"; //test
            importStream = new Scanner(new File(importName));

            while(importStream.hasNextLine())
            {
                try //this try is try to scan the contexts
                {
                    String line = importStream.nextLine(); //reading lines
                    if(line.equals("")) //to preventing to stop run when the beginning of the line is nothing
                    {
                        continue;
                    }

                    String[] parts = line.split(","); //sprit the lines by space

                    /* assume this is import format
                        ID  X   Y
                        1,1.0,1.0
                        2,2.0,2.0
                        3,3.0,5.0
                        4,7.0,8.0
                        5,8.0,7.0
                     */

                    try
                    {
                        ID = Integer.parseInt(parts[0]); // for change int array no.0

                        if (ID < 0) // exit negative number
                        {
                            System.err.println("Negative ID");
                            System.exit(0);
                        }
                    }
                    catch (NumberFormatException e) // if imported value is 'a,b..' reject
                    {
                        System.err.println("Invalid value");
                        System.exit(0);
                    }

                    try
                    {
                         X = Double.parseDouble(parts[1]); // for change double array no.1

                        if (X < 0) // exit negative number
                        {
                            System.err.println("Test Negative value");
                            System.exit(0);
                        }
                    }
                    catch (NumberFormatException e) // if imported value is 'a,b..' reject
                    {
                        System.err.println("Invalid value");
                        System.exit(0);
                    }

                    try
                    {
                         Y = Double.parseDouble(parts[2]);

                        if (Y < 0) // exit negative number
                        {
                            System.err.println("Negative value");
                            System.exit(0);
                        }
                    }
                    catch (NumberFormatException e) // if imported value is 'a,b..' reject
                    {
                        System.err.println("Invalid value");
                        System.exit(0);
                    }

                    myHotSpot.add(new hotspot(ID, X, Y));

                    size = myHotSpot.size();
                } //end of try to read

                catch(ArrayIndexOutOfBoundsException a)
                {
                    System.err.println("Invalid Line Format: Not Enough Information");
                    System.exit(0);
                }
                catch(NoSuchElementException | NullPointerException n)
                {
                    System.err.println(n.getMessage());
                    System.exit(0);
                }
            } // end of while loop

            matrix.setMyEdge(myEdge); //allow to use this edge array to weight class
            matrix.setMyHotSpot(myHotSpot); //allow to use this hotspot to weight class

        } // end of try to access the files

        catch(FileNotFoundException e) //catch for access files' errors
        {
            System.err.println("Error Opening The File " + importName);
            System.exit(0);
        }
        finally //finally done to store the values from the text file
        {
            if(importStream !=null)
            {
                importStream.close(); //closing import stream for the next
            }
        }
    }

   private boolean check()
   {
       ArrayList<Integer> myID = new ArrayList<Integer>();

       for (int i = 0; i < myHotSpot.size(); i++)
       {
           myID.add(myHotSpot.get(i).getID());
       }

       sort(myID);// 311 sort 113

       for (int i = 0; i < myID.size()-1; i++) // myID.size() is over size so need -1
       {
           if (myID.get(i) == myID.get(i + 1))  //like 113
           {
               return false;
           }
       }
       return true;
   }


    private void run()  //here is actual main runs for security reasons       String fileName
    {
        importFile(); //given name from Java cmd goes to importing function    fileName
        if(check()) // this is ture
        {
            System.out.println("Hello and welcome to Kruskal’s Clustering! ");
            System.out.println("The weighted graph of hotspots:");
            matrix.matrix(size); //store date as one array, the size uses for break the line

            System.out.println("\n");

            System.out.println("There are " + size + " hotspots.");
            System.out.println("You have requested " + cluster + " temporary fire stations.\n");

            kruskal.setMyEdge(myEdge);
            kruskal.setMyHotSpot(myHotSpot);

            kruskal.disjoint(size, cluster);

            System.out.println("\nThank you for using Kruskal's Clustering. Bye.");
        }
        else // sort myID is false not unique
        {
            System.err.println("Invalid ID");
            System.exit(0);
        }
    }



    public static void main(String[] args)
    {
        kcluster sim = new kcluster();
//        String fileName = args[0]; //this is for java cmd and any file name can take and user can gives any file from the cmd
//
//        try
//        {
//            int cluster = Integer.parseInt(args[1]); // for change int array no.0
//
//            if (cluster <= 0) // exit negative number
//            {
//                System.err.println("No Cluster");
//                System.exit(0);
//            }
//        }
//        catch (NumberFormatException e) // if imported value is 'a,b..' reject
//        {
//            System.err.println("Invalid value");
//            System.exit(0);
//        }
//        sim.run(fileName); //passing the file name to the run
        sim.run(); //do not submit with this
    }

}
