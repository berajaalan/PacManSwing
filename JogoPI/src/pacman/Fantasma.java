package pacman;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

public abstract class Fantasma {
    
    Jogo jogo;
    Point pos;
    Point vel = new Point(0,0);
    Rectangle hurtbox;

    public Fantasma(Jogo jogo) {
        this.jogo = jogo;
    }
    
    public abstract void paint(Graphics2D g);
    
    public void update(){
                
        this.move();
        
        pos.x += vel.x;
        pos.y += vel.y;
        
        hurtbox.setLocation(pos);
    }

    public abstract void move();
    
}
