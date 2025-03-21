package proj;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Main extends JPanel implements ActionListener {

    private int ticks = 0;
    private long prev = System.nanoTime();
    private final Environment env;

    public Main() {
        setPreferredSize(new Dimension(500, 500));
        setBackground(Color.BLACK);
        env = new Environment(100, 100);
        Timer timer = new Timer(100, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        ArrayList<Entity> entities = env.getEntities();
        for (Entity entity : entities)
            g.fillRect(entity.getX() * 5, entity.getY() * 5, 5, 5);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        env.tick();
        trackFPS();
        repaint();
    }

    private void trackFPS() {
        ticks++;
        if (System.nanoTime() - prev >= 1_000_000_000L) {
            System.out.println("FPS: " + ticks);
            ticks = 0;
            prev = System.nanoTime();
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Renderer");
        Main r = new Main();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(r);
        frame.pack();
        frame.setVisible(true);
    }
}