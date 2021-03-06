package datasturctures.link.sortedList;


class Link {
  public long dData;                  // data item
  public Link next;                   // next LinkInterview in list

  // -------------------------------------------------------------
  public Link(long dd)                // constructor
  {
    dData = dd;
  }

  // -------------------------------------------------------------
  public void displayLink()           // display this LinkInterview
  {
    System.out.print(dData + " ");
  }
}  // end class Link

////////////////////////////////////////////////////////////////
class SortedList {
  private Link first;                 // ref to first item

  // -------------------------------------------------------------
  public SortedList()                 // constructor
  {
    first = null;
  }

  // -------------------------------------------------------------
  public boolean isEmpty()            // true if no links
  {
    return (first == null);
  }

  // -------------------------------------------------------------
  public void insert(long key)        // insert, in order
  {
    Link newLink = new Link(key);    // make new LinkInterview
    Link previous = null;            // start at first
    Link current = first;
    // until end of list,
    while (current != null && key > current.dData) {                             // or key > current,
      previous = current;
      current = current.next;       // go to next item
    }
    if (previous == null)               // at beginning of list
      first = newLink;              // first --> newLink
    else                             // not at beginning
      previous.next = newLink;      // old prev --> newLink
    newLink.next = current;          // newLink --> old currnt
  }  // end insert()

  // -------------------------------------------------------------
  public Link remove()           // return & delete first LinkInterview
  {                           // (assumes non-empty list)
    Link temp = first;               // save first
    first = first.next;              // delete first
    return temp;                     // return value
  }

  // -------------------------------------------------------------
  public void displayList() {
    System.out.print("List (first-->last): ");
    Link current = first;       // start at beginning of list
    while (current != null)      // until end of list,
    {
      current.displayLink();   // print data
      current = current.next;  // move to next LinkInterview
    }
    System.out.println("");
  }
}

class SortedListApp {
  public static void main(String[] args) {                            // create new list
    SortedList theSortedList = new SortedList();
    theSortedList.insert(20);    // insert 2 items
    theSortedList.insert(40);

    theSortedList.displayList(); // display list

    theSortedList.insert(10);    // insert 3 more items
    theSortedList.insert(30);
    theSortedList.insert(50);

    theSortedList.displayList(); // display list

    theSortedList.remove();      // remove an item

    theSortedList.displayList(); // display list
  }  // end main()
}
