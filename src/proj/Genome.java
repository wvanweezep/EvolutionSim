package proj;

import proj.neuron.CompNeuron;
import proj.neuron.Neuron;
import proj.neuron.NeuronNetwork;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.Objects;

public record Genome(byte[] gen) {
    private static int mutations = 0;
    public Genome(byte[] gen) {
        this.gen = gen;
    }

    public static Genome randomGenome(int complexity) {
        byte[] bytes = new byte[complexity * 3];
        Environment.random.nextBytes(bytes);
        return new Genome(bytes);
    }

    public static Genome mutateGenome(Genome target) {
        byte[] newGen = new byte[target.gen.length];
        System.arraycopy(target.gen, 0, newGen, 0, target.gen.length);
        if (Environment.random.nextInt(0, 1000) > 100) return new Genome(newGen);
        for (int byteIndex = 0; byteIndex < target.gen.length; byteIndex++)
            for (int bitIndex = 0; bitIndex < 8; bitIndex++)
                if (Environment.random.nextInt(0, 1000) > 990)
                    newGen[byteIndex] ^= (byte) (1 << bitIndex);
        return new Genome(newGen);
    }

    public void connectNeurons(NeuronNetwork network) {
        ByteArrayInputStream bytes = new ByteArrayInputStream(gen);

        for (int j = 0; bytes.available() > 0; j++) {
            int b = bytes.read();
            Neuron start;
            CompNeuron end;

            if ((b >> 7 & 1) == 0)
                start = network.getInputNeuron((double) (b & 0x7F) / 127);
            else start = network.getInternalNeuron((double) (b & 0x7F) / 127);

            b = bytes.read();
            if ((b >> 7 & 1) == 0)
                end = network.getOutputNeuron((double) (b & 0x7F) / 127);
            else end = network.getInternalNeuron((double) (b & 0x7F) / 127);

            b = bytes.read();
            int sign = (b >> 7 & 1) == 1 ? 1 : -1;
            int weight = Math.round((float) (b & 0x7F) / 127 * 4);

            end.connect(start, weight * sign);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Genome genome = (Genome) o;
        return Objects.deepEquals(gen, genome.gen);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(gen);
    }

    @Override
    public String toString() {
        return new NeuronNetwork(new Entity(this), this).toString();
    }
}
