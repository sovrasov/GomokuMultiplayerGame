package gameclient;

import gamecore.GameFieldCoordinates;
import gamecore.GameResult;
import gamecore.IGameClient;
import java.rmi.RemoteException;

public class GameClient implements IGameClient {

    @Override
    public void RefreshPlayersList(String[] players) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean SendGameRequest(String senderName) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void OnRequestAnswered(boolean isAccepted) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void OnGameFinished(GameResult result) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void OnRivalMoved(GameFieldCoordinates coordinates) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
