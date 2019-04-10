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
 * This class runs Test on Guest, ServingQueue, DessertSolvers
 * 
 * @author Harrison Bell
 *
 */
public class QueueTests {

  /**
   * This tests if the push function on ServingQueue function correctly
   * 
   * @return boolean true if test passed, false otherwise
   */
  public static boolean testPush() {
    Guest.resetNextGuestIndex();
    ServingQueue testQ = new ServingQueue(10);
    Guest one = new Guest();
    Guest two = new Guest();
    Guest three = new Guest();
    Guest four = new Guest();
    Guest five = new Guest();

    testQ.add(one);
    testQ.add(two);
    testQ.add(three);
    testQ.add(four);
    testQ.add(five);


    if (testQ.toString().equals("[#1],[#2],[#3],[#4],[#5]")) {
      return true;
    }
    System.out.print(testQ.toString() + " ");
    return false;
  }

  /**
   * This tests if the pop function on ServingQueue functions correctly
   * 
   * @return boolean true if test passed, false otherwise
   */
  public static boolean testPop() {
    Guest.resetNextGuestIndex();
    ServingQueue testQ = new ServingQueue(5);

    Guest.resetNextGuestIndex();
    Guest one = new Guest();
    Guest two = new Guest();
    Guest three = new Guest();
    Guest four = new Guest();
    Guest five = new Guest();


    testQ.add(one);
    testQ.add(two);
    testQ.add(three);
    testQ.add(four);
    testQ.add(five);
    testQ.remove();


    // If remove correct removes item at the from of the queue return true
    if (testQ.toString().equals("[#2],[#3],[#4],[#5]")) {
      return true;
    }
    System.out.print(testQ.toString() + " ");
    return false;
  }

  /**
   * This tests if the peek function on ServingQueue function correctly
   * 
   * @return boolean true if test passed, false otherwise
   */
  public static boolean testPeek() {
    Guest.resetNextGuestIndex();
    ServingQueue testQ = new ServingQueue(5);

    Guest.resetNextGuestIndex();
    Guest one = new Guest();
    Guest two = new Guest();
    Guest three = new Guest();
    Guest four = new Guest();
    Guest five = new Guest();


    testQ.add(one);
    testQ.add(two);
    testQ.add(three);
    testQ.add(four);
    testQ.add(five);

    Guest front = testQ.peek();

    if (front.toString().equals("#1")) {
      return true;
    }
    System.out.print(front.toString() + " ");
    return false;
  }

  /**
   * This tests if the guest indices are reset correctly
   * 
   * @return boolean true if test passed, false otherwise
   */
  public static boolean testReset() {
    Guest.resetNextGuestIndex();
    Guest two = new Guest();
    Guest one = new Guest("fish");
    Guest three = new Guest();
    Guest.resetNextGuestIndex();
    Guest four = new Guest();
    Guest five = new Guest();
    Guest six = new Guest();
    String test = four.toString() + " " + five.toString() + " " + six.toString();
    if (test.equals("#1 #2 #3")) {
      return true;
    }

    return false;
  }

  /**
   * This tests if the guests with dietary restrictions are made correctly
   * 
   * @return boolean true if test passed, false otherwise
   */
  public static boolean testDiet() {
    Guest.resetNextGuestIndex();
    ServingQueue testQ = new ServingQueue(5);
    Guest two = new Guest();
    Guest one = new Guest();
    Guest three = new Guest("no fish");
    testQ.add(two);
    testQ.add(one);
    testQ.add(three);
    testQ.remove();

    if (two.toString().equals("#1") && one.toString().equals("#2")
        && three.toString().equals("#3(no fish)")) {
      return true;
    }
    System.out.print(one.toString());
    return false;
  }

  /**
   * This tests if the remove and add function on ServingQueue function correctly
   * 
   * @return boolean true if test passed, false otherwise
   */
  public static boolean testRemove() {
    Guest.resetNextGuestIndex();
    ServingQueue x = new ServingQueue(3);
    Guest one = new Guest();
    Guest two = new Guest();
    Guest three = new Guest();
    Guest four = new Guest();
    Guest five = new Guest();

    x.add(one);
    x.add(two);
    x.add(three);

    x.remove();
    x.remove();

    x.add(four);
    x.add(five);
    // If add and remove can function together return true
    if (x.toString().equals("[#3],[#4],[#5]")) {
      return true;
    }
    System.out.print(x.toString() + " ");
    return false;
  }

  /**
   * Main driver method to call tests
   * 
   * @param args
   */
  public static void main(String[] args) {
    System.out.println("testPush: " + testPush());
    System.out.println("testPop: " + testPop());
    System.out.println("testPeek: " + testPeek());
    System.out.println("testReset: " + testReset());
    System.out.println("testRemove: " + testRemove());
    System.out.println("testDiet: " + testDiet());

    Guest.resetNextGuestIndex();
  }

}
