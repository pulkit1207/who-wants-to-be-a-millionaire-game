import java.util.ArrayList;
import java.util.Random;

public class Utils {

    // Method to generate random question
    public static Questions randomQuestion(ArrayList allQuestions) { // Passing arraylist with all the questions
        Random rand = new Random(); // Creating rand object of Random class
        int randomIndex = rand.nextInt(allQuestions.size()); // Generating random number within the size of the array list

        Questions randQuestion = (Questions) allQuestions.get(randomIndex); // Getting question with random index generated above from the array list

        allQuestions.remove(randomIndex); // Removing the question that has already come from the questions array list

        return randQuestion; // Returning the random question
    }


    // Method to calculate the amount won
    public static int addAmount(int amountWon, int round, int questionNumber, char difficulty) { // Parameters: amountWon, round, questionNumber, difficulty
        if (difficulty == 'e' || difficulty == 'E') { // If difficulty level is easy
            switch (round) {
                case 1: // For round 1
                    if (questionNumber == 1) { // For question number 1
                        amountWon = 100;
                        break;
                    } else if (questionNumber == 2) { // For question number 2
                        amountWon = 500;
                        break;
                    } else if (questionNumber == 3) { // For question number 3
                        amountWon = 1000;
                        break;
                    }
                case 2: // For round 2
                    if (questionNumber == 4) { // For question number 4
                        amountWon = 8000;
                        break;
                    } else if (questionNumber == 5) { // For question number 5
                        amountWon = 16000;
                        break;
                    } else if (questionNumber == 6) { // For question number 6
                        amountWon = 32000;
                        break;
                    }
                case 3: // For round 3
                    if (questionNumber == 7) { // For question number 7
                        amountWon = 125000;
                        break;
                    } else if (questionNumber == 8) { // For question number 8
                        amountWon = 500000;
                        break;
                    } else if (questionNumber == 9) { // For question number 9
                        amountWon = 1000000;
                        break;
                    }
            }
        } else if (difficulty == 'h' || difficulty == 'H') { // If difficulty level is easy
            switch (round) {
                case 1: // For round 1
                    if (questionNumber == 1) { // For question number 1
                        amountWon = 100;
                        break;
                    } else if (questionNumber == 2) { // For question number 2
                        amountWon = 200;
                        break;
                    } else if (questionNumber == 3) { // For question number 3
                        amountWon = 300;
                        break;
                    } else if (questionNumber == 4) { // For question number 4
                        amountWon = 500;
                        break;
                    } else if (questionNumber == 5) { // For question number 5
                        amountWon = 1000;
                        break;
                    }
                case 2: // For round 2
                    if (questionNumber == 6) { // For question number 6
                        amountWon = 2000;
                        break;
                    } else if (questionNumber == 7) { // For question number 7
                        amountWon = 4000;
                        break;
                    } else if (questionNumber == 8) { // For question number 8
                        amountWon = 8000;
                        break;
                    } else if (questionNumber == 9) { // For question number 9
                        amountWon = 16000;
                        break;
                    } else if (questionNumber == 10) { // For question number 10
                        amountWon = 32000;
                        break;
                    }
                case 3: // For round 3
                    if (questionNumber == 11) { // For question number 11
                        amountWon = 64000;
                        break;
                    } else if (questionNumber == 12) { // For question number 12
                        amountWon = 125000;
                        break;
                    } else if (questionNumber == 13) { // For question number 13
                        amountWon = 250000;
                        break;
                    } else if (questionNumber == 14) { // For question number 14
                        amountWon = 500000;
                        break;
                    } else if (questionNumber == 15) { // For question number 15
                        amountWon = 1000000;
                        break;
                    }
            }
        }
        return amountWon; // Returning amount won
    }


}
