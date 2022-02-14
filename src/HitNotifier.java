/**
 * Interface of all the listeners to the hits.
 */
public interface HitNotifier {

    /**
     * Add listener to hit events.
     * @param hl the added listener.
     */
    void addHitListener(HitListener hl);

    /**
     * Remove al listener from the list of listeners to hit events.
     * @param hl the removed listener.
     */
    void removeHitListener(HitListener hl);
}