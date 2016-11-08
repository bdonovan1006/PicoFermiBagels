
/**
 * Brian Donovan
 * 915106202
 * 11/2/16
 * 1068-001 Lab 9
 * This program will allow the user to play a basic version of Pico, Fermi,
 * Bagels and allows the user to play multiple times.
 */
import java.util.*;

class Bagels {

    //instance variables, scanner and random objects
    Scanner keyboard = new Scanner(System.in);
    Random rand = new Random();
    private int num;
    private int num2;
    private int num3;
    private String winNum = Integer.toString(num);
    private String guess = new String();
    private CharSequence check;
    private int countF = 0;
    private int countP = 0;
    private int countB = 3;
    private int countG = 0;
    private boolean cont1 = true;
    private String replay = new String();
    private boolean rep;

    //introduces player to game concept
    private void welcome() {
        System.out.println("Hi, I have a 3 digit number on my mind. Can you guess it?"
                + "\t(Q to quit)");
    }

    //generates the random number
    private void genNum() {
        num = rand.nextInt(9) + 1;
        for (int i = 1; i < 10; i++) {
            while (num2 == num || num2 == 0) {
                num2 = rand.nextInt(9) + 1;
            }
        }
        for (int i = 1; i < 10; i++) {
            while (num3 == num || num3 == num2 || num3 == 0) {
                num3 = rand.nextInt(9) + 1;
            }
        }
        winNum = Integer.toString(num)
                + Integer.toString(num2) + Integer.toString(num3);
    }

    //asks for input and prevents invalid entries
    private void acceptGuess() {
        System.out.print("\nGuess = ");
        guess = keyboard.next().toUpperCase();
        if (guess.equals("Q")) {
            System.out.println("The player quit after " + countG + " guess(es)");
            cont1 = false;
        }
        while (guess.length() != 3 && !guess.equals("Q")) {
            System.out.println("Invalid entry: ");
            guess = keyboard.next();
        }
        if (!guess.equals("Q")) {
            countG++;
        }
    }

    //verifies user's guess as win or determines how many correct numbers in guess
    private void checkGuess() {

        if (winNum.equals(guess)) {
            System.out.print("Winner! ");
            countB = 0;
            System.out.println("You won after " + countG + " guess(es)");
            cont1 = false;
        } else if (guess.equals("Q")) {
            countB++;
        } else {
            for (int i = 0; i < 3; i++) {
                check = guess.subSequence(i, i + 1);
                if (winNum.contains(check) && winNum.charAt(i) == guess.charAt(i)) {
                    countF++;
                    countB = 0;
                } else if (winNum.contains(check)) {
                    countP++;
                    countB = 0;
                }
            }
        }
    }

    //informs user if they won, gives hint if they did not win
    private void tellUser() {
        if (countB == 0) {
            for (int i = 0; i < countF; i++) {
                System.out.print(" Fermi ");
            }
            for (int i = 0; i < countP; i++) {
                System.out.print(" Pico ");
            }
            countB++;
        } else if(countB == 3) {
            System.out.println(" Bagels");
            countB++;
        }
    }

    //resets the counts
    private void resetCount() {
        countF = 0;
        countP = 0;
        countB = 3;
    }

    //asks user if they would like to play game again
    private void askUser() {
        System.out.print("\nWould you like to play again? Y or N: ");
        replay = keyboard.next().toUpperCase();
        while (!(replay.equals("Y") || replay.equals("N"))) {
            System.out.print("Please enter Y or N: ");
            replay = keyboard.next().toUpperCase();
        }
        if (replay.toUpperCase().equals("Y")) {
            rep = true;
            cont1 = true;
        } else if (replay.toUpperCase().equals("N")) {
            rep = false;

        }
    }
    
    //initiates methods to play Pico, Fermi, Bagels
    public void playGame() {
        do {
            genNum();
            welcome();
            System.out.println(winNum);
            do {
                acceptGuess();
                checkGuess();
                tellUser();
                resetCount();
            } while (cont1 != false);
            resetCount();
            countG = 0;
            askUser();
            System.out.println();
        } while (rep);
    }  
}

public class PicoFermiBagel {

    public static void main(String[] args) {
        //instantiates Bagels object and calls method to play game
        Bagels b = new Bagels();
        b.playGame();
    }
}
