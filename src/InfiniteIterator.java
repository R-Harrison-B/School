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
import java.util.function.Function;

/**
 * Class used to create and iterator that iterates an infinite amount of times
 * 
 * @author Harrison Bell
 *
 * @param <T>
 */
public class InfiniteIterator<T> implements Iterator<T> {
  private T type; // Determines the type that this iterator will be run on
  private Function<T, T> function; // Determine the function that this iterator will do
  private boolean first = true; // Determines if this is the first iteration

  /**
   * Constructor for FiniteIterator objects to iterate infinitely
   * 
   * @param T type generic variable to decide what type of data to deal with
   * @param   Function<T,T> function function that the iterator will do with the data
   */
  public InfiniteIterator(T type, Function<T, T> function) {
    this.type = type;
    this.function = function;
  }

  /*
   * Always returns true
   * 
   * @see java.util.Iterator#hasNext()
   */
  @Override
  public boolean hasNext() {
    return true;
  }

  /*
   * Runs the function on T type data and stores the result for next iteration
   * 
   * @see java.util.Iterator#next()
   */
  @Override
  public T next() {
    if (first) {
      first = false;
      return type;
    }
    type = function.apply(type);

    return type;
  }

}
