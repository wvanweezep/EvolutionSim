package proj.neuron.input;

import proj.Entity;
import proj.neuron.Neuron;

public class FDLiNeuron extends Neuron {
    public FDLiNeuron(Entity entity) {
        super(entity);
    }

    @Override
    protected double getValue() {
        this.value = 4;
        return this.value;
    }
}
