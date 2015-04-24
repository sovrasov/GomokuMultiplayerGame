package gameclient.ui;

import java.util.Observable;
import java.util.Observer;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameRoundPanel extends GameClientView<JPanel> implements Observer {

    public GameRoundPanel(GameClientController controller) {
        super(new JPanel(), controller);
        controller.getModel().addObserver(this);

        getComponent().setLayout(new BoxLayout(getComponent(),
            BoxLayout.Y_AXIS));

        turnLabel = new JLabel(opponentsTurnMessage);
        makeAMoveButton = new JButton("Make a move");
        makeAMoveButton.addActionListener(controller.new MakeAMoveListener());
        gameField = new GameFieldPanel(controller);

        getComponent().add(turnLabel);
        getComponent().add(makeAMoveButton);
        getComponent().add(gameField);
    }

    @Override
    public void update(Observable o, Object o1) {
        GameClientModel model = (GameClientModel) o;

        if (model.isMyTurn()) {
            turnLabel.setText(myTurnMessage);
            makeAMoveButton.setEnabled(true);
        } else {
            turnLabel.setText(opponentsTurnMessage);
            makeAMoveButton.setEnabled(false);
        }
    }


    private static final String myTurnMessage = "Make a move now";
    private static final String opponentsTurnMessage =
        "Waiting for opponent's move...";

    private final JLabel turnLabel;
    private final JButton makeAMoveButton;
    private final GameFieldPanel gameField;

}
