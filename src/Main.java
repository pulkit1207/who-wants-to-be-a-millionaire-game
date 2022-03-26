import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    // Constant with the file name
    private static final String fileName = "questionpool.txt";


    public static void main(String[] args) {
        menu(); // Calling the menu option
    }


    // Method to display the rules and to start the game
    public static void menu() {
        Scanner read = new Scanner(System.in); // Scanner object
        int input1 = 0; // Initializing the input
        boolean again = true; // Boolean variable to loop for exception
        while (again) {
            try {
                System.out.println("---WHO WANTS TO BE A MILLIONAIRE---");
                System.out.println("Enter '1' to start the game");
                System.out.println("Enter '2' to view the rules");
                System.out.println("Enter '0' to exit");
                input1 = read.nextInt();
                again = false; // To exit the while loop
            } catch (InputMismatchException e) { // If user doesn't enter an integer
                System.out.println("Invalid input\n");
                read.next();
            }
        }
        switch (input1) {
            case 1: // If input is 1
                start(); // calling the start method
                break;
            case 2: // If input is 2
                gameRules(); // calling the gameRules method
                break;
            case 0: // If input is 3
                break;
            default: // If input is not 1,2 or 3
                System.out.println("Wrong input");
        }
    }


    // Method to display game rules
    public static void gameRules() {
        Scanner read = new Scanner(System.in); // Scanner object
        int input2 = 0; // Initializing input with an integer 0
        boolean again = true; // Boolean variable to loop for exception
        while (again) {
            try {
                System.out.println("The rules of the game are:");
                System.out.println("Once you start the game, there will option to select the difficulty level (Easy or Hard)");
                System.out.println("If you select 'easy' there will be 3 rounds. Each round has 3 questions each. Each question has a fixed amount which you will receive if the answer is right.");
                System.out.println("If you select 'hard' there will be 5 rounds. Each round has 3 questions each. Each question has a fixed amount which you will receive if the answer is right.");
                System.out.println("You can choose to walk away with the money after passing the each round.");
                System.out.println("You will also have 3 lifelines. (50-50, Phone a Friend and Audience Poll)\n");
                System.out.println("Enter '1' to move back to main menu.\nEnter '0' to exit");
                input2 = read.nextInt();
                again = false; // To exit the while loop
            } catch (InputMismatchException e) {
                System.out.println("Invalid input\n");
                read.next();
            }
        }
        switch (input2) {
            case 1: // if input is 1
                menu(); // Calling back the menu method
            case 0: // if input is 0
                break; // Exit the loop
            default: // If input is not 1 or 0
                System.out.println("Wrong input");
        }
    }


    // Method to start the game
    public static void start() {
        Scanner read = new Scanner(System.in); // Scanner object
        int totalAmountWon = 0; // Initializing amount won to 0

        ArrayList<Questions> questionsArray = new ArrayList<Questions>(); // Creating an empty array list with Questions class data type

        try { // Handling IOException
            questionsArray = DBProcessing.getQuestions(fileName); // Calling getQuestions method from DBProcessing class to store all the questions from the file
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Enter your name");
        String name = read.nextLine();

        System.out.println("Hello " + name + "! Let's play 'Who wants to be a Millionaire'\n");
        System.out.println("Choose the difficulty level\nEnter 'e' for easy\nEnter 'h' for hard");

        char input2 = read.next().charAt(0);

        switch (input2) {
            // For easy difficulty
            case 'e':
            case 'E':
                totalAmountWon = game(questionsArray, 'e'); // stores return value of amount won from game method
                break;
            // For hard difficulty
            case 'h':
            case 'H':
                totalAmountWon = game(questionsArray, 'h'); // stores return value of amount won from game method
                break;
        }
        System.out.println("You won $" + totalAmountWon); // Printing the final amount to the console
    }


    // Method for the functionality of the game
    public static int game(ArrayList questionsArray, char difficulty) {
        Scanner read = new Scanner(System.in); // Scanner object

        int questionsLoop = 0; // Initializing the questionsLoop to 0

        if (difficulty == 'e') { // If difficulty is easy
            questionsLoop = 3; // Loop will iterate 3 times
        } else { // If difficulty is hard
            questionsLoop = 5; // Loop will iterate 5 times
        }

        boolean wrongAnswer = false; // Flag variable to loop until the user enters wrong answer
        int userAmountWon = 0; // Initializing amount won by the user to 0
        int questionCount = 0; // Initializing questions count to 0
        char confirmation; // To confirm the answer
        int answer = 0; // Initializing answer to read the user answer

        System.out.println("\nThere will be 3 rounds. Each round will have " + questionsLoop + " questions\n");

        // Array list with string data type
        ArrayList<String> remainingLifelines = new ArrayList<String>();
        remainingLifelines.add("50-50");
        remainingLifelines.add("PhoneAFriend");
        remainingLifelines.add("AudiencePoll");

        for (int i = 0; i < 3; i++) { // To iterate for each round

            // Asking to walk away with money after 1st round
            if (i != 0 & !wrongAnswer) { // if the round is not 1 i.e i!=0 and wrong flag is false
                System.out.println("Do you want to walk away?, If yes, press 'y' or 'Y' else press any key");
                char walkAwayDecision = read.next().charAt(0);
                if (walkAwayDecision == 'y' || walkAwayDecision == 'Y') {
                    return userAmountWon; // Returning the amount won
                }
            }

            if (!wrongAnswer) { // If answer is not wrong

                System.out.println("Round: " + (i + 1));

                for (int j = 0; j < questionsLoop; j++) { // Iterating for each question (If easy, 3 questions else 5 questions)

                    Questions randomQuestion = Utils.randomQuestion(questionsArray); // Calling random question method from Utils class
                    // if (!answeredQuestions.contains(randomQuestion)) {

                    do {
                        System.out.println("Question " + (j + 1) + ": " + randomQuestion.getTitle());
                        System.out.println("1: " + randomQuestion.getOptionA());
                        System.out.println("2: " + randomQuestion.getOptionB());
                        System.out.println("3: " + randomQuestion.getOptionC());
                        System.out.println("4: " + randomQuestion.getOptionD());

                        // For lifeline options: Lifelines will only show if difficulty is easy or difficulty is hard and round > 1
                        if ((difficulty == 'e' || difficulty == 'E') || ((difficulty == 'h' || difficulty == 'H') & i + 1 >= 2)) {

                            System.out.println("Enter 'y' or 'Y' to use a lifeline else enter any key");
                            char useLifeline = read.next().charAt(0);

                            if (useLifeline == 'y' || useLifeline == 'Y') { // If user opts for lifeline
                                if (remainingLifelines.contains("50-50")) { // If remaining lifelines array list has "50-50" remaining
                                    System.out.println("5 for 50-50");
                                }
                                if (remainingLifelines.contains("PhoneAFriend")) { // If remaining lifelines array list has "PhoneAFriend" remaining
                                    System.out.println("6 for Phone a friend");
                                }
                                if (remainingLifelines.contains("AudiencePoll")) { // If remaining lifelines array list has "AudiencePoll" remaining
                                    System.out.println("7 for Audience Poll");
                                }
                                if (remainingLifelines.size() == 0) { // If remaining lifelines array list is empty
                                    System.out.println("0 lifelines remaining");
                                }
                                if (remainingLifelines.size() != 0) { // If remaining lifelines array is not empty

                                    int lifeLineChosen = read.nextInt(); // Input to choose a lifeline type
                                    String lifeLineResponse = "";  // Initializing a variable to store the return value from lifeline methods

                                    if (lifeLineChosen == 5) { // If user enters 5
                                        lifeLineResponse = Lifelines.fiftyFifty(randomQuestion); // Calling "fiftyFifty" method from lifelines class
                                        remainingLifelines.remove("50-50"); // Removing 50-50 from remaining lifelines array list after a lifeline is used

                                    } else if (lifeLineChosen == 6) { // If user enters 6
                                        lifeLineResponse = Lifelines.callAFriend(randomQuestion); // Calling "callAFriend" method from lifelines class
                                        remainingLifelines.remove("PhoneAFriend"); // Removing PhoneAFriend from remaining lifelines array list after a lifeline is used

                                    } else if (lifeLineChosen == 7) {
                                        lifeLineResponse = Lifelines.audiencePoll(randomQuestion); // Calling "audiencePoll" method from lifelines class
                                        remainingLifelines.remove("AudiencePoll"); // Removing AudiencePoll from remaining lifelines array list after a lifeline is used
                                    }
                                    System.out.println(lifeLineResponse); // Printing the return value of lifeline to the user
                                }
                            }
                        }

                        System.out.println("Enter your correct option number:");
                        answer = read.nextInt();

                        System.out.println("Is this your final answer? Type 'n' for No else enter any key");
                        confirmation = read.next().charAt(0);

                    } while (confirmation == 'n'); // Repeat the question till the user confirms the answer

                    // If the option entered is 1 and correct answer is equal to option A
                    if (answer == 1 && (randomQuestion.getCorrectAnswer().equalsIgnoreCase(randomQuestion.getOptionA()))) {
                        System.out.println("Correct answer\n");
                        questionCount++; // Incrementing the question count after every question answer
                        userAmountWon = Utils.addAmount(userAmountWon, i + 1, questionCount, difficulty); // Stores return value from addAmount method from Utils class
                    }
                    // If the option entered is 2 and correct answer is equal to option B
                    else if (answer == 2 && (randomQuestion.getCorrectAnswer().equalsIgnoreCase(randomQuestion.getOptionB()))) {
                        System.out.println("Correct answer\n");
                        questionCount++; // Incrementing the question count after every question answer
                        userAmountWon = Utils.addAmount(userAmountWon, i + 1, questionCount, difficulty); // Stores return value from addAmount method from Utils class
                    }
                    // If the option entered is 3 and correct answer is equal to option C
                    else if (answer == 3 && (randomQuestion.getCorrectAnswer().equalsIgnoreCase(randomQuestion.getOptionC()))) {
                        System.out.println("Correct answer\n");
                        questionCount++; // Incrementing the question count after every question answer
                        userAmountWon = Utils.addAmount(userAmountWon, i + 1, questionCount, difficulty); // Stores return value from addAmount method from Utils class
                    }
                    // If the option entered is 4 and correct answer is equal to option D
                    else if (answer == 4 && (randomQuestion.getCorrectAnswer().equalsIgnoreCase(randomQuestion.getOptionD()))) {
                        System.out.println("Correct answer\n");
                        questionCount++; // Incrementing the question count after every question answer
                        userAmountWon = Utils.addAmount(userAmountWon, i + 1, questionCount, difficulty); // Stores return value from addAmount method from Utils class
                    } else {
                        wrongAnswer = true; // To break the loop
                        userAmountWon = 0; // If answer is wrong, amount won is 0
                        System.out.println("Wrong answer!");
                        break;
                    }
                }
            }
        }
        return userAmountWon; // Returning amount won
    }


}


