package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Main.Difficulty;
import nz.ac.auckland.se281.ai.Ai;
import nz.ac.auckland.se281.ai.AiCreator;

public class Morra {

  private int round;
  private int pointsToWin;
  private Difficulty difficulty;

  private Human human;
  private Ai ai;
  private Player winner;

  private ArrayList<Integer> fingersHistory;

  public void newGame(Difficulty difficulty, int pointsToWin, String[] options) {
    MessageCli.WELCOME_PLAYER.printMessage(options[0]);
    // Create human player instance
    human = new Human(options[0]);
    // Create AI player instance
    ai = AiCreator.createAi(difficulty);
    // Save the end condition and difficulty
    this.pointsToWin = pointsToWin;
    this.difficulty = difficulty;
    // Reset round number
    round = 0;
    // Create a history list
    fingersHistory = new ArrayList<Integer>();
  }

  public void play() {

    int fingers;
    int guess;

    // Check if game has started
    if (human == null) {
      MessageCli.GAME_NOT_STARTED.printMessage();
    } else {

      // Print round
      round++;
      MessageCli.START_ROUND.printMessage(Integer.toString(round));

      // Check user inputs
      while (true) {
        MessageCli.ASK_INPUT.printMessage();
        String input = Utils.scanner.nextLine().trim();
        // Check if input has two elements
        if (hasTwoInputs(input)) {
          String fingersIn = input.substring(0, input.indexOf(' '));
          String guessIn = input.substring(input.indexOf(' ') + 1, input.length());
          // Check if two elements are both integers
          if (Utils.isInteger(fingersIn) && Utils.isInteger(guessIn)) {
            fingers = Integer.parseInt(fingersIn);
            guess = Integer.parseInt(guessIn);
            // Check if both values are in range
            if (fingers >= 1 && fingers <= 5 && guess >= 1 && guess <= 10) {
              // When input is valid
              human.setFingers(fingers);
              human.setGuess(guess);
              break;
            }
          }
        }
        MessageCli.INVALID_INPUT.printMessage();
      }

      fingersHistory.add(
          human.getFingers()); // Add the finger played to the list (ignore last entry)

      // Generate AI response
      ai.passRound(round);
      ai.passHistory(fingersHistory);
      ai.setStrategy();
      ai.setFingers();
      ai.setGuess();

      // Print respective fingers and guesses
      MessageCli.PRINT_INFO_HAND.printMessage(
          human.getName(),
          Integer.toString(human.getFingers()),
          Integer.toString(human.getGuess()));
      MessageCli.PRINT_INFO_HAND.printMessage(
          ai.getName(), Integer.toString(ai.getFingers()), Integer.toString(ai.getGuess()));

      getResult(human, ai);

      // Check if either player has won
      if (human.getScore() == pointsToWin || ai.getScore() == pointsToWin) {
        // Print end game message
        if (human.getScore() == pointsToWin) {
          winner = human;
        } else {
          winner = (Player) ai;
        }
        MessageCli.END_GAME.printMessage(winner.getName(), Integer.toString(round));
        // Reset for next game
        human = null;
        ai = null;
        round = 0;
      }
    }
  }

  public void showStats() {
    // Check if game has started
    if (human == null) {
      MessageCli.GAME_NOT_STARTED.printMessage();
    } else {
      MessageCli.PRINT_PLAYER_WINS.printMessage(
          human.getName(),
          Integer.toString(human.getScore()),
          Integer.toString(pointsToWin - human.getScore()));
      MessageCli.PRINT_PLAYER_WINS.printMessage(
          ai.getName(),
          Integer.toString(ai.getScore()),
          Integer.toString(pointsToWin - ai.getScore()));
    }
  }

  public ArrayList<Integer> getHistory() {
    return fingersHistory;
  }

  public int getRound() {
    return round;
  }

  public Difficulty getDifficulty() {
    return difficulty;
  }

  private boolean hasTwoInputs(String s) {
    // Check if the input has two values separated by space
    int count = 0;

    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == ' ') {
        count++;
      }
    }

    // Check if only one space exists (two inputs)
    if (count == 1) {
      return true;
    } else {
      return false;
    }
  }

  private void getResult(Player p, Ai ai) {

    int sum = p.getFingers() + ai.getFingers();

    if (p.getGuess() == sum && ai.getGuess() == sum) {
      // Both human and AI guessed the sum
      MessageCli.PRINT_OUTCOME_ROUND.printMessage("DRAW");
    } else if (p.getGuess() == sum) {
      // Human guessed the correct sum
      MessageCli.PRINT_OUTCOME_ROUND.printMessage("HUMAN_WINS");
      p.incrementScore();
    } else if (ai.getGuess() == sum) {
      // AI guessed the correct sum
      MessageCli.PRINT_OUTCOME_ROUND.printMessage("AI_WINS");
      ai.incrementScore();
    } else {
      // None guessed the correct sum
      MessageCli.PRINT_OUTCOME_ROUND.printMessage("DRAW");
    }
  }
}
