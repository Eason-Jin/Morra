package nz.ac.auckland.se281.ai;

import java.util.ArrayList;
import nz.ac.auckland.se281.Player;
import nz.ac.auckland.se281.strategies.AverageStrategy;
import nz.ac.auckland.se281.strategies.RandomStrategy;
import nz.ac.auckland.se281.strategies.Strategy;

public class MediumAi extends Player implements Ai {

  private Strategy strategy;
  private int round;
  private ArrayList<Integer> history;

  public MediumAi(String name) {
    super(name);
  }

  @Override
  public void setFingers() {
    fingers = strategy.chooseFingers();
  }

  @Override
  public void setGuess() {
    guess = strategy.chooseGuess();
  }

  @Override
  public void setStrategy() {
    if (round <= 3) {
      strategy = new RandomStrategy();
    } else {
      strategy = new AverageStrategy(history);
    }
  }

  @Override
  public void passRound(int round) {
    this.round = round;
  }

  @Override
  public void passHistory(ArrayList<Integer> history) {
    this.history = history;
  }
}
