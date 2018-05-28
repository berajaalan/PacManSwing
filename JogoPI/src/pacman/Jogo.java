package pacman;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class Jogo extends JPanel implements KeyListener {

    // PacMan
    Pacman pac = new Pacman(this);

    // Botões
    String gameState = "menu", menuSelect = "play";
    Play play = new Play(this);
    ScoreBoard sb = new ScoreBoard(this);
    Exit exit = new Exit(this);

    // Level
    Level lvl;
    ArrayList<Rectangle> walls = new ArrayList<>();
    ArrayList<Pellets> pel = new ArrayList<>();

    // Fantasmas
    Rosa fantRosa = new Rosa(this);

    public Jogo() throws IOException {
        JFrame frame = new JFrame("Pacman");
        frame.add(this);
        frame.setSize(616, 748);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener(this);
        this.setBackground(Color.black);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        this.lvl = new Level(this, "map1.txt");
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        switch (gameState) {
            case "menu":
                play.paint(g2d);
                sb.paint(g2d);
                exit.paint(g2d);
                Font fnt = new Font("arial", 1, 90);
                g.setFont(fnt);
                g.setColor(Color.ORANGE);
                g.drawString("PACMAN", 100, 150);
                switch (menuSelect) {
                    case "play":
                        g2d.setColor(Color.white);
                        g2d.fillArc(155, 275, 30, 30, 135, 90);
                        break;
                    case "sb":
                        g2d.setColor(Color.white);
                        g2d.fillArc(155, 365, 30, 30, 135, 90);
                        break;
                    case "exit":
                        g2d.setColor(Color.white);
                        g2d.fillArc(155, 455, 30, 30, 135, 90);
                        break;
                }
                break;
            case "game":
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
                fantRosa.paint(g2d);
                pac.paint(g2d);
                break;
            case "pause":
                g2d.setColor(Color.WHITE);
                g2d.fillRect(this.getWidth() - 15, 5, 10, 30);
                g2d.fillRect(this.getWidth() - 30, 5, 10, 30);
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
                for (Pellets p : pel) {
                    p.paint(g2d);
                }
                fantRosa.paint(g2d);
                pac.paint(g2d);
                break;
        }
    }

    public void update() {
        pac.update();
        fantRosa.update();
    }

    public void run() {

        while (true) {
            switch (gameState) {
                case "menu":
                    this.repaint();
                    break;
                case "game":
                    this.update();
                    this.repaint();
                    break;
                case "pause":
                    this.repaint();
                    break;
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent k) {
        switch (k.getKeyChar()) {
            // P
            case 'p':
                if (gameState == "game") {
                    gameState = "pause";
                } else {
                    gameState = "game";
                }
                break;
            case 'h': {
                try {
                    lvl.setArq("map1_1.txt");
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Jogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Jogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
            }
            break;
        }
    }

    @Override
    public void keyPressed(KeyEvent k) {
        switch (k.getKeyCode()) {
            //UP
            case 38:
                switch (gameState) {
                    case "game":
                        pac.proxDir = 'u';
                        break;
                    case "menu":
                        switch (menuSelect) {
                            case "play":
                                menuSelect = "exit";
                                break;
                            case "sb":
                                menuSelect = "play";
                                break;
                            case "exit":
                                menuSelect = "sb";
                                break;
                        }
                        break;
                }
                break;
            //DOWN
            case 40:
                switch (gameState) {
                    case "game":
                        pac.proxDir = 'd';
                        break;
                    case "menu":
                        switch (menuSelect) {
                            case "play":
                                menuSelect = "sb";
                                break;
                            case "sb":
                                menuSelect = "exit";
                                break;
                            case "exit":
                                menuSelect = "play";
                                break;
                        }
                        break;
                }
                break;
            //LEFT
            case 37:
                pac.proxDir = 'l';
                break;
            //RIGHT
            case 39:
                pac.proxDir = 'r';
                break;
            case 32:
                switch (menuSelect) {
                    case "play":
                        play.action();
                        break;
                    case "sb":
                        sb.action();
                        break;
                    case "exit":
                        exit.action();
                        break;
                }
                break;
            case 27:
                if (gameState == "game" || gameState == "pause") {
                    gameState = "menu";
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent k) {
    }

}
