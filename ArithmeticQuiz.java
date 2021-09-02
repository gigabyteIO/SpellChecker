import java.util.Random;
import java.util.Scanner;

/**
 * ArithmeticQuiz is a 10 question math quiz that chooses +, -, *, / problems at random and asks the 
 * player for an answer on the command line.
 * The player gets 2 guesses per question, first guess is equal to 10 points, second guess is equal to 5 points.
 * At the end of the quiz the players score is output(out of 100) and the player has a choice to play again or
 * exit the program. 
 * @author Martin M.
 *
 */
public class ArithmeticQuiz {

	public static void main(String[] args) {

		ArithmeticProblem problem;           // The arithmetic problem
		Scanner in = new Scanner(System.in); // The scanner to get user input
		String playAgain;					 // Whether the user wants to play another game
		int userInput;						 // The player's answer to the arithmetic problem
		int userScore;                       // The player's score
		char randomOperator;                 // The character that chooses what kind of arithmetic problem is chosen,
											 //    this is chosen at random.

		userScore = 0;

		while (true) {
			
			// Ask user 10 questions
			for (int i = 0; i < 10; i++) {

				// Random integer between 1 - 4
				Random ran = new Random();
				int randomInteger = ran.nextInt(4) + 1;
				// System.out.println(randomInteger);

				switch (randomInteger) {
				case 1:
					randomOperator = '+';
					break;
				case 2:
					randomOperator = '-';
					break;
				case 3:
					randomOperator = '*';
					break;
				default:
					randomOperator = '/';
					break;
				}

				// System.out.println(randomOperator);
				problem = new ArithmeticProblem(randomOperator);

				int guesses = 0;

				// Ask user question, giving them 2 guesses (first guess = full credit, second
				// guess = half credit)
				while (guesses < 2) {
					System.out.print("Question " + (i + 1) + ": ");
					System.out.print(problem.getQuestion());
					userInput = in.nextInt();

					if (userInput == problem.getAnswer() && guesses == 0) {
						System.out.println("Congratulations, that is correct!");
						System.out.println("You get 10 points!\n");
						userScore += 10;
						break;
					} else if (userInput == problem.getAnswer() && guesses == 1) {
						System.out.println("Congratulations, that is correct!");
						System.out.println("You get half credit (5 points)!\n");
						userScore += 5;
						break;
					} else if (userInput != problem.getAnswer() && guesses == 0) {
						System.out.println("Sorry, that is not correct. Try again for half credit.\n");
						guesses++;
					} else {
						System.out.println("Sorry, that is not correct.");
						System.out.println("You get no credit for this problem.");
						System.out.println("The correct answer is " + problem.getAnswer() + ".\n");
						guesses++;
					}

				}

			}
			System.out.println("Your score was " + userScore + " out of 100.\n");
			System.out.print("Would you like to play again(Y/N)?");
			playAgain = in.next();
			playAgain.toUpperCase();

			if (playAgain.equals("N")) {
				System.out.println("Thanks for playing!");
				break;
			} else {
				userScore = 0;
			}

			
		}

	}
}
