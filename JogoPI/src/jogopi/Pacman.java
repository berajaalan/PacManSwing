package jogopi;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Pacman {

    int x = 0;
    int y = 0;
    int xa = 1;
    int ya = 1;
    char dir = 'd';
    int arcDelta = 0, arcSize = 270;
    Rectangle hurtbox;
    boolean fecha = true;
    Jogo jogo;

    public Pacman(Jogo jogo) {
        this.jogo = jogo;
    }

    public void paint(Graphics2D g) {
        g.setColor(Color.yellow);
        hurtbox = new Rectangle(x, y, 40, 40);
        switch (dir) {
            case 'u':
                g.fillArc(x, y, 40, 40, 135 - arcDelta, arcSize);
                break;
            case 'd':
                g.fillArc(x, y, 40, 40, 315 - arcDelta, arcSize);
                break;
            case 'l':
                g.fillArc(x, y, 40, 40, 225 - arcDelta, arcSize);
                break;
            case 'r':
                g.fillArc(x, y, 40, 40, 45 - arcDelta, arcSize);
                break;
        }
    }

    private void stop() {
        this.xa = 0;
        this.ya = 0;
    }

    private void walk(int x, int y) {
        this.xa = x;
        this.ya = y;
    }

    public void move() {

        switch (dir) {
            case 'u':
                if (y + ya <= 0) {
                    this.stop();
                } else {
                    this.walk(0, -1);
                }
                break;
            case 'd':
                if (y + ya >= jogo.getHeight() - 40) {
                    this.stop();
                } else {
                    this.walk(0, 1);
                }
                break;
            case 'l':
                if (x + xa <= 0) {
                    this.stop();
                } else {
                    this.walk(-1, 0);
                }
                break;
            case 'r':
                if (x + xa >= jogo.getWidth() - 40) {
                    this.stop();
                } else {
                    this.walk(1, 0);
                }
                break;
        }

        x += xa;
        y += ya;
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

    public void update() {

        this.move();
        
        this.mouthMove();

    }

}
