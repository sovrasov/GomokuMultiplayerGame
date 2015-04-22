package gamecore;

import java.util.UUID;

public class Player {

    public Player() {
        this.name = null;
        this.ID = null;
        this.callback = null;
        this.isBusy = false;
    }
    public Player(UUID _ID, String _name, IGameClient _callback)
    {
        ID = _ID;
        name = _name;
        callback = _callback;
        isBusy = false;
    }
    
    public String getName() {
        return name;
    }

    public UUID getID() {
        return ID;
    }
    
    public IGameClient getCallback() {
        return callback;
    }    
    
    public boolean isBusy;
    
    final private IGameClient callback;
    final private String name;
    final private UUID ID;

}