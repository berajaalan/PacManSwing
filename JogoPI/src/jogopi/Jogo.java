package jogopi;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class Jogo extends JPanel implements KeyListener{
    
    Pacman pac = new Pacman(this);
    
    public void move(){
        pac.move();
    }

    public Jogo() {
        JFrame frame = new JFrame("Pacman");
        frame.add(this);
        frame.setSize(800, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener(this);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        pac.paint(g2d);
//        g2d.setColor(Color.RED);
//        g2d.fillOval(0, 0, 30, 30);
//        g2d.drawOval(0, 50, 30, 30);
//        g2d.fillRect(50, 0, 30, 30);
//        g2d.drawRect(50, 50, 30, 30);
//        g2d.draw(new Ellipse2D.Double(0, 100, 30, 30));
    }
    
    public void run(){
        while(true){
            this.move();
            this.repaint();
            try{
                Thread.sleep(10);
            } catch (InterruptedException ex){
                
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent k) {
    }

    @Override
    public void keyPressed(KeyEvent k) {
        switch (k.getKeyCode()){
            //UP
            case 38:
                pac.dir = 'u';
                break;
            //DOWN
            case 40:
                pac.dir = 'd';
                break;
            //LEFT
            case 37:
                pac.dir = 'l';
                break;
            //RIGHT
            case 39:
                pac.dir = 'r';
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent k) {
    }

}
