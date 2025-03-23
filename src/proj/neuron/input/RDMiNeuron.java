package proj.neuron.input;

import proj.Entity;
import proj.Environment;
import proj.neuron.Neuron;

public class RDMiNeuron extends Neuron {
    public RDMiNeuron(Entity entity) {
        super(entity);
    }

    @Override
    protected double getValue() {
        this.value = Environment.random.nextInt(-4, 4);
        return this.value;
    }
}
