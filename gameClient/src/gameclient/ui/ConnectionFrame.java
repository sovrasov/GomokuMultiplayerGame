package gameclient.ui;

import javax.swing.JFrame;
import java.awt.CardLayout;

public class ConnectionFrame extends JFrame {
    public ConnectionFrame(GameClientController controller) {
        this.controller = controller;
        setupFrame();

        panes = new CardLayout();
        this.setLayout(panes);
        registrationPanel = new RegistrationPanel(controller);
        this.add(registrationPanel);
    }

    private void setupFrame() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(400, 300);
        this.setTitle("Connect to Gomoku server");
    }


    private final GameClientController controller;
    private final CardLayout panes;
    private final RegistrationPanel registrationPanel;
}
