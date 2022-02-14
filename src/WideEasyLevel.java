import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class of the second level in the game, named : "Wide Easy".
 */
public class WideEasyLevel implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list =  new ArrayList<Velocity>();
        for (int i = 0; i < numberOfBalls(); i++) {
            list.add(Velocity.fromAngleAndSpeed(10 + (i * 30), 4));
        }
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 450;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        Background background = new Background();
        background.addSpriteToCollection(new Block(new Rectangle(new Point(0, 20),
                800, 600, Color.white)));
        for (int i = 0; i < 100; i++) {
            background.addSpriteToCollection(new Line(new Point(100, 100), new Point(25 + 7 * i, 200), Color.orange));
        }
        background.addSpriteToCollection(new FilledCircle(new Point(100, 100), 50, Color.YELLOW));
        background.addSpriteToCollection(new FilledCircle(new Point(100, 100), 40, Color.orange));
        background.addSpriteToCollection(new FilledCircle(new Point(100, 100), 30, Color.pink));
        return background;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<Block>();
        Random rand = new Random();
        Color color = Color.BLACK;
        for (int i = 0; i < 15; i++) {
            int r = rand.nextInt(255);
            int g = rand.nextInt(255);
            int b = rand.nextInt(255);
            if (i % 2 == 0) {
                color = new Color(r, g, b);
            }
            blockList.add(new Block(new Rectangle(new Point((800 / 15) * i, 200), 800 / 15, 15, color)));
        }
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }
}
