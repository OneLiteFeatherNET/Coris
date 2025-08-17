package net.onelitefeather.coris.door;

/**
 * Represents the different animation states that a door can be in during its lifecycle.
 * <p>
 * This enum defines a finite state machine for door animations, allowing for proper
 * state management and transition control. The states represent both static positions
 * and dynamic transition phases of door movement.
 * </p>
 * <p>
 * Typical state transitions follow this pattern:
 * {@code IDLE → OPENING → OPENED → CLOSING → IDLE}
 * </p>
 * <p>
 * The enum can be used for:
 * <ul>
 *   <li>Controlling door animation timing and sequencing</li>
 *   <li>Preventing invalid state transitions (e.g., opening an already opening door)</li>
 *   <li>Synchronizing visual effects with door movement</li>
 *   <li>Managing sound effects and particle systems</li>
 * </ul>
 *
 * @author theEvilReaper
 * @version 1.0.0
 * @since 0.1.0
 */
public enum AnimationState {

    /**
     * The door is in a resting state and not currently animating.
     * <p>
     * This is the default state for doors that are fully closed and stationary.
     * From this state, a door can transition to {@link #OPENING} when activation occurs.
     * </p>
     */
    IDLE,

    /**
     * The door is currently in the process of opening.
     * <p>
     * This transitional state represents the animation phase where the door moves
     * from closed to open position. During this state, the door should not accept
     * new open commands but may accept close commands depending on implementation.
     * </p>
     */
    OPENING,

    /**
     * The door is fully open and stationary.
     * <p>
     * This state indicates that the opening animation has completed and the door
     * is now in its fully opened position. From this state, the door can transition
     * to {@link #CLOSING} when deactivation occurs or after a timeout period.
     * </p>
     */
    OPENED,

    /**
     * The door is currently in the process of closing.
     * <p>
     * This transitional state represents the animation phase where the door moves
     * from open to closed position. During this state, the door should not accept
     * new close commands but may accept open commands depending on implementation.
     * </p>
     */
    CLOSING
}