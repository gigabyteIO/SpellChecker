import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * This is a small command line spell checking program that prompts the user to
 * input a word and outputs a list of possible corrections. If the user inputs a
 * correctly spelled word, the program tells the user that their word is spelled
 * correctly. User can input 0 to exit the program. 
 * 
 * @author Martin M.
 *
 */
public class Spellcheck {

	private static String word; // The word being spell checked
	private static WordList wordList = new WordList(); // The list of words that the program checks user input against
	private static TreeSet<String> possibleWords = new TreeSet<String>(); // The list of possible corrections to the
																			// user input

	public static void main(String[] args) {

		System.out.println(
				"Welcome to command line spell check!\n" + "Input a word below to check if it's spelled correctly.\n"
						+ "If it's not correctly spelled, the program will output a list of possible words!\n"
						+ "At any time enter 0 to quit the program\n");

		while (true) {
			doSpellCheck();
		}
	}

	/**
	 * Prompts user for input, and runs spell check methods on the input.
	 */
	public static void doSpellCheck() {
		word = getInput();

		if (wordList.contains(word))
			System.out.println(word + " is spelled correctly!\n");
		else {
			deleteCharacter(word, possibleWords);
			addCharacter(word, possibleWords);
			changeCharacter(word, possibleWords);
			swapCharacter(word, possibleWords);
			insertSpace(word, possibleWords);
			printPossibleWords(possibleWords);
		}
	}

	/**
	 * Prompts user for the input, changes input to lower case, and returns the
	 * input as a string.
	 * 
	 * @return the user's input.
	 */
	public static String getInput() {

		String userInput = null;
		int exitFlag;
		Scanner in = new Scanner(System.in);

		System.out.print("Spellcheck: ");
		if (in.hasNextInt()) {
			exitFlag = in.nextInt();
			if (exitFlag == 0) {
				System.out.println("See you next time!");
				System.exit(0);
			}
			else {
				System.out.println("Please input a word!");
				return "";
			}
		} else {
			userInput = in.nextLine();
			userInput = userInput.trim();
			userInput = userInput.toLowerCase();
		}

		return userInput;

	} // End getInput()

	/**
	 * Prints out all the possible corrections for the spell checked word.
	 * 
	 * @param possibleWords The tree set holding all of the possible corrections.
	 */
	public static void printPossibleWords(TreeSet<String> possibleWords) {
		System.out.println("Possible corrections: \n");
		for (String word : possibleWords)
			System.out.println(word);

		System.out.println();
		possibleWords.clear();
	} // End printPossibleWords()

	/**
	 * Deletes each character in a word, and tests if the resulting word is a
	 * possible correction in the word list.
	 * 
	 * @param word          the word that we're deleting characters from.
	 * @param possibleWords the list of possible words resulting from the character
	 *                      deletion.
	 * @return the list of possible corrections.
	 */
	public static TreeSet<String> deleteCharacter(String word, TreeSet<String> possibleWords) {

		for (int i = 0; i < word.length(); i++) {
			StringBuilder builder = new StringBuilder(word);
			builder.deleteCharAt(i);

			String wordCheck = builder.toString();
			if (wordList.contains(wordCheck)) {
				possibleWords.add(wordCheck);
			}
		}
		return possibleWords;
	} // End deleteCharacter()

	/**
	 * Adds a character (a to z) in each position in the word, and tests if the
	 * resulting word is a possible correction in the word list.
	 * 
	 * @param word          the word that we're adding characters to.
	 * @param possibleWords the list of possible words resulting from the character
	 *                      deletion.
	 * @return the list of possible corrections.
	 */
	public static TreeSet<String> addCharacter(String word, TreeSet<String> possibleWords) {

		for (int i = 0; i < word.length() + 1; i++) {
			for (char ch = 'a'; ch <= 'z'; ch++) {
				StringBuilder builder = new StringBuilder(word);
				builder.insert(i, ch);

				String wordCheck = builder.toString();
				if (wordList.contains(wordCheck)) {
					possibleWords.add(wordCheck);
				}
			}
		}
		return possibleWords;
	} // End addCharacter()

	/**
	 * Changes the character (a to z) in each position in the word, and tests if the
	 * resulting word is a possible correction in the word list.
	 * 
	 * @param word          the word that we're modifying.
	 * @param possibleWords the list of possible words resulting from the character
	 *                      change.
	 * @return the list of possible corrections.
	 */
	public static TreeSet<String> changeCharacter(String word, TreeSet<String> possibleWords) {

		for (int i = 0; i < word.length(); i++) {
			StringBuilder builder = new StringBuilder(word);
			for (char ch = 'a'; ch <= 'z'; ch++) {
				builder.deleteCharAt(i);
				builder.insert(i, ch);

				String wordCheck = builder.toString();
				if (wordList.contains(wordCheck)) {
					possibleWords.add(wordCheck);
				}
			}
		}
		return possibleWords;
	} // End changeCharacter()

	/**
	 * Reverses the order in each consecutive pair of characters in the word, and
	 * tests if the resulting word is a possible correction in the word list.
	 * 
	 * @param word          the word that we're modifying.
	 * @param possibleWords the list of possible words resulting from the character
	 *                      change.
	 * @return the list of possible corrections.
	 */
	public static TreeSet<String> swapCharacter(String word, TreeSet<String> possibleWords) {

		for (int i = 0; i < word.length() - 1; i++) {
			StringBuilder builder = new StringBuilder(word);
			char first;
			char second;

			first = builder.charAt(i);
			second = builder.charAt(i + 1);
			builder.setCharAt(i, second);
			builder.setCharAt(i + 1, first);

			// System.out.println(builder.toString());

			String wordCheck = builder.toString();
			if (wordList.contains(wordCheck)) {
				possibleWords.add(wordCheck);
			}
		}
		return possibleWords;
	} // End swapCharacter()

	/**
	 * Inserts a space into each position in the word, and tests if the resulting
	 * word(s) are a possible correction in the word list.
	 * 
	 * @param word          the word that we're modifying.
	 * @param possibleWords the list of possible words resulting from the character
	 *                      change.
	 * @return the list of possible corrections.
	 */
	public static TreeSet<String> insertSpace(String word, TreeSet<String> possibleWords) {

		for (int i = 0; i < word.length() - 1; i++) {
			StringBuilder builder = new StringBuilder(word);
			String left, right;

			left = builder.substring(0, i + 1);
			right = builder.substring(i + 1);
			// System.out.println("Left side: " + left);
			// System.out.println("Right side: " + right);

			if (wordList.contains(left) || wordList.contains(right)) {
				if (wordList.contains(left))
					possibleWords.add(left);
				if (wordList.contains(right))
					possibleWords.add(right);
			}
		}
		return possibleWords;
	} // End insertSpace()

} // End Spellcheck.java
