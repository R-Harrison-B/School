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

import processing.core.PImage;
import java.io.File;


public class VisibleThing extends Thing {
  private PImage image; // the graphical representation of this thing
  private int x; // the horizontal position (in pixels of this thing's left side) side
  private int y; // the vertical position (in pixels of this thing's top side) top

  /**
   * Constructor for VisibleThing
   * 
   * @param String name name of thing
   * @param        int x horizontal position of thing
   * @param        int y vertical position of thing
   */
  public VisibleThing(String name, int x, int y) {
    // initialize this new thing
    // the image for this visible thing should be loaded from :
    // "images"+File.separator+ name +".png"
    super(name);
    this.x = x;
    this.y = y;
    image = getProcessing().loadImage("images" + File.separator + name + ".png");
  }

  /**
   * Draws VisibleThing objects
   * 
   * @see Thing#update()
   */
  @Override
  public Action update() {
    getProcessing().image(image, x, y);
    // draws image at its position before returning null
    return null;
  }

  /**
   * changes x by adding dx to it (and y by dy)
   * 
   * @param int dx amount of x to change
   * @param int dy amount of y to change
   */
  public void move(int dx, int dy) {// changes x by adding dx to it (and y by dy)
    this.x = this.x + dx;
    this.y = this.y + dy;
  }

  /**
   * return true only when point x,y is over image
   * 
   * @param x
   * @param y
   * @return
   */
  public boolean isOver(int x, int y) {
    if ((x >= this.x && x <= this.x + this.image.width)
        && (y >= this.y && y <= this.y + this.image.height)) {
      return true;
    } else {
      return false;
    }
  } // return true only when point x,y is over image

  /**
   * return true only when other's image overlaps this one's
   * 
   * @param other
   * @return
   */
  public boolean isOver(VisibleThing other) {
    if (other.y > this.y + this.image.height || other.x > this.x + this.image.width) {
      return false;
    }
    if (this.y > other.y + other.image.height || this.x > other.x + other.image.width) {
      return false;
    }
    return true;
  } // return true only when other's image overlaps this one's

}
