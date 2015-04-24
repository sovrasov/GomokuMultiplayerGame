package gameclient.ui;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameRoundPanel extends GameClientView<JPanel> {

    public GameRoundPanel(GameClientController controller) {
        super(new JPanel(), controller);
        getComponent().setLayout(new BoxLayout(getComponent(),
            BoxLayout.Y_AXIS));

        turnLabel = new JLabel("Waiting for opponent's move...");
        makeAMoveButton = new JButton("Make a move");
        gameField = new GameFieldPanel(controller);

        getComponent().add(turnLabel);
        getComponent().add(makeAMoveButton);
        getComponent().add(gameField);
    }


    private final JLabel turnLabel;
    private final JButton makeAMoveButton;
    private final GameFieldPanel gameField;
}
