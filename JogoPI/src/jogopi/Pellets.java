package jogopi;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class Pellets {
    
    Jogo jogo;
    Point pos;
    
    public Pellets(Jogo jogo, int x, int y){
        this.jogo = jogo;
        this.pos = new Point(x,y);
    }
    
    public void paint(Graphics2D g){
        g.setColor(Color.white);
        g.fillOval(pos.x, pos.y, 10, 10);
    }
    
}
