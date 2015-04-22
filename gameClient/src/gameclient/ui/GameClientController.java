package gameclient.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameClientController {
    public GameClientController() {}

    public GameClientController(ConnectionFrame mainView) {
        this.mainView = mainView;
    }

    public void setAssociatedView(ConnectionFrame mainView) {
        this.mainView = mainView;
    }

    public class LoginListener implements ActionListener {
        public LoginListener(RegistrationPanel panel) {
            this.registrationPanel = panel;
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            String login = registrationPanel.getLoginTextFieldContents();
            System.out.println("Trying to login as " + login);
            mainView.switchPanel("LobbyPanel");
        }

        private final RegistrationPanel registrationPanel;
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

    private ConnectionFrame mainView;
}
