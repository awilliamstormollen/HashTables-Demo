/**
* Hash Tables
* <p>
* This Demo takes file input of State Objects puts it into a Hash Table
* Seperate Chaining deals with collision.
* <p>
*
* @author Alexander Williams (n00678108)
* @version 8 Dec, 2017
*/


import java.util.*;
import java.io.*;
import java.text.*;
import java.lang.*;

public class HashTablesDemo
{

       /**
        * Main method, reads from a CSV file, then sorts data into stack and queue using a Binary Search Tree
        *
        * @paramn String[] args
        *
        * @return void
        */
        public static void main(String[] args)
        {
                // local declarations
                String name = "str";
                String capital = "str";
                String abbre = "str";        // abbreviation
                int population = 0;
                String region = "str";
                int seats = 0;               // U.S. house seats
                int nElems = 0;

                String stringPopulation = "str";
                String stringSeats = "str";

                HashTable  theHashTable = new HashTable (101);

                Scanner scannerObject = null;

                try
                {
                    scannerObject = new Scanner(new File("States5.csv"));
                }

                catch (FileNotFoundException e)
                {
                    System.out.println("Error opening the file");  // print error if cant open
                    System.exit(0);
                }

                scannerObject.useDelimiter(",|\n");

              String word1 = scannerObject.next();
                String word2 = scannerObject.next();
                String word3 = scannerObject.next();
                String word4 = scannerObject.next();
                String word5 = scannerObject.next();
                String word6 = scannerObject.next();

                int i = 0;



                do
                {
                    //scannerObject.nextLine();
                    name =             scannerObject.next();
                    capital =          scannerObject.next();
                    abbre =            scannerObject.next();
                    stringPopulation = scannerObject.next();
                    region =           scannerObject.next();
                    stringSeats =      scannerObject.next();

                    State state = new State (name, capital, abbre, population, region, seats);


                    try
                    {
                        population = Integer.valueOf(stringPopulation);  // converts strings to int
                        seats = Integer.valueOf(stringSeats);

                        theHashTable.insert(name, population);
                        i++;


                    }

                    catch (NumberFormatException e)
                    {

                    }

                  }while(scannerObject.hasNext() == true);

                  theHashTable.displayTable();


        }


}

/*
* Node class creates a Node to be used in the Array of Linked Lists.
*
* @author Alexander Williams
* @version 8 Dec, 2017
*/
class Node
{
  String name;
  int population;
  Node nextNode;

  /**
  * Node Constructor. creates node
  *
  * @paramn String state, int population
  *
  * @return void
  */
  public Node(String s, int p)
  {
    name = s;
    population = p;
  }

  /**
  * Prints node
  *
  * @paramn none
  *
  * @return void
  */
  public void printNode()
  {
    System.out.printf("%-25s%,10d\n", name, population);
  }
}

/*
* Link class creates a Link to be used in a Linked List.
*
* @author Alexander Williams
* @version 8 Dec, 2017
*/
class Link
{
  private Node iData;
  public Link next;

  /**
  * assigns node to iData
  *
  * @paramn node it
  *
  * @return void
  */
  public Link(Node it)
  { iData = it; }

  /**
  * returns iData
  *
  * @paramn none
  *
  * @return iData
  */
  public Node getKey()
  { return iData; }

  /**
  * Prints link
  *
  * @paramn none
  *
  * @return void
  */
  public void displayLink()
  { iData.printNode(); }
}// end link class


class HashTable
{
  private LinkedList[] hashArray;
  private int arraySize;

  /**
  * HashTable Constructor
  *
  * @paramn size
  *
  * @return void
  */
  public HashTable(int size)
  {
    arraySize = size;
    hashArray = new LinkedList[arraySize];
    for(int j = 0; j<arraySize; j++)
    {
      hashArray[j] = new LinkedList();
    }
  }

  /**
  * Prints table, calls displayList
  *
  * @paramn none
  *
  * @return void
  */
  public void displayTable()
  {
    for(int j = 0; j < arraySize; j++)
    {
      System.out.print(j + ". ");
      hashArray[j].displayList();
    }
  }

  /**
  * Calculates a hashvalue from a string
  *
  * @paramn StateName string
  *
  * @return hashvalue to be assigned
  */
  public int hashFunc(String key )
  {
    char[] array = new char[100];
    array = key.toCharArray();
    int total = 0;
    int value;

    for(int i = 0; i < array.length; i++)
    {
      value = (int)array[i];
      total += value;

    }
    return total % 101;
  }

  /**
  * Inserts data into hashtable, creates a node, puts node into array of linked lists
  *
  * @paramn state name and population
  *
  * @return void
  */
  public void insert(String name, int population)
  {
    Node node = new Node(name, population);
    int hashVal = hashFunc(name);
    hashArray[hashVal].insertFirst(node);
  }

/*
  public void delete(String key)
  {
    int hashVal = hashFunc();
    hashArray[hashVal].delete(key);
  }

  public Link find(String key)
  {
    int hashVal = hashFunc(key);
    Link theLink = hashArray[hashVal].find(key);
    return theLink;
  }
*/

/**
* Linked list class creates a linked list made of links, which contain nodes
*
* @author Alexander Williams
* @version 08 Dec, 2017
*/
  class LinkedList
  {
    private Link first;

    /**
    * Linked list constructor. creates linked list and assigned first to null
    *
    * @paramn none
    *
    * @return void
    */
    public LinkedList()
    {
      first = null;
    }

    /**
    * Prints lets you know if linked list is empty
    *
    * @paramn none
    *
    * @return first = null
    */
    public boolean isEmpty()
    {
      return (first==null);
    }

    /**
    * Prints inserts a link sequintially
    *
    * @paramn node
    *
    * @return void
    */
    public void insertFirst(Node node)
    {
      Link newLink = new Link(node);
      newLink.next = first;
      first = newLink;
    }

    /*public Node deleteFirst()
    {
      Link temp = first;
      first = first.next;
      return temp.iData;
    }*/

    /**
    * Prints list
    *
    * @paramn none
    *
    * @return void
    */
    public void displayList()
    {
      Link current = first;
      while(current != null)
      {
        current.displayLink();
        current = current.next;
      }

      System.out.println("");
    }
  }



}


/**
* State class is divided into several attributes to be used in the creation of a State object.
*
* @author Alexander Williams
* @version 08 Dec, 2017
*/
class State
{
    public String name;
    public String capital;
    public String abbre;     // abbreviation
    public int population;
    public String region;
    public int seats;

    public State(String name, String capital, String abbre, int population, String region, int seats)
    {
        this.name =       name;
        this.capital =    capital;
        this.abbre =      abbre;
        this.population = population;
        this.region =     region;
        this.seats =      seats;
    }

    /**
    * Getters, get attribute stored in object array
    *
    * @paramn
    *
    * @return name
    */
    public String getName()
    {
        return name;
    }

    /**
    * Getters, get attribute stored in object array
    *
    * @paramn
    *
    * @return capital
    */
    public String getCapital()
    {
        return capital;
    }

    /**
    * Getters, get attribute stored in object array
    *
    * @paramn
    *
    * @return abbreviation
    */
    public String getAbbre()
    {
        return abbre;
    }

    /**
    * Getters, get attribute stored in object array
    *
    * @paramn
    *
    * @return population
    */
    public int getPopulation()
    {
        return population;
    }

    /**
    * Getters, get attribute stored in object array
    *
    * @paramn
    *
    * @return region
    */
    public String getRegion()
    {
        return region;
    }

    /**
    * Getters, get attribute stored in object array
    *
    * @paramn
    *
    * @return seats
    */
    public int getSeats()
    {
        return seats;
    }

    /**
    * Setters, set attribute stored in object array
    *
    * @paramn state name
    *
    * @return void
    */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
    * Setters, set attribute stored in object array
    *
    * @paramn state capital
    *
    * @return void
    */
    public void setCapital(String capital)
    {
        this.capital = capital;
    }

    /**
    * Setters, set attribute stored in object array
    *
    * @paramn state abbreviation
    *
    * @return void
    */
    public void setAbbre(String abbre)
    {
        this.abbre = abbre;
    }

    /**
    * Setters, set attribute stored in object array
    *
    * @paramn state population
    *
    * @return void
    */
    public void setPopulation(int population)
    {
        this.population = population;
    }

    /**
    * Setters, set attribute stored in object array
    *
    * @paramn state region
    *
    * @return void
    */
    public void setRegion(String region)
    {
        this.region = region;
    }

    /**
    * Setters, set attribute stored in object array
    *
    * @paramn state seats
    *
    * @return void
    */
    public void setSeats(int seats)
    {
        this.seats = seats;
    }

    /**
    * compares strings
    *
    * @paramn two strings to compare
    *
    * @return value of comparison
    */
    public int compareTo(String a, String b)
    {
        return a.compareTo(b);
    }

    /**
    * Displays a state in the state object.  Loop for full database
    *
    * @paramn
    *
    * @return void
    */
    public void displayState()
    {
        System.out.printf(name + "          " + capital + "          " + abbre + "          " + population + "          " + region + "          " + seats );
        System.out.printf("\n");

        System.out.printf("\n");
    }


    /**
    * displays attributes in state object
    *
    * @paramn
    *
    * @return String of state object
    */
    public String info()
    {
        return "State Name: " + name + "\n" + "Capital City: " + capital + "\n" + "State Abbr: " + abbre + "\n" +
        "State Population: " + population + "\n" + "Region: " + region + "\n" + "U.S. House Seats: " + seats + "\n";
    }
} // end program

