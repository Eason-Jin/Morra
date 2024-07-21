package nz.ac.auckland.se281;

public abstract class Player {
  protected String name;
  protected int score = 0;
  protected int fingers;
  protected int guess;

  public Player(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setFingers(int fingers) {
    this.fingers = fingers;
  }

  public int getFingers() {
    return fingers;
  }

  public void setGuess(int guess) {
    this.guess = guess;
  }

  public int getGuess() {
    return guess;
  }

  public void incrementScore() {
    score++;
  }

  public int getScore() {
    return score;
  }
}
