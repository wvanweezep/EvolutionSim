package proj;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class Environment {

    public static Environment instance;
    public final static Random random = new Random();

    private int ticks;
    private final int gridSize;
    private final int populationSize;
    private final ArrayList<Entity> entities = new ArrayList<>();

    public Environment(int gridSize, int populationSize) {
        instance = this;
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

    public boolean canMoveTo(int x, int y) {
        if (x >= gridSize || x < 0 || y < 0 || y >= gridSize) return false;
        for (Entity entity : entities)
            if (entity.getX() == x && entity.getY() == y)
               return false;
        return true;
    }

    public void tick() {
        for (Entity entity : entities) {
            entity.tick();
        }
        if (ticks++ > 400)
            entities.removeIf(entity -> entity.getX() < 75 || entity.getY() < 75);
        if (ticks++ > 500) {
            System.out.println(entities.size());
            ticks = 0;
            List<Genome> genomes = new ArrayList<>(new HashSet<>(entities.stream().map(Entity::getGenome).toList()));
            System.out.println(genomes.size());
            //System.out.println(genomes.getFirst());
            entities.clear();

            if (genomes.isEmpty()) {
                spawnGeneration();
            } else {
                for (int i = 0; i < populationSize; i++) {
                    entities.add(new Entity(genomes.get((int)(((float)genomes.size()/(populationSize)) * i))));
                }
            }
        }
    }
}
