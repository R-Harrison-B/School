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
import java.util.*;

/**
 * Creates box object with weight and color to be used in linked list
 * 
 * @author Harrison Bell
 *
 */
public class Box implements Comparable<Box> {
  private static Random randGen = new Random(); // generator of random numbers
  private int color; // color of this box
  private int weight; // weight of this box in lbs between 1 inclusive and 31 exclusive

  /**
   * Creates a new Box and initializes it instance fields color and weight to random values
   */
  public Box() {
    this.color = randGen.nextInt();
    this.weight = randGen.nextInt((30 - 1) + 1) + 1;
  }

  /**
   * Creates a new Box and initializes its instance fields color and weight to the specified values
   * Throws IllegalArgumentException if the provided weight value is out of the range of [1..30]
   * 
   * @param color
   * @param weight
   */
  public Box(int color, int weight) {
    this.color = color;
    if (weight < 31 && weight >= 1) {
      this.weight = weight;
    } else {
      throw new IllegalArgumentException("Number is not in range of 1 and 30");
    }
  }

  /**
   * returns true if the specified other object is a Box and this box and other have the same color
   * and same weight. Otherwise, it returns false.
   * 
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object other) {
    if (other instanceof Box) {
      Box other1 = (Box) other;
      if ((other1.weight == this.weight) && (other1.color == this.color)) {
        return true;
      } else
        return false;
    } else
      return false;

  } // equals method defined in Object class

  /**
   * Compares the weight of two boxes
   * 
   * @see java.lang.Comparable#compareTo(java.lang.Object)
   */
  @Override
  public int compareTo(Box otherBox) {
    if (otherBox.weight > this.weight) {
      return -1;
    }
    if (otherBox.weight < this.weight) {
      return 1;
    }
    if (otherBox.weight == this.weight) {
      return 0;
    }
    return 0;
  }

  /**
   * Return box color
   * 
   * @return int box color
   */
  public int getColor() {
    return this.color;
  } // Getter for the instance field color of this box

  /**
   * Returns box weight
   * 
   * @return int box weight
   */
  public int getWeight() {
    return this.weight;
  } // Getter for the instance field weight of this box
}
