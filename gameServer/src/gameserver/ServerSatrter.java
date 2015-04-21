package gameserver;

import gamecore.GlobalConstants;
import gamecore.IGameServer;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerSatrter {

    public static void main(String[] args) {
       
       GameServer server = new GameServer();
       
       try {
            IGameServer stub = (IGameServer)UnicastRemoteObject.exportObject(server, 0);
 
            Registry registry = LocateRegistry.createRegistry(GlobalConstants.serverPort);
            registry.bind(GlobalConstants.serviceName, stub);
 
        } catch (Exception e) {
            System.out.println ("Error occured: " + e.getMessage());
            System.exit (1);
        }
    }
 }
