package gameclient;

import gameclient.ui.GameClientModel;
import gamecore.GameFieldCoordinates;
import gamecore.GameResult;
import gamecore.IGameClient;
import java.rmi.RemoteException;
import javax.swing.JOptionPane;

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
        boolean invitationAccepted = JOptionPane.showConfirmDialog(null,
            senderName + " invited you to play the game. Accept?",
            "Game invitation", JOptionPane.YES_NO_OPTION) ==
            JOptionPane.YES_OPTION;

        if (invitationAccepted) {
            model.startGameWith(senderName);
        }

        return invitationAccepted;
    }

    @Override
    public void OnRequestAnswered(boolean isAccepted) throws RemoteException {
        // if accepted start game and wait for move of rival, else say WTF man?
        if (isAccepted) {
            model.startGameWithInvitee();
        } else {
            model.handleInvitationRejection();
        }
    }

    @Override
    public void OnGameFinished(GameResult result) throws RemoteException {
        //finish game, show result
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void OnRivalMoved(GameFieldCoordinates coordinates) throws RemoteException {
        //visualize rival's move, enable user to move
        model.addOpponentsPiece(coordinates);
        model.transferTurn();
    }

    private final GameClientModel model;
}