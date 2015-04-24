package gameclient;

import gameclient.ui.GameClientModel;
import gamecore.GameFieldCoordinates;
import gamecore.GameResult;
import gamecore.IGameClient;
import java.rmi.RemoteException;
import java.util.Arrays;

public class GameServiceClient implements IGameClient {
    public GameServiceClient(GameClientModel model) {
        this.model = model;
    }

    @Override
    public void RefreshPlayersList(String[] players) throws RemoteException {
        if (players.length <= 1) {
            return;
        }

        String[] otherPlayers = new String[players.length - 1];
        int otherPlayersCount = 0;
        for (String playerName : players) {
            if (!playerName.equals(model.getPlayerName())) {
                otherPlayers[otherPlayersCount] = playerName;
                otherPlayersCount += 1;
            }
        }

        model.setOtherPlayersNamesList(otherPlayers);
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

    private final GameClientModel model;
}
