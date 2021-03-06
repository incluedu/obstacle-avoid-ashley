package net.lustenauer.obstacleavoid.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import net.lustenauer.obstacleavoid.ObstacleAvoidGame;
import net.lustenauer.obstacleavoid.config.DifficultyLevel;
import net.lustenauer.obstacleavoid.config.GameConfig;

/**
 * Created by Patric Hollenstein on 14.01.18.
 *
 * @author Patric Hollenstein
 */
public class GameManager {

    public static final GameManager INSTANCE = new GameManager();

    private static final String HIGH_SCORE_KEY = "highscore";
    private static final String DIFFICULTY_KEY = "difficulty";

    private Preferences PREFS;

    private int highscore;
    private DifficultyLevel difficultyLevel = DifficultyLevel.MEDIUM;
    private int lives = GameConfig.LIVES_START;
    private int score;

    private GameManager() {
        PREFS = Gdx.app.getPreferences(ObstacleAvoidGame.class.getSimpleName());
        highscore = PREFS.getInteger(HIGH_SCORE_KEY, 0);
        String difficultyName = PREFS.getString(DIFFICULTY_KEY, DifficultyLevel.MEDIUM.name());
        difficultyLevel = DifficultyLevel.valueOf(difficultyName);
    }

    public void updateHighScore() {
        if (score < highscore) {
            return;
        }
        highscore = score;
        PREFS.putInteger(HIGH_SCORE_KEY, highscore);
        PREFS.flush();
    }

    public String getHighScoreString() {
        return String.valueOf(highscore);
    }

    public void updateDifficultyLevel(DifficultyLevel newDifficultyLevel) {
        if (difficultyLevel == newDifficultyLevel) {
            return;
        }
        difficultyLevel = newDifficultyLevel;
        PREFS.putString(DIFFICULTY_KEY, difficultyLevel.name());
        PREFS.flush();
    }

    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public int getLives() {
        return lives;
    }

    public void decrementLives() {
        lives--;
    }

    public void increaseLives() {
        lives++;
    }

    public boolean isGameOver() {
        return lives <= 0;
    }

    public int getScore() {
        return score;
    }

    public void updateScore(int amount) {
        score += amount;
    }

    public void reset() {
        lives = GameConfig.LIVES_START;
        score = 0;
    }
}
