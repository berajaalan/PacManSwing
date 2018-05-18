package jogopi;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Play implements Botao{
    
    Jogo jogo;

    public Play(Jogo jogo){
        this.jogo = jogo;
    }
    
    public void paint(Graphics2D g){
        Font fnt = new Font("arial", 1, 30);
        
        g.setFont(fnt);
        g.setColor(Color.white);
        g.drawString("Play", 360, 300);
        g.drawRect(275, 270, 227, 40);
    }
    
    @Override
    public void action(){
        jogo.gameState = "game";
    }
    
    
    
}
