package nz.ac.auckland.se281.strategies;

import nz.ac.auckland.se281.Utils;

public class RandomStrategy implements Strategy {

  private int fingers; // The AI's choice of fingers

  @Override
  public int chooseFingers() {
    // Choose a finger randomly between 1 and 5 (inclusive)
    fingers = Utils.getRandomNumber(1, 5);
    return fingers;
  }

  @Override
  public int chooseGuess() {
    // Choose a guess randomly between fingers + 1 and fingers + 5
    return Utils.getRandomNumber(fingers + 1, fingers + 5);
  }
}
