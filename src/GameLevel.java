import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.Random;

/**
 * the Game class hold the sprites and the collidables,
 * and in charge of the animation.
 */
public class GameLevel implements Animation {
    // in charge of a collection of all the sprites in the game
    private SpriteCollection sprites = new SpriteCollection();
    // in charge of a collection of all the collidable objects in the game.
    private GameEnvironment environment = new GameEnvironment();
    // width an length of the game's frame
    private static final double FRAME_WIDTH = 800;
    private static final double FRAME_HEIGHT = 600;
    // size of the bounders
    private static final int BOUNDER_SIZE = 15;
    // counter of the remaining blocks
    private Counter remainingBlocks = new Counter();
    // counter of the remaining balls
    private Counter remainingBalls = new Counter();
    // count score
    private Counter score;
    private ScoreTrackingListener scoreTrackingListener;
    private ScoreIndicator scoreIndicator;

    private AnimationRunner runner = new AnimationRunner();
    private boolean running;
    private KeyboardSensor keyboard = runner.getGui().getKeyboardSensor();
    private int framesPerSecond = 50;

    private LevelInformation levelInformation;

    /**
     * Constructor of the class.
     * @param levelInformation information about the present level.
     * @param keyboardSensor keyboardSensor.
     * @param animationRunner responsible of running the animation.
     * @param scoreCounter couter of the score.
     * @param scoreTrackingListener listen to changes in the score.
     * @param scoreIndicator drawing the score on the screen.
     */
    public GameLevel(LevelInformation levelInformation, KeyboardSensor keyboardSensor,
                     AnimationRunner animationRunner, Counter scoreCounter, ScoreTrackingListener scoreTrackingListener,
                     ScoreIndicator scoreIndicator) {
        this.levelInformation = levelInformation;
        this.runner = animationRunner;
        this.keyboard = keyboardSensor;
        this.score = scoreCounter;
        this.scoreTrackingListener = scoreTrackingListener;
        this.scoreIndicator = scoreIndicator;
    }

    /**
     * add collidable to the collidable list of the game.
     * @param c the collidable.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }
    /**
     * add sprite to the sprite list of the game.
     * @param s the sprite.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Getter of the field FRAME_WIDTH.
     * @return the frame's width
     */
    public double getFrameWidth() {
        return FRAME_WIDTH;
    }
    /**
     * Getter of the field FRAME_HEIGHT.
     * @return the frame's height
     */
    public double getFrameHeight() {
        return FRAME_HEIGHT;
    }
    /**
     * Getter of the field FRAME_SIZE.
     * @return the bounder's size
     */
    public int getBounderSize() {
        return BOUNDER_SIZE;
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle)
     * and add them to the game.
     */
    public void initialize() {
        running = true;
        PrintingHitListener printingHitListener = new PrintingHitListener();
        // create and add the objects to the game
        createBlocks();
        createBounders();
        createPaddle();
        createBallsOnTopOfPaddle();
        sprites.addSprite(scoreIndicator);
    }

    /**
     * create the game's balls and add them to the game.
     */
    private void createBallsOnTopOfPaddle() {
        for (int i = 0; i < levelInformation.numberOfBalls(); i++) {
           Ball ball = new Ball(new Point(FRAME_WIDTH / 2,
                   FRAME_HEIGHT - BOUNDER_SIZE - 20), 4, Color.white);
           ball.setVelocity(levelInformation.initialBallVelocities().get(i));
           ball.setGameEnvironment(environment);
           ball.addToGame(this);
           remainingBalls.increase(1);
        }
    }
    /**
     * create the game's paddle and add it to the game.
     */
    private void createPaddle() {
        Paddle paddle = new Paddle(new Rectangle(new Point((FRAME_WIDTH / 2) - levelInformation.paddleWidth() / 2,
                FRAME_HEIGHT - BOUNDER_SIZE - 15), levelInformation.paddleWidth(), 10), runner);
        paddle.getRectangle().setColor(Color.orange);
        paddle.setPaddleSpeed(levelInformation.paddleSpeed());
        paddle.addToGame(this);
    }
    /**
     * create the game's blocks and add them to the game.
     */
    private void createBlocks() {
        BlockRemover blockRemover = new BlockRemover(this, remainingBlocks);
        Random rand = new Random();
        sprites.addSprite(levelInformation.getBackground());
        for (Block block : levelInformation.blocks()) {
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTrackingListener);
            block.addToGame(this);
            remainingBlocks.increase(1);

        }
        /*for (int i = 0; i < 4; i++) {
            int r = rand.nextInt(255);
            int g = rand.nextInt(255);
            int b = rand.nextInt(255);
            Color color = new Color(r, g, b);
            for (int j = 0; j < (i + 6); j++) {
                remainingBlocks.increase(1);
                Block block = new Block(new Rectangle(new Point(
                        (FRAME_WIDTH - BOUNDER_SIZE) - ((j + 1) * 50), 300 - ((i + 1) * 21)), 50, 20));
                block.getRectangle().setColor(color);
                block.addHitListener(blockRemover);
                block.addHitListener(scoreTrackingListener);
                block.addToGame(this);
            }

        }
        */
    }
    /**
     * create the game's bounders and add them to the game.
     */
    private void createBounders() {
        BallRemover ballRemover = new BallRemover(this, remainingBalls);
        // create the game's bounders
        Block leftBounder = new Block(new Rectangle(new Point(0, 20), BOUNDER_SIZE, FRAME_HEIGHT));
        Block upperBounder = new Block(new Rectangle(new Point(0, 20), FRAME_WIDTH, BOUNDER_SIZE));
        Block rightBounder = new Block(new Rectangle(new Point(FRAME_WIDTH - BOUNDER_SIZE, 20),
                BOUNDER_SIZE, FRAME_HEIGHT));
        Block downerBounder = new Block(new Rectangle(new Point(0, FRAME_HEIGHT - BOUNDER_SIZE),
                FRAME_WIDTH, BOUNDER_SIZE));

        leftBounder.getRectangle().setColor(Color.gray);
        upperBounder.getRectangle().setColor(Color.gray);
        rightBounder.getRectangle().setColor(Color.gray);
        downerBounder.getRectangle().setColor(Color.gray);

        leftBounder.addToGame(this);
        upperBounder.addToGame(this);
        rightBounder.addToGame(this);
        downerBounder.addToGame(this);
        downerBounder.addHitListener(ballRemover);
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        //this.createBallsOnTopOfPaddle(); // or a similar method
        this.running = true;
        this.runner.run(new CountdownAnimation(2, 3, sprites));
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
    }
    /**
     * remove collidable from the game.
     * @param c the removed collidable.
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * remove sprite from the sprites list.
     * @param s the removed sprite.
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (remainingBlocks.getValue() == 0 || remainingBalls.getValue() == 0) {
            if (remainingBlocks.getValue() == 0) {
                score.increase(100);
            }
            this.running = false;
        }
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(keyboard, KeyboardSensor.SPACE_KEY, new PauseScreen()));
        }
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        d.setColor(Color.BLACK);
        d.drawText(20, 15, "Level Name:" + levelInformation.levelName(), 14);
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    @Override
    public int getMillisecondsPerFrame() {
        return 500 / framesPerSecond;
    }

    /**
     * @return number of remaining balls in the game.
     */
    public Counter getRemainingBalls() {
        return remainingBalls;
    }
    /**
     * @return number of remaining blocks in the game.
     */
    public Counter getRemainingBlocks() {
        return remainingBlocks;
    }
}