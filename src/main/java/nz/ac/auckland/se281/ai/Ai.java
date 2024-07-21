package nz.ac.auckland.se281.ai;

import java.util.ArrayList;

public interface Ai {

  public String getName();

  public int getScore();

  public void incrementScore();

  public void setFingers();

  public int getFingers();

  public void setGuess();

  public int getGuess();

  public void setStrategy();

  public void passRound(int round);

  public void passHistory(ArrayList<Integer> history);
}
