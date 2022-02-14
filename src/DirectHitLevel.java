import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Class of the first level in the game, named : "Direct Hit".
 */
public class DirectHitLevel implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<Velocity>();
        list.add(new Velocity(0, -1));
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 8;
    }

    @Override
    public int paddleWidth() {
        return 80;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        Background directHitBackground = new Background();
        directHitBackground.addSpriteToCollection(new Block(new Rectangle(new Point(0, 20), 800,
                600, Color.BLACK)));
        directHitBackground.addSpriteToCollection(new Circle(new Point(400 - 30 / 2 + 15, 207), 50, Color.BLUE));
        directHitBackground.addSpriteToCollection(new Circle(new Point(400 - 30 / 2 + 15, 207), 70, Color.BLUE));
        directHitBackground.addSpriteToCollection(new Circle(new Point(400 - 30 / 2 + 15, 207), 90, Color.BLUE));
        directHitBackground.addSpriteToCollection(new Line(new Point(275, 207), new Point(525, 207)));
        directHitBackground.addSpriteToCollection(new Line(new Point(400 - 30 / 2 + 15, 100),
                new Point(400 - 30 / 2 + 15, 320)));
        return directHitBackground;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<Block>();
        blockList.add(new Block(new Rectangle(new Point(400 - 30 / 2, 200), 30, 15, Color.white)));
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
