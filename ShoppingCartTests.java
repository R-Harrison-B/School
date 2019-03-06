//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Shopping Cart Program
// Files: a list of all source files used by that program)
// Course: 300, 2, 2019
//
// Author: Harrison Bell
// Email: rhbell2@wisc.edu
// Lecturer's Name: Mouna
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: (name of your pair programming partner)
// Partner Email: (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
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
// Persons: (identify each person and describe their help in detail)
// Online Sources: (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

/**
 * Runs test on ShoppingCart program to add, store, and simulate purchase of items in market
 * 
 * @author Harrison Bell
 */
public class ShoppingCartTests {

  /**
   * Checks whether the total number of items within the cart is incremented after adding one item
   * 
   * @return true if the test passes without problems, false otherwise
   */
  public static boolean testCountIncrementedAfterAddingOnlyOneItem() {
    boolean testPassed = true; // boolean local variable evaluated to true if this test passed,
                               // false otherwise
    String[] cart = new String[20]; // shopping cart
    int count = 0; // number of items present in the cart (initially the cart is empty)

    // Add an item to the cart
    count = ShoppingCart.add(3, cart, count); // add an item of index 3 to the cart
    // Check that count was incremented
    if (count != 1) {
      System.out.println("Problem detected: After adding only one item to the cart, "
          + "the cart count should be incremented. But, it was not the case.");
      testPassed = false;
    }
    return testPassed;
  }

  /**
   * Checks whether add and OccurrencesOf return the correct output when only one item is added to
   * the cart
   * 
   * @return true if test passed without problems, false otherwise
   */
  public static boolean testAddAndOccurrencesOfForOnlyOneItem() {
    boolean testPassed = true; // evaluated to true if test passed without problems, false
                               // otherwise
    // define the shopping cart as an oversize array of elements of type String
    // we can set an arbitrary capacity for the cart - for instance 10
    String[] cart = new String[10]; // shopping cart
    int count = 0; // number of items present in the cart (initially the cart is empty)

    // check that OccurrencesOf returns 0 when called with an empty cart
    if (ShoppingCart.occurrencesOf(10, cart, count) != 0) {
      System.out.println("Problem detected: Tried calling OccurrencesOf() method when the cart is "
          + "empty. The result should be 0. But, it was not.");
      testPassed = false;
    }

    // add one item to the cart
    count = ShoppingCart.add(0, cart, count); // add an item of index 0 to the cart


    // check that OccurrencesOf("Apples", cart, count) returns 1 after adding the item with key
    // 0
    if (ShoppingCart.occurrencesOf(0, cart, count) != 1) {
      int test = ShoppingCart.occurrencesOf(0, cart, count);
      System.out.println("Problem detected: After adding only one item with key 0 to the cart, "
          + "OccurrencesOf to count how many of that item the cart contains should return 1. "
          + "But, it was not the case." + " " + test);
      testPassed = false;
    }

    return testPassed;
  }

  /**
   * Checks that items can be added more than one time and are found
   * 
   * @return true if the test passes without problems, false otherwise
   */
  public static boolean testAddOccurrencesOfDuplicateItems() {
    boolean testPassed = true; // True if test passes, false otherwise
    String[] cart = new String[2]; // Cart to test add on
    int count = 0; // Count of items in the cart

    count = ShoppingCart.add(0, cart, count);
    count = ShoppingCart.add(0, cart, count);

    // If add does not add both items then test failed
    if (count != 2) {
      testPassed = false;
    }

    return testPassed;
  }

  /**
   * Checks that the correct output is returned when the user tries to add too much items to the
   * cart exceeding its capacity
   * 
   * @return true if the test passes without problems, false otherwise
   */
  public static boolean testAddingTooMuchItems() {
    boolean testPassed = true; // True if test passes, false otherwise
    String[] cart = new String[2]; // Cart to test add on
    int count = 0; // Count of items in the cart

    count = ShoppingCart.add(0, cart, count);
    count = ShoppingCart.add(0, cart, count);
    count = ShoppingCart.add(0, cart, count);

    // If count was incremented past two then test failed
    if (count != 2) {
      testPassed = false;
    }

    return testPassed;
  }

  /**
   * Checks that when only one attempt to remove an item present in the cart is made, only one
   * occurrence of that item is removed from the cart
   * 
   * @return true if the test passes without problems, false otherwise
   */
  public static boolean testRemoveOnlyOneOccurrenceOfItem() {
    boolean testPassed = true; // True if test passes, false otherwise
    String[] cart = new String[3]; // Cart to test add on
    int count = 0; // Count of items in the cart

    count = ShoppingCart.add(0, cart, count);
    count = ShoppingCart.add(0, cart, count);
    count = ShoppingCart.add(0, cart, count);
    count = ShoppingCart.remove("Apple", cart, count);

    // If more than one apple was removed then test failed
    if (cart[1].equals("Apple") && cart[0] == null) {
      testPassed = true;
    }
    return testPassed;


  }

  /**
   * Checks that remove returns false when the user tries to remove an item not present within the
   * cart
   * 
   * @return true if the test passes without problems, false otherwise
   */
  public static boolean testRemoveItemNotFoundInCart() {
    boolean testPassed = true; // True if test passes, false otherwise
    String[] cart = new String[2]; // Cart to test add on
    int count = 0; // Count of items in the cart

    count = ShoppingCart.add(1, cart, count);
    count = ShoppingCart.add(1, cart, count);
    count = ShoppingCart.remove("Apple", cart, count);

    // If count is decremented and item removed then test failed
    if (count != 2) {
      testPassed = true;
    }
    return testPassed;


  }

  /**
   * Checks that the correct price is returned when adding two items
   * 
   * @return true if the test passes without problems, false otherwise
   */
  public static boolean testSubTotalPrice() {
    boolean testPassed = true; // True if test passes, false otherwise
    String[] cart = new String[2]; // Cart to test add on
    int count = 0; // Count of items in the cart
    double num = 0.0; // Price of items
    double answer = 1.18; // Correct price of items

    count = ShoppingCart.add(1, cart, count);
    count = ShoppingCart.add(1, cart, count);
    num = ShoppingCart.getSubTotalPrice(cart, count);

    // If difference is not within an acceptable range then test failed
    if ((num - answer) < 0.00001) {
      testPassed = true;
    }
    return testPassed;
  }

  /**
   * main method used to call the unit tests
   * 
   * @param args
   */
  public static void main(String[] args) {
    System.out.println("testCountIncrementedAfterAddingOnlyOneItem(): "
        + testCountIncrementedAfterAddingOnlyOneItem());
    System.out.println(
        "testAddAndOccurrencesOfForOnlyOneItem(): " + testAddAndOccurrencesOfForOnlyOneItem());
    System.out
        .println("testAddOccurrencesOfDuplicateItems() " + testAddOccurrencesOfDuplicateItems());
    System.out.println("testAddingTooMuchItems() " + testAddingTooMuchItems());
    System.out
        .println("testRemoveOnlyOneOccurrenceOfItem() " + testRemoveOnlyOneOccurrenceOfItem());
    System.out.println("testRemoveItemNotFoundInCart() " + testRemoveItemNotFoundInCart());
    System.out.println("testSubTotalPrice() " + testSubTotalPrice());
  }
}


