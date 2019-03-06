//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Sokoban
// Files: Eliza.java, Eliza.rsp, ElizaTests.java, Config.java
// Course: 200, 1st, 2018
//
// Author: Harrison Bell
// Email: rhbell2@wisc.edu
// Lecturer's Name: Jim Williams
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:
// Partner Email:
// Lecturer's Name:
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// __ Write-up states that pair programming is allowed for this assignment.
// __ We have both read and understand the course Pair Programming Policy.
// __ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Persons:
//
///////////////////////////////////////////////////////////////////////////////


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * This class contains a few methods for testing methods in the Eliza class as they are developed.
 * These methods are private since they are only intended for use within this class.
 * 
 * @author Jim Williams
 * @author Harrison
 *
 */
public class ElizaTests {

    /**
     * This is the main method that runs the various tests. Uncomment the tests when you are ready
     * for them to run.
     * 
     * @param args (unused)
     */
    public static void main(String[] args) {

        // Milestone 1: Process User Input
        // M1: main nothing to do
        // testSeparatePhrases();
        // testFoundQuitWord();
        // testSelectPhrase();
        // testReplaceWord();
        // testAssemblePhrase();

        // Milestone 2:
        // M2: implement parts of main as described in main method comments
        // testSwapWords();
        // testPrepareInput();
        testLoadResponseTable();

        // Milestone 3:
        // main: implement the rest of main as described in the main method comments
        // testFindKeyWordsInPhrase();
        // testSelectResponse();
        // testInputAndResponse();
        // testSaveDialog();
    }

    /**
     * This runs some tests on the separatePhrases method. 1. TODO describe each test in your own
     * words. 2.
     */
    private static void testSeparatePhrases() {
        boolean error = false;
        {
            // 1. Tests to see if method can correctly separate phrases with punctuation and spaces.
            // Test if sizes are equal
            ArrayList<String> phrases =
                Eliza.separatePhrases("No.  I'm talking     to my dog! Bye.");
            ArrayList<String> expected = new ArrayList<>();
            expected.add("no");
            expected.add("i'm talking to my dog");
            expected.add("bye");
            if (phrases.size() != expected.size()) {
                error = true;
                System.out.println("testSeparatePhrases: expected " + expected.size()
                    + " but found " + phrases.size() + " phrases.");
            }


            // 2. Tests if output is equal
            for (int i = 0; i < expected.size(); i++) {
                if (!expected.get(i).equals(phrases.get(i))) {
                    error = true;
                    System.out.println("testSeparatePhrases 2: phrases not the same");
                    System.out.println("  " + expected.get(i));
                    System.out.println("  " + phrases.get(i));
                }
            }
        }
        {
            // 3 Tests if method can separate without punctuation
            ArrayList<String> phrases2 = Eliza.separatePhrases("this tab");
            ArrayList<String> expected2 = new ArrayList<>();
            expected2.add("this tab");
            if (!expected2.get(0).equals(phrases2.get(0))) {
                error = true;
                System.out.println("testSeparatePhrases 3: phrases not the same");
                System.out.println("result: " + phrases2.get(0));
                System.out.println("expected: " + expected2.get(0));
            }
        }
        {
            // 4 Tests if method can separate without punctuation
            ArrayList<String> phrases2 =
                Eliza.separatePhrases("Thank you, but no, I have to go. Goodbye!!!");
            ArrayList<String> expected2 = new ArrayList<>();
            expected2.add("thank you");
            expected2.add("but no");
            expected2.add("i have to go");
            expected2.add("goodbye");

            if (!expected2.get(3).equals(phrases2.get(3))) {
                error = true;
                System.out.println("testSeparatePhrases 4: phrases not the same");
                System.out.println("result: " + phrases2.get(0));
                System.out.println("expected: " + expected2.get(0));
            }
        }



        // Additional test suggestions{
        // "What? This isn't the 4th messy-sentence!" should result in ?
        // "NO" should result in?
        // "this tab" should result in?
        // "What?" should result in?
        // "Thank you, but no, I have to go. Goodbye!!!" should result in?

        if (error) {
            System.out.println("testSeparatePhrases failed");
        } else {
            System.out.println("testSeparatePhrases passed");
        }
    }

    /**
     * This runs some tests on the foundQuitWord method. 
     * 1. TODO describe each test in your own words. 
     * 2.
     */
    // TODO should QUIT_WORDS be embedded within foundQuitWord?
    private static void testFoundQuitWord() {
        boolean error = false;

        // 1.
        // Test if method can find quit words in separate phrase
        ArrayList<String> phrases = new ArrayList<>();
        phrases.add("thank you for talking");
        phrases.add("bye");

        boolean quit = Eliza.foundQuitWord(phrases);
        if (!quit) {
            error = true;
            System.out.println("testFoundQuitWord 1: failed");
        }

        // 2 tests if foundQuitWord returns true if bye is a part of a phrase rather than
        // separate


        // 3 tests if foundQuitWord returns true if goodbye is the first or only phrase
        ArrayList<String> phrases3 = new ArrayList<>();
        phrases3.add("goodbye");


        boolean quit2 = Eliza.foundQuitWord(phrases3);
        if (!quit2) {
            error = true;
            System.out.println("testFoundQuitWord 3: failed");
        }
        // 4 test if foundQuitWord returns true if there are no quit words
        ArrayList<String> phrases4 = new ArrayList<>();
        phrases3.add("Hello");

        boolean quit3 = Eliza.foundQuitWord(phrases4);
        if (quit3 == true) {
            error = true;
            System.out.println("testFoundQuitWord 4: failed");
        }
        // additional test suggestions:
        // should foundQuitWord return true if bye is a part of a phrase rather than
        // separate?
        // should foundQuitWord return true if goodbye is the first or only phrase?
        // should foundQuitWord return true if there are no quit words in the phrases
        // list.

        if (error) {
            System.err.println("testFoundQuitWord failed");
        } else {
            System.out.println("testFoundQuitWord passed");
        }
    }

    /**
     * This runs some tests on the selectPhrase method. 
     * 1. TODO describe each test in your own words. 
     * 2.
     */
    private static void testSelectPhrase() {
        boolean error = false;

        // Test 1 test if 2 have same length
        ArrayList<String> phrases = new ArrayList<>();
        phrases.add("no");
        phrases.add("ni");
        phrases.add("nn");
        String choice = Eliza.selectPhrase(phrases);
        if (!choice.equals("no")) {
            error = true;
            System.out.println("testSelectPhrase 1: failed");
        }

        // additional test suggestions:
        // What should happen when there are 2 choices of the same length?
        // What should happen if an empty list is passed to selectPhrase?

        // Test 2 tests an empty list
        ArrayList<String> phrases1 = new ArrayList<>();
        String choice2 = Eliza.selectPhrase(phrases1);
        if (choice2 == null) {
            System.out.println("testSelectPhrase 2 passed");
        } else {
            System.out.println("testSelectPhrase 2 failed");
        }

        if (error) {
            System.err.println("testSelectPhrase failed");
        } else {
            System.out.println("testSelectPhrase passed");
        }
    }

    /**
     * This runs some tests on the assemblePhrase method. 
     * 1. TODO describe each test in your own words. 
     * 2.
     */
    private static void testAssemblePhrase() {
        boolean error = false;
        // 1 test if assemble phrase can correctly form a phrase
        String[] words = {"This", "is a", "sentence"};
        String sentence = Eliza.assemblePhrase(words);
        String expectedSentence = "This is a sentence";

        if (!sentence.equals(expectedSentence)) {
            error = true;
            System.out.println("testAssembleSentence 1 failed '" + sentence + "'");
        }
        // 2 tests if array is null
        String[] words1 = null;
        String sentence2 = Eliza.assemblePhrase(words1);

        if (!(sentence2 == null)) {
            error = true;
            System.out.println("testAssembleSentence 2 failed '" + sentence + "'");
        }
        // 3 test if just a list of words, with no internal spaces, is
        // passed in
        String[] words2 = {"Thisisasentence"};
        String sentence3 = Eliza.assemblePhrase(words2);
        String expectedSentence1 = "Thisisasentence";
        if (!sentence3.equals(expectedSentence1)) {
            error = true;
            System.out.println("testAssembleSentence 3 failed '" + sentence + "'");
        }

        // additional test suggestions:
        // what should happen if an array with no strings in it is passed in?
        // what should happen if just a list of words, with no internal spaces, is
        // passed in?

        if (error) {
            System.err.println("testAssemblePhrase failed");
        } else {
            System.out.println("testAssemblePhrase passed");
        }
    }

    /**
     * This runs some tests on the findKeyWordsInPhrase method. 
     * 1. TODO describe each test in your own words. 
     * 2.
     */
    private static void testFindKeyWordsInPhrase() {
        boolean error = false;

        {// block so each test has its own variable scope.
         // 1. Tests if method can find keywords at the end
            ArrayList<String> keywords = new ArrayList<String>();
            keywords.add("computer");
            String[] phrase = {"are", "you", "a", "computer"};

            String[] matches = Eliza.findKeyWordsInPhrase(keywords, phrase);
            if (matches == null || matches.length != 2 || !matches[0].equals("are you a")
                || !matches[1].equals("")) {
                error = true;
                System.out.println("testFindKeyWordsInPhrase 1 failed.");
                System.out.println(Arrays.toString(matches));
            }
        }

        {
            // 2. Tests if method can find keywords at the start
            ArrayList<String> keywords = new ArrayList<String>();
            keywords.add("computer");
            String[] phrase = {"computer", "are", "you"};

            String[] matches = Eliza.findKeyWordsInPhrase(keywords, phrase);
            if (matches == null || matches.length != 2 || !matches[0].equals("")
                || !matches[1].equals("are you")) {
                error = true;
                System.out.println("testFindKeyWordsInPhrase 2 failed.");
                System.out.println(Arrays.toString(matches));
            }
        }

        {
            // 3. Tests if method can separate input based on keywords
            ArrayList<String> keywords = new ArrayList<String>();
            keywords.add("computer");
            String[] phrase = {"does", "that", "computer", "on", "your", "desk", "work"};
            String[] matches = Eliza.findKeyWordsInPhrase(keywords, phrase);
            if (matches == null || matches.length != 2 || !matches[0].equals("does that")
                || !matches[1].equals("on your desk work")) {
                error = true;
                System.out.println("testFindKeyWordsInPhrase 3 failed.");
                System.out.println(Arrays.toString(matches));
            }
        }

        {
            // 4. Tests if method can separate input based on keywords again and leaves elements
            // empty at beginning and end if keywords are there
            ArrayList<String> keywords = new ArrayList<String>();

            keywords.add("you");
            keywords.add("me");

            String[] phrase = {"why", "don't", "you", "like", "me"};
            String[] matches = Eliza.findKeyWordsInPhrase(keywords, phrase);
            if (matches == null || matches.length != 3 || !matches[0].equals("why don't")
                || !matches[1].equals("like") || !matches[2].equals("")) {
                error = true;
                System.out.println("testFindKeyWordsInPhrase 4 failed.");
                System.out.println(Arrays.toString(matches));
            }
        }

        {
            // 5. Tests is method only finds keywords in order.
            ArrayList<String> keywords = new ArrayList<String>();
            keywords.add("you");
            keywords.add("me");
            String[] sentence = {"me", "don't", "like", "you"};
            String[] matches = Eliza.findKeyWordsInPhrase(keywords, sentence);
            if (matches != null) {
                error = true;
                System.out.println("testFindKeyWordsInPhrase 5 failed.");
                System.out.println(Arrays.toString(matches));
            }
        }
        {
            // 6. Tests if method can separate input based on keywords and leaves elements empty
            ArrayList<String> keywords = new ArrayList<String>();
            keywords.add("you");
            keywords.add("remind");
            keywords.add("me");
            keywords.add("of");
            String[] sentence = {"you", "remind", "me", "of", "a", "grumpy", "uncle"};
            String[] matches = Eliza.findKeyWordsInPhrase(keywords, sentence);
            if (matches == null) {
                error = true;
                System.out.println("testFindKeyWordsInPhrase 6 failed.");
                System.out.println(Arrays.toString(matches));
            }
        }
        {
            // 7. Tests if method can separate input based on keywords
            ArrayList<String> keywords = new ArrayList<String>();
            keywords.add("you");

            String[] sentence = {"you", "dont", "seem", "to", "say"};
            String[] matches = Eliza.findKeyWordsInPhrase(keywords, sentence);
            if (matches == null) {
                error = true;
                System.out.println("testFindKeyWordsInPhrase 6 failed.");
                System.out.println(Arrays.toString(matches));
            }
        }

        // additional tests?

        if (error) {
            System.err.println("testFindKeyWordsInPhrase failed");
        } else {
            System.out.println("testFindKeyWordsInPhrase passed");
        }
    }

    /**
     * This runs some tests on the saveDialog method. 
     * 1. TODO describe each test in your own words. 
     * 2.
     */
    private static void testSaveDialog() {
        boolean error = false;
        final String TEST_FILENAME = "testing.txt";
        { // 1. Tests if file is created from ArrayList and can read
            ArrayList<String> list = new ArrayList<>();
            list.add("this is a single line.");
            try {
                Eliza.saveDialog(list, TEST_FILENAME);
                String readFromFile = readFile(TEST_FILENAME);
                if (!readFromFile.equals(list.get(0) + "\n")) {
                    error = true;
                    System.out.println("testSaveDialog 1 failed.");
                    System.out.println("content read: " + readFromFile);
                }
                removeFile(TEST_FILENAME);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        // 2. test multiple lines of output written to the file.
        {
            ArrayList<String> list = new ArrayList<>();
            list.add("this is a single line.");
            list.add("this is another line");

            try {
                Eliza.saveDialog(list, TEST_FILENAME);
                String readFromFile = readFile(TEST_FILENAME);
                if (!readFromFile.equals(list.get(0) + "\n" + list.get(1) + "\n")) {
                    error = true;
                    System.out.println("testSaveDialog 1 failed.");
                    System.out.println("content read: " + readFromFile);
                }
                removeFile(TEST_FILENAME);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        if (error) {
            System.err.println("testSaveDialog failed");
        } else {
            System.out.println("testSaveDialog passed");
        }
    }

    /**
     * Supporting method for testSaveDialog
     * @param fileName name of the file to read
     * @return the contents of the file
     */
    private static String readFile(String fileName) {
        // Reads the file to see if saveDialog works
        StringBuffer buf = new StringBuffer();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName));) {
            String line;
            while ((line = reader.readLine()) != null) {
                buf.append(line);
                buf.append("\n");
            }
            return buf.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Supporting method for testSaveDialog.
     * Removes file after it is read
     * @param filename  file to be removed.
     */
    private static void removeFile(String filename) {
        File file = new File(filename);
        try {
            if (file.exists())
                file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This runs some tests on the replaceword method. 
     * 1. TODO describe each test in your own words. 
     * 2.
     */
    private static void testReplaceWord() {
        boolean error = false;

        { // 1. Tests to see if replaceWord can correctly find and replace words from the word map
            String word = "machine";
            String expected = "computer";
            String result = Eliza.replaceWord(word, Config.INPUT_WORD_MAP);
            if (result == null || !result.equals(expected)) {
                error = true;
                System.out.println("testReplaceWord 1 failed. result:'" + result + "' expected:'"
                    + expected + "'");
            }
        }

        { // 2. Tests to see if replaceWord can correctly find and replace words from the pronoun
          // map
            String word = "yourself";
            String expected = "myself";
            String result = Eliza.replaceWord(word, Config.PRONOUN_MAP);
            if (result == null || !result.equals(expected)) {
                error = true;
                System.out.println("testReplaceWord 2 failed. result:'" + result + "' expected:'"
                    + expected + "'");
            }
        }
        // 3 Tests a null wordMap
        String[][] testArray = null;
        String word1 = "cant";
        String result1 = Eliza.replaceWord(word1, testArray);
        if (!(result1.equals(word1))) {
            System.out.print(
                "testReplaceWord 3 failed. result:'" + result1 + "' expected:'" + word1 + "'");
            error = true;
        }

        // additional suggestions:
        // Do these tests meet all the requirements described in the replaceWord header comment,
        // such as handling a null wordMap?

        if (error) {
            System.err.println("testReplaceWord failed");
        } else {
            System.out.println("testReplaceWord passed");
        }
    }

    /**
     * This runs some tests on the swapWords method. 
     * 1. TODO describe each test in your own words. 
     * 2.
     */
    private static void testSwapWords() {
        boolean error = false;

        { // 1.
            String someWords = "What other connections do i see?";
            String expected = "i am cannot remember";
            String result = Eliza.swapWords(someWords, Config.PRONOUN_MAP);
            if (result == null || !result.equals(expected)) {
                error = true;
                System.out.println(
                    "testSwapWords 1 failed. result:'" + result + "' expected:'" + expected + "'");
            }
        }
        /*
         * { // 2. String someWords = "i'm happy"; String expected = "you are happy"; String result
         * = Eliza.swapWords(someWords, Config.PRONOUN_MAP); if (result == null ||
         * !result.equals(expected)) { error = true; System.out.println(
         * "testSwapWords 2 failed. result:'" + result + "' expected:'" + expected + "'"); } }
         * 
         * { // 3. String someWords = "about my dog"; String expected = "about your dog"; String
         * result = Eliza.swapWords(someWords, Config.PRONOUN_MAP); if (result == null ||
         * !result.equals(expected)) { error = true; System.out.println(
         * "testSwapWords 3 failed. result:'" + result + "' expected:'" + expected + "'"); } } { //
         * 4. String someWords = "i am cannot remember"; String expected = "i am cannot remember";
         * String result = Eliza.swapWords(someWords, Config.PRONOUN_MAP); if (result == null ||
         * !result.equals(expected)) { error = true; System.out.println(
         * "testSwapWords 1 failed. result:'" + result + "' expected:'" + expected + "'"); } }
         */
        // additional tests?

        if (error) {
            System.err.println("testSwapWords failed");
        } else {
            System.out.println("testSwapWords passed");
        }
    }

    /**
     * This runs some tests on the selectResponse method. 
     * 1. TODO describe each test in your own words. 
     * 2.
     */
    private static void testSelectResponse() {
        boolean error = false;

        { // 1.
          // is one of the 3 strings chosen?
          // Test to see if method will choose a phrase randomly
            Random randGen = new Random(434);
            ArrayList<String> strList = new ArrayList<>();
            strList.add("The");
            strList.add("happy");
            strList.add("cat");
            String choice = Eliza.selectResponse(randGen, strList);

            if (!(choice.equals("The") || choice.equals("happy") || choice.equals("cat"))) {
                error = true;
                System.out.println("testSelectResponse 1 failed.");
            }
        }

        { // 2.
          // if called 1000 times, are the choices approximately random?
          // This test if the method will appropriately returns the correct phrase after being
          // called
          // 1000 times
            Random randGen = new Random(765);
            ArrayList<String> strList = new ArrayList<>();
            strList.add("this");
            strList.add("is");
            strList.add("a");
            strList.add("list");
            strList.add("to");
            strList.add("choose");
            strList.add("from");
            int[] actualCount = new int[strList.size()];
            int[] expectedCount = new int[] {156, 146, 142, 138, 160, 130, 128};
            for (int iterationIndex = 0; iterationIndex < 1000; iterationIndex++) {
                String choice = Eliza.selectResponse(randGen, strList);
                for (int wordIndex = 0; wordIndex < strList.size(); wordIndex++) {
                    if (choice.equals(strList.get(wordIndex))) {
                        actualCount[wordIndex]++;
                    }
                }
            }
            // since we set a seed on the random number generator we should know the expected
            // outcome
            // Tests to see if method will return the correct phrase if we call it set seed
            for (int countIndex = 0; countIndex < actualCount.length; countIndex++) {
                if (actualCount[countIndex] != expectedCount[countIndex]) {
                    error = true;
                    System.out.println("testSelectResponse 2 failed.");
                    System.out.println("  expectedCount: " + Arrays.toString(expectedCount));
                    System.out.println("  actualCount: " + Arrays.toString(actualCount));
                }
            }

        }
        // 3 tests if null
        {

            Random randGen = new Random(Config.SEED);
            ArrayList<String> strList = null;
            String choice = Eliza.selectResponse(randGen, strList);

            if (choice != null) {
                error = true;
                System.out.println("testSelectResponse 1 failed.");
            }
        }

        // additional test suggestions:
        // What should happen when a list a single string is provided?
        // What should happen when null is passed to selectResponse?


        if (error) {
            System.err.println("testSelectResponse failed");
        } else {
            System.out.println("testSelectResponse passed");
        }
    }

    /**
     * This runs some tests on the prepareInput method. 
     * 1. TODO describe each test in your own words. 
     * 2.
     */
    private static void testPrepareInput() {
        boolean error = false;

        { // 1. Test if prepare input properly responds to a quit word
            String input = "bye";
            String[] result = null;
            result = Eliza.prepareInput("bye");
            if (result != null) {
                error = true;
                System.out.println("testPrepareInput 1: failed");
                System.out.println("  input: " + input);
                System.out.println("  result: " + Arrays.toString(result));
            }
        }
        // 2 Tests if method properly alters user's input

        {
            String input = "I can't do that";
            String[] result = Eliza.prepareInput("I can't do that");
            String[] expected = {"i", "cannot", "do", "that"};
            if (!(Arrays.toString(result).equals(Arrays.toString(expected)))) {
                error = true;
                System.out.println("testPrepareInput 2: failed");
                System.out.println("  input: " + input);
                System.out.println("  result: " + Arrays.toString(result));
                System.out.println("  expected: " + Arrays.toString(expected));
            }
        }

        // 3 Test if method can identify various characters and extra spaces
        {
            String input = "I said    ** goodbye";
            String[] result = Eliza.prepareInput(input);
            String[] expected = {"i", "said", "goodbye"};
            if (!(Arrays.toString(result).equals(Arrays.toString(expected)))) {
                error = true;
                System.out.println("testPrepareInput 3: failed");
                System.out.println("  input: " + input);
                System.out.println("  result: " + Arrays.toString(result));
                System.out.println("  expected: " + Arrays.toString(expected));
            }
        }
        // additional test suggestions
        // "I said goodbye" should result in "i", "said", "goodbye"
        // "I can't do that" should result in "i", "cannot", "do", "that"


        if (error) {
            System.err.println("testPrepareInput failed");
        } else {
            System.out.println("testPrepareInput passed");
        }
    }

    /**
     * This runs some tests on the loadResponseTable method. 
     * 1. TODO describe each test in your own words. 
     * 2.
     */
    private static void testLoadResponseTable() {
        boolean error = false;

        { // 1. Tests if the method properly copies the first row
            ArrayList<String> expected1stRow = new ArrayList<String>();
            expected1stRow.add("computer");
            ArrayList<ArrayList<String>> table = Eliza.loadResponseTable("Eliza.rsp");


            if (!table.get(0).equals(expected1stRow)) {
                error = true;
                System.out.println("testLoadResponseTable 1: failed");
                System.out.println("  expected1stRow: " + expected1stRow);
                System.out.println("  table1stRow: " + table.get(0));



            }

            // 2.Tests to make sure that the produced table has an even number of elements
            if (table.size() % 2 != 0) {
                error = true;
                System.out.println("testLoadResponseTable 2: failed");
                System.out.println(
                    "  expected an even number of elements in table but found: " + table.size());
            }
        }
        { // 3. Tests if the method properly copies the last row
            ArrayList<String> expectedLastRow = new ArrayList<String>();
            expectedLastRow.add("I'm not sure I understand you fully.");
            expectedLastRow.add("Please go on.");
            expectedLastRow.add("What does that suggest to you?");
            expectedLastRow.add("Do you feel strongly about discussing such things?");
            ArrayList<ArrayList<String>> table = Eliza.loadResponseTable("Eliza.rsp");

            if (!table.get(131).equals(expectedLastRow)) {
                error = true;
                System.out.println("testLoadResponseTable 1: failed");
                System.out.println("  expected1stRow: " + expectedLastRow);
                System.out.println("  table1stRow: " + table.get(131));



            }
        }


        // additional test suggestions
        // Are the right number of keywords and responses read in for a keyword/response pair?
        // Are blank lines (containing no spaces or only whitespace) ignored?
        // Are the last rows of the file read in?
        // Are the right number of rows read in?

        if (error) {
            System.err.println("testLoadResponseTable failed");
        } else {
            System.out.println("testLoadResponseTable passed");
        }
    }

    /*
     * Supporting method for testInputAndResponse. Returns 1 if the test passed and 0 if the test
     * failed.
     */
    private static int checkResponse(String input, String expectedResponse, Random rand,
        ArrayList<ArrayList<String>> table) {

        // Tests to see if input and response returns the expected response for each input
        String[] words = Eliza.prepareInput(input);
        if (words == null) {
            if (expectedResponse == null) {
                return 1;
            } else {
                System.out.println("testInputLines  checkResponse error");
                System.out.println("  input='" + input + "'");
                System.out.println("  response=null");
                System.out.println("  expected='" + expectedResponse + "'");
                return 0;
            }
        }

        String response = Eliza.prepareResponse(words, rand, table);
        if (!response.equals(expectedResponse)) {
            System.out.println("testPrepareResponse  checkResponse error");
            System.out.println("  input='" + input + "'");
            System.out.println("  response='" + response + "'");
            System.out.println("  expected='" + expectedResponse + "'");
            return 0;
        } else {
            return 1;
        }
    }

    /*
     * Runs tests on a bunch of example sentences using a Random number generator with a seed.
     */
    private static void testInputAndResponse() {
        int numPassed = 0;
        // since we are using a seed in the random number generator, the responses should be fixed
        // for the provided response table.
        // Test to see if method will return the expected output given certain input.

        Random randGen = new Random(923);
        ArrayList<ArrayList<String>> responseTable =
            Eliza.loadResponseTable("Eliza" + Config.RESPONSE_FILE_EXTENSION);

        numPassed += checkResponse("I like computers.",
            "What do you think machines have to do with your problem?", randGen, responseTable);

        numPassed += checkResponse("What is your name?",
            "I don't care about names -- please continue.", randGen, responseTable);

        numPassed += checkResponse("You remind me of a grumpy uncle.",
            "What other connections do you see?", randGen, responseTable);

        numPassed += checkResponse("You don't seem to say much.", "What are your feelings now?",
            randGen, responseTable);

        numPassed += checkResponse("You reflect me.", "What makes you think I reflect you?",
            randGen, responseTable);

        numPassed += checkResponse("I wish to believe you.",
            "Why do you think you wish to believe me?", randGen, responseTable);

        numPassed += checkResponse("I dreamed of going into space.",
            "Have you ever dreamed of going into space before?", randGen, responseTable);

        numPassed += checkResponse("I apologize for boring you.",
            "Why do you think you apologize for boring me?", randGen, responseTable);

        numPassed += checkResponse("Because it seems the polite thing to do.",
            "Does that reason seem to explain anything else?", randGen, responseTable);

        numPassed += checkResponse("Yes.", "You are sure.", randGen, responseTable);

        numPassed += checkResponse("I remember being in 1st grade.",
            "What is the connection between me and being in 1st grade?", randGen, responseTable);

        numPassed += checkResponse("No, not often. Sometimes I remember being on a boat.",
            "Why do you recollect being on a boat just now?", randGen, responseTable);

        numPassed += checkResponse("My family liked to play cards.",
            "Tell me more about your family.", randGen, responseTable);

        numPassed += checkResponse("Do you remember growing up?", "What about growing up?", randGen,
            responseTable);

        numPassed += checkResponse("Are you a Packers fan?",
            "Perhaps I am a packers fan in your fantasies.", randGen, responseTable);

        numPassed += checkResponse("I am sad to hear that.", "Can you explain what made you sad?",
            randGen, responseTable);

        numPassed += checkResponse("I cannot explain this.",
            "Do you really want to be able to explain this?", randGen, responseTable);

        numPassed += checkResponse("You seem to have a different perspective than many.",
            "You're not really talking about me -- are you?", randGen, responseTable);

        numPassed += checkResponse("I'm talking to my dog.",
            "How long have you been talking to your dog?", randGen, responseTable);

        numPassed += checkResponse("goodbye", null, randGen, responseTable);

        numPassed +=
            checkResponse("", "I'm not sure I understand you fully.", randGen, responseTable);


        int expected = 21;
        if (numPassed == expected) {
            System.out.println("testInputAndResponse passed ");
        } else {
            System.err.println("testInputAndResponse failed " + (expected - numPassed));
        }
    }

}
