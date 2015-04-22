package gameclient.ui;

import javax.swing.JPanel;

public class LobbyPanel extends GameClientView<JPanel> {

    public LobbyPanel(GameClientController controller) {
        super(new JPanel(), controller);
    }

}
