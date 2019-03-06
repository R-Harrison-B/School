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
/**
 * This file contains testing methods for the Sokoban project. These methods are intended to provide
 * an example of a way to incrementally test your code, and to provide example method calls for the
 * Sokoban methods
 *
 * Toward these objectives, the expectation is that part of the grade for the Sokoban project is to
 * write some tests and write header comments summarizing the tests that have been written. Specific
 * places are noted with FIXME but add any other comments you feel would be useful.
 */

import java.util.Arrays;

/**
 * This class contains a few methods for testing methods in the Sokoban class as they are developed.
 * These methods are all private as they are only intended for use within this class.
 * 
 * @author Marc Renault
 * @author Harrison Bell
 *
 */
public class TestSokoban {

    /**
     * This is the main method that runs the various tests. Uncomment the tests when you are ready
     * for them to run.
     * 
     * @param args (unused)
     */
    public static void main(String[] args) {
        // Milestone 1
        testCheckLevel();
        // Milestone 2
        testInitBoard();
        testCheckWin();
        testCalcDelta();
        testCheckDelta();
        // Milestone 3
        testTogglePos();
        testShiftBox();
        testDoMove();
        testProcessMove();
    }

    private static void testCheckLevel() {
        int numTests = 5; // The number of tests
        int testsPassed = numTests; // The number of tests passed; decremented upon failure
        int returnVal; // The return value of the test

        // Test 1
        if ((returnVal = Sokoban.checkLevel(-1, Config.LEVELS, Config.GOALS)) != 0) {
            System.out.println(
                "FAILED: Sokoban.checkLevel Test 1. Expected 0, but value returned " + returnVal);
            testsPassed--;
        }

        // Test 2
        char[][][] test2Level1 = new char[2][][]; // An array with no 2nd or 3rd dimension
        char[][][] test2Level2 = new char[2][1][]; // An array with no 3rd dimension
        if ((returnVal = Sokoban.checkLevel(1, test2Level1, Config.GOALS)) != -1) {
            System.out.println(
                "FAILED: Sokoban.checkLevel Test 2. Expected -1, but value returned " + returnVal);
            testsPassed--;
        } else if ((returnVal = Sokoban.checkLevel(1, test2Level2, Config.GOALS)) != -1) {
            System.out.println(
                "FAILED: Sokoban.checkLevel Test 2. Expected -1, but value returned " + returnVal);
            testsPassed--;
        }

        // Test 3
        char[][][] test3Level1 = new char[3][3][3]; // An array with length 3 in all dimensions
        int[][] test3Goals1 = new int[3][]; // A goal array with 0 columns
        int[][] test3Goals2 = new int[3][3]; // A goal array with an odd number of columns
        if ((returnVal = Sokoban.checkLevel(2, test3Level1, Config.GOALS)) != -2) {
            System.out.println(
                "FAILED: Sokoban.checkLevel Test 3. Expected -2, but value returned " + returnVal);
            testsPassed--;
        } else if ((returnVal = Sokoban.checkLevel(0, Config.LEVELS, test3Goals1)) != -2) {
            System.out.println(
                "FAILED: Sokoban.checkLevel Test 3. Expected -2, but value returned " + returnVal);
            testsPassed--;
        } else if ((returnVal = Sokoban.checkLevel(0, Config.LEVELS, test3Goals2)) != -2) {
            System.out.println(
                "FAILED: Sokoban.checkLevel Test 3. Expected -2, but value returned " + returnVal);
            testsPassed--;
        }

        // Test 4
        char[][][] test4Level1 = new char[2][2][2]; // An array not big enough to contain the goals
        if ((returnVal = Sokoban.checkLevel(0, test4Level1, Config.GOALS)) != -3) {
            System.out.println(
                "FAILED: Sokoban.checkLevel Test 4. Expected -3, but value returned " + returnVal);
            testsPassed--;
        }

        // Test 5
        int[][] test5Goal1 = {{2, 2}, {2, 2}}; // An array with duplicate goals
        if ((returnVal = Sokoban.checkLevel(0, Config.LEVELS, test5Goal1)) != -4) {
            System.out.println(
                "FAILED: Sokoban.checkLevel Test 5. Expected -4, but value returned " + returnVal);
            testsPassed--;
        }

        System.out.println("testCheckLevel: Passed " + testsPassed + " of " + numTests + " tests.");
    }

    /**
     * Returns true if the arrays are the same size and have the same contents.
     */
    private static boolean compBoards(char[][] a, char[][] b) {
        if (a == null || b == null)// Checks if the 2d char arrays are null or not equal
            return false;
        if (a.length != b.length)
            return false;
        // Checks if arrays are equal to each other
        for (int i = 0; i < a.length; i++) {
            if (!Arrays.equals(a[i], b[i])) {
                return false;
            }
        }
        return true;
    }

    // This method checks to see if inItBoard is working properly
    private static void testInitBoard() {
        int numTests = 4; // The number of tests
        int testsPassed = numTests; // The number of tests passed; decremented upon failure

        // Test 1
        int[] posTest1 = new int[2]; // A new test position
        char[][] boardTest1 = Sokoban.initBoard(0, Config.LEVELS, Config.GOALS, posTest1);
        if (!Arrays.equals(posTest1, new int[] {4, 4})) {
            System.out.println(
                "FAILED: Sokoban.initBoard Test 1. Expected initial position: {4, 4} , but value after call "
                    + Arrays.toString(posTest1));
            testsPassed--;
        }

        // Test 2
        // Test if inItBoard method correctly initializes boards
        char[][] bCompTest1 = new char[][] {
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.GOAL_CHAR, Config.BOX_CHAR,
                Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.WORKER_CHAR}};
        if (!compBoards(boardTest1, bCompTest1)) {
            System.out.println("FAILED: Sokoban.initBoard Test 2. Board not as expected!");
            System.out.println("Generated:");
            Sokoban.printBoard(boardTest1);
            System.out.println("Expected:");
            Sokoban.printBoard(bCompTest1);
            testsPassed--;
        }

        // Test 3
        // Check if initial position is correct in initialized boards
        int[] posTest2 = new int[2];
        char[][] boardTest2 = Sokoban.initBoard(1, Config.LEVELS, Config.GOALS, posTest2); // Board
                                                                                           // at
                                                                                           // level
                                                                                           // 1
        if (!Arrays.equals(posTest2, new int[] {7, 10})) {
            System.out.println(
                "FAILED: Sokoban.initBoard Test 3. Expected initial position: {7, 10} , but value after call "
                    + Arrays.toString(posTest2));
            testsPassed--;
        }

        // Test 4
        // Tests if the initialized board matches the original board in Config.java
        char[][] bCompTest2 = new char[][] {
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR,
                Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR,
                Config.BOX_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR,
                Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.BOX_CHAR},
            {Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.BOX_CHAR, Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR},
            {Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR,
                Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
                Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR,
                Config.WALL_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR,
                Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
                Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR,
                Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.GOAL_CHAR,
                Config.GOAL_CHAR},
            {Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.BOX_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.GOAL_CHAR,
                Config.GOAL_CHAR},
            {Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR,
                Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR,
                Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WORKER_CHAR, Config.WALL_CHAR,
                Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.GOAL_CHAR,
                Config.GOAL_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR,
                Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR,
                Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR,
                Config.WALL_CHAR}};

        if (!compBoards(boardTest2, bCompTest2)) {
            System.out.println("FAILED: Sokoban.initBoard Test 4. Board not as expected!");
            System.out.println("Generated:");
            Sokoban.printBoard(boardTest2);
            System.out.println("Expected:");
            Sokoban.printBoard(bCompTest2);
            testsPassed--;
        }

        System.out.println("testInitBoard: Passed " + testsPassed + " of " + numTests + " tests.");
    }

    // This method checks to see if checkWin is working properly
    private static void testCheckWin() {
        int numTests = 1;
        int passed = numTests;
        // Checks if the checkWin method can correctly recognize a completed board
        char[][] winTest = {{Config.GOAL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WORKER_CHAR}};
        if (Sokoban.checkWin(winTest)) {
            System.out.println("FAILED: Sokoban.checkWin Test 1. Not a winning board!");
            passed--;
        }

        System.out.println("testCheckWin: Passed " + passed + " of " + numTests + " tests.");

    }

    // This method checks to see if calcDelta is working properly
    private static void testCalcDelta() {
        int numTests = 1;
        int passed = numTests;

        int testCalcArray[] = new int[2];
        testCalcArray = Sokoban.calcDelta(Config.DOWN_CHAR + "" + "2");
        // Tests to see if number index 0 of array returned by calcDelta is the correct number
        if (!(testCalcArray[0] == 2)) {
            System.out
                .println("FAILED: Sokoban.calcDelta Test 1. Expected value at index [0]: 2. Got: "
                    + testCalcArray[0]);
            passed--;
        }
        System.out.println("testCalcDelta: Passed " + passed + " of " + numTests + " tests.");
    }

    // This method checks to see if checkDelta is working properly
    private static void testCheckDelta() {
        int numTests = 2;
        int passed = numTests;

        int deltaPos[] = new int[3];
        char[][] deltaTestLvl = {{Config.GOAL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR},
            {Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WORKER_CHAR}};
        int testDelta[] = {0, 0};
        // This tests to see if checkDelta is returning the right number for null arrays and
        // catching errors
        if (!(Sokoban.checkDelta(deltaTestLvl, deltaPos, testDelta, new char[2]) == -1)) {
            System.out
                .println("FAILED: Sokoban.checkDelta Test 1. Expected return value: -1. Actual: "
                    + Sokoban.checkDelta(deltaTestLvl, deltaPos, testDelta, new char[2]));
            passed--;
        }

        // Test 2
        int[] testDeltaWall = {0, -2};
        int[] deltaWallPos = {2, 2};
        char[] valid = {Config.WORK_GOAL_CHAR, Config.WORKER_CHAR};
        // This checks if checkDelta checks to make sure that the new position is on the board
        if (!(Sokoban.checkDelta(deltaTestLvl, deltaWallPos, testDeltaWall, valid) == -4)) {
            System.out
                .println("FAILED: Sokoban.checkDelta Test 2. Expected return value: -4. Actual: "
                    + Sokoban.checkDelta(deltaTestLvl, deltaWallPos, testDeltaWall, valid));
            passed--;

        }

        System.out.println("testCheckDelta: Passed " + passed + " of " + numTests + " tests.");
    }

    // This method checks to see if togglePos is working properly
    private static void testTogglePos() {
        int numTests = 1; // The number of tests
        int testsPassed = numTests; // Number of tests passed; decremented upon failure
        char[][] testBoard = {{Config.EMPTY_CHAR, Config.WORK_GOAL_CHAR}}; // A simple test board
        int[] testPos = {0, 1}; // Original position of the worker on the test board
        int[] newTestPos = {0, 0}; // Updated position of the worker on the test board
        char[][] newBoard = {{Config.WORKER_CHAR, Config.GOAL_CHAR}}; // The updated test board

        Sokoban.togglePos(testBoard, newTestPos, Config.GOAL_CHAR, Config.WORK_GOAL_CHAR,
            Config.WORKER_CHAR);
        Sokoban.togglePos(testBoard, testPos, Config.WORK_GOAL_CHAR, Config.GOAL_CHAR,
            Config.EMPTY_CHAR);
        if (!(testBoard[0][0] == Config.WORKER_CHAR) || !(testBoard[0][1] == Config.GOAL_CHAR)) {
            System.out
                .println("FAILED: Sokoban.togglePos Test 1. Board not as expected!\nGenerated:");
            Sokoban.printBoard(testBoard);
            System.out.println("Expected:");
            Sokoban.printBoard(newBoard);
            testsPassed--;
        }
        System.out.println("testTogglePos: Passed " + testsPassed + " of " + numTests + " tests.");
    }

    // This method checks to see if shiftBox is working properly
    private static void testShiftBox() {
        int numTests = 1; // The number of tests
        int testsPassed = numTests; // Number of tests passed; decremented upon failure
        int returnVal;
        char[][] testBoard =
            {{Config.GOAL_CHAR, Config.BOX_CHAR}, {Config.EMPTY_CHAR, Config.EMPTY_CHAR}}; // A
                                                                                           // simple
                                                                                           // test
                                                                                           // board
        int[] testPos = {0, 1}; // Original position of the box on the test board
        int[] testDelta = {0, -1}; // Delta to move the box
        char[][] newBoard =
            {{Config.BOX_GOAL_CHAR, Config.EMPTY_CHAR}, {Config.EMPTY_CHAR, Config.EMPTY_CHAR}}; // The
                                                                                                 // updated
                                                                                                 // test
                                                                                                 // board

        returnVal = Sokoban.shiftBox(testBoard, testPos, testDelta);
        if (returnVal != 1) {
            System.out.println(
                "FAILED: Sokoban.shiftBox Test 1. Expected return value 1, got " + returnVal);
            System.out.println("Generated board:");
            Sokoban.printBoard(testBoard);
            System.out.println("Expected:");
            Sokoban.printBoard(newBoard);
            testsPassed--;
        }

        System.out.println("testShiftBox: Passed " + testsPassed + " of " + numTests + " tests.");
    }

    // This method checks to see if doMove is working properly
    private static void testDoMove() {
        char[] testValid = {Config.EMPTY_CHAR, Config.WORKER_CHAR, Config.WORK_GOAL_CHAR}; // Valid
                                                                                           // characters
                                                                                           // to
                                                                                           // test
        int[] testPos = {0, 0}; // An example position to test with
        int[] testdelta = {1, 1}; // An example change in position
        char[][] testCharBoard = new char[][] {

            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.GOAL_CHAR},
            {Config.WORKER_CHAR, Config.BOX_CHAR, Config.WALL_CHAR,},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR}}; // A board to test

        int testDelta = Sokoban.checkDelta(testCharBoard, testPos, testdelta, testValid);

        if (testDelta == -5) {

            int[] testBox = {1, 2};
            int shiftReturn = Sokoban.shiftBox(testCharBoard, testBox, testdelta);
            // Test to see if doMove can recognize boxes on the board
            if (!(shiftReturn < 0)) {
                System.out.println("Test failed, doMove did not recognize box character");
            } else {
                System.out.println("testDoMove: Passed 1 of 1 tests.");
            }
        }
    }

    // This method checks to see if processMove is working properly
    private static void testProcessMove() {
        int[] testPos1 = {0, 0}; // Position to test with
        int[] testdelta1 = {-1, 1}; // Array to represent movement
        char[][] testCharBoard1 = new char[][] {

            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.GOAL_CHAR}, // Test board
            {Config.WORKER_CHAR, Config.BOX_CHAR, Config.WALL_CHAR,},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR}};
            
        // Records what processMove returns
        int testNum = Sokoban.processMove(testCharBoard1, testPos1, testdelta1);
        
        //Tests if processMove returns correct number
        if (testNum == -2) {
            System.out.println("testProccesMove: Passed 1 of 1 test");
        } else {
            System.out.println("processMove incorrectly returned " + testNum + " instead of -2");
        }

    }

}
