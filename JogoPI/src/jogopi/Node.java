package jogopi;

import java.awt.Point;


public class Node {

    Point pos;
    boolean isWall;

    public Node(Point pos, boolean isWall) {
        this.pos = pos;
        this.isWall = isWall;
    }

    @Override
    public boolean equals(Object o) {
        
        Node obj = (Node) o;
        
        if (pos.equals(obj.pos) && isWall == obj.isWall) {
            return true;
        }
        
        return false;
    }
    
}
