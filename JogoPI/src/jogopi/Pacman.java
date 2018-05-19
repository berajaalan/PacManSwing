package jogopi;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

public class Pacman {

    Point pos = new Point(0,0);
    Point vel = new Point(0,0);
    char dir = 'd';
    int arcDelta = 0, arcSize = 270;
    Rectangle hurtbox = new Rectangle(pos.x, pos.y, 40, 40);;
    boolean fecha = true;
    Jogo jogo;

    public Pacman(Jogo jogo) {
        this.jogo = jogo;
        
    }

    public void paint(Graphics2D g) {
        g.setColor(Color.yellow);
        switch (dir) {
            case 'u':
                g.fillArc(pos.x, pos.y, 40, 40, 135 - arcDelta, arcSize);
                break;
            case 'd':
                g.fillArc(pos.x, pos.y, 40, 40, 315 - arcDelta, arcSize);
                break;
            case 'l':
                g.fillArc(pos.x, pos.y, 40, 40, 225 - arcDelta, arcSize);
                break;
            case 'r':
                g.fillArc(pos.x, pos.y, 40, 40, 45 - arcDelta, arcSize);
                break;
        }
    }

    private void stop() {
        this.vel.x = 0;
        this.vel.y = 0;
    }

    private void walk(int x, int y) {
        this.vel.x = x;
        this.vel.y = y;
    }

    public void move() {

        switch (dir) {
            case 'u':
                if (pos.y + vel.y <= 0) {
                    this.stop();
                } else {
                    this.walk(0, -1);
                }
                break;
            case 'd':
                if (pos.y + vel.y >= jogo.getHeight() - 40) {
                    this.stop();
                } else {
                    this.walk(0, 1);
                }
                break;
            case 'l':
                if (pos.x + vel.x <= 0) {
                    this.stop();
                } else {
                    this.walk(-1, 0);
                }
                break;
            case 'r':
                if (pos.x + vel.x >= jogo.getWidth() - 40) {
                    this.stop();
                } else {
                    this.walk(1, 0);
                }
                break;
        }
        
        hurtbox.x += vel.x;
        hurtbox.y += vel.y;

        pos.x += vel.x;
        pos.y += vel.y;
    }

    public void mouthMove() {
        if (fecha) {
            arcDelta += 2;
            arcSize += 4;
            if (arcSize >= 360) {
                fecha = false;
            }
        }else{
            arcDelta -= 2;
            arcSize -= 4;
            if (arcSize <= 270) {
                fecha = true;
            }
        }
    }
    
    private void wallCollisions(){
        for (Rectangle rect : jogo.walls) {
            if (this.hurtbox.intersects(rect)) {
                this.hurtbox.x -= this.vel.x;
                this.hurtbox.y -= this.vel.y;
                this.pos.x -= this.vel.x;
                this.pos.y -= this.vel.y;
                this.stop();
            }
        }
    }

    public void update() {

        this.move();
        
        this.wallCollisions();
        
        this.mouthMove();

    }

}
