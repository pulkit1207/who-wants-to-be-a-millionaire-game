import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DBProcessing {

    public static ArrayList<Questions> getQuestions(String nameOfFile) throws IOException {
        ArrayList<Questions> questions = new ArrayList<>();

        FileReader file = new FileReader(nameOfFile); // Looks for the path
        BufferedReader input = new BufferedReader(file); // Actually reading the file

        String row = input.readLine(); // Reads a single line

        while (row != null) {
            String ques[] = row.split(",");

            questions.add(new Questions(ques[0], ques[1], ques[2], ques[3], ques[4], ques[5]));

            row = input.readLine(); // Reads next line
        }

        input.close(); // Closing connection to the file

        return questions; // Returns questions array list
    }

}
