package proj;

import java.util.ArrayList;
import java.util.Random;

public class Environment {

    public final static Random random = new Random(6);

    private final int gridSize;
    private final int populationSize;
    private final ArrayList<Entity> entities = new ArrayList<>();

    public Environment(int gridSize, int populationSize) {
        this.gridSize = gridSize;
        this.populationSize = populationSize;
        spawnGeneration();
    }

    public ArrayList<Entity> getEntities() {
        return this.entities;
    }

    private void spawnGeneration() {
        for (int i = 0; i < populationSize; i++)
            entities.add(new Entity(random.nextInt(0, gridSize), random.nextInt(0, gridSize)));
    }

    public void tick() {
        for (Entity entity : entities) {
            entity.tick();
        }
    }
}
