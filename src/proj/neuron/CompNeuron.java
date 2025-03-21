package proj.neuron;

import proj.Entity;
import proj.neuron.output.MLoNeuron;
import proj.neuron.output.MRoNeuron;
import proj.util.Pair;

import java.util.ArrayList;

public class CompNeuron extends Neuron {

    protected final ArrayList<Pair<Neuron, Integer>> input = new ArrayList<>();

    public CompNeuron(Entity entity) {
        super(entity);
    }

    /**
     * Connect a {@code Neuron} as an input for this {@code Neuron} with
     * either an excitation or inhibition connection.
     *
     * @param neuron The {@code Neuron} to connect with.
     * @param influence The excitation or inhibition weight of the connection.
     */
    public void connect(Neuron neuron, int influence) {
        if (this.equals(neuron)) return;

        if (neuron.getClass() == CompNeuron.class) {
            CompNeuron compNeuron = (CompNeuron) neuron;
            if (deepSearchConnections(compNeuron, this)) {
                return;
            }
        }

        input.add(new Pair<>(neuron, influence));
    }

    public boolean deepSearchConnections(CompNeuron neuron, CompNeuron target) {
        for (Pair<Neuron, Integer> neuronPair : neuron.input) {
            if (neuronPair.getFirst().getClass() == CompNeuron.class) {
                CompNeuron compNeuron = (CompNeuron) neuronPair.getFirst();
                if (compNeuron == target) return true;
                else if (deepSearchConnections(compNeuron, target)) return true;
            }
        } return false;
    }

    /**
     * Traverses input {@code List<Neuron>} to compute {@code Neuron} value.
     *
     * @return The computed value of the {@code Neuron}.
     */
    @Override
    public double getValue() {
        this.value = normalize(input.stream().mapToDouble(n -> n.getFirst().getValue() * n.getSecond()).sum());
        return this.value;
    }

    /**
     * Resets the {@code Neuron} value and all connected back to zero.
     */
    @Override
    public void reset() {
        for (Pair<Neuron, Integer> neuron : input)
            neuron.getFirst().reset();
        this.value = 0;
    }

    /**
     * Clears all connections of connected {@code Neuron}.
     */
    @Override
    public void disconnect() {
        for (Pair<Neuron, Integer> neuron : input)
            neuron.getFirst().disconnect();
        this.input.clear();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Internal <- ");
        int i = 0;
        for (Pair<Neuron, Integer> neuron : this.input)
            builder.append("\n  {").append(neuron.getFirst().toString()).append('}');
        return builder.toString();
    }
}
