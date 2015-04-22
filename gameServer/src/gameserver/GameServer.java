package gameserver;

import gamecore.*;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.UUID;

public class GameServer implements IGameServer {
    
    public GameServer()
    {
        playersList = new HashMap<>();
        games = new LinkedList<>();
    }

    @Override
    public UUID RegisterClient(IGameClient clientCallback, String playerName)
            throws RemoteException {
        
        if(playerName != null && clientCallback != null
                && CheckIsUsernameUnique(playerName))
        {
            Player newPlayer = new Player(UUID.randomUUID(), playerName, clientCallback);
            playersList.put(newPlayer.getID(), newPlayer);
            return newPlayer.getID();
        }
        else
            return null;
    }

    @Override
    public void DropClient(UUID playerID) throws RemoteException {
        if( playersList.containsKey(playerID))
        {
            Iterator<GomokuGame> i = games.iterator();
            
            while(i.hasNext())
            {
                GomokuGame game = i.next();
                if(game.hasPlayer(playerID))
                {
                    UUID anotherPlayerID;
                    if(game.getFirstPlayer().getID() != playerID)
                    {
                        anotherPlayerID = game.getFirstPlayer().getID();
                        game.getFirstPlayer().getCallback().OnGameFinished(GameResult.RIVAL_LEAVED);
                    }
                    else
                    {
                        anotherPlayerID = game.getSecondPlayer().getID();
                        game.getSecondPlayer().getCallback().OnGameFinished(GameResult.RIVAL_LEAVED);
                    }
                    games.remove(game);
                    
                    Player anotherPlayer = playersList.remove(anotherPlayerID);
                    anotherPlayer.isBusy = false;
                    playersList.put(anotherPlayer.getID(), anotherPlayer);
                    
                    break;
                }
            }  
            
            playersList.remove(playerID);
        }
        else
            throw new NoSuchElementException("ID is not found.");
    }

    @Override
    public void SendRequestForGame(UUID senderID, String rivalName) throws RemoteException {
        
        if( playersList.containsKey(senderID))
        {
            UUID rivalID = FindPlayerByName(rivalName);
            if(rivalID != null)
            {
                Player rival = playersList.get(rivalID);
                Player   sender = playersList.get(senderID);
                if(rival.isBusy)
                    sender.getCallback().OnRequestAnswered(false);
                else
                {
                    boolean answer = rival.getCallback().OnSentGameRequest(sender.getName());
                    sender.getCallback().OnRequestAnswered(answer);
                    if(answer == true)
                    {
                        sender.isBusy = rival.isBusy = true;
                        GomokuGame newGame = new GomokuGame(sender, rival);
                        games.add(newGame);
                    }
                }
            }
        }
        else
            throw new NoSuchElementException("ID is not found.");
    }

    @Override
    public String[] GetPlayersList(UUID senderID) throws RemoteException {
        
        
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void MakeMove(UUID playerID, GameFieldCoordinates coordinates) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    } 
    
    private boolean CheckIsUsernameUnique(String uName)
    {
        if(FindPlayerByName(uName) != null)
            return true;
        else
            return false;
    }
    private UUID FindPlayerByName(String name)
    {
        
        for (Player value : playersList.values()) {
           if(value.getName().equals(name))
               return value.getID();
        }
        
        return null;
    }
    
    
    private final HashMap<UUID, Player> playersList;
    private final LinkedList<GomokuGame> games;
}
