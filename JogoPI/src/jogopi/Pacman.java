package jogopi;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class Pacman {

    int x = 0;
    int y = 0;
    int xa = 1;
    int ya = 1;
    char dir = 'd';
    Ellipse2D hurtbox;
    boolean mapcol;
    Jogo jogo;

    public Pacman(Jogo jogo) {
        this.jogo = jogo;
    }

    public void paint(Graphics2D g) {
        g.setColor(Color.yellow);
        hurtbox = new Ellipse2D.Double(x,y,40,40);
        g.fill(hurtbox);
    }
    
    private void stop(){
        this.xa = 0;
        this.ya = 0;
    }
    
    private void walk(int x, int y){
        this.xa = x;
        this.ya = y;
    }

    public void move() {
        
        switch (dir) {
            case 'u':
                if (y + ya <= 0 || mapcol)
                    this.stop();
                else
                    this.walk(0,-1);
                break;
            case 'd':
                if (y + ya >= jogo.getHeight() - 40 || mapcol)
                    this.stop();
                else
                    this.walk(0,1);
                break;
            case 'l':
                if (x + xa <= 0 || mapcol)
                    this.stop();
                else
                    this.walk(-1,0);
                break;
            case 'r':
                if (x + xa >= jogo.getWidth() - 40 || mapcol)
                    this.stop();
                else
                    this.walk(1,0);
                break;
        }
        
        x += xa;
        y += ya;
    }

}
