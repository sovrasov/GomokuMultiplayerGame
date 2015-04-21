package gamecore;

import java.io.Serializable;

public class GameFieldCoordinates implements Serializable{
    
    public GameFieldCoordinates()
    {
        x = y = 0;
    }
    public GameFieldCoordinates(int _x, int _y)
    {
        x = _x;
        y = _y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    private int x;
    private int y;
}
