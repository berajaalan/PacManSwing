package pacman;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class ScoreBoard implements Botao{
    
    Jogo jogo;

    public ScoreBoard(Jogo jogo) {
        this.jogo = jogo;
    }
    
    public void paint(Graphics2D g) {
        Font fnt = new Font("arial", 1, 30);
        
        g.setFont(fnt);
        g.setColor(Color.white);
        g.drawString("Leaderboard", 203, 390);
        g.drawRect(180, 360, 227, 40);

    }
    
    @Override
    public void action() {
        
    }
    
}
