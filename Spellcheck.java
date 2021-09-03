import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * 
 * @author Martin M.
 *
 */
public class Spellcheck {

	private static String word; // The word being spell checked
	private static WordList wordList = new WordList(); // The list of words that the program checks user input against
	private static TreeSet<String> possibleWords = new TreeSet<String>();

	public static void main(String[] args) {

		while (true) {
			word = getInput();
			 deleteCharacter(word, possibleWords);
			 addCharacter(word, possibleWords);
			 changeCharacter(word, possibleWords);
			 swapCharacter(word, possibleWords);
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

		String userInput;
		Scanner in = new Scanner(System.in);

		System.out.print("Spellcheck: ");
		userInput = in.nextLine();
		userInput = userInput.trim();
		userInput = userInput.toLowerCase();

		return userInput;

	}

	/**
	 * Prints out all the possible corrections for the spell checked word.
	 * 
	 * @param possibleWords The tree set holding all of the possible corrections.
	 */
	public static void printPossibleWords(TreeSet<String> possibleWords) {
		for (String word : possibleWords)
			System.out.println(word);

		System.out.println();
		possibleWords.clear();
	}

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

		// StringBuilder builder = new StringBuilder(word);
		for (int i = 0; i < word.length(); i++) {
			StringBuilder builder = new StringBuilder(word);
			builder.deleteCharAt(i);

			String wordCheck = builder.toString();
			if (wordList.contains(wordCheck)) {
				possibleWords.add(wordCheck);
			}
		}

		return possibleWords;
	}

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

		// StringBuilder builder = new StringBuilder(word);
		for (int i = 0; i < word.length(); i++) {
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
	}

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

		// StringBuilder builder = new StringBuilder(word);
		for (int i = 0; i < word.length(); i++) {
			StringBuilder builder = new StringBuilder(word);
			for (char ch = 'a'; ch <= 'z'; ch++) {
				builder.deleteCharAt(i);
				builder.insert(i, ch);
				// System.out.println(builder.toString());

				String wordCheck = builder.toString();
				if (wordList.contains(wordCheck)) {
					possibleWords.add(wordCheck);
				}
			}
		}

		return possibleWords;
	}

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
			second = builder.charAt(i+1);
			builder.setCharAt(i, second);
			builder.setCharAt(i+1, first);
			
			//System.out.println(builder.toString());

			String wordCheck = builder.toString();
			if (wordList.contains(wordCheck)) {
				possibleWords.add(wordCheck);
			}

		}

		return possibleWords;
	}

}
