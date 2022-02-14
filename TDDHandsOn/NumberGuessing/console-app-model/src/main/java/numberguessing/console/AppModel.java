package numberguessing.console;

import numberguessing.PositiveIntegerGenerator;

import java.util.stream.Stream;

public final class AppModel {
    private static final String NEW_LINE = System.lineSeparator();
    private static final String SELECT_MODE_MESSAGE = "1: Single player game" + NEW_LINE + "2: Multiplayer game" + NEW_LINE +
            "3: Exit" + NEW_LINE + "Enter selection: ";

    interface Processor {
        Processor run(String input);
    }

    private final PositiveIntegerGenerator generator;
    private final StringBuffer outputBuffer;
    boolean completed;
    private Processor processor;

    public AppModel(PositiveIntegerGenerator generator) {
        this.generator = generator;
        completed = false;
        outputBuffer = new StringBuffer(SELECT_MODE_MESSAGE); // 초기화
        processor = this::processModeSelection;
    }

    public boolean isCompleted() {
        return completed;
    }

    public String flushOutput() {
        String flushOutput = outputBuffer.toString();
        outputBuffer.setLength(0); // 버퍼 비워주기
        return flushOutput;
    }

    public void processInput(String input) { // 프로세서를 실행하고 결과값으로 프로세스를 다시 교체하는 역할로 재구현
        processor = processor.run(input);
    }

    private Processor processModeSelection(String input) {
        if(input.equals("1")) {
            outputBuffer.append("Single player game" + NEW_LINE + "I'm thinking of a number between 1 and 100."
                    + NEW_LINE + "Enter your guess: ");
            int answer = generator.generateLessThanOrEqualToHundred();
            return getSinglePlayerGameProcessor(answer, 1);
        } else if(input.equals("2")) {
            outputBuffer.append("Multiplayer game" + NEW_LINE + "Enter player names separated with commas: ");
            return getMultiPlayerGameProcessor();
        } else {
            completed = true;
            return null;
        }
    }

    private Processor getMultiPlayerGameProcessor() {
        return input -> {
            Object[] players = Stream.of(input.split(",")).map(String::trim).toArray(); // 플레이어 이름 옆에 공백이 하나 더 들어가서 처리(CsvSource 에 들어간 배열값을 자세히보라), toArray의 반환타입이 Object이기 때문에 반환타입 수정.
            outputBuffer.append("I'm thinking of a number between 1 and 100.");
            outputBuffer.append("Enter " + players[0] + "'s guess: ");
            return input2 -> {
                outputBuffer.append("Enter " + players[1] + "'s guess: ");
                return input3 -> {
                    outputBuffer.append("Enter " + players[2] + "'s guess: ");
                    return null;
                };
            };
        };
    }

    private Processor getSinglePlayerGameProcessor(int answer, int tries) {
        return input -> {
            int guess = Integer.parseInt(input);
            if (guess < answer) {
                outputBuffer.append("Your guess is too low." + NEW_LINE + "Enter your guess: ");
                return getSinglePlayerGameProcessor(answer, tries + 1);
            } else if (guess > answer) {
                outputBuffer.append("Your guess is too high." + NEW_LINE + "Enter your guess: ");
                return getSinglePlayerGameProcessor(answer, tries + 1);
            } else {
                outputBuffer.append("Correct! " + tries + (tries == 1 ? " guess." : " guesses.") + NEW_LINE + SELECT_MODE_MESSAGE);
                return this::processModeSelection;
            }
        };
    }

}