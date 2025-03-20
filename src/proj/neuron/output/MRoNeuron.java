package proj.neuron.output;

import proj.Entity;
import proj.neuron.OutputNeuron;

public class MRoNeuron extends OutputNeuron {
    public MRoNeuron(Entity entity) {
        super(entity);
    }

    public void execute() {
        entity.move(1, 0);
    }
}