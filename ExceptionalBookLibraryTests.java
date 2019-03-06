//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: ExceptionalLibrary
// Files: Book, Subscriber, Librarian, ExceptionalLibrary
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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
/**
 * @author Harrison Bell
 *
 */
import java.text.ParseException;

public class ExceptionalBookLibraryTests {

  /**
   * @return
   */
  public static boolean testLibraryParseCardBarCode() {
    ExceptionalLibrary test =
        new ExceptionalLibrary("address", "librarianUsername", "librarianPassword");
    boolean test1 = false;
    try {
      test.parseCardBarCode("Fred", 1);
    } catch (ParseException e) {
      test1 = true;
    }
    return test1;
  }

  /**
   * @return
   */
  public static boolean testLibraryParsePinCode() {
    ExceptionalLibrary test =
        new ExceptionalLibrary("address", "librarianUsername", "librarianPassword");
    boolean test1 = false;
    try {
      test.parsePinCode("red", 1);
    } catch (ParseException e) {
      test1 = true;
    }
    try {
      test.parsePinCode("000", 1);
    } catch (ParseException e) {
      test1 = true;
    }
    return test1;
  }

  /**
   * @return
   */
  public static boolean testLibraryParseRunLibrarianCheckoutBookCommand() {
    String[] commands = {"1", "2"};
    boolean test3 = false;
    ExceptionalLibrary test =
        new ExceptionalLibrary("address", "librarianUsername", "librarianPassword");

    try {
      test.parseRunLibrarianCheckoutBookCommand(commands);
    } catch (ParseException e) {
      test3 = true;
    }
    return test3;
  }

  /**
   * @return
   */
  public static boolean testLibraryParseBookId() {
    ExceptionalLibrary test =
        new ExceptionalLibrary("address", "librarianUsername", "librarianPassword");
    boolean test4 = false;
    try {
      test.parseBookId("red", 1);
    } catch (ParseException e) {
      test4 = true;
    }
    return test4;
  }

  /**
   * @return
   */
  public static boolean testLibraryParseRunSubscriberReturnBookCommand() {
    String[] commands = {"1"};
    boolean test5 = false;
    ExceptionalLibrary test =
        new ExceptionalLibrary("address", "librarianUsername", "librarianPassword");
    Subscriber sub = null;
    try {
      sub = new Subscriber("Name", 1001, "Addres", "0000000000");
    } catch (InstantiationException e) {

    }

    try {
      test.parseRunSubscriberReturnBookCommand(commands, sub);
    } catch (ParseException e) {
      test5 = true;
    }
    return test5;
  }

  public static void main(String[] args) {
    System.out.println("testLibraryParseCardBarCode() " + testLibraryParseCardBarCode());
    System.out.println("testLibraryParsePinCode " + testLibraryParsePinCode());
    System.out.println("testLibraryParseBookId " + testLibraryParseBookId());
    System.out.println("testLibraryParseRunLibrarianCheckoutBookCommand "
        + testLibraryParseRunLibrarianCheckoutBookCommand());
    System.out.println("testLibraryParseRunSubscriberReturnBookCommand "
        + testLibraryParseRunSubscriberReturnBookCommand());

  }
}
