package gamecore;

public class GameRequest {
    
    public GameRequest()
    {
        
    }
    public GameRequest(Player _sender, Player _receiver)
    {
        sender = _sender;
        receiver = _receiver;
    }
    
    public Player getSender() {
        return sender;
    }

    public Player getReceiver() {
        return receiver;
    }
    
    private Player sender;
    private Player receiver;
}