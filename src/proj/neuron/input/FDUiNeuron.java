package proj.neuron.input;

import proj.Entity;
import proj.neuron.Neuron;

public class FDUiNeuron extends Neuron {
    public FDUiNeuron(Entity entity) {
        super(entity);
    }

    @Override
    protected double getValue() {
        this.value = 4;
        return this.value;
    }
}
