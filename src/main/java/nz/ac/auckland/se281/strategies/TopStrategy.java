package nz.ac.auckland.se281.strategies;

import java.util.ArrayList;
import java.util.Collections;
import nz.ac.auckland.se281.Utils;

public class TopStrategy implements Strategy {

  private int fingers; // Take in the AI's choice of fingers
  private ArrayList<Integer> history;

  public TopStrategy(ArrayList<Integer> history) {
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
    return fingers + findMostPlayed(history);
  }

  private int findMostPlayed(ArrayList<Integer> history) {
    ArrayList<Integer> frequency = new ArrayList<Integer>();
    int finger;
    int count;

    // Initialise frequency with 5 zeros, indicating the count for each finger
    for (int i = 0; i < 5; i++) {
      frequency.add(0);
    }

    // Count the frequencies of each finger and put them into the frequency list
    for (int i = 0; i < history.size() - 1; i++) {
      finger = history.get(i);
      count = frequency.get(finger - 1) + 1;
      frequency.set(finger - 1, count);
    }

    // Find the most occurance
    return frequency.indexOf(Collections.max(frequency)) + 1;
  }
}
