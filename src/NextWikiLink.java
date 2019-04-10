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
import java.io.IOException;
import java.util.function.Function;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.util.*;

/**
 * This class finds a Wiki page and then access the first item on that page and goes to that page
 * and repeats the process until given limit is reached or page cannot be found
 * 
 * @author Harrison Bell, others
 *
 */
public class NextWikiLink implements Function<String, String> {

  /*
   * Takes a wiki page and then finds the first link on that page and returns it returns error
   * message if page cannot be found
   * 
   * @see java.util.function.Function#apply(java.lang.Object)
   */
  @Override
  public String apply(String t) {
    try {
      // Download a Wikipedia page, using t in their internal link format: /wiki/Some_Subject
      Document doc = Jsoup.connect("https://en.wikipedia.org" + t).get();
      // Use .css selector to retrieve a collection of links from this page's description
      // "p a" selects links within paragraphs
      // ":not([title^='Help'])" skips pronunciations
      // ":not(sup a)" skips citations
      Elements links = doc.select("p a:not([title^='Help']):not(sup a)");
      // return the link attribute from the first element of this list
      return links.get(0).attr("href");
      // Otherwise return an appropriate error message:
    } catch (IOException | IllegalArgumentException e) {
      return "FAILED to find wikipedia page: " + t;
    } catch (IndexOutOfBoundsException e) {
      return "FAILED to find a link in wikipedia page: " + t;
    }
  }

  /**
   * Main driver method to run process on wiki pages
   * 
   * @param args
   */
  public static void main(String[] args) {
    // Implement your own Wikipedia crawling application here.
    // 1. prompt the user to enter a topic name and number of iterations to follow
    // 2. prepend "/wiki/" to the user's input, and replace spaces with underscores
    // 3. use a for-each loop to iterate through the number of links requested
    System.out.print("Enter a wikipedia page topic: ");
    Scanner scan = new Scanner(System.in);
    String input = scan.nextLine().trim();
    input = "/wiki/" + input;
    System.out.print("Enter the number of pages you'd like to step through: ");
    Integer pages = scan.nextInt();
    Generator<String> generate = new Generator<String>(input, new NextWikiLink(), pages);

    // Continuously prints out the pages until limit is reached
    for (String geter : generate) {
      System.out.println(geter);
      if (geter.contains("FAILED to find")) { // If error message is returned then end the process
        break;
      }
    }
    if (scan != null) { // Closes scanner after using it
      scan.close();
    }
  }
}
