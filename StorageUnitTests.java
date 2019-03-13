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
 * Class that runs test on Box, LinkedBosList, and LinkedBoxNode
 * 
 * @author Harrison Bell
 *
 */
public class StorageUnitTests {

  /**
   * Main driver method for StorageUnitTests
   * 
   * @param args
   */
  public static void main(String[] args) {
    System.out.println("TestAdd " + testAdd());
    System.out.println("testBoxEquals " + testBoxEquals());
    System.out.println("testBoxCompareTo " + testBoxCompareTo());
    System.out.println("testLinkedBoxListRemove " + testLinkedBoxListRemove());
    System.out.println("testLinkedBoxListIsFull " + testLinkedBoxListIsFull());
    System.out.println("testLinkedBoxListGet " + testLinkedBoxListGet());
  }

  /**
   * Checks whether the behavior of equals method is correct
   * 
   * @return boolean true if test passed false otherwise
   */
  public static boolean testBoxEquals() {
    Box one = new Box(1, 1);
    Box two = new Box(1, 1);
    if (one.equals(two)) {
      return true;
    }
    return false;
  }

  /**
   * Checks whether the behavior of compareTo method is correctly implemented
   * 
   * @return boolean true if test passed false otherwise
   */
  public static boolean testBoxCompareTo() {
    Box one = new Box(1, 1);
    Box two = new Box(1, 2);
    if (one.compareTo(two) == -1) {
      return true;
    } else
      return false;
  }

  /**
   * Checks whether the behavior of add method is correctly implemented
   * 
   * @return boolean true if test passed false otherwise
   * 
   */
  public static boolean testAdd() {
    Box one = new Box(1, 2);
    Box three = new Box(2, 3);
    Box four = new Box(3, 4);
    Box five = new Box(4, 5);
    Box six = new Box(5, 6);
    Box two = new Box(6, 7);
    Box seven = new Box(7, 8);
    Box eight = new Box(8, 9);
    Box nine = new Box(9, 10);
    Box ten = new Box(10, 1);

    LinkedBoxList test = new LinkedBoxList(50);
    test.add(one);
    test.add(three);
    test.add(four);
    test.add(five);
    test.add(six);
    test.add(two);
    test.add(seven);
    test.add(eight);
    test.add(nine);
    test.add(ten);

    if (test.get(0).getWeight() == 10) {
      return true;
    }
    System.out.println(test.toString());
    return false;
  }

  /**
   * Checks whether remove method defined in your LinkedBoxList works correctly
   * 
   * @return boolean true if test passed false otherwise
   */
  public static boolean testLinkedBoxListRemove() {
    Box one = new Box(1, 2);
    Box two = new Box(1, 8);
    Box three = new Box(1, 20);
    Box four = new Box(1, 1);
    LinkedBoxList test = new LinkedBoxList(4);
    test.add(one);
    test.add(two);
    test.add(three);
    test.add(four);
    test.remove(0);
    if (test.get(0).getWeight() == 8) {
      return true;
    } else {
      System.out.print(test.toString());
    }
    return false;
  }

  /**
   * Checks whether isFull method defined in your LinkedBoxList works correctly
   * 
   * @return boolean true if test passed false otherwise
   */
  public static boolean testLinkedBoxListIsFull() {
    Box one = new Box(1, 2);
    Box two = new Box(1, 8);
    Box three = new Box(1, 20);
    Box four = new Box(1, 1);
    LinkedBoxList test = new LinkedBoxList(4);
    test.add(one);
    test.add(two);
    test.add(three);
    test.add(four);
    if (test.isFull()) {
      return true;
    }
    return false;

  }

  /**
   * Checks whether get method defined in your LinkedBoxList works correctly
   *
   * @return boolean true if test passed false otherwise
   */
  public static boolean testLinkedBoxListGet() {
    Box one = new Box(1, 2);
    Box two = new Box(1, 8);
    Box three = new Box(1, 20);
    LinkedBoxList test = new LinkedBoxList(4);
    test.add(one);
    test.add(two);
    test.add(three);
    if (test.get(2).getWeight() == 2) {
      return true;
    }

    return false;
  }
}
