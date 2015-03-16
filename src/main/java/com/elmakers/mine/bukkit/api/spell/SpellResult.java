package com.elmakers.mine.bukkit.api.spell;

/**
 * Every Spell will return a SpellResult when cast. This result
 * will determine the messaging and effects used, as well as whether 
 * or not the Spell cast consumes its CastingCost costs. 
 * 
 * A Spell that fails to cast will not consume costs or register for cooldown.
 */
public enum SpellResult {
    // Order is important here
    PENDING(true, false, false),
    CAST(true, false, false),
    ALTERNATE(true, false, false, true),
    ALTERNATE_UP(true, false, false, true),
    ALTERNATE_DOWN(true, false, false, true),
    ALTERNATE_SNEAK(true, false, false, true),

    FIZZLE(false, true, false),
    BACKFIRE(false, true, false),

    DEACTIVATE(false, false, true),
    TARGET_SELECTED(false, false, true),

    CURSED(false, true, true),
    COOLDOWN(false, true, true),

    NO_TARGET(false, false, false),

    FAIL(false, true, true),
    CANCELLED(false, true, true),
    INSUFFICIENT_RESOURCES(false, true, true),
    INSUFFICIENT_PERMISSION(false, true, true),

    ENTITY_REQUIRED(false, true, true),
    LIVING_ENTITY_REQUIRED(false, true, true),
    PLAYER_REQUIRED(false, true, true),
    LOCATION_REQUIRED(false, true, true),
    WORLD_REQUIRED(false, true, true),
    INVALID_WORLD(false, true, true),

    // This should always be last
    NO_ACTION(false, false, false);

    private final boolean success;
    private final boolean failure;
    private final boolean free;
    private final boolean alternate;

    private SpellResult(boolean success, boolean failure, boolean free) {
        this.success = success;
        this.failure = failure;
        this.free = free;
        this.alternate = false;
    }

    private SpellResult(boolean success, boolean failure, boolean free, boolean alternate) {
        this.success = success;
        this.failure = failure;
        this.free = free;
        this.alternate = alternate;
    }

    /**
     * Determine if this result is a failure or not.
     *
     * Note that a spell result can be neither failure nor
     * success.
     *
     * @return True if this cast was a failure.
     */
    public boolean isFailure() {
        return failure;
    }

    /**
     * Determine if this result is a success or not.
     *
     * @return True if this cast was a success.
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Determine if this result is a free cast or not.
     *
     * @return True if this cast should not consume costs.
     */
    public boolean isFree() {
        return free;
    }

    /**
     * Determine if this result is an alternate-mode cast.
     *
     * @return True if this cast was an alternate-mode.
     */
    public boolean isAlternate() {
        return alternate;
    }

    public SpellResult min(SpellResult other) {
        return (this.ordinal() < other.ordinal()) ? this : other;
    }

    public SpellResult max(SpellResult other) {
        return (this.ordinal() > other.ordinal()) ? this : other;
    }
}
