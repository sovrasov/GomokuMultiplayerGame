package gameclient.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameClientController {

    public class LoginListener implements ActionListener {
        public LoginListener(RegistrationPanel panel) {
            this.registrationPanel = panel;
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            String login = registrationPanel.getLoginTextFieldContents();
            System.out.println("Trying to login as " + login);
        }


        private final RegistrationPanel registrationPanel;
    }
}
