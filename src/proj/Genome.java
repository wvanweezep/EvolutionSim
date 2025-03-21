package proj;

import proj.neuron.CompNeuron;
import proj.neuron.Neuron;
import proj.neuron.NeuronNetwork;

import java.io.ByteArrayInputStream;

public record Genome(byte[] gen) {
    public Genome(byte[] gen) {
        this.gen = gen;
    }

    public static Genome randomGenome(int complexity) {
        byte[] bytes = new byte[complexity * 3];
        Environment.random.nextBytes(bytes);
        return new Genome(bytes);
    }

    public static Genome mutateGenome(Genome target) {
        byte[] newGen = target.gen.clone();
        for (int byteIndex = 0; byteIndex < target.gen.length; byteIndex++)
            for (int bitIndex = 0; bitIndex < 8; bitIndex++)
                if (Environment.random.nextInt(0, 1000) > 995)
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
            int weight = Math.round((float) (b & 0x7F) / 127) * 4;

            end.connect(start, weight * sign);
        }
    }
}
