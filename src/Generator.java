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
 * This class creates either infinite or finite iterators
 * 
 * @author Harrison Bell
 *
 * @param <T>
 */
public class Generator<T> implements Iterable<T> {
  private T type; // Determines the type that this iterator will be run on
  private Function<T, T> function; // Determine the function that this iterator will do
  private int length; // Length that iteration should occur
  private boolean whichConstructor; // Determines whether to create finite or infinite iterator

  /**
   * Creates infinite iterator
   * 
   * @param T firstValue initial data to be acted on
   * @param   Function<T, T> generateNextFromLast function to be done on data
   */
  public Generator(T firstValue, Function<T, T> generateNextFromLast) {
    function = generateNextFromLast;
    type = firstValue;
    whichConstructor = true;
  }

  /**
   * Creates finite iterator
   * 
   * @param T firstValue initial data to be acted on
   * @param   Function<T, T> generateNextFromLast function to be done on data
   * @param   int length limit for the iteration to occur
   */
  public Generator(T firstValue, Function<T, T> generateNextFromLast, int length) {
    type = firstValue;
    function = generateNextFromLast;
    this.length = length;
    whichConstructor = false;
  }

  /*
   * Creates either a FiniteIterator or an InfiniteIterator depending on whichConstructor
   * 
   * @see java.lang.Iterable#iterator()
   */
  @Override
  public Iterator<T> iterator() {
    if (whichConstructor) {
      return new InfiniteIterator<T>(type, function);
    } else {
      return new FiniteIterator<T>(new InfiniteIterator<T>(type, function), length);
    }
  }
}
