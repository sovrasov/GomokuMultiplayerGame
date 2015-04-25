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
        String[] otherPlayers = model.filterOtherPlayers(players);

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
        model.finishGame(result);
    }

    @Override
    public void OnRivalMoved(GameFieldCoordinates coordinates) throws RemoteException {
        //visualize rival's move, enable user to move
        model.addOpponentsPiece(coordinates);
        model.transferTurn();
    }

    private final GameClientModel model;
}
