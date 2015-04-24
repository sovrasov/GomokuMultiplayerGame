package gameclient.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameClientController {
    public GameClientController(GameClientModel model) {
        this.model = model;
    }

    public GameClientController(ConnectionFrame mainView) {
        this.mainView = mainView;
    }

    public void setAssociatedView(ConnectionFrame mainView) {
        this.mainView = mainView;
    }

    public GameClientModel getModel() {
        return model;
    }

    public class LoginListener implements ActionListener {
        public LoginListener(RegistrationPanel panel) {
            this.registrationPanel = panel;
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            String playerName = registrationPanel.getLoginTextFieldContents();
            System.out.println("Trying to login as " + playerName);
            model.connectToServer(playerName);
            mainView.switchPanel("LobbyPanel");
        }

        private final RegistrationPanel registrationPanel;
    }

    public class InvitePlayerListener implements ActionListener {
        public InvitePlayerListener(LobbyPanel panel) {
            this.lobbyPanel = panel;
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            String opponentsName =
                lobbyPanel.getSelectedOpponentsName();

            model.invitePlayer(opponentsName);
        }

        private final LobbyPanel lobbyPanel;
    }

    public class RejectInvitationListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            System.out.println("Rejecting invitation");
            mainView.switchPanel("LobbyPanel");
        }
    }

    public class AcceptInvitationListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            System.out.println("Accepting invitation");
        }
    }

    private GameClientModel model;
    private ConnectionFrame mainView;
}
