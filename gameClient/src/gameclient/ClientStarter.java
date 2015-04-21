package gameclient;

import gamecore.GlobalConstants;
import gamecore.IGameClient;
import gamecore.IGameServer;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.UUID;

public class ClientStarter {
    public static void main(String[] args)
    {
        GameClient client = new GameClient();
 
        try {
            Registry registry = LocateRegistry.getRegistry(null, GlobalConstants.serverPort);
            IGameServer server = (IGameServer)registry.lookup(GlobalConstants.serviceName);
 
            IGameClient stub = (IGameClient)UnicastRemoteObject.exportObject(client, 0);
            
            ////////obtain id, need unique player name
            UUID myID = server.RegisterClient(stub, "player1");
            
            System.out.println("I got this shit from server: " + myID.toString());
            
            ///////finishing work with server
            server.DropClient(myID);
            
        } catch (Exception e) {
            System.out.println ("Error occured: " + e.getMessage());
            System.exit (1);
        }
    }
    
}
