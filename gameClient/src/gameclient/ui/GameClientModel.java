package gameclient.ui;

import gameclient.GameServiceClient;
import gamecore.GlobalConstants;
import gamecore.IGameClient;
import gamecore.IGameServer;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Observable;
import java.util.UUID;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class GameClientModel extends Observable {
    public GameClientModel() {
        controller = null;

        serviceClient = new GameServiceClient(this);
        playerId = null;
        playerName = null;
        otherPlayersNamesList = new String[0];
    }

    public void setController(GameClientController controller) {
        this.controller = controller;
    }

    public void connectToServer(String playerName) {
        try {
            Registry registry = LocateRegistry.getRegistry(
                null, GlobalConstants.serverPort);
            currentServer = (IGameServer)registry.lookup(
                GlobalConstants.serviceName);
            clientStub = (IGameClient)UnicastRemoteObject.exportObject(
                serviceClient, 0);

            this.playerName = playerName;
            playerId = currentServer.RegisterClient(clientStub, playerName);
            setChanged();
            notifyObservers();
        } catch (RemoteException | NotBoundException e) {
            System.out.println ("Error occured: " + e.getMessage());
            System.exit (1);
        }
    }

    public void disconnectFromServer() {
        try {
            currentServer.DropClient(playerId);
        } catch (RemoteException ex) {
            System.out.println("Problem with disconnection: " +
                ex.getMessage());
        }
    }

    public String getPlayerName() {
        return playerName;
    }

    public boolean isCurrentlyLoggedIn() {
        return playerId != null;
    }

    public void setOtherPlayersNamesList(String[] playerNamesList) {
        otherPlayersNamesList = playerNamesList;
        setChanged();
        notifyObservers();
    }

    public String[] getOtherPlayersNamesList() {
        return otherPlayersNamesList;
    }

    public void invitePlayer(String opponentName) {
        try {
            inviteeName = opponentName;
            currentServer.SendRequestForGame(playerId, inviteeName);
        } catch (RemoteException ex) {
            System.out.println(
                "Error while inviting opponent: " + ex.getMessage());
        }
    }

    public void startGameWith(String opponent) {
        controller.switchToGameRoundPanel();
    }

    public void startGameWithInvitee() {
        startGameWith(inviteeName);
    }

    public void handleInvitationRejection() {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                JOptionPane.showMessageDialog(null,
                inviteeName + " rejected your invitation.",
                "Invitation rejection", JOptionPane.INFORMATION_MESSAGE);
            }
        });

    }


    private GameClientController controller;

    private final GameServiceClient serviceClient;
    private IGameServer currentServer;
    private IGameClient clientStub;
    private UUID playerId;
    private String playerName;
    private String inviteeName;
    private String[] otherPlayersNamesList;
}
