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

import processing.core.PApplet;

public class Thing {
  private final String NAME; // the constant name identifying this object
  private boolean isActive; // active means thing is visible and can be interacted with
  private static PApplet processing = null;

  /**
   * initializes processing field
   * 
   * @param processing
   */
  public static void setProcessing(PApplet processing) {
    Thing.processing = processing;
  } // initializes processing field

  /**
   * return processing field
   * 
   * @return PApplet processing field
   */
  protected static PApplet getProcessing() { // accessor method to retrieve this static field
    return processing;
  }

  /**
   * Constructor for Thing
   * 
   * @param String name name of thing
   */
  public Thing(String name) {
    NAME = name;
    isActive = true;
  } // initialize name, and set isActive to true

  /**
   * returns true only when contents of name equal NAME
   * 
   * @param name
   * @return
   */
  public boolean hasName(String name) { // returns true only when contents of name equal NAME
    if (name.equals(NAME)) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * returns true only when isActive is true
   * 
   * @return boolean
   */
  public boolean isActive() { // returns true only when isActive is true
    if (isActive) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * changes isActive to true
   */
  public void activate() {
    isActive = true;
  } // changes isActive to true

  /**
   * changes isActive to false
   */
  public void deactivate() {
    isActive = false;
  } // changes isActive to false

  /**
   * subclass types will override this update() method to do more interesting things
   * 
   * @return
   */
  public Action update() {
    return null;
  } // this method returns null
  // subclass types will override this update() method to do more interesting things
}
