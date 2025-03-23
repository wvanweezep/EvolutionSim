package proj.neuron.input;

import proj.Entity;
import proj.Environment;
import proj.neuron.Neuron;

public class CMDiNeuron extends Neuron {
    public CMDiNeuron(Entity entity) {
        super(entity);
    }

    @Override
    protected double getValue() {
        if (Environment.instance.canMoveTo(entity.getX(), entity.getY() + 1))
            this.value = 1;
        else this.value = -1;
        return this.value;
    }
}