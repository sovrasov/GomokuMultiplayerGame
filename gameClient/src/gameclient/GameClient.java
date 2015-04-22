package gameclient;

import gamecore.GameFieldCoordinates;
import gamecore.GameResult;
import gamecore.IGameClient;
import java.rmi.RemoteException;

public class GameClient implements IGameClient {

    @Override
    public void RefreshPlayersList(String[] players) throws RemoteException {
        //players contains current client name, so we need to delete it before showing
        //list to user
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean OnSentGameRequest(String senderName) throws RemoteException {
        
        // return accept or decline game
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void OnRequestAnswered(boolean isAccepted) throws RemoteException {
        // if accepted start game and wait for move of rival, else say WTF man?
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void OnGameFinished(GameResult result) throws RemoteException {
        //finish game, show result
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void OnRivalMoved(GameFieldCoordinates coordinates) throws RemoteException {
        //visualize rival's move, enable user to move
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
