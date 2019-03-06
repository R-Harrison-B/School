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

public class ClickableThing extends VisibleThing {
  private Action action; // action returned from update when this object is clicked
  private boolean mouseWasPressed; // tracks whether the mouse was pressed during the last update()

  /**
   * Constructor for ClickableThing
   * 
   * @param name
   * @param x
   * @param y
   * @param action
   */
  public ClickableThing(String name, int x, int y, Action action) {
    super(name, x, y);
    this.action = action;
  } // initializes this new object

  /**
   * calls VisibleThing update, then returns action only when mouse is first clicked
   * 
   * @see VisibleThing#update()
   */
  @Override
  public Action update() {
    super.update();
    if (this.isOver(getProcessing().mouseX, getProcessing().mouseY)) {
      if (!mouseWasPressed && getProcessing().mousePressed) {
        mouseWasPressed = true;
        return action;
      }
      // return null;
    }
    if (getProcessing().mousePressed) { // mouseWasPressed is true if mouse was clicked last update
      mouseWasPressed = true;
    } else {
      mouseWasPressed = false;
    }
    return null;
  } // calls VisibleThing update, then returns action only when mouse is
    // first clicked

}
