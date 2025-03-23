package proj.neuron.input;

import proj.Entity;
import proj.Environment;
import proj.neuron.Neuron;

public class CMRiNeuron extends Neuron {
    public CMRiNeuron(Entity entity) {
        super(entity);
    }

    @Override
    protected double getValue() {
        if (Environment.instance.canMoveTo(entity.getX() - 1, entity.getY()))
            this.value = 1;
        else this.value = -1;
        return this.value;
    }
}
