package numberguessing.console;

import numberguessing.PositiveIntegerGenerator;

public class AppModel {

    private static final String NEW_LINE = System.lineSeparator();
    private String output = "1: Single player game" + NEW_LINE + "2: Multiplayer game" + NEW_LINE +
            "3: Exit" + NEW_LINE + "Enter selection: ";
    private boolean completed;
    private static int answer = 0;
    private boolean isSinglePlayerMode = true;


    public AppModel(PositiveIntegerGenerator randomGenerator) {
        answer = randomGenerator.generateLessThanOrEqualToHundread();
        completed = false;
    }

    public boolean isCompleted() {
        isSinglePlayerMode = false;
        return completed;
    }

    public String flushOutput() {
        return output;
    }

    public void processInput(String input) {
        if (input.equals("3")) {
            completed = true;
            return;
        } else if (input.equals("1")) {
            output = "Single player game Start!" + NEW_LINE + "I'm thinking of a number between 1 and 100." + NEW_LINE + "Enter your guess: ";
            return;
        }

        if (Integer.parseInt(input) < answer) {
            output = "Your guess is too low." + NEW_LINE + "Enter your guess: ";
        } else if (Integer.parseInt(input) > answer) {
            output = "Your guess is too high." + NEW_LINE + "Enter your guess: ";
        }
    }
}
