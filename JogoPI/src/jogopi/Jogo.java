package jogopi;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class Jogo extends JPanel implements KeyListener {

    Pacman pac = new Pacman(this);
    String gameState = "menu", menuSelect = "play";
    Play play = new Play(this);
    ScoreBoard sb = new ScoreBoard(this);
    Exit exit = new Exit(this);
    ArrayList <Rectangle> walls = new ArrayList<>();

    public Jogo() {
        JFrame frame = new JFrame("Pacman");
        frame.add(this);
        frame.setSize(480, 853);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener(this);
        this.setBackground(Color.black);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
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
                g.drawString("PACMAN", 45, 150);
                switch (menuSelect) {
                    case "play":
                        g2d.setColor(Color.white);
                        g2d.fillArc(100, 275, 30, 30, 135, 90);
                        break;
                    case "sb":
                        g2d.setColor(Color.white);
                        g2d.fillArc(100, 365, 30, 30, 135, 90);
                        break;
                    case "exit":
                        g2d.setColor(Color.white);
                        g2d.fillArc(100, 455, 30, 30, 135, 90);
                        break;
                }
                break;
            case "game":
                pac.paint(g2d);
                break;
            case "pause":
                pac.paint(g2d);
                g2d.setColor(Color.WHITE);
                g2d.fillRect(this.getWidth() - 15, 5, 10, 30);
                g2d.fillRect(this.getWidth() - 30, 5, 10, 30);
                break;
        }
    }
    
    public void update() {
        pac.update();
    }

    public void run() {

        while (true) {
            switch (gameState) {
                case "menu":
                    this.repaint();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException ex) {
                    }
                    break;
                case "game":
                    this.update();
                    this.repaint();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException ex) {
                    }
                    break;
                case "pause":
                    this.repaint();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException ex) {
                    }
                    break;
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
        }
    }

    @Override
    public void keyPressed(KeyEvent k) {
        switch (k.getKeyCode()) {
            //UP
            case 38:
                switch(gameState){
                    case "game":
                        pac.dir = 'u';
                        break;
                    case "menu":
                        switch(menuSelect){
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
                switch(gameState){
                    case "game":
                        pac.dir = 'd';
                        break;
                    case "menu":
                        switch(menuSelect){
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
                pac.dir = 'l';
                break;
            //RIGHT
            case 39:
                pac.dir = 'r';
                break;
            case 32:
                switch(menuSelect){
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
                if(gameState == "game" || gameState == "pause")
                    gameState = "menu";
            break;
        }
    }

    @Override
    public void keyReleased(KeyEvent k) {
    }

}
