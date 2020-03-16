package jogopi;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

public class Pacman {

    Point pos = new Point(297, 506);
    Point vel = new Point(0, -1);
    String dir = "l", proxDir;
    int arcDelta = 0, arcSize = 270;
    Rectangle hurtbox = new Rectangle(pos.x, pos.y, 22, 22);
    boolean fecha = true;
    Jogo jogo;

    public Pacman(Jogo jogo) {
        this.jogo = jogo;

    }

    public void paint(Graphics2D g) {
        g.setColor(Color.yellow);
        switch (proxDir) {
            case "u":
                g.fillArc(pos.x, pos.y, 22, 22, 135 - arcDelta, arcSize);
                break;
            case "d":
                g.fillArc(pos.x, pos.y, 22, 22, 315 - arcDelta, arcSize);
                break;
            case "l":
                g.fillArc(pos.x, pos.y, 22, 22, 225 - arcDelta, arcSize);
                break;
            case "r":
                g.fillArc(pos.x, pos.y, 22, 22, 45 - arcDelta, arcSize);
                break;
        }
//        g.setColor(Color.gray);
//        g.draw(hurtbox);
    }

    public void move() {
        switch (dir) {
            case "u":
                vel.setLocation(0, -1);
                this.proxDir = this.dir;
                break;
            case "d":
                vel.setLocation(0, 1);
                this.proxDir = this.dir;
                break;
            case "l":
                vel.setLocation(-1, 0);
                this.proxDir = this.dir;
                break;
            case "r":
                vel.setLocation(1, 0);
                this.proxDir = this.dir;
                break;
        }

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

    private boolean changeDir() {

        for (Rectangle rect : jogo.walls) {
            switch (dir) {
                case "u":
                    if (rect.intersects(new Rectangle(pos.x, pos.y - 1, 22, 22))) {
                        return false;
                    }
                    break;
                case "d":
                    if (rect.intersects(new Rectangle(pos.x, pos.y + 1, 22, 22))) {
                        return false;
                    }
                    break;
                case "l":
                    if (rect.intersects(new Rectangle(pos.x - 1, pos.y, 22, 22))) {
                        return false;
                    }
                    break;
                case "r":
                    if (rect.intersects(new Rectangle(pos.x + 1, pos.y, 22, 22))) {
                        return false;
                    }
                    break;
            }
        }
        return true;
    }

    public void update() {

        if (this.changeDir()) {
            this.move();
        }

        if (pos.x <= -22) {
            pos.x = jogo.getWidth();
            hurtbox.x = jogo.getWidth();
        }

        else if (pos.x >= jogo.getWidth()) {
            pos.x = -22;
            hurtbox.x = -22;
        }

        hurtbox.x += vel.x;
        hurtbox.y += vel.y;

        pos.x += vel.x;
        pos.y += vel.y;

        this.wallCollisions();
        
        this.pelletsCollisions();
        
        this.mouthMove();

    }

}
