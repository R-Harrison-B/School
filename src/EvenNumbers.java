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
 * This class iterates through even numbers
 * 
 * @author Harrison Bell
 *
 */
public class EvenNumbers implements Iterator<Integer> {
  private Integer number; // This represents the intial number
  private boolean first = true; // This represents the first number returned

  /**
   * Constructor for creating EvenNumber iterator
   * 
   * @param Integer number number to begin with
   */
  public EvenNumbers(Integer number) {
    this.number = number;
  }

  /*
   * Checks if there is a next number to iterate to always returns true in this case
   * 
   * @see java.util.Iterator#hasNext()
   */
  @Override
  public boolean hasNext() {
    return true;
  }

  /*
   * Returns number then adds two to it
   * 
   * @see java.util.Iterator#next()
   */
  @Override
  public Integer next() {
    if (first) {
      first = false;
      return number;
    }
    number++;
    number++;

    return number;
  }
}
