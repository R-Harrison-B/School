//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Iterating through Philosophy
// Files: jsoup.jar
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
import java.util.Iterator;

/**
 * Class used to create and iterator that iterates a finite amount of times
 * 
 * @author Harrison Bell
 *
 * @param <T>
 */
public class FiniteIterator<T> implements Iterator<T> {
  private InfiniteIterator<T> infinite; // Infinite iterator used to iterate infinitely
  private int length; // Length that iteration should occur
  private int counter; // Length that iteration has occurred
  private boolean finite = true; // Variable to see if limit has been reached

  /**
   * Constructor for FiniteIterator objects to iterate finitely
   * 
   * @param InfiniteIterator infinite used to iterate infinitely
   * @param                  int length limit for iteration
   */
  public FiniteIterator(InfiniteIterator<T> infinite, int length) {
    this.infinite = infinite;
    this.length = length;
  }

  /*
   * Return false if limit has been reached
   * 
   * @see java.util.Iterator#hasNext()
   */
  @Override
  public boolean hasNext() {
    return finite;
  }

  /*
   * Run InfiniteIterator.next() until limit is reached
   * 
   * @see java.util.Iterator#next()
   */
  @Override
  public T next() {
    if (counter < length - 1) {
      counter++;
      return infinite.next();
    } else {
      finite = false;
      return infinite.next();
    }
  }
}

