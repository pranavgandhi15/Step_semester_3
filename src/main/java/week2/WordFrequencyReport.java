package Step_semester_3.src.main.java.week2;

import java.util.*;

public class WordFrequencyReport {

    static void printFrequencyReport(String text) {

        String[] words = text.toLowerCase().split(" ");

        String[] stopWords = {"the", "is", "a", "an"};

        HashMap<String, Integer> frequency = new HashMap<>();

        for (String word : words) {

            boolean isStopWord = false;

            for (String stop : stopWords) {
                if (word.equals(stop)) {
                    isStopWord = true;
                    break;
                }
            }

            if (!isStopWord) {
                if (frequency.containsKey(word))
                    frequency.put(word, frequency.get(word) + 1);
                else
                    frequency.put(word, 1);
            }
        }

        for (String word : frequency.keySet()) {
            System.out.println(word + " : " + frequency.get(word));
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter text: ");
        String text = sc.nextLine();

        printFrequencyReport(text);
    }
}