package proj;

import proj.neuron.NeuronNetwork;

public class Entity {

    private int x, y;
    private final NeuronNetwork neuronNetwork;
    private final Genome genome;

    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
        this.genome = Genome.randomGenome(8);
        this.neuronNetwork = new NeuronNetwork(this, this.genome);
    }

    public int getX() { return this.x; }

    public int getY() { return this.y; }

    public NeuronNetwork getNeuronNetwork() {
        return this.neuronNetwork;
    }

    public void move(int dx, int dy) {
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
