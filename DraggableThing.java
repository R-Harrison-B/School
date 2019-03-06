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

public class DraggableThing extends VisibleThing {
  private boolean mouseWasPressed; // similar to use in ClickableThing
  private boolean isDragging; // true when this object is being dragged by the user
  private int oldMouseX; // horizontal position of mouse during last update
  private int oldMouseY; // vertical position of mouse during last update

  /**
   * Constructor for DraggableThing
   * 
   * @param name
   * @param x
   * @param y
   */
  public DraggableThing(String name, int x, int y) {
    super(name, x, y);
    oldMouseX = x;
    oldMouseY = y;
  } // initialize new thing

  /**
   * Calls VisibleThing update(), then moves according to mouse drag each time isDragging changes
   * from true to false, the drop() method below will be called once and any action objects returned
   * from that method should then be returned from update()
   * 
   * @see VisibleThing#update()
   */
  @Override
  public Action update() {
    super.update();
    // If mouse is pressed over object then will drag object until mouse is released
    if (this.isOver(getProcessing().mouseX, getProcessing().mouseY)
        && getProcessing().mousePressed) {
      if (!mouseWasPressed) {
        isDragging = true;
        mouseWasPressed = true;
      }
    }
    if (isDragging) {
      int x = getProcessing().mouseX - oldMouseX;
      int y = getProcessing().mouseY - oldMouseY;
      this.move(x, y);
      oldMouseX = getProcessing().mouseX;
      oldMouseY = getProcessing().mouseY;
    }
    if (isDragging && (getProcessing().mousePressed == false)) {
      isDragging = false;
      mouseWasPressed = false;
      return drop();
    }
    // If mouse was previous pressed will not be able to drag another object.
    if (getProcessing().mousePressed) {
      mouseWasPressed = true;
    } else {
      mouseWasPressed = false;
    }
    return null;
  }

  /**
   * Subclass types will override this drop() method to do more interesting things
   * 
   * @return
   */
  protected Action drop() {
    return null;
  } // this method returns null
}
