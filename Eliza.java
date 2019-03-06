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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * The Eliza class holds the user input and response formation for a system that collects user input
 * and responds appropriately. Eliza is based off of a computer program written at MIT in the 1960's
 * by Joseph Weizenbaum. Eliza uses keyword matching to respond to users in a way that displays
 * interest in the users and continues the conversation until instructed otherwise.
 */
public class Eliza {

    /*
     * This method does input and output with the user. It calls supporting methods to read and
     * write files and process each user input.
     * 
     * @param args (unused)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random(Config.SEED);
        String therapist; // File for therapist
        String nameTherapist = null; // Name of therapist
        ArrayList<String> dialog = new ArrayList<String>(); // ArrayList to save dialogue

        // Check is there are command line arguments
        if (args.length == 0) {
            therapist = "Eliza" + Config.RESPONSE_FILE_EXTENSION;
            nameTherapist = "Eliza";
        } else if (args.length == 1) {
            therapist = args[0] + Config.RESPONSE_FILE_EXTENSION;
            nameTherapist = args[0];
        } else {
            System.out.print("Would you like to speak with Eliza, ");
            int k = 0;
            for (int i = 0; i < args.length - 1; i++) {
                System.out.print(args[i] + ", ");
                k = i;
            }
            System.out.println(args[k + 1] + "?");
            therapist = scanner.next();
            nameTherapist = therapist;
            therapist = therapist + Config.RESPONSE_FILE_EXTENSION;
        }

        // Table of responses
        ArrayList<ArrayList<String>> responseTable = loadResponseTable(therapist);

        System.out.println("Hi I'm " + nameTherapist + " what is your name?");
        String name = scanner.next();

        // These are all saving the dialogue into the file
        String dialog2 = "Nice to meet you " + name + ". What is on your mind?";
        String dialog1 = "Hi I'm " + nameTherapist + " what is your name?";
        String dialog3 = "Goodbye " + name;
        dialog.add(dialog1);
        dialog.add(dialog2);


        String responses; // Store user responses
        boolean end = true; // Variable to end loop
        String responses2; // Stores therapist responses

        System.out.println("Nice to meet you " + name + ". What is on your mind?");
        String userInput = scanner.nextLine();

        // Conversation loop continues until quit word is found in a phrase
        do {
            userInput = scanner.nextLine();
            String[] response = prepareInput(userInput);

            if (prepareInput(userInput) == null) {
                end = false;
                System.out.println("Goodbye " + name);
            } else {
                responses = String.join(" ", response).trim();
                dialog.add(responses);
                responses2 = prepareResponse(response, rand, responseTable);
                System.out.println(responses2);
                dialog.add(responses2);
            }
        } while (end);
        dialog.add(dialog3);

        boolean quit = true; // Variable to end save loop
        String endStr; // String to store user answer
        String filename = null; // Name of the file to create
        do {
            System.out.println("Would you like to have a record of our conversation (y/n): ");
            endStr = scanner.nextLine().trim().toLowerCase();

            // If user wants save file calls save dialog else end conversation
            try {
                if (endStr == null || endStr.length() == 0) {
                    break;
                }
                if (!(endStr.charAt(0) == 'y')) {
                    quit = false;
                    break;
                } else {
                    System.out.println("Enter filename: ");
                    filename = scanner.next();
                    saveDialog(dialog, filename);
                    
                }
            } catch (IOException ex) {
                System.out.println("Unable to save conversation to: " + filename);
                continue;
            }
            System.out
                .println("Thanks again for talking! Our conversation is saved in: " + filename);
            quit = false;
        } while (quit);
    }

    /**
    * This method processes the user input, returning an ArrayList containing Strings,
    * where each String is a phrase from the user's input. This is done by removing leading
    * and trailing whitespace, making the user's input all lower case, then going through 
    * each character of the user's input. When going through each character this
    * keeps all digits, alphabetic characters and ' (single quote). The characters ? ! , . 
    * signal the end of a phrase, and possibly the beginning of the next phrase,
    * but are not included in the result.
    * All other characters such as ( ) - " ] etc. should be replaced with a space. 
    * This method makes sure that every phrase has some visible characters but no
    * leading or trailing whitespace and only a single space between words of a phrase.
    * If userInput is null then return null, if no characters then return a
    * 0 length list, otherwise return a list of phrases.  Empty phrases and phrases
    * with just invalid/whitespace characters should NOT be added to the list.
    * 
    * Example userInput: "Hi,  I  am! a big-fun robot!!!"
    * Example returned: "hi", "i am", "a big fun robot"
    * 
    * @param userInput text the user typed
    * @return the phrases from the user's input
    */
    public static ArrayList<String> separatePhrases(String userInput) {
        String input = userInput.trim().toLowerCase().replaceAll("[^'?,!.A-Za-z0-9]", " ");
        // Takes user input and trims whitespace, converts it to lower case and removes bad
        // characters

        input = input + "."; // Ensures that every userInput will have end punctuation

        ArrayList<String> userResponses = new ArrayList<String>(); // Array of user responses
        int placeHolder = 0; // marks where the loop finds an end character

        // Loop searches through users input and divides it accordingly
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == ',' || input.charAt(i) == '!' || input.charAt(i) == '?'
                || input.charAt(i) == '.') {

                String newString =
                    input.substring(placeHolder, i).trim().replaceAll("\\s{2,}", " ");
                // divides up string when a end character is found

                placeHolder = i + 1;
                // Replaces invalid characters with spaces and adds it to userResponse if it is not
                // null
                if (!(newString.length() == 0) && !(newString == null)) {
                    userResponses.add(newString);
                }
            }
        }

        return userResponses;

    }

    /**
     * Checks whether any of the phrases in the parameter match
     * a quit word from Config.QUIT_WORDS.  Note: complete phrases
     * are matched, not individual words within a phrase.
     * 
     * @param phrases List of user phrases
     * @return true if any phrase matches a quit word, otherwise false
     */
    public static boolean foundQuitWord(ArrayList<String> phrases) {
        // iterates through array list and looks for quit words
        for (int i = 0; i < phrases.size(); i++) {
            for (int j = 0; j < Config.QUIT_WORDS.length; j++) {
                if (phrases.get(i).equals(Config.QUIT_WORDS[j])) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Iterates through the phrases of the user's input, finding
     * the longest phrase to which to respond. If two phrases are the same 
     * length, returns whichever has the lower index in the list. 
     * If phrases parameter is null or size 0 then return null.
     * 
     * @param phrases List of user phrases
     * @return the selected phrase
     */
    public static String selectPhrase(ArrayList<String> phrases) {
        Integer storageIndex = 0;// Stores the index of largest array

        if (phrases.size() == 0 || phrases == null) {
            return "";
        }
        int storageLength = phrases.get(0).length();// Stores the length of the largest array
        // Searches array for the largest and stores its index in storageIndex
        for (int i = 0; i < phrases.size(); i++) {
            if ((phrases.get(i).length() > storageLength)) {
                storageIndex = i;
                storageLength = phrases.get(i).length();
            }
        }
        return phrases.get(storageIndex);
    }

    /** 
     * Looks for a replacement word for the word parameter and if found,
     * returns the replacement word. Otherwise if the word parameter is not
     * found then the word parameter itself is returned. 
     * The wordMap parameter contains rows of match and replacement strings.
     * On a row, the element at the 0 index is the word to match and if it 
     * matches return the string at index 1 in the same row.  Some example
     * word maps that will be passed in are Config.INPUT_WORD_MAP and 
     * Config.PRONOUN_MAP. 
     * 
     * If word is null return null. If wordMap is null or wordMap length is 
     * 0 simply return word parameter. For this implementation it is reasonable to 
     * assume that if wordMap length is >= 1 then the number of elements in 
     * each row is at least 2.
     * 
     * @param word The word to look for in the map
     * @param wordMap  The map of words to look in
     * @return the replacement string if the word parameter is found in the 
     * wordMap otherwise the word parameter itself.
     */
    public static String replaceWord(String word, String[][] wordMap) {
        word = word.toLowerCase();

        if (wordMap == null || word == null || word.length() == 0) {
            return word;
        } else {
            // If word matches word from wordMap then it is replaces with its given replacement
            for (int i = 0; i < wordMap.length; i++) {

                if (word.equals(wordMap[i][0])) {
                    word = wordMap[i][1];
                    i = wordMap.length;
                }
            }
        }
        return word;
    }

    /**
     * Concatenates the elements in words parameter into a string with
     * a single space between each array element. Does not change any 
     * of the strings in the words array. There are no leading or trailing 
     * spaces in the returned string.
     * 
     * @param words a list of words
     * @return a string containing all the words with a space between each.
     */
    public static String assemblePhrase(String[] words) {
        String str;
        if (!((words == null) || (words.length == 0))) {
            str = String.join(" ", words).trim(); // Joins the words in words array and trims it
        } else {
            str = null;
        }
        return str;
    }

    /**
     * Replaces words in phrase parameter if matching words are found
     * in the mapWord parameter. A word at a time from phrase parameter
     * is looked for in wordMap which may result in more than one word.
     * For example: i'm => i am
     * Uses the replaceWord and assemblePhrase methods.
     * Example wordMaps are Config.PRONOUN_MAP and Config.INPUT_WORD_MAP.
     * If wordMap is null then phrase parameter is returned.
     * Note: there will Not be a case where a mapping will itself
     * be a key to another entry. In other words, only one pass
     * through swapWords will ever be necessary.
     * 
     * @param phrase The given phrase which contains words to swap
     * @param wordMap Pairs of corresponding match & replacement words
     * @return The reassembled phrase
     */
    public static String swapWords(String phrase, String[][] wordMap) {
        //Splits the string into an array with each index forming after running into a space
        String[] words = phrase.split(" ");

        //Runs replace word for every word in the phrase string
        for (int i = 0; i < words.length; i++) {

            words[i] = replaceWord(words[i], wordMap);
        }
        // Puts the array together into a string
        String newString = assemblePhrase(words); //

        return newString;
    }

    /**
     * This prepares the user input. First, it separates input into phrases
     * (using separatePhrases). If a phrase is a quit word (foundQuitWord) 
     * then return null.  Otherwise, select a phrase (selectPhrase), swap input 
     * words (swapWords with Config.INPUT_WORD_MAP) and return an array with
     * each word its own element in the array. 
     * 
     * @param input The input from the user
     * @return  words from the selected phrase
     */
    public static String[] prepareInput(String input) {
        //Separates phrases to look at
        ArrayList<String> userResponse = separatePhrases(input);
        
        //Checks if phrase is a quit word
        if (foundQuitWord(userResponse)) {
            return null;
        } else {
          //Chooses a phrase to look at and runs swapWords
            String phrase = selectPhrase(userResponse);
            String newPhrase = swapWords(phrase, Config.INPUT_WORD_MAP);
            String[] response = newPhrase.split(" ");;
            return response;
        }
    }

    /**
     * Reads a file that contains keywords and responses.  A line contains either a list of keywords
     * or response, any blank lines are ignored. All leading and trailing whitespace on a line
     * is ignored. A keyword line begins with "keywords" with all the following tokens on the line, 
     * the keywords.  Each line that follows a keyword line that is not blank is a possible response
     * for the keywords. For example (the numbers are for our description purposes here and are not in the
     * file): 
     * 
     *1  keywords computer
     *2  Do computers worry you?
     *3  Why do you mention computers?
     *4
     *5  keywords i dreamed
     *6  Really, <3>?
     *7  Have you ever fantasized <3> while you were awake?
     *8
     *9  Have you ever dreamed <3> before?
     *
     *   In line 1 is a single keyword "computer" followed by two possible responses on lines
     *   2 and 3. Line 4 and 8 are ignored since they are blank (contain only whitespace).
     *   Line 5 begins new keywords that are the words "i" and "dreamed".  This keyword list
     *   is followed by three possible responses on lines 6, 7 and 9.
     *   
     *   The keywords and associated responses are each stored in their own ArrayList. The
     *   response table is an ArrayList of the keyword and responses lists. For every keywords list
     *   there is an associated response list. They are added in pairs into the list
     *   that is returned.  There will always be an even number of items in the returned list.
     *   
     *   Note that in the event an IOException occurs when trying to read the file then
     *   an error message "Error reading <fileName>", where <fileName> is the parameter, 
     *   is printed and a non-null reference is returned, which may or may not have any elements
     *   in it.
     * 
     * @param fileName  The name of the file to read
     * @return  The response table
     */
    public static ArrayList<ArrayList<String>> loadResponseTable(String fileName) {
        Scanner scanner = null;
        ArrayList<ArrayList<String>> responseTable = new ArrayList<ArrayList<String>>();
        String[] ar;
        
        //Take name of a file and creates a 2D array list out of file
        try {
            File text = new File(fileName);
            scanner = new Scanner(text);
            String line;
            String restLine;
            boolean responses = false;
            
            //Checks if line is a keywords, empty, or response line
            while (scanner.hasNextLine()) {
                
                //If line is keywords line add the keywords
                line = scanner.nextLine().trim();
                if (line.contains("keywords")) {
                    restLine = line.substring(8).trim();
                    ar = restLine.split(" ");
                    responseTable.add(new ArrayList<String>());
                    int i = responseTable.size() - 1;
                    for (int j = 0; j < ar.length; j++) {
                        responseTable.get(i).add(ar[j]);
                    }

                    responses = true;

                } else if (line.length() == 0 || line == null) {
                    //continues if line is empty
                    continue;
                } else {
                    //add responses to array to match keywords
                    if (responses) {
                        responseTable.add(new ArrayList<String>());
                        responses = false;
                    }
                    responseTable.get(responseTable.size() - 1).add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading " + fileName);
            return responseTable;
        }
        return responseTable;
    }

    /**
     * Checks to see if the keywords match the sentence. In other words, checks to see that all the
     * words in the keyword list are in the sentence and in the same order. If all the keywords match
     * then this method returns an array with the unmatched words before, between and after 
     * the keywords. If the keywords do not match then null is returned. 
     * 
     * When the phrase contains elements before, between, and after the keywords, each set of the 
     * three is returned in its own element
     * String[] keywords = {"i", "dreamed"};
     * String[] phrase = {"do", "you", "know", "that", "i", "have", "dreamed", "of", "being", "an", "astronaut"};
     * 
     * 
     * 
    
     * 
     * toReturn[0] = "do you know that"
     * toReturn[1] = "have"
     * toReturn[2] = "of being an astronaut"
     *  
     * In an example where there is a single keyword, the resulting List's first element will be the 
     * the pre-sequence element and the second element will be everything after the keyword, in the phrase
     * String[] keywords = {"always"};
     * String[] phrase = {"I", "always", "knew"};
     * 
     * toReturn[0] = "I"
     * toReturn[1] = "knew"
     * 
     * In an example where a keyword is not in the phrase in the correct order, null is returned.
     * String[] keywords = {"computer"};
     * String[] phrase = {"My","dog", "is", "lost"};
     * 
     * return null
     * 
     * @param keywords The words to match, in order, in the sentence.
     * @param phrase Each word in the sentence.
     * @return The unmatched words before, between and after the keywords or null if the keywords
     * are not all matched in order in the phrase.
     */
    public static String[] findKeyWordsInPhrase(ArrayList<String> keywords, String[] phrase) {
        int j = 0;
        int k = 0;
        String newString = "";
        int counter = 0;
        int end = 0;
        int keyword = keywords.size() + 1;
        String[] toReturn = new String[keyword];
        
        //Compares each input to keywords ArrayList
        for (int i = 0; i <= keywords.size(); i++) {
            if (j == phrase.length) {
                toReturn[k] = newString;
                break;
            }
            if (i == keywords.size()) {
                for (int b = 0; b < phrase.length - counter; b++) {
                    newString = newString + " " + phrase[j];
                    newString = newString.trim();
                    j++;
                }
                toReturn[k] = newString;
                break;
            } else if (!(keywords.get(i).equals(phrase[j]))) {
                //If input doesn't match keywords add to return array
                newString = newString + " " + phrase[j];
                newString = newString.trim();
                j++;
                i--;
                counter++;
            } else {
                //If it does add previous words from input to Array
                toReturn[k] = newString;
                j++;
                k++;
                newString = "";
                counter++;
            }
            end = i;
        }
        for (int c = 0; c < toReturn.length; c++) {
            if (toReturn[c] == null) {
                toReturn = null;
                break;
            }
        }
        if (end < keywords.size() - 1) {
            toReturn = null;
        }
        return toReturn;
    }

    /**
     * Selects a randomly generated response within the list of possible responses
     * using the provided random number generator where the number generated corresponds
     * to the index of the selected response. Use Random nextInt( responseList.size())
     * to generate the random number.  If responseList is null or 0 length then
     * return null.
     * 
     * @param rand  A random number generator.
     * @param responseList  A list of responses to choose from.
     * @return A randomly selected response
     */
    public static String selectResponse(Random rand, ArrayList<String> responseList) {
        //If response list is not null select a random response 
        if (responseList == null || responseList.size() == 0) {
            return null;
        } else {
            int random = rand.nextInt(responseList.size());
            return responseList.get(random);
        }



    }

    /**
     * This method takes processed user input and forms a response.
     * This looks through the response table in order checking to
     * see if each keyword pattern matches the userWords. The first matching 
     * keyword pattern found determines the list of responses to choose from. 
     * A keyword pattern matches the userWords, if all the keywords are found, 
     * in order, but not necessarily contiguous. This keyword matching is done 
     * by findKeyWordsInPhrase method.  See the findKeyWordsInPhrase algorithm
     * in the Eliza.pdf.  
     * 
     * If no keyword pattern matches then Config.NO_MATCH_RESPONSE is returned.
     * Otherwise one of possible responses for the matched keywords is selected
     * with selectResponse method. The response selected is checked for the 
     * replacement symbol <n> where n is 1 to the length of unmatchedWords array
     * returned by findKeyWordsInPhrase.  For each replacement symbol the 
     * corresponding unmatched words element (index 0 for <1>, 1 for <2> etc.)
     * has its pronouns swapped with swapWords using Config.PRONOUN_MAP and then
     * replaces the replacement symbol in the response.
     * 
     * @param userWords using input after preparing.
     * @param rand A random number generator.
     * @param responseTable  A table containing a list of keywords and response pairs.  
     * @return The generated response
     */
    public static String prepareResponse(String[] userWords, Random rand,
        ArrayList<ArrayList<String>> responseTable) {
        String toReturn = null;
        String newString = null;

        // Iterate through the response table.
        // The response table has paired rows. The first row is a list of key
        // words, the next a list of corresponding responses. The 3rd row another
        // list of keywords and 4th row the corresponding responses.
        // checks to see if the current keywords match the user's words
        // using findKeyWordsInPhrase.
        // if no keyword pattern was matched, return Config.NO_MATCH_RESPONSE
        // else, select a response using the appropriate list of responses for the keywords
        // Look for <1>, <2> etc in the chosen response. The number starts with 1 and
        // there won't be more than the number of elements in unmatchedWords returned by
        // findKeyWordsInPhrase. Note the number of elements in unmatchedWords will be
        // 1 more than the number of keywords.
        // For each <n> found, find the corresponding unmatchedWords phrase (n-1) and swap
        // its pronoun words (swapWords using Config.PRONOUN_MAP). Then use the
        // result to replace the <n> in the chosen response.
        // in the selected echo, swap pronouns
        // inserts the new phrase with pronouns swapped, into the response

        for (int i = 0; i < responseTable.size(); i++) {
            if (findKeyWordsInPhrase(responseTable.get(i), userWords) == null) {
                toReturn = Config.NO_MATCH_RESPONSE;
            } else {
                String[] keywords = findKeyWordsInPhrase(responseTable.get(i), userWords);
                toReturn = selectResponse(rand, responseTable.get(i + 1));
                i = responseTable.size();

                for (int n = 0; n <= keywords.length; n++) {
                    String number = "<" + n + ">";
                    if (n >= 1) {
                        newString = keywords[n - 1];
                        newString = swapWords(newString, Config.PRONOUN_MAP);
                    }
                    if (toReturn.contains(number)) {
                        toReturn = toReturn.replace(number, newString);
                    }
                }
            }
        }



       


        return toReturn;
    }

    /**
     * Creates a file with the given name, and fills that file
     * line-by-line with the tracked conversation. Every line ends
     * with a newline. Throws an IOException if a writing error occurs.
     * 
     * @param dialog the complete conversation
     * @param fileName The file in which to write the conversation
     * @throws IOException
     */
    public static void saveDialog(ArrayList<String> dialog, String fileName) throws IOException {
        PrintWriter writer = new PrintWriter(new FileOutputStream(fileName));
        //Creates a file and writes the ArrayList into it
        for (String dialogue : dialog)
            writer.println(dialogue);
        writer.close();
    }
}
