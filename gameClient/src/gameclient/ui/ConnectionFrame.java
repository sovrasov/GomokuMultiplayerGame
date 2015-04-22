package gameclient.ui;

import javax.swing.JFrame;
import java.awt.CardLayout;

public class ConnectionFrame extends GameClientView<JFrame> {
    public ConnectionFrame(GameClientController controller) {
        super(new JFrame(), controller);
        setupFrame();

        panes = new CardLayout();
        getComponent().setLayout(panes);
        registrationPanel = new RegistrationPanel(getController());
        getComponent().add(registrationPanel.getComponent());
    }

    public void show() {
        getComponent().setVisible(true);
    }


    private void setupFrame() {
        getComponent().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getComponent().setSize(400, 300);
        getComponent().setTitle("Connect to Gomoku server");
    }

    private final CardLayout panes;
    private final RegistrationPanel registrationPanel;
}
