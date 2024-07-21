package nz.ac.auckland.se281.ai;

import java.util.ArrayList;
import nz.ac.auckland.se281.Player;
import nz.ac.auckland.se281.strategies.RandomStrategy;
import nz.ac.auckland.se281.strategies.Strategy;

public class EasyAi extends Player implements Ai {

  private Strategy strategy;

  public EasyAi(String name) {
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
    this.strategy = new RandomStrategy();
  }

  @Override
  public void passRound(int round) {}

  @Override
  public void passHistory(ArrayList<Integer> history) {}
}
