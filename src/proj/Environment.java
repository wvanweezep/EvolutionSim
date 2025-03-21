package proj;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Environment {

    public final static Random random = new Random();

    private int ticks;
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

    private void respawnGeneration() {

    }

    public void tick() {
        for (Entity entity : entities) {
            entity.tick();
        }

        if (ticks++ > 100) {
            entities.removeIf(entity -> entity.getX() < 99 || entity.getY() < 99);

            ticks = 0;
            List<Genome> genomes = entities.stream().map(Entity::getGenome).toList();
            entities.clear();
            if (genomes.isEmpty()) {
                spawnGeneration();
            } else {
                int size = entities.size();
                for (int i = 0; i < populationSize - size; i++) {
                    entities.add(new Entity(genomes.get((genomes.size()/(populationSize-size)) * i)));
                }
            }
        }
    }
}
