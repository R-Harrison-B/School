//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Shopping Cart Program
// Files: a list of all source files used by that program)
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
// Persons: (identify each person and describe their help in detail)
// Online Sources: (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.util.Scanner;

/**
 * Shopping cart program to add, store, and simulate purchase of items in market
 * @author Harrison Bell
 */
public class ShoppingCart {
  // Define final parameters
  private static final int CART_CAPACITY = 20; // shopping cart max capacity
  private static final double TAX_RATE = 0.05; // sales tax

  // a perfect-size two-dimensional array that stores the available items in the market
  // MARKET_ITEMS[i][0] refers to a String that represents the description of the item
  // identified by index i
  // MARKET_ITEMS[i][1] refers to a String that represents the unit price of the item
  // identified by index i in dollars.
  public static final String[][] MARKET_ITEMS = new String[][] {{"Apple", "$1.59"},
      {"Avocado", "$0.59"}, {"Banana", "$0.49"}, {"Beef", "$3.79"}, {"Blueberry", "$6.89"},
      {"Broccoli", "$1.79"}, {"Butter", "$4.59"}, {"Carrot", "$1.19"}, {"Cereal", "$3.69"},
      {"Cheese", "$3.49"}, {"Chicken", "$5.09"}, {"Chocolate", "$3.19"}, {"Cookie", "$9.5"},
      {"Cucumber", "$0.79"}, {"Eggs", "$3.09"}, {"Grape", "$2.29"}, {"Ice Cream", "$5.39"},
      {"Milk", "$2.09"}, {"Mushroom", "$1.79"}, {"Onion", "$0.79"}, {"Pepper", "$1.99"},
      {"Pizza", "$11.5"}, {"Potato", "$0.69"}, {"Spinach", "$3.09"}, {"Tomato", "$1.79"}};

  /**
   * Adds the item at given index to the cart
   * 
   * @param index index of item to add
   * @param cart  Shopping cart
   * @param count number of items present in the shopping cart
   * @return count in the cart after adding
   */
  public static int add(int index, String[] cart, int count) {
    boolean fullCart = true; // used to determine if cart if full

    for (int i = 0; i < cart.length; i++) {
      if (cart[i] == null) {
        fullCart = false;
      }
    }
    if (fullCart) {
      System.out.println("WARNING: The cart is full. You cannot add any new item.");
      return count;
    }
    // iterates through the array to add the given item, then increments cart
    for (int i = 0; i < MARKET_ITEMS.length; ++i) {
      if (i == index) {
        for (int j = 0; j < cart.length; ++j) {
          if (cart[j] == null) {
            cart[j] = MARKET_ITEMS[i][0];
            count++;
            break; // j = cart.length;
          }
        }
      }
    }
    return count;
  }

  /**
   * Returns the index of an item within the shopping cart
   * 
   * @param item  description
   * @param cart  Shopping cart
   * @param count number of items present in the shopping cart
   * @return index of the item within the shopping cart, and -1 if the item does not exist in the
   *         cart
   */
  private static String getItemDescription(int index) {
    String item = MARKET_ITEMS[index][0]; // item that corresponds to given index
    return item;
  }

  /**
   * Returns the how many of a given item exist within cart
   * 
   * @param itemIndex index of chosen item
   * @param cart      Shopping cart
   * @param count     number of items present in the shopping cart
   * @return number of times given item appears in the cart
   */
  public static int occurrencesOf(int itemIndex, String[] cart, int count) {
    count = 0; // uses count to count how many items of given index are in cart
    for (int i = 0; i < cart.length; i++) {
      if (cart[i] == null) {
        continue;
      } else if (cart[i].equals(getItemDescription(itemIndex))) {
        count++;
      }
    }
    return count;
  }

  /**
   * Removes first occurrence of itemToRemove and returns current count in shopping cart
   * 
   * @param itemToRemove items selected for removal
   * @param cart         Shopping cart
   * @param count        number of items present in the shopping cart
   * @return index of the item within the shopping cart
   */
  public static int remove(String itemToRemove, String[] cart, int count) {
    // uses indexOf to find item in the cart
    if (indexOf(itemToRemove, cart, count) == -1) {
      System.out.println("WARNING: " + itemToRemove + " not found in the shopping cart.");
    } else {
      // removes item and decrements count
      // cart[indexOf(itemToRemove, cart, count)] = null;
      cart[indexOf(itemToRemove, cart, count)] = cart[count - 1];
      cart[count - 1] = null;
      count--;
    }
    return count;
  }

  /**
   * Returns the index of an item within the shopping cart
   * 
   * @param item  description
   * @param cart  Shopping cart
   * @param count number of items present in the shopping cart
   * @return index of the item within the shopping cart, and -1 if the item does not exist in the
   *         cart
   */
  private static int indexOf(String item, String[] cart, int count) {
    int index = 0;// variable to store index of chosen item
    for (int i = 0; i < cart.length; ++i) {
      if (cart[i] == null) {
        index = -1;
      } else if (cart[i].equals(item)) {
        index = i;
        break;
      }
    }
    return index;
  }

  /**
   * Returns the price of an item within the shopping cart
   * 
   * @param item description
   * @return price of chosen item
   */
  private static double priceOfCart(String item) {
    double price = 0.0; // stores the price of given item
    // String cost = ""; // Stores the chosen item
    for (int i = 0; i < MARKET_ITEMS.length; i++) {
      if (MARKET_ITEMS[i][0].equals(item)) {
        // cost = MARKET_ITEMS[i][1];
        // cost = cost.replace("$", ""); // removes dollar sign from price
        price = Double.parseDouble(MARKET_ITEMS[i][1].replace("$", ""));
      }
    }
    return price;
  }

  /**
   * Calculates the total tax of purchase
   * 
   * @param total sub-total before tax
   * @return tax on the total
   */
  private static double taxOf(double total) {
    total = total * TAX_RATE;
    return total;
  }

  /**
   * Returns the sub-total of the shopping cart before tax in double
   * 
   * @param cart  Shopping cart
   * @param count number of items in the cart
   * @return the sub-total before tax
   */
  public static double getSubTotalPrice(String[] cart, int count) {
    double num = 0.0; // stores sub-total
    // iterates through the cart to search for items and add their prices
    for (int i = 0; i < cart.length; i++) {
      if (cart[i] != null) {
        num = num + priceOfCart(cart[i]);
      }
    }
    return num;
  }

  /**
   * Prints the entire market catalog
   * 
   * @return void
   */
  public static void printMarketCatalog() {
    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
    System.out.println("Item id     Description      Price");
    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
    // iterates through and prints each line of market array
    for (int i = 0; i < MARKET_ITEMS.length; i++) {
      System.out.println(i + "\t\t" + MARKET_ITEMS[i][0] + "    \t " + MARKET_ITEMS[i][1]);
    }
    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
  }

  /**
   * Prints the current content of the cart
   * 
   * @param cart  Shopping cart
   * @param count number of items in the cart
   * @return void
   */
  public static void displayCartContent(String[] cart, int count) {
    System.out.print("Cart Content: ");
    String fullCart = "";
    for (int i = 0; i < count; i++) {
      System.out.print(cart[i] + ", ");
    }
    System.out.println(fullCart);
  }

  public static void main(String[] args) {
    Scanner scnr = new Scanner(System.in);
    boolean end = true;
    int count = 0;
    String[] cart = new String[CART_CAPACITY];
    double total = 0.0;
    double tax = 0.0;
    String userResponse = "";
    String userResponse2 = "";

    System.out.println("=============   Welcome to the Shopping Cart App   =============\n");

    do {
      System.out.println("\nCOMMAND MENU:\n" + " [P] print the market catalog\n"
          + " [A <index>] add one occurrence of an item to the cart given its identifier\n"
          + " [C] checkout\n" + " [D] display the cart content\n"
          + " [O <index>] number of occurrences of an item in the cart given its identifier\n"
          + " [R <index>] remove one occurrence of an item from the cart given its identifier\n"
          + " [Q]uit the application\n");

      System.out.print("ENTER COMMAND: ");
      userResponse = scnr.next().trim().toLowerCase();

      if (userResponse.charAt(0) == 'p') {
        printMarketCatalog();
        // userResponse = scnr.nextLine().trim().toLowerCase();
      } else if (userResponse.charAt(0) == 'a') {
        userResponse2 = scnr.nextLine().trim();

        int result = Integer.parseInt(userResponse2);
        // index = Character.getNumericValue(userResponse.charAt(2));
        count = add(result, cart, count);

      } else if (userResponse.charAt(0) == 'c') {
        total = getSubTotalPrice(cart, count);
        tax = taxOf(total);

        System.out
            .println("#items: " + count + " Subtotal: $" + String.format("%.2f", total) + " Tax: $"
                + String.format("%.2f", tax) + " TOTAL: $" + String.format("%.2f", (total + tax)));
      } else if (userResponse.charAt(0) == 'd') {
        displayCartContent(cart, count);
      } else if (userResponse.charAt(0) == 'o') {
        userResponse2 = scnr.nextLine().trim();
        int result = Integer.parseInt(userResponse2);
        System.out.println("The number of occurrences of " + getItemDescription(result) + " (id #"
            + result + ")" + " is: " + occurrencesOf(result, cart, count));
        // userResponse = scnr.nextLine().trim().toLowerCase();
      } else if (userResponse.charAt(0) == 'r') {
        userResponse2 = scnr.nextLine().trim();

        int result = Integer.parseInt(userResponse2);
        count = remove(getItemDescription(result), cart, count);
      } else if (userResponse.charAt(0) == 'q') {
        end = false;
        System.out.println("=============  Thank you for using this App!!!!!  =============");
      }

    } while (end);

  }

}
