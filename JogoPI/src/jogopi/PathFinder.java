package jogopi;

import java.awt.Point;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;

public class PathFinder {

    Jogo jogo;
    String arq_lvl_1 = "txt\\map1.txt";
    BufferedReader br;
    ArrayList<Node> labirinto;

    public PathFinder(Jogo jogo) {
        this.jogo = jogo;
        labirinto = new ArrayList<Node>();
        try {
            this.br = new BufferedReader(new FileReader(arq_lvl_1));
            this.criarLabirinto();
            
            Point temp = acharPacMan();
            
            System.out.println(temp.x + "|" + temp.y);
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
    public void criarLabirinto() throws IOException{
        for (int i = 0; i < 31; i++) {
            String line[] = br.readLine().split(" ");
            for (int j = 0; j < line.length; j++) {
                int h = Integer.parseInt(line[j]);
                switch (h){
                    default:
                        labirinto.add(new Node(new Point(j,i), false));
                        
                        if (j == 0)
                            labirinto.add(new Node(new Point(j-1,i), false));
                        
                        if (j == line.length-1)
                            labirinto.add(new Node(new Point(j+1,i), false));
                            
                        break;
                    case 1:
                        labirinto.add(new Node(new Point(j,i), true));
                        break;
                    case 3:
                        break;
                    
                }
            }
        }
    }
    
    public Point acharFantasma(Fantasma f){
        
        int x = f.pos.x/22,
            y = f.pos.y/22;
        
        return new Point(x, y);
        
    }
    
    public Point acharPacMan(){
        
        int x = jogo.pac.pos.x/22,
            y = jogo.pac.pos.y/22;
        
        return new Point(x, y);
        
    }
    
    public Point findNextPosition(Point ini, Point fim){
        
        try {
            
            Point h;
            
            PriorityQueue <Node> f = new PriorityQueue<Node>();
            
        } catch (Exception e) {
            System.err.println("Path not found");
        }
        
        return null;
    }
    
}
