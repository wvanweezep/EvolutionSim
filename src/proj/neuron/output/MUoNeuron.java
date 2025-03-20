package proj.neuron.output;

import proj.Entity;
import proj.neuron.OutputNeuron;

public class MUoNeuron extends OutputNeuron {
    public MUoNeuron(Entity entity) {
        super(entity);
    }

    public void execute() {
        entity.move(0, -1);
    }
}
