import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class of the fourth level in the game, named : "Final Four".
 */
public class FinalFourLevel implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list =  new ArrayList<Velocity>();
        for (int i = 0; i < numberOfBalls(); i++) {
            list.add(Velocity.fromAngleAndSpeed(i * 30 + 30, 4));
        }
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 8;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        Background background = new Background();
        background.addSpriteToCollection(new Block(new Rectangle(new Point(0, 20),
                800, 600, Color.cyan)));
        // first cloud
        for (int i = 0; i < 9; i++) {
            background.addSpriteToCollection(new Line(new Point(100 + 10 * i, 400),
                    new Point(85 + 10 * i, 800), Color.white));
        }
        background.addSpriteToCollection(new FilledCircle(new Point(100, 400), 20, Color.gray));
        background.addSpriteToCollection(new FilledCircle(new Point(120, 420), 20, Color.gray));
        background.addSpriteToCollection(new FilledCircle(new Point(130, 390), 20, Color.gray));
        background.addSpriteToCollection(new FilledCircle(new Point(140, 415), 20, Color.gray));
        background.addSpriteToCollection(new FilledCircle(new Point(160, 400), 30, Color.gray));

        // second cloud
        for (int i = 0; i < 9; i++) {
            background.addSpriteToCollection(new Line(new Point(600 + 10 * i, 500),
                    new Point(585 + 10 * i, 800), Color.white));
        }
        background.addSpriteToCollection(new FilledCircle(new Point(600, 500), 20, Color.lightGray));
        background.addSpriteToCollection(new FilledCircle(new Point(620, 520), 20, Color.lightGray));
        background.addSpriteToCollection(new FilledCircle(new Point(630, 490), 20, Color.lightGray));
        background.addSpriteToCollection(new FilledCircle(new Point(640, 515), 20, Color.lightGray));
        background.addSpriteToCollection(new FilledCircle(new Point(660, 500), 30, Color.lightGray));


        return background;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<Block>();
        Random rand = new Random();
        for (int i = 0; i < 7; i++) {
            int r = rand.nextInt(255);
            int g = rand.nextInt(255);
            int b = rand.nextInt(255);
            Color color = new Color(r, g, b);
            for (int j = 0; j < 15; j++) {
                Block block = new Block(new Rectangle(new Point(15 + j * (800 - 30) / 15,
                        150 + i * 20), (800 - 30) / 15, 20));
                block.getRectangle().setColor(color);
                blockList.add(block);
            }

        }
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 0;
    }
}
