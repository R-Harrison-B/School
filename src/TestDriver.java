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
import java.util.function.Function;
import java.util.ArrayList;

/**
 * This class runs tests on EvenNumbers, Finite and Infinite Iterator, and on Generator
 * 
 * @author Harrison Bell
 *
 */
public class TestDriver {

  /**
   * Test if EvenNumbers correctly returns even numbers
   * 
   * @return boolean true is test passed false otherwise
   */
  public static boolean testEvenNumbers() {
    EvenNumbers it = new EvenNumbers(44);
    if (it.next() != 44) {
      System.out.println("The first call of EvenNumbers.next() "
          + "did not return the value passed into its constructor.");
      return false;
    }
    if (it.next() != 46) {
      System.out.println(
          "The second call of EvenNumbers.next() " + "did not return the smallest even number, "
              + "that is larger than the previously returned number.");
      return false;
    }
    if (it.next() != 48) {
      System.out.println(
          "The third call of EvenNumbers.next() " + "did not return the smallest even number, "
              + "that is larger than the previously returned number.");
      return false;
    }
    if (it.hasNext() != true) {
      System.out.println("EvenNumbers.next() returned false, " + "but should always return true.");
      return false;
    }
    return true;
  }

  /**
   * Test if InfiniteIterator correctly returns powers of two
   * 
   * @return boolean true is test passed false otherwise
   */
  public static boolean testPowersOfTwo() {
    InfiniteIterator<Integer> it = new InfiniteIterator<Integer>(8, new NextPowerOfTwo());

    if (it.next() != 8) {
      System.out.println("The first call of InfiniteIterator.next() "
          + "did not return the number passed into its constructor.");
      return false;
    }
    if (it.next() != 16) {
      System.out.println("The second call of InfiniteIterator.next() "
          + "did not return the smallest power of two number, "
          + "that is larger than the previously returned number.");
      return false;
    }
    if (it.hasNext() != true) {
      System.out
          .println("InfiniteIterator.next() returned false, " + "but should always return true.");
      return false;
    }
    return true;
  }

  /**
   * Test if InfiniteIterator correctly adds smiles to Strings
   * 
   * @return boolean true is test passed false otherwise
   */
  public static boolean testAddExtraSmile() {
    InfiniteIterator<String> it = new InfiniteIterator<>("Hello", new AddExtraSmile());
    if (!it.next().equals("Hello")) {
      System.out.println("The first call of InfiniteIterator.next() "
          + "did not return the string passed into its constructor.");
      return false;
    }
    if (!it.next().contains(" :)")) {
      System.out.println("The second call of InfiniteIterator.next() "
          + "did not return the a string with one more :), "
          + "than the previously returned string.");
      return false;
    }
    if (it.hasNext() != true) {
      System.out
          .println("InfiniteIterator.next() returned false, " + "but should always return true.");
      return false;
    }
    return true;
  }

  /**
   * Test if FiniteIterator correctly returns a finite sequence of numbers
   * 
   * @return boolean true is test passed false otherwise
   */
  public static boolean testFiniteIterator() {
    InfiniteIterator<Integer> infinite = new InfiniteIterator<>(2, new NextPowerOfTwo());
    FiniteIterator<Integer> it = new FiniteIterator<>(infinite, 8);
    String s = "";
    while (it.hasNext())
      s += " " + it.next();
    if (!s.equals(" 2 4 8 16 32 64 128 256")) {
      System.out.println("Repeatedly called the next() method of a FiniteIterator,"
          + "and the incorrect valuese were returned:" + s);
      return false;
    }
    return true;
  }

  /**
   * Test if Generator correctly creates functioning iterators
   * 
   * @return boolean true is test passed false otherwise
   */
  public static boolean testGenerator() {
    Generator<Integer> one = new Generator<Integer>(2, new NextPowerOfTwo(), 3);
    FiniteIterator<Integer> it = (FiniteIterator<Integer>) one.iterator();
    ArrayList<Integer> two = new ArrayList<Integer>();
    Integer add = 0;

    while (it.hasNext()) {
      two.add(it.next());
    }
    for (Integer number : two) {
      add = add + number;
    }
    if (add != 14) {
      System.out.println("Test failed. add =" + add);
      return false;
    }
    return true;

  }

  /**
   * Main driver method to call tests
   * 
   * @param args
   */
  public static void main(String[] args) {
    if (testEvenNumbers()) {
      System.out.println("testEvenNumbers passed");
    }
    if (testPowersOfTwo()) {
      System.out.println("testPowersOfTwo passed");
    }
    if (testAddExtraSmile()) {
      System.out.println("testAddExtraSmile passed");
    }
    if (testFiniteIterator()) {
      System.out.println("testFiniteIterator passed");
    }
    if (testGenerator()) {
      System.out.println("testGenerator passed");
    }
  }
}


/**
 * Class to be used to add smiles to Strings
 * 
 * @author Harrison Bell
 *
 */
class AddExtraSmile implements Function<String, String> {
  @Override
  public String apply(String t) {
    return t + " :)";
  }
}


/**
 * Class to be used to find the next power of two
 * 
 * @author Harrison Bell
 *
 */
class NextPowerOfTwo implements Function<Integer, Integer> {
  @Override
  public Integer apply(Integer previous) {
    return 2 * previous;
  }
}

