//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Escape Program
// Files: P5Distributables.zip
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
// Persons: none
// Online Sources: none
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.util.ArrayList;

public class Action {
  private String message; // message printed by this action (or null to do nothing)
  private Thing thing; // Thing to be acted on

  /**
   * Constructor for Action
   * 
   * @param message
   */
  public Action(String message) { // initialize this new action
    this.message = message;
  }

  /**
   * Constructor for Action
   * 
   * @param Thing thing
   */
  public Action(Thing thing) { // initialize this new action
    this.thing = thing;
  }

  /**
   * Constructor for Action
   * 
   * @param String message
   * @param Thing  thing
   */
  public Action(String message, Thing thing) { // initialize this new action
    this.message = message;
    this.thing = thing;
  }

  /**
   * Activates thing if not null and adds to list of things
   * 
   * @param ArrayList thing list of things
   */
  public void act(ArrayList<Thing> thing) {
    if (this.message != null) {
      System.out.println(message);
    }
    if (this.thing != null) {
      this.thing.activate();
      thing.add(this.thing);
      this.thing = null;
    }
  } // when message is not null, message is printed to System.out
}
