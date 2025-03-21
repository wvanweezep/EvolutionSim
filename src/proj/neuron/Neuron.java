package proj.neuron;

import proj.Entity;

public class Neuron {

    protected double value = 2;
    protected final Entity entity;

    public Neuron(Entity entity) {
        this.entity = entity;
    }

    /**
     * Getter for the value of the {@code Neuron}.
     *
     * @return The computed value of the {@code Neuron}.
     */
    protected double getValue() {
        return this.value;
    }

    /**
     * Resets the {@code Neuron} value back to zero.
     */
    protected void reset() {
        this.value = 0;
    }

    /**
     * Clears all connections of connected {@code Neuron}.
     */
    protected void disconnect() {}

    /**
     * Clamp the computed value between the set bounds (-4; 4).
     *
     * @param value The value to clamp.
     * @return The clamped value.
     */
    protected static double normalize(double value) {
        return Math.clamp(value, -4, 4);
    }

    @Override
    public String toString() {
        return "In(" + this.getClass() + ")";
    }
}
