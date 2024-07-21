package nz.ac.auckland.se281.strategies;

import java.util.ArrayList;
import nz.ac.auckland.se281.Utils;

public class AverageStrategy implements Strategy {

  private int fingers; // Take in the AI's choice of fingers
  private ArrayList<Integer> history;

  public AverageStrategy(ArrayList<Integer> history) {
    this.history = history;
  }

  @Override
  public int chooseFingers() {
    // Choose a finger randomly between 1 and 5 (inclusive)
    fingers = Utils.getRandomNumber(1, 5);
    return fingers;
  }

  @Override
  public int chooseGuess() {
    int sum = 0;
    int average;
    // Ignore the last entry
    for (int i = 0; i < history.size() - 1; i++) {
      sum += history.get(i);
    }

    // Calculate the average of fingers played by human
    average = Math.round((float) sum / (history.size() - 1));
    return fingers + average;
  }
}
