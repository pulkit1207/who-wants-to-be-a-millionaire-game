import java.util.Random;
import java.util.Scanner;

public class Lifelines {

    // Method for Fifty-Fifty lifeline
    public static String fiftyFifty(Questions randomQuestion) { // Passing random question as a parameter
        String randomOption;
        do {
            Random rand = new Random();
            int index = rand.nextInt(4) + 1; // Random number between 1 and 4 (including 1 and 4)
            if (index == 1) {
                randomOption = randomQuestion.getOptionA(); // If random index is 1, get option A
            } else if (index == 2) {
                randomOption = randomQuestion.getOptionB(); // If random index is 2, get option B
            } else if (index == 3) {
                randomOption = randomQuestion.getOptionC(); // If random index is 3, get option C
            } else {
                randomOption = randomQuestion.getOptionD(); // If random index is 4, get option D
            }
        } while (randomOption.equalsIgnoreCase(randomQuestion.getCorrectAnswer())); // To make sure random option is not a correct answer

        return ("Remaining options:\n" + randomOption + " & " + randomQuestion.getCorrectAnswer()); // Returning 2 options
    }


    // Method for Call a Friend lifeline
    public static String callAFriend(Questions randomQuestion) { // Passing random question as a parameter
        Scanner read = new Scanner(System.in);

        System.out.println("What is your friend name?");
        String name = read.nextLine();

        System.out.println("What is your friend number?");
        String cellnumber = read.nextLine();

        System.out.println("Calling " + name + " on " + cellnumber);

        try {
            Thread.sleep(5000); // Next statement will execute after 3 seconds
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        String randomOption;
        Random rand = new Random(); // Random object
        do {
            int index = rand.nextInt(4) + 1; // Random number between 1 and 4 (including 1 and 4)
            if (index == 1) {
                randomOption = randomQuestion.getOptionA(); // If random index is 1, get option A
            } else if (index == 2) {
                randomOption = randomQuestion.getOptionB(); // If random index is 2, get option B
            } else if (index == 3) {
                randomOption = randomQuestion.getOptionC(); // If random index is 3, get option C
            } else {
                randomOption = randomQuestion.getOptionD(); // If random index is 4, get option D
            }
        } while (randomOption.equalsIgnoreCase(randomQuestion.getCorrectAnswer())); // To make sure random option is not a correct answer

        int percent = rand.nextInt(101) + 25; // Generating random number between 25 and 100 (including 25 and 100)
        if (percent > 40) { // To be more biased towards the correct answer
            return (name + "'s answer: " + randomQuestion.getCorrectAnswer());
        } else { // Else returning random option generated
            return (name + "'s answer: " + randomOption);
        }
    }


    // Method for Audience Poll lifeline
    public static String audiencePoll(Questions randomQuestion) { // Passing random question as a parameter
        Random rand = new Random(); // Random object
        int one = rand.nextInt(50); // Generating a random number from 0 to 50
        int two = rand.nextInt(101 - one); // Generating a random number from 0 to (100 - one)
        int three = rand.nextInt(101 - one - two); // Generating a random number from 0 to (100 - one - two)
        int four = 100 - one - two - three; // Remaining number out of 100
        return (one + "%: " + randomQuestion.getOptionA() + "\n" +
                two + "%: " + randomQuestion.getOptionB() + "\n" +
                +three + "%: " + randomQuestion.getOptionC() + "\n" +
                +four + "%: " + randomQuestion.getOptionD() + "\n");
    }
}
