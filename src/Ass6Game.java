
import java.util.ArrayList;
import java.util.List;

/**
 * running the game.
 */
public class Ass6Game {

    /**
     * main of the Ass3Game Class.
     * @param args arguments string.
     */
    public static void main(String[] args) {
        AnimationRunner animationRunner = new AnimationRunner();
        List<LevelInformation> levels = new ArrayList<LevelInformation>();
        if (args.length == 0) {
            levels.add(new DirectHitLevel());
            levels.add(new WideEasyLevel());
            levels.add(new Green3Level());
            levels.add(new FinalFourLevel());
        } else {
            for (int i = 0; i < args.length; i++) {
                if (args[i] == "1") {
                    levels.add(new DirectHitLevel());
                }
                if (args[i] == "2") {
                    levels.add(new WideEasyLevel());
                }
                if (args[i] == "3") {
                    levels.add(new Green3Level());
                }
                if (args[i] == "4") {
                    levels.add(new FinalFourLevel());
                }
            }
        }
        // create new game
        GameFlow gameFlow = new GameFlow(animationRunner, animationRunner.getGui().getKeyboardSensor());
        gameFlow.runLevels(levels);
    }

}
