package proj.neuron.output;

import proj.Entity;
import proj.neuron.OutputNeuron;

public class MDoNeuron extends OutputNeuron {
    public MDoNeuron(Entity entity) {
        super(entity);
    }

    public void execute() {
        entity.move(0, 1);
    }
}
