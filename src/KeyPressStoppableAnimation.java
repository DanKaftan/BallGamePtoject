import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * Decorator of animation which give it the ability to stop.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean isAlreadyPressed = true;

    /**
     * Constructor.
     * @param sensor the keyboard.
     * @param key the key which stop the animation.
     * @param animation the decoratored animation.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
    }

    @Override
    public boolean shouldStop() {
        if (isAlreadyPressed) {
            isAlreadyPressed = false;
            return false;
        }
        return this.sensor.isPressed(key);
    }

    @Override
    public int getMillisecondsPerFrame() {
        return 10;
    }
}
