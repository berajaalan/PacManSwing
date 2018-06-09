package jogopi;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class HUD {
    
    Jogo jogo;
    int score = 0;
    
    
    public HUD(Jogo jogo){
        this.jogo = jogo;
    }
    
    public void paint(Graphics2D g){
        
        Font fnt = new Font("arial", 1, 15);
        g.setFont(fnt);
        g.setColor(Color.white);
        g.drawString("Score:", 10, 700);
        if (score < 10) {
            g.drawString("0000000"+score, 60, 715);        
        } else if (score < 100) {
            g.drawString("000000"+score, 60, 715);        
        }
        g.setColor(Color.yellow);
        g.fillArc(580, 695, 22, 22, 45, 270);
        g.fillArc(550, 695, 22, 22, 45, 270);
        g.fillArc(520, 695, 22, 22, 45, 270);
    }
    
    
    
    
}
