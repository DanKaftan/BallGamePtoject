import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * Class of the third level in the game, named : "Green 3".
 */
public class Green3Level implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list =  new ArrayList<Velocity>();
        for (int i = 0; i < numberOfBalls(); i++) {
            list.add(Velocity.fromAngleAndSpeed(i * 30 + 30, 3));
        }
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        Background background = new Background();
        background.addSpriteToCollection(new Block(new Rectangle(new Point(0, 20),
                800, 600, Color.GREEN)));
        background.addSpriteToCollection(new Block(new Rectangle(new Point(50, 400), 100, 200, Color.BLACK)));
        background.addSpriteToCollection(new Block(new Rectangle(new Point(90, 350), 25, 50, Color.DARK_GRAY)));
        background.addSpriteToCollection(new Block(new Rectangle(new Point(97, 200), 10, 150, Color.GRAY)));
        background.addSpriteToCollection(new FilledCircle(new Point(102, 200), 10, Color.red));
        background.addSpriteToCollection(new FilledCircle(new Point(102, 200), 5, Color.orange));
        background.addSpriteToCollection(new FilledCircle(new Point(102, 200), 2, Color.white));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                background.addSpriteToCollection(new Block(new Rectangle(new Point(65 + 30 * j, 425 + 50 * i),
                        15, 25, Color.white)));
            }
        }
        return background;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<Block>();
        Random rand = new Random();
        for (int i = 0; i < 4; i++) {
            int r = rand.nextInt(255);
            int g = rand.nextInt(255);
            int b = rand.nextInt(255);
            Color color = new Color(r, g, b);
            for (int j = 0; j < (i + 6); j++) {
                Block block = new Block(new Rectangle(new Point(
                        (800 - 15) - ((j + 1) * 50), 300 - ((i + 1) * 21)), 50, 20));
                block.getRectangle().setColor(color);
                blockList.add(block);
            }

        }
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 20;
    }
}
