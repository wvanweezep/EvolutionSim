package proj;

import proj.neuron.NeuronNetwork;

import java.awt.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Entity {

    private int x, y;
    private final NeuronNetwork neuronNetwork;
    private final Genome genome;

    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
        this.genome = Genome.randomGenome(18);
        this.neuronNetwork = new NeuronNetwork(this, this.genome);
    }

    public Entity(Genome genome) {
        this.x = Environment.random.nextInt(0, 100);
        this.y = Environment.random.nextInt(0, 100);
        this.genome = Genome.mutateGenome(genome);
        this.neuronNetwork = new NeuronNetwork(this, this.genome);
    }

    public Color generateColor() {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(genome.gen());  // 256-bit hash
            return new Color(hash[0] & 0xFF, hash[1] & 0xFF, hash[2] & 0xFF);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public int getX() { return this.x; }

    public int getY() { return this.y; }

    public Genome getGenome() {
        return this.genome;
    }

    public void move(int dx, int dy) {
        if (!Environment.instance.canMoveTo(x + dx, y + dy)) return;
        this.x += dx;
        this.y += dy;
    }

    public void tick() {
        this.neuronNetwork.activate();
    }

    @Override
    public String toString() {
        return "Entity(" + x + ", " + y + ", " + neuronNetwork + ")";
    }
}
