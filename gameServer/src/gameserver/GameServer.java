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
        activeRequests = new HashMap<>();
    }

    @Override
    public UUID RegisterClient(IGameClient clientCallback, String playerName)
            throws RemoteException {
        
        if(playerName != null && clientCallback != null
                && CheckIsUsernameUnique(playerName))
        {
            Player newPlayer = new Player(UUID.randomUUID(), playerName, clientCallback);
            playersList.put(newPlayer.getID(), newPlayer);
            BroadcastPlayersList();
            System.out.println("\nPlayer joined: " + playerName);
            return newPlayer.getID();
        }
        else
            return null;
    }

    @Override
    public void DropClient(UUID playerID) throws RemoteException {
        if( playersList.containsKey(playerID))
        {
            for(GameRequest value : activeRequests.values())
            {
                if(value.getSender().getID() == playerID ||
                        value.getReceiver().getID() == playerID)
                {
                    activeRequests.remove(value.getReceiver().getID());
                    break;
                }
            }
            
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
                    
                    ChangePlayerBusyStatus(anotherPlayerID);
                    
                    break;
                }
            }  
            System.out.println("\nPlayer leaved: " + playersList.get(playerID).getName());
            playersList.remove(playerID);
            BroadcastPlayersList();
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
                Player sender = playersList.get(senderID);
                if(rival.isBusy)
                    sender.getCallback().OnRequestAnswered(false);
                else
                {
                    //boolean answer = rival.getCallback().OnSentGameRequest(sender.getName());
                    rival.getCallback().OnSentGameRequest(sender.getName());
                    activeRequests.put(rivalID, new GameRequest(sender, rival));
                    /*sender.getCallback().OnRequestAnswered(answer);
                    if(answer == true)
                    {
                        //sender.isBusy = rival.isBusy = true;
                        ChangePlayerBusyStatus(senderID);
                        ChangePlayerBusyStatus(rivalID);
                        GomokuGame newGame = new GomokuGame(sender, rival);
                        games.add(newGame);
                        System.out.println("\nGame started. Players: " + 
                                sender.getName() + " and " + rivalName);
                    }
                            */
                }
            }
        }
        else
            throw new NoSuchElementException("ID is not found.");
    }

    @Override
    public String[] GetPlayersList(UUID senderID) throws RemoteException {
        
        String[] usernamesArray = new String[playersList.size()];
        
        int i=0;
        for (Player value : playersList.values()) {
           usernamesArray[i] = value.getName();
           i++;
        }
        return usernamesArray;
    }
    
    @Override
    public void AnswerGameRequest(UUID receiverID, boolean answer) throws RemoteException {
        
        GameRequest answeredRequest = activeRequests.get(receiverID);
        if(answeredRequest != null)
        {
            answeredRequest.getSender().getCallback().OnRequestAnswered(answer);
            if(answer)
            {
                ChangePlayerBusyStatus(answeredRequest.getSender().getID());
                ChangePlayerBusyStatus(answeredRequest.getReceiver().getID());
                GomokuGame newGame = new GomokuGame(answeredRequest.getSender(),
                        answeredRequest.getReceiver());
                games.add(newGame);
                System.out.println("\nGame started. Players: " + 
                                answeredRequest.getSender().getName() + " and "
                        + answeredRequest.getReceiver());
            }
            activeRequests.remove(receiverID);
        }
        else
            throw new NoSuchElementException("GameRequest is not found,"
                    + " probably sender leaved game.");
    }

    @Override
    public void MakeMove(UUID playerID, GameFieldCoordinates coordinates) throws RemoteException {
        
        Iterator<GomokuGame> i = games.iterator();
        boolean isGameFound = false;
        
        while(i.hasNext())
        {
            GomokuGame game = i.next();
            if(game.hasPlayer(playerID))
            {
                isGameFound = true;
                game.MakeMove(coordinates, playerID);
                if(game.getFirstPlayer().getID() == playerID)
                    game.getSecondPlayer().getCallback().OnRivalMoved(coordinates);
                else
                    game.getFirstPlayer().getCallback().OnRivalMoved(coordinates);
                
                switch(game.CheckGameStatus()) 
                {
                    case firstWins:
                        game.getFirstPlayer().getCallback().OnGameFinished(GameResult.WIN);
                        game.getSecondPlayer().getCallback().OnGameFinished(GameResult.LOOSE);
                        games.remove(game);
                        ChangePlayerBusyStatus(game.getFirstPlayer().getID());
                        ChangePlayerBusyStatus(game.getSecondPlayer().getID());
                        System.out.println("\nGame finished. Player " + game.getFirstPlayer().getName()
                        + " wins.");
                        break;
                    case secondWins:
                        game.getFirstPlayer().getCallback().OnGameFinished(GameResult.LOOSE);
                        game.getSecondPlayer().getCallback().OnGameFinished(GameResult.WIN);
                        games.remove(game);
                        ChangePlayerBusyStatus(game.getFirstPlayer().getID());
                        ChangePlayerBusyStatus(game.getSecondPlayer().getID());
                        System.out.println("\nGame finished. Player " + game.getSecondPlayer().getName()
                        + " wins.");
                        break;
                    case inProcess:
                        break;
                }
            }
            break;
        }
        if(!isGameFound)
            throw new NoSuchElementException("Game is not found.");
    } 
    
    private boolean CheckIsUsernameUnique(String uName)
    {
        if(FindPlayerByName(uName) == null)
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
    private void ChangePlayerBusyStatus(UUID playerID)
    {
        Player tmpPlayer = playersList.remove(playerID);
        if(tmpPlayer != null)
        {
            tmpPlayer.isBusy = !tmpPlayer.isBusy;
            playersList.put(tmpPlayer.getID(), tmpPlayer);
        }
    }
    private void BroadcastPlayersList() throws RemoteException
    {
        for (Player value : playersList.values())
           value.getCallback().RefreshPlayersList(this.GetPlayersList(null));
    }
    
    private final HashMap<UUID, GameRequest> activeRequests;
    private final HashMap<UUID, Player> playersList;
    private final LinkedList<GomokuGame> games;

}