package baseball;

import baseball.generator.RandomNumberGenerator;
import baseball.receiver.ConsoleInputReceiver;
import baseball.receiver.InputReceiver;
import baseball.number.GameNumber;
import baseball.printer.ConsolePrinter;
import baseball.printer.Printer;
import baseball.result.Result;

import java.util.Scanner;

public class GameManager {
    private final RandomNumberGenerator generator;
    private final Printer printer;
    private final InputReceiver receiver;
    private GameNumber seedNumber;
    private GameNumber userNumber;

    public GameManager(Scanner scanner) {
        generator = new RandomNumberGenerator();
        printer = new ConsolePrinter();
        receiver = new ConsoleInputReceiver(scanner);
        seedNumber = new GameNumber();
        userNumber = new GameNumber();
    }

    public void run() {
        Result result = new Result();
        boolean continueGame = true;

        printer.greet();
        printer.askStartGame();
        continueGame = receiver.checkContinueGame();
        while(continueGame) {
            generator.generate(seedNumber);
            startGame(result);
            printer.askStartGame();
            continueGame = receiver.checkContinueGame();
        }
    }

    private void startGame(Result result) {

        while(result.isThreeStrike()) {
            receiveUserInput();
            seedNumber.compare(userNumber, result);
            printer.printResult(result);
        }
    }

    private void receiveUserInput() {
        printer.requestUserNumber();
        receiver.receiveUserNumber(userNumber);
    }
}
