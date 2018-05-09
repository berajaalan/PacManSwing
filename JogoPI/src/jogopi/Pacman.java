package jogopi;

import java.awt.Graphics2D;

public class Pacman {

    int x = 1;
    int y = 1;
    int xa = 1;
    int ya = 1;
    char dir = 'd';
    Jogo jogo;

    public Pacman(Jogo jogo) {
        this.jogo = jogo;
    }

    public void paint(Graphics2D g) {
        g.fillOval(x, y, 40, 40);
    }

    public void move() {
        switch (dir) {
            case 'u':
                if (y + ya <= 0) {
                    this.xa = 0;
                    this.ya = 0;
                }else{
                    this.xa = 0;
                    this.ya = -1;
                }
                break;
            case 'd':
                if (y + ya >= jogo.getHeight() - 40) {
                    this.xa = 0;
                    this.ya = 0;
                }else{
                    this.xa = 0;
                    this.ya = 1;
                }
                break;
            case 'l':
                if (x + xa <= 0) {
                    this.xa = 0;
                    this.ya = 0;
                }else{
                    this.xa = -1;
                    this.ya = 0;
                }
                break;
            case 'r':
                if (x + xa >= jogo.getWidth() - 40) {
                    this.xa = 0;
                    this.ya = 0;
                }else{
                    this.xa = 1;
                    this.ya = 0;
                }
                break;
        }
        x += xa;
        y += ya;
    }

}
