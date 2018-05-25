package jogopi;

import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Level {

    Jogo jogo;
    String arq_lvl_1 = "\\txt\\map1.txt";
    BufferedReader br;
    
    public Level(Jogo jogo) throws FileNotFoundException, IOException {
        this.jogo = jogo;
        String l = System.getProperty("user.dir")+arq_lvl_1;
        this.br = new BufferedReader(new FileReader(l));
        this.lvl_1();
    }
    
    public void lvl_1() throws IOException{
        int x = 0, y = 0;
        for (int i = 0; i < 31; i++) {
            String line[] = br.readLine().split(" ");
            for (int j = 0; j < line.length; j++) {
                System.out.print(line[j] + " ");
                int h = Integer.parseInt(line[j]);
                switch (h){
                    case 0:
                        jogo.pel.add(new Pellets(jogo,x+8,y+8));
                        x += 22;
                        break;
                    case 1:
                        jogo.walls.add(new Rectangle(x,y,22,22));
                        x += 22;
                        break;
                    default:
                        x += 22;
                        break;
                }
            }
            x = 0;
            y += 22;
            System.out.println("");
        }
    }
    
}
