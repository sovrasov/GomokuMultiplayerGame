package gamecore;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IGameClient extends Remote {
    
    void RefreshPlayersList(String[] players) throws RemoteException;
    boolean SendGameRequest(String senderName) throws RemoteException;
    
    void OnRequestAnswered(boolean isAccepted) throws RemoteException;
    void OnGameFinished(GameResult result) throws RemoteException;
    void OnRivalMoved(GameFieldCoordinates coordinates) throws RemoteException;
    
}