//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Sokoban
// Files: TestSokoban.java, Sokoban.java, MyLevels.java, Config.java
// Course: 200, 1st, 2018
//
// Author: Harrison Bell
// Email: rhbell2@wisc.edu
// Lecturer's Name: Jim Williams
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: Andrew Zagorkis
// Partner Email: alzagorski@wisc.edu
// Lecturer's Name: Jim Williams
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// _x__ Write-up states that pair programming is allowed for this assignment.
// __x_ We have both read and understand the course Pair Programming Policy.
// _x__ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Persons: Andrew
//                                                                           
///////////////////////////////////////////////////////////////////////////////
import java.util.Scanner;
import java.util.Random;
import java.lang.Character;

public class Sokoban {

    /**
     * Prompts the user for a value by displaying prompt. Note: This method should not add a new
     * line to the output of prompt.
     *
     * After prompting the user, the method will consume an entire line of input while reading an
     * int. If the value read is between min and max (inclusive), that value is returned. Otherwise,
     * "Invalid value." terminated by a new line is output to the console and the user is prompted
     * again.
     *
     * @param sc     The Scanner instance to read from System.in.
     * @param prompt The name of the value for which the user is prompted.
     * @param min    The minimum acceptable int value (inclusive).
     * @pardam max The maximum acceptable int value (inclusive).
     * @return Returns the value read from the user.
     */
    public static int promptInt(Scanner sc, String prompt, int min, int max) {
        int userInt = min - 1;// variable to hold the user's input; will be returned when valid
        // Loops until a valid level is entered, printing "Invalid value." when not
        while (userInt < min || userInt > max) {
            System.out.print(prompt);
            if (sc.hasNextInt()) {
                userInt = sc.nextInt();
                if (userInt < min || userInt > max) {
                    System.out.println("Invalid value.");
                }
            } else
                System.out.println("Invalid value.");
            sc.nextLine();
        }

        return userInt;
    }

    /**
     * Prompts the user for a char value by displaying prompt.
     * Note: This method should not be a new line to the output of prompt. 
     *
     * After prompting the user, the method will read an entire line of input and return the first
     * non-whitespace character converted to lower case.
     *
     * @param sc The Scanner instance to read from System.in
     * @param prompt The user prompt.
     * @return Returns the first non-whitespace character (in lower case) read from the user. If 
     *         there are no non-whitespace characters read, the null character is returned.
     */
    public static char promptChar(Scanner sc, String prompt) {
        char userChar = '\u0000';// variable to hold the user's char; will be returned

        System.out.print(prompt);
        userChar = sc.next().charAt(0);
        userChar = Character.toLowerCase(userChar);

        return userChar;
    }

    /**
     * Prompts the user for a string value by displaying prompt.
     * Note: This method should not be a new line to the output of prompt. 
     *
     * After prompting the user, the method will read an entire line of input, remove any leading and 
     * trailing whitespace, and return the input converted to lower case.
     *
     * @param sc The Scanner instance to read from System.in
     * @param prompt The user prompt.
     * @return Returns the string entered by the user, converted to lower case with leading and trailing
     *         whitespace removed.
     */
    public static String promptString(Scanner sc, String prompt) {
        System.out.print(prompt);
        String userString = sc.nextLine();// the user's string; will be returned
        userString = userString.toLowerCase();
        userString = userString.trim();

        return userString;
    }

    /**
     * Initializes the game board to a given level. You can assume that the level at lvl has been 
     * successfully verified by the checkLevel method and that pos is an array of length 2.
     *
     * 1 - The game board should be created row-by-row. 
     *     a - For each row, copy the values from the corresponding row in the 2-d array contained 
     *         at index lvl in levels.
     *     b - When the worker is located, it's position should be recorded in the pos parameter.
     * 2 - For each goal described in the array at index lvl of goals, convert the character at the 
     *     goal coordinate to:
     *     - Config.WORK_GOAL_CHAR if it contains the worker
     *     - Config.BOX_GOAL_CHAR if it contains a box
     *     - Config.GOAL_CHAR otherwise  
     * 
     * @param lvl The index of the level to load.
     * @param levels The array containing the levels.
     * @param goals The parallel array to levels, containing the goals for the levels.
     * @param pos The starting pos of the worker. A length 2 array, where index 0 is the row and 
     *            index 1 is the column. 
     * @return A two dimension array representing the initial configuration for the given level.
     */
    public static char[][] initBoard(int lvl, char[][][] levels, int[][] goals, int[] pos) {
        // this for loop finds and stores the position of the worker
        for (int i = 0; i < levels[lvl].length; i++) {
            for (int j = 0; j < levels[lvl][i].length; j++) {
                if (levels[lvl][i][j] == Config.WORKER_CHAR) {
                    pos[0] = i;
                    pos[1] = j;
                }
            }
        }

        int goalRow = 0;// the row of the current goal
        int goalCol = 0;// the column of the current goal

        // this for loop changes the location at [goalRow][goalCol] in the given level to the proper
        // goal character
        for (int i = 0; i < goals[lvl].length; i = i + 2) {
            goalRow = goals[lvl][i];
            goalCol = goals[lvl][i + 1];
            if (levels[lvl][goalRow][goalCol] == Config.WORKER_CHAR) {
                levels[lvl][goalRow][goalCol] = Config.WORK_GOAL_CHAR;
            } else if (levels[lvl][goalRow][goalCol] == Config.BOX_CHAR) {
                levels[lvl][goalRow][goalCol] = Config.BOX_GOAL_CHAR;
            } else
                levels[lvl][goalRow][goalCol] = Config.GOAL_CHAR;
        }

        char[][] initBoard = new char[levels[lvl].length][];// a new 2-D array to be returned when
                                                            // filled with the proper characters

        // this for loop sets the length of each row in initBoard
        for (int i = 0; i < levels[lvl].length; i++) {
            initBoard[i] = new char[levels[lvl][i].length];
        }
        // this for loop copies the correct values of levels[lvl] into initBoard
        for (int i = 0; i < levels[lvl].length; i++) {
            for (int j = 0; j < levels[lvl][i].length; j++) {
                initBoard[i][j] = levels[lvl][i][j];
            }
        }

        return initBoard;
    }

    /**
     * Prints out the game board.
     * 
     * 1 - Since the game board does not contain the outer walls, print out a sequence of
     *     Config.WALL_CHAR with a length equal to that of the first row of board, plus the outer
     *     wall to the left and the right.
     * 2 - For each row in board, print out a Config.WALL_CHAR, followed by the contents
     *     of the row, followed by a Config.WALL_CHAR.
     * 3 - Finally, print out a sequence of Config.WALL_CHAR with a length equal to that 
     *     of the last row of board, plus the outer wall to the left and the right.
     *
     * Note: each row printed out should be terminated by a new line.
     *
     * @param board The board to print.
     */
    public static void printBoard(char[][] board) {
        // This for loop prints out the top row of wall characters
        for (int i = 0; i < board[0].length + 2; i++) {
            System.out.print(Config.WALL_CHAR);
        }
        System.out.println("");

        // This for loop prints out the initialized board, bookended by wall characters
        for (int i = 0; i < board.length; i++) {
            System.out.print(Config.WALL_CHAR);
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println(Config.WALL_CHAR);
        }

        // This for loop prints out the bottom row of wall characters
        for (int i = 0; i < board[board.length - 1].length + 2; i++) {
            System.out.print(Config.WALL_CHAR);
        }
        System.out.println("");
    }

    /**
     * Runs a given level through some basic sanity checks.
     *
     * This method performs the following tests (in order):
     * 1 - lvl >= 0
     * 2 - lvl is a valid index in levels, that the 2-d array at index lvl exists and that 
     *     it contains at least 1 row.
     * 3 - lvl is a valid index in goals, the 1-d array at index lvl exists and that it
     *     contains an even number of cells.
     * 4 - the number of boxes is more than 0.
     * 5 - the number of boxes equals the number of goals.
     * 6 - the coordinate of each goal is valid for the given lvl and does not
     *     correspond to a wall cell.
     * 7 - the number of workers is exactly 1.
     * 8 - check for duplicate goals.
     *
     * @param lvl The index of the level to load.
     * @param levels The array containing the levels.
     * @param goals The parallel array to levels, containg the goals for the levels.
     * @return 1 if all tests pass.
     *         Otherwise if test:
     *          - Test 1 fails: 0
     *          - Test 2 fails: -1
     *          - Test 3 fails: -2
     *          - Test 4 fails: -3
     *          - Test 5 fails: -4
     *          - Test 6 fails: -5
     *          - Test 7 fails: -6
     *          - Test 8 fails: -7
     *  
     */
    public static int checkLevel(int lvl, char[][][] levels, int[][] goals) {

        // Test 1: returns 0 if the level called is a negative number
        if (lvl < 0) {
            return 0;
        }

        // Test 2:
        // This if statement checks that lvl does not correspond to an invalid index
        if (lvl >= levels.length) {
            return -1;
        }
        // This for loop checks that each level is not an empty array
        for (int i = 0; i < levels.length; i++) {
            if ((levels[i] == null)) {
                return -1;
            }
        }
        // This for loop checks that there is at least 1 row in each level
        for (int i = 0; i < levels.length; i++) {
            for (int j = 0; j < levels[i].length; j++) {
                if ((levels[i][j] == null)) {
                    return -1;
                } else if (levels[i][j].length < 1) {
                    return -1;
                }
            }
        }

        // Test 3:
        if (lvl >= goals.length)// lvl is not above the max level value in goals
            return -2;
        if (goals[lvl] == null) // goals[lvl] has something in it
            return -2;
        if (goals[lvl].length % 2 == 1) // goals[lvl] must be even
            return -2;

        // Test 4:
        boolean error = true;// A boolean that becomes false when the condition is satisfied

        // This set of loops iterates through the entire array to look for a box character
        for (int i = 0; i < levels.length; i++) {
            for (int j = 0; j < levels[i].length; j++) {
                for (int k = 0; k < levels[i][j].length; k++) {
                    if (levels[i][j][k] == (Config.BOX_CHAR)) {
                        error = false;
                    }
                }
            }
        }
        if (error)
            return -3;

        // Test 5:
        int numBoxes;// The number of boxes in the level
        int numGoals = 0;// The number of goals in the level

        // This for loop increments numBoxes upon finding a box character
        for (int i = 0; i < levels.length; i++) {
            numBoxes = 0;
            for (int j = 0; j < levels[i].length; j++) {
                for (int k = 0; k < levels[i][j].length; k++) {
                    if (levels[i][j][k] == (Config.BOX_CHAR)) {
                        numBoxes++;
                    }
                }
            }
            // This last part of the loop compares numGoals and numBoxes
            numGoals = goals[i].length / 2;
            if (numBoxes != numGoals)
                return -4;
        }

        // Test 6:
        int rowNum;// The row coordinate of a goal character
        int colNum;// The column of a goal character

        // This for loop iterates through all goals and checks if they occupy a wall character
        for (int i = 0; i < goals.length; i++) {
            for (int j = 0; j < goals[i].length; j += 2) {
                rowNum = goals[i][j];
                colNum = goals[i][j + 1];
                if (levels[i][rowNum][colNum] == Config.WALL_CHAR) {
                    return -5;
                }
            }
        }

        // Test 7:
        int numWorkers;// The number of workers in the level

        // This for loop iterates through the levels and increments numWorkers if the current index
        // contains a worker character
        for (int i = 0; i < levels.length; i++) {
            numWorkers = 0;
            for (int j = 0; j < levels[i].length; j++) {
                for (int k = 0; k < levels[i][j].length; k++) {
                    if (levels[i][j][k] == (Config.WORKER_CHAR)) {
                        numWorkers++;
                    }
                }
            }
            // This last part of the loop compares numWorkers to 1
            if (numWorkers != 1)
                return -6;
        }

        // Test 8:
        // This for loop iterates through goals, comparing the goal at (i, i+1) to every other goal
        for (int i = 0; i < goals[lvl].length - 1; i += 2) {
            for (int j = i + 2; j < goals[lvl].length - 1; j += 2) {
                if (goals[lvl][i] == goals[lvl][j] && goals[lvl][i + 1] == goals[lvl][j + 1]) {
                    return -7;
                }
            }
        }

        return 1;
    }

    /**
     * This method builds an int array with 2 cells, representing a movement vector, based on the 
     * String parameter.
     *
     * The rules to create the length 2 int array are as follows:
     *   - The 1st character of the String represents the direction.
     *   - The remaining characters (if there are any) are interpreted as integer and represent the
     *     magnitude or the number of steps to take.
     *
     * The cell at index 0 represents movement in the rows. Hence, a negative value represents 
     * moving up the rows and a positive value represents moving down the rows.
     *
     * The cell at index 1 represents movement in the columns. Hence, a negative value represents 
     * moving left in the columns and a positive value represents moving right in the columns.
     *
     * If the first character of moveStr does not match on of Config.UP_CHAR, Config.DOWN_CHAR,
     * Config.LEFT_CHAR, or Config.RIGHT_CHAR, then return an array with 0 in both cells.
     *
     * If there are no characters after the first character of moveStr or the characters cannot be
     * interpreted as an int, set the magnitude of the movement to 1.
     *
     * Hint: Use Scanner to parse the magnitude.
     *
     * Some examples: 
     *   - If the parameter moveStr is "81": An array {-1, 0} would represent moving up by one 
     *     character.
     *   - If the parameter moveStr is "65": An array {0, 5} would represent moving right by 5
     *     characters.   
     *
     * @param moveStr The string to parse.
     * @return The calculated movement vector as a 2 cell int array.
     */
    public static int[] calcDelta(String moveStr) {
        int deltaArray[] = {0, 0};// The array of delta, which will be returned

        // This switch modifies the array to be a delta of magnitude 1 in the correct direction
        // based on the string entered by the user, and returns {0, 0} if the string was invalid
        switch (moveStr.charAt(0)) {
            case Config.UP_CHAR:
                deltaArray[0] = -1;
                break;
            case Config.DOWN_CHAR:
                deltaArray[0] = 1;
                break;
            case Config.LEFT_CHAR:
                deltaArray[1] = -1;
                break;
            case Config.RIGHT_CHAR:
                deltaArray[1] = 1;
                break;
            default:
                deltaArray[0] = 0;
                deltaArray[1] = 0;
                return deltaArray;
        }

        String magnitudeString = new String();// The rest of the user input; delta's magnitude

        if (moveStr.length() > 1) {
            magnitudeString = moveStr.substring(1, moveStr.length());
            Scanner strScnr = new Scanner(magnitudeString);// A scanner to read magnitudeString
            if (!(strScnr.hasNextInt())) {
                strScnr.close();
                return deltaArray;
            } else if (magnitudeString.contains(" ") || magnitudeString.contains("\t")) {
                strScnr.close();
                return deltaArray;
            } else {
                int magnitudeMultiple = strScnr.nextInt();// A variable storing the magnitude
                deltaArray[0] *= magnitudeMultiple;// magnitudeMultiple correctly updates the
                deltaArray[1] *= magnitudeMultiple;// magnitude of delta through multiplication
            }
            strScnr.close();
        }

        return deltaArray;
    }

    /**
     * This method checks that moving from one position to another position is a valid move.
     *
     * To validate the move, the method should (in order) check:
     *   1 - that pos is valid.
     *   2 - that the character at pos in board is in the valid array.
     *   3 - that the delta is valid.
     *   4 - that the new position is valid and not a wall character.
     *   5 - that the new position is not a box character 
     * For what makes each test invalid, see the return details below.
     *
     * @param board The current board.
     * @param pos The position to move from. A length 2 array, where index 0 is the row and 
     *            index 1 is the column.
     * @param delta The move distance. A length 2 array, where index 0 is the change in row and
     *              index 1 is the change in column.
     * @param valid A character array containing the valid characters for the cell at pos.
     * @return 1 if the move is valid.
     * Otherwise:
     *  -1 : if pos is null, not length 2, or not on the board.
     *  -2 : if the character at pos is not valid (not in the valid array).
     *  -3 : if delta is null or not length 2.
     *  -4 : if the new position is off the board or a wall character
     *  -5 : if the new position is a box character
     */
    public static int checkDelta(char[][] board, int[] pos, int[] delta, char[] valid) {
        int[] newPos = new int[2];// An array resulting from adding pos and delta index-wise

        // Test 1:
        if (((pos == null)) || !(pos.length == 2)) {
            return -1; // Guarantees pos is not null or anything other than length 2
        }
        if ((pos[0] < 0) || (pos[1] < 0)) {
            return -1; // Guarantees the coordinates in pos are not negative
        }
        if ((pos[0] >= board.length) || pos[1] >= board[pos[0]].length) {
            return -1; // Guarantees the coordinates in pos are valid indeces of board
        }

        // Test 2:
        boolean isValid = false;// A boolean used for expressing whether the character in board
        // located at (pos[0], pos[1]) is included in the valid array

        for (int i = 0; i < valid.length; i++) {
            if (board[pos[0]][pos[1]] == valid[i])
                isValid = true;// Sets isValid to true if the character is in the valid array
        }
        if (!isValid) {
            return -2;// Guarantees the character at pos is in the valid array
        }

        // Test 3:
        if ((delta == null) || !(delta.length == 2)) {
            return -3;// Guarantees delta is not null and has a length of 2 if not null
        }

        // Test 4:
        newPos[0] = pos[0] + delta[0];// Sets newPos's elements to the correct value of the old
        newPos[1] = pos[1] + delta[1];// pos + delta for each index
        if ((newPos[0] < 0) || (newPos[1] < 0)) {
            return -4;// Guarantees newPos has two positive coordinates, since they are used as
                      // array indexes
        }
        if ((newPos[0] >= board.length) || (newPos[1] >= board[newPos[0]].length)) {
            return -4;// Guarantees newPos is not above the max index of the board array
        }
        if (board[newPos[0]][newPos[1]] == Config.WALL_CHAR) {
            return -4;// Guarantees board[newPos[0]][newPos[1]] is not a wall character
        }

        // Test 5:
        if (board[newPos[0]][newPos[1]] == Config.BOX_CHAR
            || board[newPos[0]][newPos[1]] == Config.BOX_GOAL_CHAR) {
            return -5;// Guarantees board at newPos is not a box character
        }

        return 1;// Returns 1 if all tests pass
    }

    /**
     * Changes a character on the board to one of two characters (opt1 or opt2), depending on the 
     * value of the cell.
     *
     * Check the cell at position pos. If the character is val, change it to opt1. Otherwise, change
     * it to opt2.
     *
     * @param board The current board.
     * @param pos The position to change. A length 2 array, where index 0 is the row and index 1 is
     *            the column.
     * @param val The value to check for in the board.
     * @param opt1 The character to change to if the value is val.
     * @param opt2 The character to change to if the value is not val.
     */
    public static void togglePos(char[][] board, int[] pos, char val, char opt1, char opt2) {
        if (board[pos[0]][pos[1]] == val) {
            board[pos[0]][pos[1]] = opt1;
        } else {
            board[pos[0]][pos[1]] = opt2;
        } // Pretty straightforward
    }

    /**
     * Moves a box on the board.
     *
     * Step 1: Use your checkDelta method to check that the move is valid. Recall that there are
     *         2 characters that can represent a box.
     * Step 2: Use your togglePos method to correctly change the character at the new position to 
     *         the appropriate box character.
     * Step 3: Again use your togglePos method to correctly change the character at pos to the 
     *         the appropriate character without a box.
     *
     * @param board The current board.
     * @param pos The position to change. A length 2 array, where index 0 is the row and index 1 is
     *            the column.
     * @param delta The move distance. A length 2 array, where index 0 is the change in row and
     *              index 1 is the change in column.
     * @return The return value of checkDelta if less than 1. Otherwise 1.
     */
    public static int shiftBox(char[][] board, int[] pos, int[] delta) {
        char[] validBox = {Config.BOX_CHAR, Config.BOX_GOAL_CHAR}; // An array containing the valid
                                                                   // characters for a box

        if (checkDelta(board, pos, delta, validBox) < 1) {// If checkDelta fails any test
            return checkDelta(board, pos, delta, validBox);// Return the corresponding value
        }

        int[] newPos = {pos[0] + delta[0], pos[1] + delta[1]};// This array is pos's values added
                                                              // index-wise with delta's values

        // This call to togglePos changes the char at newPos to the appropriate box character
        togglePos(board, newPos, Config.GOAL_CHAR, Config.BOX_GOAL_CHAR, Config.BOX_CHAR);

        // This call to togglePos changes the char at pos to the appropriate empty or goal character
        togglePos(board, pos, Config.BOX_GOAL_CHAR, Config.GOAL_CHAR, Config.EMPTY_CHAR);

        return 1;
    }

    /**
     * Processes a move of the worker step-by-step.
     *
     * Go through the delta step-by-step, calling doMove for each step. 
     * That is, if the delta is {0, -3}, your method should call doMove three times with an argument of
     * {0, -1} for the delta parameter of doMove. Or, if the delta is {6, 0}, it would call the doMove
     * six times with an argument of {1, 0} for the delta parameter of the doMove method. 
     *
     * During the processing of the move, if ever a call to doMove returns a value less than 1, your 
     * method should stop processing and return that value.
     *
     * Note: You can assume that one of the cells of delta will be 0. 
     *
     * @param board The current board.
     * @param pos The position to change. A length 2 array, where index 0 is the row and index 1 is
     *            the column.
     * @param delta The move distance. A length 2 array, where index 0 is the change in row and
     *              index 1 is the change in column. 
     * @return If both of the cells of delta are 0, return 0.
     *         If the call to doMove returns a value less than 1, return that value.
     *         Otherwise, return 1.
     */
    public static int processMove(char[][] board, int[] pos, int[] delta) {
        boolean rowMove = false;// A boolean for expressing if the move is in the rows or columns
        int doMoveReturn;// An int to hold the return value of doMove
        int[] singleDelta = {0, 0};// Holds a delta in the proper direction with a magnitude of 1

        // This if statement determines whether the given delta is a move in the rows or columns
        if (!(delta[0] == 0)) {
            rowMove = true;
        } else if (!(delta[1] == 0)) {
            rowMove = false;
        } else
            return 0;// returns 0 if both of the cells of delta are 0

        // This if else branch calls doMove once for the magnitude, in the correct direction
        if (rowMove) {
            singleDelta[0] = delta[0] / Math.abs(delta[0]);
            for (int i = 0; i < Math.abs(delta[0]); i++) {
                doMoveReturn = doMove(board, pos, singleDelta);
                if (doMoveReturn < 1)
                    return doMoveReturn;
            }
        } else {
            singleDelta[1] = delta[1] / Math.abs(delta[1]);
            for (int i = 0; i < Math.abs(delta[1]); i++) {
                doMoveReturn = doMove(board, pos, singleDelta);
                if (doMoveReturn < 1)
                    return doMoveReturn;
            }
        }

        return 1;
    }

    /**
     * Moves the worker on the board.
     *
     * Step 1: Use your checkDelta method to check that the move is valid. Recall that there are
     *         2 characters that can represent the worker.
     * Step 2: If checkDelta returns -5, use your shiftBox method to move the box by delta before
     *         moving the worker.
     * Step 3: Use your togglePos method to correctly change the character at the new position to 
     *         the appropriate worker character.
     * Step 4: Again use your togglePos method to correctly change the character at pos to the 
     *         the appropriate character without a worker.
     * Step 5: Update the position of the worker in pos.
     *
     * @param board The current board.
     * @param pos The position to change. A length 2 array, where index 0 is the row and index 1 is
     *            the column.
     * @param delta The move distance. A length 2 array, where index 0 is the change in row and
     *              index 1 is the change in column.
     * @return If checkDelta returns a value less than 1 that is not -5, return that value. 
     *         If checkDelta returns -5 and shiftBox returns a value less than 0, return 0.
     *         Otherwise, return 1.
     */
    public static int doMove(char[][] board, int[] pos, int[] delta) {
        char[] valid = {Config.WORKER_CHAR, Config.WORK_GOAL_CHAR}; // Holds the valid characters
                                                                    // for a worker

        int deltaReturn = checkDelta(board, pos, delta, valid); // Stores the return value of
                                                                // checkDelta

        if (deltaReturn == -5) { // Calls shiftBox since new pos is a box
            int[] boxPos = {pos[0] + delta[0], pos[1] + delta[1]}; // Array with box's position
            int shiftReturn = shiftBox(board, boxPos, delta); // Stores shiftBox's return value
            if (shiftReturn < 0)
                return 0;// Returns 0 if shiftBox can't be done
        } else if (deltaReturn < 1)
            return deltaReturn;// Returns checkDelta's return value if it wasn't -5 or 1

        int[] newPos = {pos[0] + delta[0], pos[1] + delta[1]}; // This array is pos's values added
                                                               // index-wise with delta's values

        // Changes the char at newPos to the correct worker character
        togglePos(board, newPos, Config.GOAL_CHAR, Config.WORK_GOAL_CHAR, Config.WORKER_CHAR);

        // Changes the char at the original pos to the correct empty character
        togglePos(board, pos, Config.WORK_GOAL_CHAR, Config.GOAL_CHAR, Config.EMPTY_CHAR);

        // Updates the pos array to be the new array with delta added
        pos[0] = newPos[0];
        pos[1] = newPos[1];

        return 1;
    }

    /**
     * Checks all the cells in board and ensures that there are no goals that are not covered by
     * boxes.
     *
     * @param board The current board.
     * @return true if all the goals are covered by boxes. Otherwise, false.
     */
    public static boolean checkWin(char[][] board) {
        // Iterates through the array; if there is a goal uncovered by a box, returns false
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == Config.GOAL_CHAR) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * This is the main method for the Sokoban game. It consists of the main game and play again
     * loops with calls to the various supporting methods. The details of the main method for each 
     * milestone can be found in the BP1 - Sokoban write-up on the CS 200 webpage:
     * https://cs200-www.cs.wisc.edu/wp/programs/
     *
     * For all milestones, you will need to create a Scanner object to read from System.in that you
     * will pass to the helper methods.
     *
     * For milestone 3, you will need to create a Random object using Config.SEED as the seed. This
     * object should be create at the beginning of the method, outside of any loops.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        int maxLvl = Config.LEVELS.length - 1; // The max level of the Sokoban game
        int userLvl = 0; // Variable storing the level chosen by the user
        char playChar = 'y'; // Stores the user's character when asked "Play again?"
        boolean winnerWinner = false; // A boolean used to determine whether the user has won
        Random randLevel = new Random(Config.SEED); // A random object used to select a random level
        String moveString = new String(); // The string the user inputs to do a move on the board
        int[] userDelta = new int[2]; // An array with the delta determined from moveString
        int[] workerPos = new int[2]; // The position array with length 2 to store the row and
                                      // column
        int returnVal; // Stores the return value when checkLevel is called
        int numMoves; // Stores the number of moves done by the user

        Scanner sc = new Scanner(System.in); // Used to read user input in various methods

        System.out.println("Welcome to Sokoban!");
        while (playChar == 'y') {// Begins the play loop
            winnerWinner = false;
            numMoves = 0;
            userLvl = promptInt(sc, "Choose a level between 0 and " + maxLvl + ": ", -1, maxLvl);
            if (userLvl == -1) {
                userLvl = randLevel.nextInt(maxLvl + 1);
            }
            returnVal = checkLevel(userLvl, Config.LEVELS, Config.GOALS);
            if (!(returnVal == 1)) {
                System.out.println("Error loading level!");
                // This switch prints the proper error message if checkLevel's return is less than 1
                switch (returnVal) {
                    case 0:
                        System.out.println("Level lvl must be 0 or greater!");
                        break;
                    case -1:
                        System.out.println("Error with Config.LEVELS");
                        break;
                    case -2:
                        System.out.println("Error with Config.GOALS");
                        break;
                    case -3:
                        System.out.println("Level lvl does not contain any boxes.");
                        break;
                    case -4:
                        System.out
                            .println("Level lvl does not have the same number of boxes as goals.");
                        break;
                    case -5:
                        System.out.println("Level lvl has a goal location that is a wall.");
                        break;
                    case -6:
                        System.out.println("Level lvl has 0 or more than 1 worker(s).");
                        break;
                    case -7:
                        System.out.println("Level lvl contains duplicate goals.");
                        break;
                    default:
                        System.out.println("Unknown Error");
                        break;
                }
            } else if (returnVal == 1) {// Begins the gameplay of that level if checkLevel passed
                char[][] board = initBoard(userLvl, Config.LEVELS, Config.GOALS, workerPos);
                System.out.println("Sokoban Level " + userLvl);
                // Loops as long as the user hasn't won or entered quit character, repeatedly
                // printing the board and asking the user for input
                while (!winnerWinner) {
                    printBoard(board);
                    moveString = promptString(sc, ": ");
                    if (moveString.length() == 0)
                        continue; // Loop again if nothing was entered
                    if (moveString.charAt(0) == Config.QUIT_CHAR)
                        break; // Leave the loop if quit character entered
                    userDelta = calcDelta(moveString); // Gets delta from moveString
                    // If delta isn't {0,0}, calls process move correctly
                    if (!(userDelta[0] == 0) || !(userDelta[1] == 0)) {
                        processMove(board, workerPos, userDelta);
                        if (userDelta[0] == 0)
                            numMoves = numMoves + Math.abs(userDelta[1]);
                        else if (userDelta[1] == 0)
                            numMoves = numMoves + Math.abs(userDelta[0]);
                    }
                    // Checks if the user won, and prints the victory screen if so
                    winnerWinner = checkWin(board);
                    if (winnerWinner) {
                        System.out.println("Congratulations! You won in " + numMoves + " moves!");
                        printBoard(board);
                    }
                }
            }
            // After the gameplay loop, asks the user if they would like to play again
            playChar = promptChar(sc, "Play again? (y/n) ");
            if (playChar == 'y')
                sc.nextLine();
        }
        System.out.println("Thanks for playing!");

    }
}
