package gameserver;

import gamecore.IGameClient;
import gamecore.IGameServer;
import java.rmi.RemoteException;
import java.util.UUID;

public class GameServer implements IGameServer {

    @Override
    public UUID RegisterClient(IGameClient clientCallback, String playerName) throws RemoteException {
        //if(playerName!=null && clientCallback!=null)
        //{
            return UUID.randomUUID();
        //}
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void DropClient(UUID playerID) throws RemoteException {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void SendRequestForGame(UUID senderID, String rivalName) throws RemoteException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[] GetPlayersList(UUID senderID) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void MakeMove(UUID playerID, int i, int j) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    } 
    
}
