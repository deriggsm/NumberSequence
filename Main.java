//Number (sequence) game
//Uses three methods.
// numberBank() method,
// meth() method and meth2 () method

import java.util.Scanner;                   //for use inputs
public class Main {
    // numberBank method to store numbers that will be mapped to guess
    static void numberBank(String[] numbersUsedInGame) {
        // array of Strings
        // number bank contains 10 numbers greater than 7 characters in length.
        numbersUsedInGame[0]="123456789"; //number is stored in 10 element string array an is 9 characters in length
        numbersUsedInGame[1]="0123456789"; //number is stored in 10 element string array an is 9 characters in length
        numbersUsedInGame[2]="01259865301"; //number is stored in 10 element string array an is 11 characters in length
        numbersUsedInGame[3]="782467347634"; //number is stored in 10 element string array an is 12 characters in length
        numbersUsedInGame[4]="5341079678"; //number is stored in 10 element string array an is 10 characters in length
        numbersUsedInGame[5]="7657867345"; //number is stored in 10 element string array an is 10 characters in length
        numbersUsedInGame[6]="934784727"; //number is stored in 10 element string array an is 9 characters in length
        numbersUsedInGame[7]="3058965338"; //number is stored in 10 element string array an is 10 characters in length
        numbersUsedInGame[8]="1723673439"; //number is stored in 10 element string array an is 10 characters in length
        numbersUsedInGame[9]="4588578383"; //number is stored in 10 element string array an is 10 characters in length
    }
    //meth method for "Allow the user to guess the number in its entirety at any time. "
    static boolean meth(String[] numbersUsedInGame, String guessDigitInNumber, int randomNumber ) {
        //uses random number in bank and Digit guess from user, when following statement true, followed by if statement in main
        return numbersUsedInGame[randomNumber].equals(guessDigitInNumber.toLowerCase());
    }
    //meth 2 method for "Keep track of the Digits the user guesses so that they don't guess the same Digit twice."
    static boolean meth2 (String[] guessCorrectDigitsInNumber, String guessDigitInNumber, boolean [] checkDigitRepeatedGuessed, int[] numeralCount, Character [] numeral, int high, int low){
        for (int m = 0; m < 10; m++) {         //index for numeral
            if (guessDigitInNumber.equalsIgnoreCase(numeral[m].toString())) {  //converts character to string in array check if equal to guess Digit
                checkDigitRepeatedGuessed[m] = true;  //boolean is true when Digit is matching numeral
                numeralCount[m]++;        // the count for the corresponding Digit goes up by one
            }
        }
        for  (int l=low; l <= high; l++) { //uses result from previous loop, but this index is for length of game number
            for (int k = 0; k < 10; k++) { //index for numeral
                if (checkDigitRepeatedGuessed[k]) { //if boolean true then enter next if statement
                    if ((guessCorrectDigitsInNumber[l].equalsIgnoreCase(guessDigitInNumber))&&(numeralCount[k]>1) && (guessDigitInNumber.equalsIgnoreCase(numeral[k].toString()))){ //check the count for greater than one repeated guess occurrence
                        return true; //return true if Digit has been guess more than once for game number
                    }
                }
            }
        }
        return false; //otherwise return false
    }
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in); // allows input from keyboard
        char playAgain ;        // variable character that when is Y or y will continue game
        //int numbersUsedCount=0; // was used to count numbers used
        String[]numbersUsedInGame=new String[10]; // String array used to store future random numbers (sequence)
        boolean[]numbersUsedInGameBank=new boolean[numbersUsedInGame.length]; //A boolean array that stores true if corresponding number used
        numberBank(numbersUsedInGame);  // method 1 invoked // stores numbers in string array
        Character [] numeral ={'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        do  {  //do while loop for repeating the game, will go through loop first time but condition set through playAgain variable = y or Y to continue iterations
            // generate random number to use in string of arrays
            int randomNumber = ((int) (Math.floor(Math.random() * (numbersUsedInGame.length)))); // select random number from string array

            while (numbersUsedInGameBank[randomNumber]) { //while loop with if statement to randomize number again if previously used is true
                if (numbersUsedInGameBank[randomNumber]) {
                    randomNumber = ((int) (Math.floor(Math.random() * (numbersUsedInGame.length))));
                }
            }
            //Inputs Welcomes user to my game, tell user number is ready to be guessed, and have 10 guess remaining
            System.out.println("Welcome to Mikael's number (sequence) Game!\n\nI'm ready: I've chosen a number (sequence) for you to guess!\n\nYou have 10 incorrect guesses left.\n");
            int incorrectGuessRemaining = 10; //used to determine when game ends, in one way it is equal to 0
            int DigitsCorrect = 0, temp=0, temp2=0; //used in for loops below, DigitCorrect helps map guess to number
            int low = 0; //used in loops for start checking first element in array
            int high = (numbersUsedInGame[randomNumber].length() - 1); //used in loops for start checking last element in array
            String[] guessCorrectDigitsInNumber = new String[high + 1]; //used to store successful guess attempts (mapped number)
            String[] guessIncorrectDigitsInNumber = new String[10]; //usd to store unsuccessful guess attempts
            int foundIncorrectCount = 0, foundCorrectCount = 0; //used to count incorrect and count correct Digits
            boolean[] checkDigitRepeatedGuessed = new boolean[10];
            int[] numeralCount = new int[10];
            for (int a = low; a <= (high); a++) { //initialize guessCorrectDigitsInNumber[a] = "*"
                guessCorrectDigitsInNumber[a] = "*";
            }
            for (int b = low; b < (10); b++) {    //initialize guessIncorrectDigitsInNumber[b] = "_"
                guessIncorrectDigitsInNumber[b] = "_";
            }
            while (incorrectGuessRemaining > 0 && !(DigitsCorrect == (high + 1))) {//conditions for guessing a Digit in number
                //either no more guess remaining or Digits selected are correct
                System.out.print("\nGuess a Digit in the number (sequence):"); //prompts user for guess
                //Output number Map
                for (int f = low; f <= (high); f++) {
                    System.out.print(guessCorrectDigitsInNumber[f]); //outputs number map
                }
                System.out.print(": ");
                String guessDigitInNumber;
                guessDigitInNumber = myObj.next(); //reads in value of guess from keyboard
                for (int c = low; c <= high; c++) { //loop of checking if Digit guess equal to game number
                    if ((numbersUsedInGame[randomNumber].charAt(c)) == (guessDigitInNumber.toLowerCase().charAt(0))) {
                        if (guessCorrectDigitsInNumber[c].equals("*")) { //makes sure not previously guessed
                            if (guessDigitInNumber.length() > 1) { // checks to see if entered 1 or more characters in string (guess entire number for later method meth)
                                char guessDigitInNumberReduceToCharacter = guessDigitInNumber.charAt(0); // variable set to first character in string
                                guessCorrectDigitsInNumber[c] = String.valueOf(guessDigitInNumberReduceToCharacter); //converts char to string
                            }
                            else {
                                guessCorrectDigitsInNumber[c] = guessDigitInNumber; //sets Digit to number map
                            }
                            ++DigitsCorrect; //increases DigitsCorrect to plus one more
                        }
                    }
                }
                for (int d = low; d <= high; d++) { // loop to find foundCorrectCount
                    if ((numbersUsedInGame[randomNumber].charAt(d)) == (guessDigitInNumber.toLowerCase().charAt(0))) {
                        foundCorrectCount++;
                    }
                    else{
                        foundIncorrectCount++;
                    }
                }
                if (foundCorrectCount==0) { // if statement for no Digits found
                    if ((foundIncorrectCount > 0) && (temp < 10)) { //wont exceed array index
                        guessIncorrectDigitsInNumber[temp] = guessDigitInNumber;
                        temp++; //move index to one plus for guessIncorrectDigitsInNumber[]
                        foundIncorrectCount = 0; //reset foundIncorrectCount for next Digit guessed
                    }
                }
                foundCorrectCount = 0; //reset foundCorrectCount for next Digit guessed
                for (int e = low; e < 10; e++) { // loop to go through guessIncorrectDigitsInNumber[e] is "_" in if statement
                    if (guessIncorrectDigitsInNumber[e].equals("_")&&(!guessDigitInNumber.equals("_"))) {//if user guess "_" they run out of guesses.
                        temp2++; //finds the incorrectGuessRemaining for this iteration of loop
                    }
                }
                incorrectGuessRemaining=temp2; //after previous loop set incorrectGuessRemaining = temp2
                temp2=0; //reset temp2 for next Digit guessed
                //check to see if all 10 incorrect Digits guesses used by checking if character other than "_" is there
                if ((!guessIncorrectDigitsInNumber[0].equals("_"))
                        && (!guessIncorrectDigitsInNumber[1].equals("_"))
                        && (!guessIncorrectDigitsInNumber[2].equals("_"))
                        && (!guessIncorrectDigitsInNumber[3].equals("_"))
                        && (!guessIncorrectDigitsInNumber[4].equals("_"))
                        && (!guessIncorrectDigitsInNumber[5].equals("_"))
                        && (!guessIncorrectDigitsInNumber[6].equals("_"))
                        && (!guessIncorrectDigitsInNumber[7].equals("_"))
                        && (!guessIncorrectDigitsInNumber[8].equals("_"))
                        && (!guessIncorrectDigitsInNumber[9].equals("_"))){
                    incorrectGuessRemaining=0; //makes sure if guessIncorrectDigitsInNumber[] is full then 0 incorrectGuessRemaining
                }
                //invoke method 2 in if statement
                if (meth(numbersUsedInGame, guessDigitInNumber, randomNumber )) { //compares whether guess is complete game number
                    System.out.printf("\nYou guessed the number (sequence) %s ", (numbersUsedInGame[randomNumber])); //if true lets user know guess correct
                    break; //exits while loop, no need to calculate/display incorrect guess for this game number
                }
                //meth 2: check for repeated Digits
                if (meth2(guessCorrectDigitsInNumber, guessDigitInNumber, checkDigitRepeatedGuessed, numeralCount, numeral, high, low)){ //if boolean meth2 true then print message below
                    System.out.printf("\nOops, you've already used the %s!\n", (guessDigitInNumber)); //incorrectGuessRemaining was not deducted for this so user can keep guessing correct Digit without loses a guess
                }

                System.out.printf("\nYou have %d incorrect guesses left.\n", (incorrectGuessRemaining)); //displays incorrect guesses left

                if (DigitsCorrect == (high + 1)) { //if correct guesses total length of game number
                    System.out.printf("\nYou guessed the number (sequence) %s ", (numbersUsedInGame[randomNumber]));
                }
                if (incorrectGuessRemaining <= 0) { //if incorrect guesses is 0 or less
                    System.out.printf("\nYou ran out of guesses!\nYour number (sequence) was %s ", (numbersUsedInGame[randomNumber]));
                }
            }
            numbersUsedInGameBank[randomNumber] = true; //used the number in number bank, boolean set to true
            System.out.print("\nDo you want to play again? (Y/N) "); //prompts user for input (y/n) for do while loop
            Scanner reader = new Scanner(System.in);
            playAgain = reader.next().charAt(0); //reads in a character
            // meth: to make sure number used is not repeated randomly
            if (numbersUsedInGameBank[0] && numbersUsedInGameBank[1] && numbersUsedInGameBank[2]&& numbersUsedInGameBank[3]
                    && numbersUsedInGameBank[4]&& numbersUsedInGameBank[5]&& numbersUsedInGameBank[6]&& numbersUsedInGameBank[7]
                    && numbersUsedInGameBank[8]&& numbersUsedInGameBank[9]) {
                System.out.println("\nYou've guessed all of my number (sequence)!");
                break; //exits do while loop, game over.
            }
        } while ((playAgain=='y'||playAgain=='Y'));
    }
}



