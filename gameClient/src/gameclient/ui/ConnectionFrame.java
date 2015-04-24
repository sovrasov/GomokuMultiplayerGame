package gameclient.ui;

import javax.swing.JFrame;
import java.awt.CardLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ConnectionFrame extends GameClientView<JFrame> {
    public ConnectionFrame(GameClientController controller) {
        super(new JFrame(), controller);
        setupFrame();
        panes = new CardLayout();
        getComponent().setLayout(panes);
        getComponent().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                controller.getModel().disconnectFromServer();
                System.exit(0);
            }
        });

        registrationPanel = new RegistrationPanel(getController());
        getComponent().add("RegistrationPanel",
            registrationPanel.getComponent());

        lobbyPanel = new LobbyPanel(getController());
        getComponent().add("LobbyPanel", lobbyPanel.getComponent());

        invitationPanel = new InvitationPanel(getController());
        getComponent().add("InvitationPanel", invitationPanel.getComponent());
    }

    public void show() {
        getComponent().setVisible(true);
    }

    public void switchPanel(String panelName) {
        panes.show(getComponent().getContentPane(), panelName);
    }


    private void setupFrame() {
        getComponent().setSize(400, 300);
        getComponent().setTitle("Connect to Gomoku server");
    }

    private final CardLayout panes;
    private final RegistrationPanel registrationPanel;
    private final LobbyPanel lobbyPanel;
    private final InvitationPanel invitationPanel;
}
