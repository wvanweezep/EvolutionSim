package proj.neuron;

import proj.Entity;
import proj.Genome;
import proj.neuron.input.*;
import proj.neuron.output.*;
import proj.util.Pair;

import java.util.ArrayList;

public class NeuronNetwork {

    private final ArrayList<Neuron> inputNeurons = new ArrayList<>();
    private final ArrayList<CompNeuron> internalNeurons = new ArrayList<>();
    private final ArrayList<OutputNeuron> outputNeurons = new ArrayList<>();

    public NeuronNetwork(Entity entity, Genome genome) {
        initializeNeurons(entity);
        genome.connectNeurons(this);
    }

    public Neuron getInputNeuron(double fraction) {
        return this.inputNeurons.get(Math.round( (float) fraction * (inputNeurons.size() - 1)));
    }

    public CompNeuron getInternalNeuron(double fraction) {
        return this.internalNeurons.get(Math.round( (float) fraction * (internalNeurons.size() - 1)));
    }

    public OutputNeuron getOutputNeuron(double fraction) {
        return this.outputNeurons.get(Math.round( (float) fraction * (outputNeurons.size() - 1)));
    }

    private void initializeNeurons(Entity entity) {
        // Input neurons
        inputNeurons.add(new FDDiNeuron(entity));
        inputNeurons.add(new FDUiNeuron(entity));
        inputNeurons.add(new FDRiNeuron(entity));
        inputNeurons.add(new FDLiNeuron(entity));

        // Internal neurons
        for (int i = 0; i < 4; i++)
            internalNeurons.add(new CompNeuron(entity));

        // Output neurons
        outputNeurons.add(new MDoNeuron(entity));
        outputNeurons.add(new MUoNeuron(entity));
        outputNeurons.add(new MRoNeuron(entity));
        outputNeurons.add(new MLoNeuron(entity));
    }

    public void activate() {
        ArrayList<Pair<OutputNeuron, Double>> result = new ArrayList<>();
        for (OutputNeuron outputNeuron : this.outputNeurons)
            result.add(new Pair<>(outputNeuron, outputNeuron.getValue()));
        double max = result.stream().mapToDouble(Pair::getSecond).max().getAsDouble();
        if (max == 0) return;
        result.stream().filter(n -> n.getSecond() == max).findAny().get().getFirst().execute();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("NeuronNetwork");
        for (OutputNeuron neuron : this.outputNeurons)
            builder.append("\n  ").append(neuron.toString());
        return builder.toString();
    }
}
