package gamecore;

import java.util.UUID;

public class Player {

    public Player() {
        this.name = null;
        this.ID = null;
    }
    public Player(UUID _ID, String _name)
    {
        ID = _ID;
        name = _name;
    }
    
    public String getName() {
        return name;
    }

    public UUID getID() {
        return ID;
    }
    
    final private String name;
    final private UUID ID;
}
