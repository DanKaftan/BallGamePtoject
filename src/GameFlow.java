import biuoop.KeyboardSensor;

import java.util.List;

/**
 * class responsible for the fluent transitions of levels.
 */
public class GameFlow {
    // the animation runner
    private AnimationRunner animationRunner;
    // keyboard sensor
    private KeyboardSensor keyboardSensor;
    // score counter
    private Counter score = new Counter();
    private ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(score);
    private ScoreIndicator scoreIndicator = new ScoreIndicator(score);

    /**
     * Constructor of the class.
     * @param ar the animation runner.
     * @param ks keyboard sensor.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
    }

    /**
     * runs the levels of the game by order.
     * @param levels list of the levels of the game.
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner, score,
                    scoreTrackingListener, scoreIndicator);

            level.initialize();

            while (!level.shouldStop()) {
                level.run();
            }

            if (level.getRemainingBalls().getValue() == 0) {
                animationRunner.run(new KeyPressStoppableAnimation(keyboardSensor, KeyboardSensor.SPACE_KEY,
                        new EndScreen(false, score)));
                animationRunner.getGui().close();
                break;
            }
            if (level.getRemainingBlocks().getValue() == levelInfo.numberOfBlocksToRemove()
                    && levelInfo.levelName().equals("Final Four")) {
                animationRunner.run(new KeyPressStoppableAnimation(keyboardSensor, KeyboardSensor.SPACE_KEY,
                        new EndScreen(true, score)));
                animationRunner.getGui().close();
                break;

            }

        }
    }
}
