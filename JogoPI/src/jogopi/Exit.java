package jogopi;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Exit implements Botao{
    
    Jogo jogo;

    public Exit(Jogo jogo) {
        this.jogo = jogo;
    }
    
    public void paint(Graphics2D g) {
        Font fnt = new Font("arial", 1, 30);
        
        g.setFont(fnt);
        g.setColor(Color.white);
        g.drawString("Exit", 265, 480);
        g.drawRect(180, 450, 227, 40);
    }
    
    @Override
    public void action() {
        System.exit(0);
    }
    
}
