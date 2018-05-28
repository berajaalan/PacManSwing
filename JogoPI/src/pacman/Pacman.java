package pacman;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Pacman {

    Point pos = new Point(297, 374);
    Point vel = new Point(0, 0);
    char dir = 'l', proxDir = 'l';
    int arcDelta = 0, arcSize = 270;
    Rectangle hurtbox = new Rectangle(pos.x, pos.y, 22, 22);
    boolean fecha = true;
    Jogo jogo;

    public Pacman(Jogo jogo) {
        this.jogo = jogo;

    }

    public void paint(Graphics2D g) {
        g.setColor(Color.yellow);
        switch (dir) {
            case 'u':
                g.fillArc(pos.x, pos.y, 22, 22, 135 - arcDelta, arcSize);
                break;
            case 'd':
                g.fillArc(pos.x, pos.y, 22, 22, 315 - arcDelta, arcSize);
                break;
            case 'l':
                g.fillArc(pos.x, pos.y, 22, 22, 225 - arcDelta, arcSize);
                break;
            case 'r':
                g.fillArc(pos.x, pos.y, 22, 22, 45 - arcDelta, arcSize);
                break;
        }
        g.setColor(Color.gray);
        g.draw(hurtbox);
//        Font fnt = new Font("arial", 1, 20);
//        g.setFont(fnt);
//        g.setColor(Color.ORANGE);
//        g.drawString("DIR: " + dir + " | PROXDIR: " + proxDir, 10, 704);
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
                this.walk(0, -1);
                break;
            case 'd':
                this.walk(0, 1);
                break;
            case 'l':
                if (pos.x <= -22) {
                    pos.x = jogo.getWidth();
                    hurtbox.x = jogo.getWidth();
                } else {
                    this.walk(-1, 0);
                }
                break;
            case 'r':
                if (pos.x + vel.x >= jogo.getWidth()) {
                    pos.x = -22;
                    hurtbox.x = -22;
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
        } else {
            arcDelta -= 2;
            arcSize -= 4;
            if (arcSize <= 270) {
                fecha = true;
            }
        }
    }

    private void wallCollisions() {
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

    private void pelletsCollisions() {
        for (Pellets p : jogo.pel) {
            double x = (this.pos.x + 11) - (p.pos.x + 5);
            double y = (this.pos.y + 11) - (p.pos.y + 5);
            double dist = Math.sqrt((x * x) + (y * y));
            if (dist <= 5) {
                jogo.pel.remove(p);
                break;
            }
        }
    }

    private void changeDir() {
        switch (proxDir) {
            case 'u':
                this.hurtbox.y -= 1;
                for (Rectangle rect : jogo.walls) {
                    if (!this.hurtbox.intersects(rect)) {
                        dir = 'u';
                    }
                }
                this.hurtbox.y += 1;
                break;
            case 'd':
                this.hurtbox.y += 1;
                for (Rectangle rect : jogo.walls) {
                    if (!this.hurtbox.intersects(rect)) {
                        dir = 'd';
                    }
                }
                this.hurtbox.y -= 1;
                break;
            case 'l':
                this.hurtbox.x -= 1;
                for (Rectangle rect : jogo.walls) {
                    if (!this.hurtbox.intersects(rect)) {
                        dir = 'l';
                    }
                }
                this.hurtbox.x += 1;
                break;
            case 'r':
                this.hurtbox.x += 1;
                for (Rectangle rect : jogo.walls) {
                    if (!this.hurtbox.intersects(rect)) {
                        dir = 'r';
                    }
                }
                this.hurtbox.x -= 1;
                break;
        }
    }

    public void update() {

        this.changeDir();

        this.move();

        this.wallCollisions();

        this.pelletsCollisions();

        this.mouthMove();

    }

}
