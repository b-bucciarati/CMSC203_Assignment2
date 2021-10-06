/*
 *  Class: CMSC203
 *  Instructor: Farnaz Eivazi
 *  Description: This program chooses a random number from 1-100. User then tries to guess the random number while being told how many
 *  guesses, and if guess was > or < the random number. Once number is guessed, gives user option to play again.
 *  Due: 10/5/2021
 *  Platform/Compiler: Eclipse
 *  I pledge that I have completed the programming assignment independently. 
 *  I have not copied the code from a student or any source
 *  I have not given my code to any student.
 *  Daniel Alvarez
 */

import java.util.Scanner; 

public class RandomNumberGuesser {

	public static void main(String[] args) {
		// We start the program off with creating a random number
		RNG randomNum = new RNG();
		int currentRandNum = randomNum.rand();
		//these booleans below are for condition checking 
		boolean keepPlaying = true;
		boolean inputIsValid = false;
		
		//We now have our random number, we can start guessing
		int userGuess = 0; 
		int guessIsHigh = 100;
		int guessIsLow = 1; //initialized our variables to default values
		String yesOrNo = "noAnswerYet"; //string used for asking user if they want to play again
		System.out.println("Please enter your first guess: ");
		Scanner keyboard = new Scanner(System.in);
		userGuess = keyboard.nextInt();
		randomNum.resetCount(); //the class given to us starts the count at 1 for some reason so I just reset it to zero
		
		do { //We want the code to execute at least once so that we can give response if they guess first try; so a do while loop is used
			//Before we compare input to that random number we have to make sure the guess they made was a valid input
			inputIsValid = randomNum.inputValidation(userGuess, guessIsLow, guessIsHigh);
			if(inputIsValid == false) {
				userGuess = keyboard.nextInt();
			}
			//Now that we have a valid input, we let them know if guess is too high or too low & show counter
			//If their guess is correct we ask if they want to play again 
			if(inputIsValid == true) {	
				if(userGuess > currentRandNum) {
					System.out.println("Number of guesses is " + randomNum.getCount());
					System.out.println("Your guess is too high");
					guessIsHigh = userGuess;
					System.out.println("Enter your next guess between "+guessIsLow+" and "+guessIsHigh);
					userGuess = keyboard.nextInt();
				} else if(userGuess < currentRandNum) {
					System.out.println("Number of guesses is " + randomNum.getCount());
					System.out.println("Your guess is too low");
					guessIsLow = userGuess;
					System.out.println("Enter your next guess between "+guessIsLow+" and "+guessIsHigh);
					userGuess = keyboard.nextInt();
				} else if (userGuess == currentRandNum){
					System.out.println("Number of guesses is " + randomNum.getCount());
					System.out.println("Congratulations, you guessed correctly");
					System.out.println("Try again? (yes or no)");
					keyboard.nextLine();
					yesOrNo = keyboard.nextLine();					
					if(yesOrNo.equals("no")) {
						keepPlaying = false;
						System.out.println("Thanks for playing...");
					}
					if(yesOrNo.equals("yes")) {
						randomNum.resetCount();
						guessIsHigh = 100;
						guessIsLow = 1; 
						System.out.println("Please enter your first guess: ");
						userGuess = keyboard.nextInt();
					}
					
				}
			}
		} while(keepPlaying == true);
		keyboard.close();
	}

}
