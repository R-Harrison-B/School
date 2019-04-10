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
 * This class solves a version of the Josephus problem involving dinner
 * 
 * @author Harrison Bell
 *
 */
public class DessertSolvers {

  /**
   * This method iterates through a queue serving guest based on the skipping pattern and returns
   * the final guest to be served
   * 
   * @param int numberOfGuests the number of guests to be seated
   * @param int guestsSkipped the number of guest to skip each time around
   * @return Guest the final guest to be served
   */
  public static Guest firstDessertVariableSkips(int numberOfGuests, int guestsSkipped) {
    if (numberOfGuests < 1 || guestsSkipped < 0) {
      throw new IllegalArgumentException(
          "Error: numberOfGuests and guestsSkipped must be positive");
    } // throws exception if parameters aren't positive

    ServingQueue party = new ServingQueue(numberOfGuests);
    Guest guest;
    for (int i = 0; i < numberOfGuests; i++) {// Adds all the guests to the serving que
      guest = new Guest();
      party.add(guest);
    }
    boolean end = false;
    while (!end) {
      // Serves the guests based on the order they entered and on the rule for guestsSkipped
      guest = party.remove();
      if (party.isEmpty()) {
        return guest;
      }
      for (int i = 0; i < guestsSkipped; i++) {
        party.add(party.peek());
        party.remove();
      }
    }
    return new Guest();
  }

  /**
   * Private helper method the serves members of a party and then returns them in the order of
   * removal with the last person served now first
   * 
   * @param ServingQueue party intial party to serve
   * @param ServingQueue partyTwo queue to hold the served party in order of removal
   * @param              int guests the number of guests at the party
   * @return ServingQueue queue of party that was served in the original party
   */
  private static ServingQueue dessertQueue(ServingQueue party, ServingQueue partyTwo, int guests) {
    boolean end = false;
    while (!end) {
      // Serves the first party skipping one and saves it to the second party
      partyTwo.add(party.peek());
      party.remove();
      if (party.isEmpty()) {
        break;
      }
      party.add(party.peek());
      party.remove();
    }

    int counter = 0;
    Guest guest;
    while (counter != guests - 1) { // reorders the second party but with the last one first
      guest = partyTwo.remove();
      partyTwo.add(guest);
      counter++;
    }
    return partyTwo;
  }

  /**
   * This method iterates through a queue serving guest based on number of course to be served. The
   * order served on the first course translates to the next course and so on this continues. Return
   * the person who will be served dessert (the last course) first.
   * 
   * @param int numberOfGuests number of guests to be served
   * @param int coursesServed number of courses to be served
   * @return Guest the guest who will be served dessert first
   */
  public static Guest firstDessertVariableCourses(int numberOfGuests, int coursesServed) {
    if (numberOfGuests < 0 || coursesServed < 0) {
      throw new IllegalArgumentException(
          "Error: numberOfGuests and guestsSkipped must be positive");
    } // throws exception if parameters aren't positive
    ServingQueue party = new ServingQueue(numberOfGuests);
    ServingQueue partyTwo = new ServingQueue(numberOfGuests);
    Guest guest;
    // adds the guest to the party
    for (int i = 0; i < numberOfGuests; i++) {
      guest = new Guest();
      party.add(guest);
    }
    int endCounter = 0; // counter to determine when the last course is
    while (endCounter + 1 != coursesServed) {// runs dessertQueue until the last course
      party = dessertQueue(party, partyTwo, numberOfGuests);
      partyTwo = new ServingQueue(numberOfGuests);
      endCounter++;
    }
    return party.peek();// returns the first to be served the last course
  }
}
