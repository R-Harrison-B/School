//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: StorageUnitOrganizer
// Files: none provided
// Course: 300, 2, 2019
//
// Author: Harrison Bell
// Email: rhbell2@wisc.edu
// Lecturer's Name: Mouna
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: none
// Partner Email: none
// Partner Lecturer's Name: none
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do. If you received no outside help from either type
// of source, then please explicitly indicate NONE.
//
// Persons: none
// Online Sources: none
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

/**
 * This class is the main class for creating a Linked list from scratch
 * 
 * @author Harrison Bell
 *
 */
public class LinkedBoxList {
  private LinkedBoxNode head; // head of this LinkedBoxList (refers to the element
  // stored at index 0 within this list)
  private int size; // number of boxes already stored in this list
  private int capacity; // capacity of this LinkedBoxList
  // maximum number of box elements that this LinkedBoxList
  // can store


  /**
   * Creates an empty LinkedBoxList with a given initial capacity
   * 
   * @param capacity
   */
  public LinkedBoxList(int capacity) {
    this.capacity = capacity;
  }

  /**
   * Returns the size of this list
   * 
   * @return
   */
  public int size() {
    return this.size;
  }

  /**
   * Return the capacity of this list
   * 
   * @return
   */
  public int getCapacity() {
    return this.capacity;
  }

  /**
   * Expands the capacity of this LinkedBoxList with the specified number a of additional elements
   * 
   * @param int a
   */
  public void expandCapacity(int a) {
    this.capacity = this.capacity + a;
  }

  /**
   * Checks whether this LinkedBoxList is empty returns true if this LinkedBoxList is empty, false
   * otherwise
   * 
   * @return
   */
  public boolean isEmpty() {
    if (this.size == 0) {
      return true;
    } else
      return false;
  }

  /**
   * Checks whether this LinkedBoxList is full Returns true if this list is full, false otherwise
   * 
   * @return
   */
  public boolean isFull() {
    if (this.size == this.capacity) {
      return true;
    } else
      return false;
  }

  /**
   * Adds a new box into this sorted list Throws IllegalArgumentException if newBox is null Throws
   * IllegalStateException if this list is full
   * 
   * @param newBox
   * @throws IllegalArgumentException
   * @throws IllegalStateException
   */
  public void add(Box newBox) throws IllegalArgumentException, IllegalStateException {
    if (newBox == null) {
      throw new IllegalArgumentException("Box is null");
    }
    if (this.isFull()) {
      throw new IllegalStateException("List is full");
    }
    LinkedBoxNode box = new LinkedBoxNode(newBox, null);
    LinkedBoxNode temp;

    if (this.isEmpty()) {
      head = new LinkedBoxNode(newBox, null);
      size++;
      return;
    } else if (newBox.compareTo(head.getBox()) == 1) {
      temp = head;
      head = box;
      head.setNext(temp);
      size++;
      return;
    } else if (newBox.compareTo(head.getBox()) == 0) {
      temp = head.getNext();
      head.setNext(box);
      box.setNext(temp);
      size++;
      return;
    } else {
      if (head.getNext() == null) {
        head.setNext(box);
        size++;
        return;
      }
      if (newBox.compareTo(head.getNext().getBox()) > 0) {
        box.setNext(head.getNext());
        head.setNext(box);
        size++;
        return;
      }
      LinkedBoxNode previous = head.getNext();
      LinkedBoxNode current = head;
      while (previous != null) {
        if (newBox.compareTo(previous.getBox()) > 0) {
          break;
        }
        current = previous;
        previous = previous.getNext();
      }
      box.setNext(current.getNext());
      current.setNext(box);
      size++;
      return;
    }

  }

  /**
   * Checks if this list contains a box that matches with (equals) a specific box object Returns
   * true if this list contains findBox, false otherwise
   * 
   * @param findBox
   * @return
   */
  public boolean contains(Box findBox) {
    LinkedBoxNode current = head;
    while (current != null) {
      if (current.getBox().equals(findBox)) {
        return true;
      }
      current = current.getNext();
    }
    return false;
  }

  /**
   * Returns a box stored in this list given its index Throws IndexOutOfBoundsException if index is
   * out of the range 0..size-1
   * 
   * @param index
   * @return
   * @throws IndexOutOfBoundsException
   */
  public Box get(int index) throws IndexOutOfBoundsException {
    if (index < 0 || index > size - 1) {
      throw new IndexOutOfBoundsException("Index out of bounds");
    }
    if (index == 0) {
      return head.getBox();
    }
    if (index == 1) {
      return head.getNext().getBox();
    }

    LinkedBoxNode current = head;

    int count = 0;
    while (current != null) {
      if (count == index) {
        return current.getBox();
      } else {
        current = current.getNext();
        count++;
      }
    }
    return null;
  }

  /**
   * Removes a returns the box stored at index from this LinkedBoxList Throws
   * IndexOutOfBoundsException if index is out of bounds. index should be in the range of [0..
   * size()-1]
   * 
   * @param index
   * @return
   * @throws IndexOutOfBoundsException
   */
  public Box remove(int index) throws IndexOutOfBoundsException {
    if (index < 0 || index > size - 1) {
      throw new IndexOutOfBoundsException("Index out of bounds");
    }
    Box box = head.getBox();
    LinkedBoxNode current = head.getNext();
    LinkedBoxNode previous = head;
    if (index == 0) {
      head = head.getNext();
      size--;
      return box;
    }
    if (index == 1) {
      box = head.getNext().getBox();
      head.setNext(head.getNext().getNext());
      size--;
      return box;
    }
    for (int i = 0; i < index; i++) {
      if (i == index - 1) {
        break;
      }
      previous = current;
      current = current.getNext();
    }
    box = current.getBox();
    previous.setNext(current.getNext());
    size--;
    return box;
  }

  /**
   * Removes all the boxes from this list
   * 
   */
  public void clear() {
    while (head != null) {
      this.remove(0);
    }
  }

  /**
   * Returns a String representation for this LinkedBoxList
   */
  @Override
  public String toString() {
    StringBuilder result = new StringBuilder(); // creates a StringBuilder object
    String newLine = System.getProperty("line.separator");
    result.append("------------------------------------------------" + newLine);
    if (!isEmpty()) {
      LinkedBoxNode runner = head;
      int index = 0;
      // traverse the list and add a String representation for each box
      while (runner != null) {
        result.insert(0, "Box at index " + index + ": " + runner.getBox().getWeight() + " lbs"
            + ": color " + runner.getBox().getColor() + newLine);
        runner = runner.getNext();
        index++;
      }
      result.insert(0, "Box List Content:" + newLine);
    }
    result.insert(0, "List size: " + size + " box(es)." + newLine);
    result.insert(0, "Box List is empty: " + isEmpty() + newLine);
    result.insert(0, "------------------------------------------------" + newLine);
    return result.toString();
  }

}

