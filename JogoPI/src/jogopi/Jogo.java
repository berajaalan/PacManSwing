package jogopi;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class Jogo extends JPanel implements KeyListener {

    Pacman pac = new Pacman(this);
    String gameState = "game", menuSelect = "play";
    Level lvl;
    Fantasma red;
    ArrayList<Rectangle> walls = new ArrayList<>();
    ArrayList<Pellets> pel = new ArrayList<>();
    PathFinder pf;

    public Jogo() throws IOException {
        JFrame frame = new JFrame("Pacman");
        frame.add(this);
        frame.setSize(616, 704);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener(this);
        this.setBackground(Color.black);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        this.lvl = new Level(this);
        red = new Fantasma(this, "images\\Red");
        pf = new PathFinder(this);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (Rectangle rect : walls) {
            if (rect.y == 264) {
                if (rect.x != 286 && rect.x != 308) {
                    g2d.setColor(Color.blue);
                    g2d.fill(rect);
                }
            } else {
                g2d.setColor(Color.blue);
                g2d.fill(rect);
            }
        }
        
        g2d.setColor(Color.white);
        g2d.fillRect(286, 269, 44, 11);
        
        for (Pellets p : pel) {
            p.paint(g2d);
        }
        
        red.paint(g2d);
        pac.paint(g2d);
    }

    public void update() {
        pac.update();
    }

    public void run() {

        while (true) {
            this.repaint();
            this.update();
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
            }

        }
    }

    @Override
    public void keyTyped(KeyEvent k) {
    }

    @Override
    public void keyPressed(KeyEvent k) {
        switch (k.getKeyCode()) {
            //UP
            case 38:
                pac.dir = "u";
                break;
            //DOWN
            case 40:
                pac.dir = "d";
                break;
            //LEFT
            case 37:
                pac.dir = "l";
                break;
            //RIGHT
            case 39:
                pac.dir = "r";
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent k) {
    }

}
