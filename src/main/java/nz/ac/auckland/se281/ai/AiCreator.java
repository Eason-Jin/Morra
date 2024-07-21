package nz.ac.auckland.se281.ai;

import nz.ac.auckland.se281.Main.Difficulty;

public class AiCreator {

  public static Ai createAi(Difficulty difficulty) {

    switch (difficulty) {
      case EASY:
        // Create an easy AI
        return new EasyAi("Jarvis");
      case MEDIUM:
        // Create a medium AI
        return new MediumAi("Jarvis");
      case HARD:
        // Create a hard AI
        return new HardAi("Jarvis");
      case MASTER:
        // Create a master AI
        return new MasterAi("Jarvis");
      default:
        return null;
    }
  }
}
