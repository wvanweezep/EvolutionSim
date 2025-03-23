package proj.neuron;

import proj.Entity;
import proj.util.Pair;

public class OutputNeuron extends CompNeuron {

    public OutputNeuron(Entity entity) {
        super(entity);
    }

    /**
     * The output action to execute on activation.
     */
    public void execute() {}

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Out(" + this.getClass() + ") <- ");
        for (Pair<Neuron, Integer> neuron : this.input)
            builder.append("\n  {").append(neuron.getFirst().toString()).append('}');
        return builder.toString();
    }
}
