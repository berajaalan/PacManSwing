package pacman;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;


public class Rosa extends Fantasma{

    public Rosa(Jogo jogo) {
        super(jogo);
        this.pos = new Point(297, 220);
//        this.pos = new Point(0, 0);
        this.hurtbox = new Rectangle(pos.x, pos.y, 22, 22);
    }

    @Override
    public void paint(Graphics2D g) {
        g.setColor(Color.PINK);
        g.fill(hurtbox);
    }

    @Override
    public void move() {
        int dx = jogo.pac.pos.x - pos.x;
        int dy = jogo.pac.pos.y - pos.y;
        
        if (dx > 1) {
            dx = 1;
        }
        
        if (dx < -1) {
            dx = -1;
        }
        
        if (dy > 1) {
            dy = 1;
        }
        
        if (dy < -1) {
            dy = -1;
        }
        
        vel.x = dx;
        vel.y = dy;
    }
    
    @Override
    public void update(){
                
        this.move();
        
        pos.x += vel.x;
        pos.y += vel.y;
        
        hurtbox.setLocation(pos);
        
        //this.wallCollisions();
    }
    
    private void wallCollisions() {
        for (Rectangle rect : jogo.walls) {
            if (this.hurtbox.intersects(rect)) {
                this.pos.x -= this.vel.x;
                this.pos.y -= this.vel.y;
                this.hurtbox.x -= this.vel.x;
                this.hurtbox.y -= this.vel.y;
            }
        }
    }

}
