package jogopi;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Fantasma {

    Point pos = new Point(22, 22);
    Point vel;
    Point lastVel;
    String imgFolder;
    String dir = "up";
    BufferedImage currentImageFrame;
    boolean frame = true;
    int animTimer;
    Jogo jogo;

    public Fantasma(Jogo jogo, String imgFolder) {
        this.jogo = jogo;
        this.imgFolder = imgFolder;

        this.changeImage("up_1");
    }

    public void paint(Graphics2D g) {

        if (animTimer > 20) {
            if (frame) {
                changeImage(dir + "_1");
            } else {
                changeImage(dir + "_2");
            }
            frame = !frame;
            animTimer = 0;
        }
        
        g.drawImage(currentImageFrame, pos.x, pos.y, jogo);

        animTimer++;

    }

    public void update() {

    }

    public void changeImage(String name) {
        try {

            String fileName = imgFolder + "\\" + name;

            currentImageFrame = ImageIO.read(new File(fileName + ".png"));
        } catch (Exception e) {
            System.err.println(e);
        }

    }
    
}
