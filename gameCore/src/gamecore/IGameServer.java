package gamecore;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;

public interface IGameServer extends Remote {
    
    public UUID RegisterClient(IGameClient clientCallback, String playerName) throws RemoteException;
    public void DropClient(UUID playerID) throws RemoteException;
    public void SendRequestForGame(UUID senderID, String rivalName) throws RemoteException;
    String[] GetPlayersList(UUID senderID) throws RemoteException;
    void MakeMove(UUID playerID, int i, int j) throws RemoteException;
    
}