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
 * This class creates a single node in the Linked list
 * 
 * @author Harrison Bell
 *
 */
public class LinkedBoxNode {
  private Box box; // box that represents the data for this Linked node
  private LinkedBoxNode next; // reference to the next Linked Box Node

  // constructors
  /**
   * Creates a new LinkedBoxNode object with a given box and without referring to any next
   * LinkedBoxNode
   * 
   * @param box box to put in node
   */
  public LinkedBoxNode(Box box) {
    this.box = box;
  }

  /**
   * Creates a new LinkedBoxNode object and sets its instance fields box and next to the specified
   * ones
   * 
   * @param box           box to put in node
   * @param LinkedBoxNode node next in list
   */
  public LinkedBoxNode(Box box, LinkedBoxNode next) {
    this.box = box;
    this.next = next;
  }

  // getters and setters methods
  /**
   * Returns next node
   * 
   * @return LinkedBoxNode next node
   */
  public LinkedBoxNode getNext() {
    return this.next;
  }

  /**
   * Set next node for this node
   * 
   * @param LinkedBoxNode node to be set
   */
  public void setNext(LinkedBoxNode next) {
    this.next = next;
  }

  /**
   * Returns box for this node
   * 
   * @return Box box to for this node
   * 
   */
  public Box getBox() {
    return this.box;
  }

  /**
   * Set the box for this node
   * 
   * @param Box box to be set for this node
   */
  public void setBox(Box box) {
    this.box = box;
  }
}
