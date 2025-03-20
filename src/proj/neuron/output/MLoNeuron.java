package proj.neuron.output;

import proj.Entity;
import proj.neuron.OutputNeuron;

public class MLoNeuron extends OutputNeuron {
    public MLoNeuron(Entity entity) {
        super(entity);
    }

    public void execute() {
        entity.move(-1, 0);
    }
}
