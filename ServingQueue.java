//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Dessert Queue
// Files: none
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
 * This class adds guest objects to a queue and serves them in order by removing them
 * 
 * @author Harrison Bell
 *
 */
public class ServingQueue {
  private Guest[] array; // array of guests
  private int front; // represents the front of the circular array
  private int back; // represents the back of the circular array
  private int size; // represents how many guest are in the queue

  /**
   * Creates a new array based queue with a capacity of "seatsAtTable" guests. This queue should be
   * initialized to be empty.
   * 
   * @param seatsAtTable the size of the array holding this queue data
   */
  public ServingQueue(int seatsAtTable) {
    array = new Guest[seatsAtTable];
    size = 0;
    front = -1;
    back = -1;
  }

  /**
   * Checks whether there are any guests in this serving queue.
   * 
   * @return true when this queue contains zero guests, and false otherwise.
   */
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Adds a single new guest to this queue (to be served after the others that were previously added
   * to the queue).
   * 
   * @param newGuest is the guest that is being added to this queue.
   * @throws IllegalStateException when called on a ServingQueue with an array that is full
   */
  public void add(Guest newGuest) {
    if (size == array.length) { // if there is no room for guests throws an exception
      throw new IllegalStateException("Queue is full");
    }
    if (back == -1) { // If there are no guests in the queue
      back++;
      front++;
      size++;
      array[back] = newGuest;
    } else if (back == array.length - 1 && front != 0) {
      // if the back is at the last index and the first index is open
      size++;
      back = 0;
      array[back] = newGuest;
    } else {// Otherwise add the guest, advance back and increment size
      back = (back + 1) % array.length;
      array[back] = newGuest;
      size++;
    }
    return;
  }

  /**
   * Accessor for the guest that has been in this queue for the longest. This method does not add or
   * remove any guests.
   * 
   * @return a reference to the guest that has been in this queue the longest.
   * @throws IllegalStateException when called on an empty ServingQueue
   */
  public Guest peek() {
    if (isEmpty()) {
      throw new IllegalStateException();
    } // If there is no front
    return array[front];
  }

  /**
   * Removes the guest that has been in this queue for the longest.
   * 
   * @return a reference to the specific guest that is being removed.
   * @throws IllegalStateException when called on an empty ServingQueue
   */
  public Guest remove() {
    if (front == -1) {
      throw new IllegalStateException("Queue is empty");
    } // Throw exception if queue is empty
    // else remove the front of the queue, advance front, and decrement size
    Guest guest = array[front];
    array[front] = null;
    front = (front + 1) % array.length;
    size--;

    return guest;
  }

  /**
   * The string representation of the guests in this queue should display each of the guests in this
   * queue (using their toString() implementation), and should display them in a comma separated
   * list that is surrounded by a set of square brackets. (this is similar to the formatting of
   * java.util.ArrayList.toString()). The order that these guests are presented in should be (from
   * left to right) the guest that has been in this queue the longest, to the guest that has been in
   * this queue the shortest. When called on an empty ServingQueue, returns "[]".
   * 
   * @return string representation of the ordered guests in this queue
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    if (front == -1) {
      return "[]";
    } // if queue is empty

    String display = "";
    if (back >= front) {
      // if the queues front is lower than the back cycle from the front till you reach the back
      for (int i = front; i <= back; i++) {
        display = display + "[" + array[i] + "],";
        if (i == back - 1) { // if at the last item remove the comma
          display = display + "[" + array[back] + "]";
          break;
        }
      }
    } else { // else cycle individually
      for (int i = front; i < size; i++) {
        if (i == back) {
          display = display + "[" + array[i] + "]";
          break;
        }
        display = display + "[" + array[i] + "],";
      }
      for (int i = 0; i <= back; i++) {
        if (i == back) {
          display = display + "[" + array[i] + "]";
          break;
        }
        display = display + "[" + array[i] + "],";
      }
    }
    return display;
  }
}
