package proj;

public class Main {
    public static void main(String[] args) {
        Environment env = new Environment(100, 5);
        for (int i = 0; i < 10; i++)
            env.tick();
    }
}